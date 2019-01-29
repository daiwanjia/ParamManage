package com.dcits.paramManage.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.entity.Ipaddress;
import com.dcits.paramManage.service.IpaddressService;

@Controller
@RequestMapping("/ipaddress")
public class IpaddressController {
	
	private static final Log log=LogFactory.getLog(IpaddressController.class);
	
	@Autowired
	private IpaddressService ipService;
	
	/**
	 * 查询ip列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ipList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json;charset=UTF-8")
	public String ipList(int limit,int page,String ipadr,String ipChannel,String ipEnvironment) throws Exception {
		int offset=limit*(page-1);
		return ipService.selectIpList(limit, offset, ipadr, ipChannel, ipEnvironment);
	}

	/**
	 * 新增ip信息
	 * 
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addIp", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String addIp(@RequestBody Ipaddress ip) throws Exception {
		
		return ipService.insertIp(ip);
	}

	/**
	 * 修改环境信息
	 * 
	 * @param environment
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateIpInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String updateIpInfo(@RequestBody Ipaddress ip) throws Exception {
		
		return ipService.updateByPrimaryKey(ip);
	}

	/**
	 * 删除服务接口
	 * 
	 * @param environmentId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delIp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String delIp(String ipId) throws Exception {

		return ipService.deleteByPrimaryKey(ipId);
	}

	@ResponseBody
	@RequestMapping(value="/batchimportIp",produces="application/json;charset=UTF-8")
	public String batchimportIp(MultipartFile file) throws Exception{
		return ipService.batchImportIp(file);
	}
	
	/**
	 * 异常处理
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public String ExceptionOut(Exception ex) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", false);
		jsonObject.put("msg", "系统异常！" + ex);
		return jsonObject.toJSONString();
	}
}
