package com.dcits.paramManage.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.dcits.paramManage.entity.Ipaddress;
import com.dcits.paramManage.mapper.IpaddressMapper;
import com.dcits.paramManage.service.IpaddressService;
import com.dcits.paramManage.util.ExcelUtil;
import com.dcits.paramManage.util.UUIDUtil;

@Transactional
@Service
public class IpaddressServiceImpl implements IpaddressService{
	
	@Autowired
	private IpaddressMapper ipMapper;
	
	/**
	 * delete
	 */
	@Override
	public String deleteByPrimaryKey(String ipId) throws Exception {
		JSONObject result=new JSONObject();
		int flag=ipMapper.deleteByPrimaryKey(ipId);
		if(flag==1){
			result.put("code", true);
			result.put("msg", "数据删除成功！");
		}else{
			result.put("code", false);
			result.put("msg", "数据删除失败！");
		}
		return result.toJSONString();
	}

	/**
	 * insertOne
	 */
	@Override
	public String insertIp(Ipaddress ip) throws Exception {
		JSONObject result=new JSONObject();
		if(StringUtils.isAnyBlank(ip.getIpadr(),ip.getIpChannel(),ip.getIpEnvironment())){
			result.put("code", false);
			result.put("msg", "必传参数不能为空！");
			return result.toJSONString();
		}
		ip.setIpId(UUIDUtil.getUUID());
		
		int flag=ipMapper.insertIp(ip);
		if(flag==1){
			result.put("code", true);
			result.put("msg", "数据新增成功！");
		}else{
			result.put("code", false);
			result.put("msg", "数据新增失败！");
		}
		return result.toJSONString();
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Ipaddress selectByPrimaryKey(String ipId) throws Exception {
		Ipaddress ip=ipMapper.selectByPrimaryKey(ipId);
		return ip;
	}
	/**
	 * 更新
	 */
	@Override
	public String updateByPrimaryKey(Ipaddress ip) throws Exception {
		JSONObject result=new JSONObject();
		
		if(StringUtils.isAnyBlank(ip.getIpadr(),ip.getIpChannel(),ip.getIpEnvironment())){
			result.put("code", false);
			result.put("msg", "必传参数不能为空！");
			return result.toJSONString();
		}
		
		int flag=ipMapper.updateByPrimaryKey(ip);
		if(flag==1){
			result.put("code", true);
			result.put("msg", "数据修改成功！");
		}else{
			result.put("code", false);
			result.put("msg", "数据修改失败！");
		}
		return result.toJSONString();
	}
	/**
	 * 查询ip列表
	 */
	@Override
	public String selectIpList(int limit, int offset, String ipadr, String ipChannel, String ipEnvironment)
			throws Exception {
		List<Ipaddress> ipList=ipMapper.selectIpList(limit, offset, ipadr, ipChannel, ipEnvironment);
		JSONArray dataArray=JSONArray.parseArray(JSON.toJSONString(ipList));//ipList
		int ipCount=ipMapper.selectIpCount(ipadr, ipChannel, ipEnvironment);//ip 数量
		return JsonData.toTableData(ipCount, dataArray);//格式转换
	}

	/**
	 * 批量导入Ip信息
	 */
	@Override
	public String batchImportIp(MultipartFile file) throws Exception {
		JSONObject result = new JSONObject();
		if (file == null) {
			result.put("code", false);
			result.put("msg", "上传文件为空！");
			return result.toJSONString();
		}
		InputStream is = file.getInputStream();
		Workbook workbook = null;
		if(file.getOriginalFilename().endsWith(".xls")){
			workbook = new HSSFWorkbook(is);
		}else if(file.getOriginalFilename().endsWith(".xlsx")){
			workbook = new XSSFWorkbook(is);
		}else {
			is.close();
			result.put("code", false);
			result.put("msg", "格式错误！必须为xls或xlsx");
			return result.toJSONString();
		}
		is.close();
		
		// 获取第一个sheet页
		Sheet sheet = workbook.getSheetAt(0);
		workbook.close();
		// 获取最大行数
		int rownum = sheet.getPhysicalNumberOfRows();
		Row row = null;
		List<Ipaddress> ipList=new ArrayList<>();
		for (int i = 1; i < rownum; i++) {
			row=sheet.getRow(i);
			if(row!=null){
				String ipadr=ExcelUtil.getCellFormatValue(row.getCell(0));
				String ipChannel=ExcelUtil.getCellFormatValue(row.getCell(1));
				String ipEnvironment=ExcelUtil.getCellFormatValue(row.getCell(2));
				String ipRemake=ExcelUtil.getCellFormatValue(row.getCell(3));
				String ipId=UUIDUtil.getUUID();
				Ipaddress ip=new Ipaddress(ipId, ipadr, ipEnvironment, ipChannel, ipRemake);
				ipList.add(ip);
			}
		}
		int flag=ipMapper.batchImportIp(ipList);
		if(flag>0){
			result.put("code", true);
			result.put("msg", "数据导入成功！");
		}else{
			result.put("code", false);
			result.put("msg", "数据导入失败！");
		}
		return result.toJSONString();
	}
	
}
