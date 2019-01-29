package com.dcits.paramManage.service;

import java.util.List;

import com.dcits.paramManage.entity.Environment;

public interface EnvironmentService {

	/**
	 * 查询环境信息
	 * @return
	 * @throws Exception
	 */
	String queryEnvronmentInfo() throws Exception;
	
	/**
     * 新增环境
     * @param environment
     * @return int
     */
    int insert(Environment environment)throws Exception;
    /**
     * 修改环境信息
     * @param environment
     * @return int
     */
    int updateByPrimaryKey(Environment environment)throws Exception;
    /**
     * 删除环境信息
     * @param environmentId
     * @return
     */
    int deleteByPrimaryKey(String environmentId)throws Exception;
    
    /**
     * 判断是否存在此条记录
     * @param environmentId 
     * @return  不存在返回false，否则true
     */
    boolean judgeExistEnvironment(String environmentId);
    
    /**
     * 查询所有环境列表
     * @return
     */
    List<Environment> selectAllEnvironment();
}
