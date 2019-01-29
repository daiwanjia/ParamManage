package com.dcits.paramManage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.dcits.paramManage.common.JsonData;
import com.dcits.paramManage.entity.ProvideSystem;
import com.dcits.paramManage.mapper.ProvideSystemMapper;
import com.dcits.paramManage.service.ProvideSystemService;
import com.dcits.paramManage.util.UUIDUtil;

@Service
@Transactional
public class ProvideSystemServiceImpl implements ProvideSystemService{
	@Autowired
	private ProvideSystemMapper systemMapper;

	@Override
	public String systemList(int limit, int offset,String systemEnname,String systemCnname) {
		List<ProvideSystem> systemlist=systemMapper.systemInfoList(limit, offset,systemEnname,systemCnname);
		//查询分页数据
		JSONArray systemArray=JSONArray.parseArray(systemlist.toString());
		int count=systemMapper.systemCount(systemEnname,systemCnname);
		
		return JsonData.toTableData(count, systemArray);
	}

	@Override
	public int addSystem(ProvideSystem system) {
		system.setSystemId(UUIDUtil.getUUID());
		int insertFlag=systemMapper.insert(system);
		return insertFlag;
	}

	@Override
	public int updateSystem(ProvideSystem system) {
		int updateFlag=systemMapper.updateByPrimaryKey(system);
		return updateFlag;
	}

	@Override
	public int deleteSystem(String systemId) {
		int deleteFlag=systemMapper.deleteByPrimaryKey(systemId);
		return deleteFlag;
	}

	@Override
	public List<ProvideSystem> allSystem() {
		
		return systemMapper.querySystemAll();
	}

}
