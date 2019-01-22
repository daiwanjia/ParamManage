package com.dcits.paramManage.service;

import org.springframework.web.multipart.MultipartFile;

import com.dcits.paramManage.entity.IntfService;

public interface IntfServiceService {
	
	/**
	 * 新增接口
	 * 
	 * @param service
	 * @return int
	 * @throws Exception
	 */
	String insertService(IntfService service,String systemId) throws Exception;

	/**
	 * 修改服务信息
	 * @param service
	 * @param systemId 系统表id
	 * @param sid  关联表id
	 * @return
	 * @throws Exception
	 */
	String uadateByPrimaryKey(IntfService service,String systemId,String sid) throws Exception;

	/**
	 * 删除服务
	 * 
	 * @param serviceId  服务id
	 * @param sid  关联表id
	 * @return int
	 * @throws Exception
	 */
	int deleteByPrimaryKey(String serviceId,String sid) throws Exception;
	/**
	 * 查询接口信息
	 * @param limit  每页的数量
	 * @param offset 起始下标
	 * @param serviceEnname 服务英文名称
	 * @param serviceCnname 服务中文名称
	 * @param systemId 提供方系统
	 * @param serviceStatus 服务状态
	 * @return serviceList
	 */
	String serviceList(int limit, int offset, String serviceEnname, String serviceCnname, String serviceStatus,
			String systemId)throws Exception;
	
	/**
	 * 查询服务接口调用频率
	 * @return
	 */
	String intfCallfrqncyList(int limit,int offset,String intfCallfrqncyList)throws Exception;
	
	/**
	 * 查询调用此服务接口的渠道
	 * @param serviceId
	 * @return
	 */
	String calledChnnList(String serviceId)throws Exception;
	
	/**
	 * 批量导入接口
	 * @param file
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	String batchimportIntf(MultipartFile file)throws Exception;
}
