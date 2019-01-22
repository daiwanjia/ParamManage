package com.dcits.paramManage.mapper;

import java.util.List;

import com.dcits.paramManage.entity.Environment;

public interface EnvironmentMapper {

	int insert(Environment environment);

    Environment selectByPrimaryKey(String environmentId);

    int updateByPrimaryKeySelective(Environment record);

    /**
     * 查询环境信息 all
     * @return list
     */
    List<Environment> queryEnvironmentInfo();
    
    /**
     * 查询环境总量
     * @return
     */
    int queryEnvironmentInfoCount();
    
    /**
     * 新增环境
     * @param environment
     * @return int
     */
    int insertSelective(Environment environment);
   
    /**
     * 修改环境信息
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Environment environment);
    /**
     * 删除环境信息
     * @param environmentId
     * @return
     */
    int deleteByPrimaryKey(String environmentId);
    /**
     * 判断是否存在此条记录
     * @param environmentId 
     * @return  存在返回1，否则0
     */
    int judgeExistEnvironment(String environmentId);
}