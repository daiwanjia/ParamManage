package com.dcits.paramManage.service;

import com.dcits.paramManage.entity.OpenSystem;

public interface OpenSystemService {

	/**
	 * 
	 * @param limit
	 * @param offset
	 * @param openEnvironmentId
	 *            模块所在环境id
	 * @param openModuleName
	 *            模块名称
	 * @param openModuleServer
	 *            模块所在服务器
	 * @return openModuleList
	 */
	String openModuleList(String limit, String offset, String openEnvironmentId, String openModuleName,
			String openModuleServer);

	/**
	 * 新增模块信息
	 * 
	 * @param openSystem
	 * @return
	 */
	int insertSelective(OpenSystem openSystem);

	/**
	 * 修改模块信息
	 * 
	 * @param openSystem
	 * @return
	 */
	int updateByPrimaryKeySelective(OpenSystem openSystem);

	/**
	 * 删除模块
	 * 
	 * @param openModuleId
	 * @return
	 */
	int deleteByPrimaryKey(String openModuleId);
}
