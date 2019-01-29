package com.dcits.paramManage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dcits.paramManage.entity.Ipaddress;

public interface IpaddressMapper {
	/**
	 * 删除ip信息
	 * 
	 * @param ipId
	 * @return
	 */
	int deleteByPrimaryKey(String ipId);

	/**
	 * 新增ip
	 * 
	 * @param record
	 * @return
	 */
	int insertIp(Ipaddress ip);

	/**
	 * 根据ipid查询ip信息
	 * 
	 * @param ipId
	 * @return
	 */
	Ipaddress selectByPrimaryKey(String ipId);

	/**
	 * 更新ip信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Ipaddress ip);

	/**
	 * 查询ip列表
	 * 
	 * @param limit
	 * @param offset
	 * @param ipadr
	 * @param ipChannel
	 * @param ipEnvironment
	 * @return
	 */
	List<Ipaddress> selectIpList(@Param("limit") int limit, @Param("offset") int offset, @Param("ipadr") String ipadr,
			@Param("ipChannel") String ipChannel, @Param("ipEnvironment") String ipEnvironment);

	/**
	 * 查询ip总数量
	 * 
	 * @param ipadr
	 * @param ipChannel
	 * @param ipEnvironment
	 * @return
	 */
	int selectIpCount(@Param("ipadr") String ipadr, @Param("ipChannel") String ipChannel,
			@Param("ipEnvironment") String ipEnvironment);
	
	/**
	 * 批量增加ip信息
	 * @param ipList
	 * @return
	 */
	int batchImportIp(List<Ipaddress> ipList);
	
}