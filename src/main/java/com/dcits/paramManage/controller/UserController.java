package com.dcits.paramManage.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;

	private static Log log=LogFactory.getLog(UserController.class);
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public String login(@RequestParam("username") String username,@RequestParam("userpwd") String userpwd,HttpServletRequest request)throws Exception {
		
		JSONObject jsonObject=new JSONObject();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpwd)) {
			jsonObject.put("msg", "请输入用户名或密码！");
		} else {			
			if(!userService.findUserByUsername(username)){
				jsonObject.put("msg", "没有此用户！");
			}else{
				if(userService.findUserpwdByUsername(username).equals(userpwd)){
					request.getSession().setAttribute("username", username);
					jsonObject.put("msg", "登录成功！");
				}else{
					jsonObject.put("msg", "用户名或密码有误！");				
				}
			}			
		}
		log.info("登录信息："+jsonObject.toJSONString());
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("username");
		response.sendRedirect("/html/login.html");	
	}
	
	@ExceptionHandler
	@ResponseBody
	public String ExceptionOut(Exception ex){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("msg", "系统异常！ -----"+ex);	
		return jsonObject.toJSONString();
	}
}
