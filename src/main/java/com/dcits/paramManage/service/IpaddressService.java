package com.dcits.paramManage.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.dcits.paramManage.entity.Ipaddress;

public interface IpaddressService {
	/**
	 * 删除ip信息
	 * @param ipId
	 * @return
	 */
	String deleteByPrimaryKey(String ipId)throws Exception;

	/**
	 * 新增ip
	 * @param record
	 * @return
	 */
	String insertIp(Ipaddress ip)throws Exception;
	
	/**
	 * 根据ipid查询ip信息
	 * @param ipId
	 * @return
	 */
	Ipaddress selectByPrimaryKey(String ipId)throws Exception;
	
	/**
	 * 更新ip信息
	 * @param record
	 * @return
	 */
	String updateByPrimaryKey(Ipaddress ip)throws Exception;
	
	/**
	 * 查询ip列表
	 * @param limit
	 * @param offset
	 * @param ipadr
	 * @param ipChannel
	 * @param ipEnvironment
	 * @return
	 */
	String selectIpList(@Param("limit") int limit, @Param("offset") int offset, @Param("ipadr") String ipadr,
			@Param("ipChannel") String ipChannel, @Param("ipEnvironment") String ipEnvironment)throws Exception;
	
	/**
	 * 批量导入ip
	 * @param file
	 * @return
	 * @throws Exception
	 */
	String batchImportIp(MultipartFile file)throws Exception;
}
