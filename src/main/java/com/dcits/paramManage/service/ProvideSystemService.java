package com.dcits.paramManage.service;

import java.util.List;

import com.dcits.paramManage.entity.ProvideSystem;

public interface ProvideSystemService {
	/**
	 * 分页查询系统信息
	 * @param limit
	 * @param offset
	 * @param systemEnname
	 * @param systenCnname
	 * @return systemList
	 */
	String systemList(int limit,int offset,String systemEnname,String systemCnname);
	/**
	 * 分页查询数据总量
	 * @param systemEnname
	 * @param systemCnname
	 * @return
	 */
	int addSystem(ProvideSystem system);
	/**
	 * 修改系统信息
	 * @param system
	 * @return int
	 */
	int updateSystem(ProvideSystem system);
	/**
	 * 删除系统
	 * @param systemId
	 * @return int
	 */
	int deleteSystem(String systemId);
	
	/**
	 * 查询全量系统信息（systemEnname，systemCnname）
	 * @return
	 */
	List<ProvideSystem> allSystem();
}
