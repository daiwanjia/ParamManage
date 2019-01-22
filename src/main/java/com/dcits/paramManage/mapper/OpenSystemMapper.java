package com.dcits.paramManage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dcits.paramManage.entity.OpenSystem;

public interface OpenSystemMapper {

	int insert(OpenSystem record);

	OpenSystem selectByPrimaryKey(String openModuleId);

	int updateByPrimaryKey(OpenSystem record);

	/**
	 * 
	 * @param limit 
	 * @param offset
	 * @param openEnvironmentId  模块所在环境id
	 * @param openModuleName  模块名称
	 * @param openModuleServer 模块所在服务器
	 * @return openModuleList
	 */
	List<OpenSystem> openModuleList(@Param("limit") String limit, @Param("offset") String offset,
			@Param("openEnvironmentId") String openEnvironmentId, @Param("openModuleName") String openModuleName,
			@Param("openModuleServer") String openModuleServer);
	
	/**
	 * 
	 * @param limit
	 * @param offset
	 * @param openEnvironmentId 模块所在环境id
	 * @param openModuleName 模块名称
	 * @param openModuleServer 模块所在服务器
	 * @return openModuleCount
	 */
	int openModuleCount(@Param("limit") String limit, @Param("offset") String offset,
			@Param("openEnvironmentId") String openEnvironmentId, @Param("openModuleName") String openModuleName,
			@Param("openModuleServer") String openModuleServer);
	
	/**
	 * 新增模块信息
	 * @param openSystem
	 * @return
	 */
	int insertSelective(OpenSystem openSystem);
	
	/**
	 * 修改模块信息
	 * @param openSystem
	 * @return
	 */
	int updateByPrimaryKeySelective(OpenSystem openSystem);
	
	/**
	 * 删除模块
	 * @param openModuleId
	 * @return
	 */
	int deleteByPrimaryKey(String openModuleId);
}