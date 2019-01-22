package com.dcits.paramManage.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.entity.IntfService;
import com.dcits.paramManage.service.IntfServiceService;

@Controller
@RequestMapping(value = "/intfService")
public class IntfServiceController {

	@Autowired
	private IntfServiceService service;
	private static Log log = LogFactory.getLog(IntfServiceController.class);

	/**
	 * 分页查询服务接口信息
	 * @param limit 每页数据限制量
	 * @param page  页码
	 * @param serviceEnname 服务接口英文名称
	 * @param serviceCnname 服务接口中文名称
	 * @param serviceSystem 服务所属系统
	 * @param serviceStatus 服务状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceInfo", method = { RequestMethod.POST, RequestMethod.GET }, produces ="application/json;charset=UTF-8")
	@ResponseBody
	public String serviceInfo(@RequestParam("limit") int limit, @RequestParam("page") int page,
			@RequestParam(value = "serviceEnname", required=false) String serviceEnname,
			@RequestParam(value = "serviceCnname", required=false) String serviceCnname,
			@RequestParam(value = "systemId", required=false) String systemId,
			@RequestParam(value = "serviceStatus", required=false) String serviceStatus) throws Exception {
		int offset = limit * (page - 1);// 获取当前页第一位下标
		return service.serviceList(limit, offset, serviceEnname, serviceCnname, serviceStatus, systemId);
	}
	
	/**
	 * 新增服务接口
	 * @param service
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/addService",method={RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	public String addSystem(@RequestBody IntfService intfService, String systemId) throws Exception{
		JSONObject jsonMsg=new JSONObject();
		
		if(StringUtils.isBlank(intfService.getServiceEnname())){
			jsonMsg.put("msg", "接口服务英文名不能为空！");
			return jsonMsg.toJSONString();
		}
		if(StringUtils.isBlank(intfService.getServiceCnname())){
			jsonMsg.put("msg", "接口服务中文名不能为空！");
			return jsonMsg.toJSONString();
		}
		if(StringUtils.isBlank(systemId)){
			jsonMsg.put("msg", "提供方系统不能为空！");
			return jsonMsg.toJSONString();
		}
		
		return service.insertService(intfService,systemId);
	}
	
	/**
	 * 修改服务接口
	 * @param intfService
	 * @param systemId  系统表id
	 * @param sid  关联表id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/updateService",method={RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String updateService(@RequestBody Map<String, String> map) throws Exception{
		
		IntfService intfService=new IntfService();
		intfService.setServiceId(map.get("serviceId"));
		intfService.setServiceNo(map.get("serviceNo"));
		intfService.setServiceEnname(map.get("serviceEnname"));
		intfService.setServiceCnname(map.get("serviceCnname"));
		intfService.setServiceStatus(map.get("serviceStatus"));
		intfService.setServiceDesc(map.get("serviceDesc"));
		intfService.setServiceVersion(map.get("serviceVersion"));
		intfService.setServiceUpdatetime(map.get("serviceUpdatetime"));
		String sid=map.get("sid");
		String systemId=map.get("systemId");
//		JSONObject jo=JSONObject.parseObject(JSON.toJSONString(map)) ;
		JSONObject jsonMsg=new JSONObject();
		
		if(StringUtils.isBlank(intfService.getServiceEnname())){
			jsonMsg.put("msg", "接口服务英文名不能为空！");
			return jsonMsg.toJSONString();
		}
		if(StringUtils.isBlank(intfService.getServiceCnname())){
			jsonMsg.put("msg", "接口服务中文名不能为空！");
			return jsonMsg.toJSONString();
		}
		if(StringUtils.isBlank(systemId)){
			jsonMsg.put("msg", "提供方系统不能为空！");
			return jsonMsg.toJSONString();
		}
		
		return service.uadateByPrimaryKey(intfService,systemId,sid);
	}
	
	/**
	 * 删除服务接口
	 * @param service
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/delService",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String delService(String serviceId,String sid) throws Exception{
		JSONObject jsonMsg=new JSONObject();
		
		int delFlag=service.deleteByPrimaryKey(serviceId,sid);
		
		if(delFlag==1){
			jsonMsg.put("msg", "数据删除成功！");
		}else{
			jsonMsg.put("msg", "数据删除失败！");
		}
		return jsonMsg.toJSONString();
	}
	
	/**
	 * 查询接口调用频率
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/intfCallfrqncy",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String intfCallfrqncyList(int limit,int page,String serviceEnname) throws Exception{
		int offset = limit * (page - 1);// 获取当前页第一位下标
		return service.intfCallfrqncyList(limit,offset,serviceEnname);
	}
	
	/**
	 * 查询调用此接口的渠道列表
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/calledChannel",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String calledChnnList(String serviceId) throws Exception{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("msg", service.calledChnnList(serviceId));	
		return jsonObject.toJSONString();
		
	}
	
	/**
	 * 批量导入接口
	 * @param multipartFile
	 * @param systemId 接口提供方id 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/batchimportIntf",produces = {"application/json;charset=UTF-8"})
	public String batchimportIntf(@RequestParam("file") MultipartFile multipartFile,String systemId) throws Exception{

		return service.batchimportIntf(multipartFile);
	}
	
	/**
	 * 异常处理
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public String ExceptionOut(Exception ex){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("msg", "系统异常！ -----"+ex);	
		return jsonObject.toJSONString();
	}
}
