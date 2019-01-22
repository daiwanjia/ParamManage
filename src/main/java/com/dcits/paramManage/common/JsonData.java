package com.dcits.paramManage.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonData {
	
	private static Log log=LogFactory.getLog(JsonData.class);

	/**
	 * 前端layui_table获取数据固定格式
	 * @param count  数据总量
	 * @param dataArray  业务数据
	 * @return
	 */
	public static String toTableData(int count,JSONArray dataArray){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("count",count);
		jsonObject.put("data", dataArray);
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		
		if(log.isDebugEnabled()){
			log.debug("组装的jsonData---"+jsonObject.toJSONString());
		}
		return jsonObject.toJSONString();
	}
}
