package com.dcits.paramManage.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobalHandlerException implements HandlerExceptionResolver{

	private static final Log log=LogFactory.getLog(GlobalHandlerException.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		CustomException customException=null;
		if(ex instanceof CustomException){
			customException=(CustomException) ex;
		}else{
			customException=new CustomException("未知错误！"+ex);
		}
		log.error(customException);
		//错误信息
		String msg=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView("/children/error.html");
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}

}
