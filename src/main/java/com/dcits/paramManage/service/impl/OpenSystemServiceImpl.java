package com.dcits.paramManage.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.dcits.paramManage.common.JsonData;
import com.dcits.paramManage.entity.OpenSystem;
import com.dcits.paramManage.mapper.OpenSystemMapper;
import com.dcits.paramManage.service.EnvironmentService;
import com.dcits.paramManage.service.OpenSystemService;
import com.dcits.paramManage.util.TimeUtil;
import com.dcits.paramManage.util.UUIDUtil;

@Service
@Transactional
public class OpenSystemServiceImpl implements OpenSystemService{

	@Autowired
	private OpenSystemMapper mapper;
	
	@Autowired
	private EnvironmentService environmentService;
	
	private static Log log=LogFactory.getLog(OpenSystemServiceImpl.class);
	
	//查询
	@Override
	public String openModuleList(String limit, String offset, String openEnvironmentId, String openModuleName,
			String openModuleServer) {
		//获取openModuleList
		List<OpenSystem> list=mapper.openModuleList(limit, offset, openEnvironmentId, openModuleName, openModuleServer);
		JSONArray array=JSONArray.parseArray(list.toString());
		//获取数据总量
		int count=mapper.openModuleCount(limit, offset, openEnvironmentId, openModuleName, openModuleServer);
		//转化为固定输出格式
		String openModuleData=JsonData.toTableData(count, array);
		return openModuleData;
	}

	//新增
	@Override
	public int insertSelective(OpenSystem openSystem) {
		
		//判断此模块所属环境是否存在   如果存在则可为该环境新增一个模块
		if(environmentService.judgeExistEnvironment(openSystem.getOpenEnvironmentId())){
			//设置唯一id
			openSystem.setOpenEnvironmentId(UUIDUtil.getUUID());
			//设置insert time
			openSystem.setOpenModuleUpdatetime(TimeUtil.getSystemTime());
			
			return mapper.insertSelective(openSystem);
		}else{
			if(log.isErrorEnabled()){
				log.error("没有找到此环境！");
			}
			return 0;
		}	
	}
	//修改
	@Override
	public int updateByPrimaryKeySelective(OpenSystem openSystem) {
		//判断此模块修改的环境是否存在   如果存在则可修改模块的系统
		if(environmentService.judgeExistEnvironment(openSystem.getOpenEnvironmentId())){
			
			//设置update time
			openSystem.setOpenModuleUpdatetime(TimeUtil.getSystemTime());
			
			return mapper.updateByPrimaryKeySelective(openSystem);
		}else{
			if(log.isErrorEnabled()){
				log.error("没有找到此环境！");
			}
			return 0;
		}
	}

	//删除
	@Override
	public int deleteByPrimaryKey(String openModuleId) {
		
		return mapper.deleteByPrimaryKey(openModuleId);
	}

}
