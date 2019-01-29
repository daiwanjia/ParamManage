package com.dcits.paramManage.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.entity.Environment;
import com.dcits.paramManage.mapper.EnvironmentMapper;
import com.dcits.paramManage.service.EnvironmentService;

@Service
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService{

	@Autowired
	private EnvironmentMapper environmentMapper;
	
	private static Log log = LogFactory.getLog(EnvironmentServiceImpl.class);
	
	//查询环境清单 all
	@Override
	public String queryEnvronmentInfo() throws Exception {
		List<Environment> environmentList=environmentMapper.queryEnvironmentInfo();
		JSONArray environmentArray=JSONArray.parseArray(environmentList.toString());
		JSONObject jsonObject = new JSONObject();
		// 查询数据总量 code和msg均无用，为了组装成layui_table的固定格式
		jsonObject.put("count",environmentMapper.queryEnvironmentInfoCount());
		jsonObject.put("data", environmentArray);
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		if(log.isDebugEnabled()){
			log.debug("envronmentInfo===" + jsonObject.toJSONString());
		}	
		return jsonObject.toJSONString();
	}
	
	//新增环境信息
	@Override
	public int insert(Environment environment) throws Exception {
		//设置唯一ID
		String environmentId=UUID.randomUUID().toString().substring(0, 7);
		environment.setEnvironmentId(environmentId);
		return environmentMapper.insertSelective(environment);
	}
	//修改环境信息
	@Override
	public int updateByPrimaryKey(Environment environment) throws Exception {
		return environmentMapper.updateByPrimaryKey(environment);
	}
	//删除环境信息
	@Override
	public int deleteByPrimaryKey(String environmentId) throws Exception {
		return environmentMapper.deleteByPrimaryKey(environmentId);
	}

	@Override
	public boolean judgeExistEnvironment(String environmentId) {
		int flag=environmentMapper.judgeExistEnvironment(environmentId);
		if(flag==0){
			return false;
		}
		return true;
	}

	@Override
	public List<Environment> selectAllEnvironment() {
		
		return environmentMapper.queryEnvironmentInfo();
	}
	
	

}
