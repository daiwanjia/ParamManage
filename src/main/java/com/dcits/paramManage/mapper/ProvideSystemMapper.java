package com.dcits.paramManage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dcits.paramManage.entity.ProvideSystem;

@Mapper
public interface ProvideSystemMapper {
	/**
	 * 删除系统
	 * 
	 * @param systemEnname
	 * @return
	 */
	int deleteByPrimaryKey(String systemId);

	/**
	 * 新增系统
	 * 
	 * @param system
	 * @return
	 */
	int insert(ProvideSystem system);

	int insertSelective(ProvideSystem record);

	ProvideSystem selectByPrimaryKey(String systemEnname);

	int updateByPrimaryKeySelective(ProvideSystem record);

	/**
	 * 修改系统信息
	 * 
	 * @param system
	 * @return
	 */
	int updateByPrimaryKey(ProvideSystem system);

	/**
	 * 查询系统信息
	 * 
	 * @param limit
	 *            每页的数量
	 * @param offset
	 *            起始下标
	 * @return systemInfoList
	 */
	List<ProvideSystem> systemInfoList(@Param("limit") int limit, @Param("offset") int offset,
			@Param("systemEnname") String systemEnname, @Param("systemCnname") String systemCnname);

	/**
	 * 查询system总量
	 * 
	 * @return int
	 */
	int systemCount(@Param("systemEnname") String systemEnname, @Param("systemCnname") String systemCnname);
	
	/**
	 * 查询全量系统
	 * @return
	 */
	List<ProvideSystem> querySystemAll();
}