package com.dcits.paramManage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	/**
	 * 获取系统当前时间  yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public  static String getSystemTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	//获取当前日期
	public static String getData(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
}
