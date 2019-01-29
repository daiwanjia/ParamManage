package com.dcits.paramManage.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.entity.ProvideSystem;
import com.dcits.paramManage.service.ProvideSystemService;

@Controller
@RequestMapping(value="/provideSystem")
public class ProvideSystemController {
	@Autowired
	private ProvideSystemService systemService;
		
	/**
	 * 分页查询系统信息
	 * @param limit 每页总数据量
	 * @param page  当前页码
	 * @return  
	 */
	@ResponseBody
	@RequestMapping(value="/systemInfo",method={RequestMethod.POST,RequestMethod.GET},produces={"application/json;charset=UTF-8"})
	public String systemInfo(@RequestParam("limit") int limit,@RequestParam("page") int page,
			@RequestParam(defaultValue = "",value="systemEnname") String systemEnname,@RequestParam(defaultValue = "",value="systemCnname") String systemCnname){
  		int offset=limit*(page-1);//获取当前页第一位下标
  		return systemService.systemList(limit, offset, systemEnname, systemCnname);		
	}
	/**
	 * 新增系统
	 * @param system
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addSystem",method={RequestMethod.POST,RequestMethod.GET},produces={"application/json;charset=UTF-8"})
	public String addSystem(@RequestBody ProvideSystem providesystem){
		JSONObject jsonObject=new JSONObject();
		
		if(StringUtils.isBlank(providesystem.getSystemEnname())){
			jsonObject.put("msg", "系统英文名称不能为空！");
			return jsonObject.toJSONString();
		}
		if(StringUtils.isBlank(providesystem.getSystemCnname())){
			jsonObject.put("msg", "系统中文名称不能为空！");
			return jsonObject.toJSONString();
		}
		
		int insertFlag=systemService.addSystem(providesystem);
		
		if(insertFlag==1){
			jsonObject.put("msg", "数据添加成功！");
		}else{
			jsonObject.put("msg", "数据添加失败！");
		}
		return jsonObject.toJSONString();
	}
	/**
	 * 修改系统信息
	 * @param system
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSystem",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String uadateSystem(@RequestBody ProvideSystem providesystem){
 		JSONObject jsonObject=new JSONObject();
		
		if(StringUtils.isBlank(providesystem.getSystemEnname())){
			jsonObject.put("msg", "系统英文名称不能为空！");
			return jsonObject.toJSONString();
		}
		if(StringUtils.isBlank(providesystem.getSystemCnname())){
			jsonObject.put("msg", "系统中文名称不能为空！");
			return jsonObject.toJSONString();
		}
		int uadateFlag=systemService.updateSystem(providesystem);
		if(uadateFlag==1){
			jsonObject.put("msg", "数据修改成功！");
		}else{
			jsonObject.put("msg", "数据修改失败！");
		}
		return jsonObject.toJSONString();
	}
	/**
	 * 删除系统
	 * @param systemEnname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSystem",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String deleteSystem(@RequestParam("systemId") String systemId){
		int delFlag=systemService.deleteSystem(systemId);
		JSONObject jsonObject=new JSONObject();
		if(delFlag==1){
			jsonObject.put("msg", "数据删除成功！");
		}else{
			jsonObject.put("msg", "数据删除失败！");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 查询全量系统信息
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value="/allSystem",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String allSystem(){
		return systemService.allSystem().toString();
	}
	
	
	/**
	 * 异常处理
	 * @param ex
	 * @return
	 */
//	@ExceptionHandler
//	@ResponseBody
//	public String ExceptionOut(Exception ex){
//		
//		log.error(ex);
//		
//		JSONObject jsonObject=new JSONObject();
//		jsonObject.put("msg", "系统异常！ -----"+ex);			
//		return jsonObject.toJSONString();
//	}
}
