package com.dcits.paramManage.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.common.JsonData;
import com.dcits.paramManage.entity.IntfService;
import com.dcits.paramManage.entity.ProvideSystem;
import com.dcits.paramManage.mapper.IntfServiceMapper;
import com.dcits.paramManage.mapper.ProvideSystemMapper;
import com.dcits.paramManage.service.IntfServiceService;
import com.dcits.paramManage.util.ExcelUtil;
import com.dcits.paramManage.util.TimeUtil;
import com.dcits.paramManage.util.UUIDUtil;

@Transactional
@Service
public class IntfServiceServiceImpl implements IntfServiceService {

	@Autowired
	private IntfServiceMapper serviceMapper;
	@Autowired
	private ProvideSystemMapper systemMapper;

	// 新增接口
	@Override
	public String insertService(IntfService service, String systemId) throws Exception {

		JSONObject jsonObject = new JSONObject();

		// 判断接口英文名称是否已有（应唯一）
		if (serviceMapper.selectIntfexist(service.getServiceEnname()) > 0) {
			jsonObject.put("msg", "服务接口已存在！");
			return jsonObject.toJSONString();
		}

		// 设置接口服务的唯一id
		String serviceId = UUIDUtil.getUUID();
		service.setServiceId(serviceId);
		// service.setServiceUpdatetime(TimeUtil.GetSystemTime());
		if (serviceMapper.insertService(service) == 1) {
			// 创建link
			HashMap<String, String> linkmap = new HashMap<String, String>();
			linkmap.put("sid", UUIDUtil.getUUID());// link主键
			linkmap.put("source", serviceId);// 源id
			linkmap.put("relate", systemId);// 关联对象id
			linkmap.put("rank", "intf_sys");// 关联关系
			int insertFlag = serviceMapper.insertLink(linkmap);
			if (insertFlag == 1) {
				jsonObject.put("msg", "数据添加成功！");
				return jsonObject.toJSONString();
			}
		}
		jsonObject.put("msg", "数据添加失败！");
		return jsonObject.toJSONString();
	}

	@Override
	public String uadateByPrimaryKey(IntfService service, String systemId, String sid) throws Exception {
		JSONObject jsonObject = new JSONObject();

		if (serviceMapper.updateByPrimaryKeySelective(service) == 1) {
			// 创建link
			HashMap<String, String> linkmap = new HashMap<String, String>();
			linkmap.put("sid", sid);// link主键
			linkmap.put("source", service.getServiceId());// 源id
			linkmap.put("relate", systemId);// 关联对象id
			if (serviceMapper.updateLink(linkmap) == 1) {
				jsonObject.put("msg", "数据修改成功！");
				return jsonObject.toJSONString();
			}
		}
		jsonObject.put("msg", "数据修改失败！");
		return jsonObject.toJSONString();
	}

	@Override
	public int deleteByPrimaryKey(String serviceId, String sid) throws Exception {
		if (serviceMapper.deleteByPrimaryKey(serviceId) == 1) {
			// 删除关联表信息
			return serviceMapper.deleteLink(sid);
		}
		return 0;
	}

	// 服务接口列表
	@Override
	public String serviceList(int limit, int offset, String serviceEnname, String serviceCnname, String serviceStatus,
			String systemId) throws Exception {
		HashMap<String, Object> queryMap = new HashMap<>();
		queryMap.put("limit", limit);
		queryMap.put("offset", offset);
		queryMap.put("serviceEnname", serviceEnname);
		queryMap.put("serviceCnname", serviceCnname);
		queryMap.put("serviceStatus", serviceStatus);
		queryMap.put("systemId", systemId);
		// 查询分页数据
		List<HashMap<String, Object>> list = serviceMapper.serviceList(queryMap);
		JSONArray serviceArray = JSONArray.parseArray(JSON.toJSONString(list));
		// 数据总量
		int count = serviceMapper.seriveCount(queryMap);
		// 查询数据总量 code和msg均无用，为了组装成layui_table的固定格式
		return JsonData.toTableData(count, serviceArray);
	}

	// 接口调用频率统计
	@Override
	public String intfCallfrqncyList(int limit, int offset, String serviceEnname) throws Exception {

		// 查询分页数据
		List<HashMap<String, Object>> list = serviceMapper.selectIntfCallfrqncy(limit, offset, serviceEnname);
		JSONArray IntfCallfrqncyArray = JSONArray.parseArray(JSON.toJSONString(list));
		int count = serviceMapper.selectIntfCallfrqncyCount();
		// 查询数据总量 code和msg均无用，为了组装成layui_table的固定格式
		return JsonData.toTableData(count, IntfCallfrqncyArray);
	}

	// 查询接口调用渠道
	@Override
	public String calledChnnList(String serviceId) throws Exception {

		List<String> list = serviceMapper.selectCalledChnn(serviceId);

		StringBuffer calledChnn = new StringBuffer();
		for (String str : list) {
			calledChnn.append("[").append(str).append("] ");
		}
		return calledChnn.toString();
	}

	// 批量导入接口
	@Override
	public String batchimportIntf(MultipartFile file) throws Exception {
		JSONObject result = new JSONObject();
		if (file == null) {
			result.put("code", false);
			result.put("msg", "上传文件为空！");
			return result.toJSONString();
		}
		// 获取文件后缀
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		InputStream is = file.getInputStream();
		Workbook workbook = null;
		if (".xls".equals(suffix)) {
			workbook = new HSSFWorkbook(is);
		} else if (".xlsx".equals(suffix)) {
			workbook = new XSSFWorkbook(is);
		} else {
			is.close();
			result.put("code", false);
			result.put("msg", "格式错误！必须为xls或xlsx");
			return result.toJSONString();
		}
		is.close();
		
		// 获取index页
		Sheet sheet = workbook.getSheet("INDEX");
		workbook.close();
		// 获取最大行数
		int rownum = sheet.getPhysicalNumberOfRows();
		Row row = null;
		// 从文件名获取接口版本和接口描述
		String filename=file.getOriginalFilename();
		String serviceVersion = filename.substring(file.getOriginalFilename().lastIndexOf("_") + 1,filename.lastIndexOf("."));
		String describe=filename.substring(filename.indexOf("字段映射")+5,filename.lastIndexOf("_"));
		//查询提供方系统
		List<ProvideSystem> systemList=systemMapper.querySystemAll();
		//系统map key-系统中文名称  value-系统id 新建接口从Excel匹配提供方系统名称对应系统
		//缓存系统信息，减少与数据库交互
		Map<String, String> map=new HashMap<String,String>();
		for (int i = 0; i <systemList.size(); i++) {
			ProvideSystem provideSystem=systemList.get(i);
			map.put(provideSystem.getSystemCnname(), provideSystem.getSystemId());
		}
		StringBuffer resp = new StringBuffer();
		for (int i = 1; i < rownum; i++) {
			row = sheet.getRow(i);
			//若行不为空且服务英文名称不为空 
			if (row != null&&StringUtils.isNotBlank(ExcelUtil.getCellFormatValue(row.getCell(8)))) {
				
				String systemId=map.get(ExcelUtil.getCellFormatValue(row.getCell(10)));//Excel读取提供方名称
				// 创建接口对象
				IntfService intfService = new IntfService();
				intfService.setServiceNo(ExcelUtil.getCellFormatValue(row.getCell(2)));// 读取服务接口码
				intfService.setServiceEnname(ExcelUtil.getCellFormatValue(row.getCell(8)));// 读取服务英文名称
				intfService.setServiceCnname(ExcelUtil.getCellFormatValue(row.getCell(3)));// 读取服务中文名称
				intfService.setServiceVersion(serviceVersion);
				intfService.setServiceDesc(describe);
				intfService.setServiceUpdatetime(TimeUtil.getData());

				// 缓存公共返回信息
				StringBuffer text = new StringBuffer("第").append(row.getRowNum()+1).append("行")
						.append(intfService.getServiceEnname());
				// 循环插入接口
				// 判断接口英文名称是否已有（应唯一）
				if (serviceMapper.selectIntfexist(intfService.getServiceEnname()) > 0) {
					resp.append(text).append("接口已存在").append("<br/>");
				}else{
					// 设置接口服务的唯一id
					String serviceId = UUIDUtil.getUUID();
					// 设置接口创建的时间
					intfService.setServiceId(serviceId);
					// service.setServiceUpdatetime(TimeUtil.GetSystemTime());
					if (serviceMapper.insertService(intfService) == 1) {
						// 创建link
						HashMap<String, String> linkmap = new HashMap<String, String>();
						linkmap.put("sid", UUIDUtil.getUUID());// link主键
						linkmap.put("source", serviceId);// 源id
						linkmap.put("relate", systemId);// 关联对象id
						linkmap.put("rank", "intf_sys");// 关联关系
						int insertFlag = serviceMapper.insertLink(linkmap);
						if (insertFlag == 1) {
							resp.append(text).append("接口导入成功").append("<br/>");

						} else {
							resp.append(text).append("接口导入失败").append("<br/>");
						}
					} else {
						resp.append(text).append("接口导入失败").append("<br/>");
					}
				}
				

			}
		}
		result.put("msg", resp.toString());
		return result.toJSONString();
	}

}
