package com.dcits.paramManage.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dcits.paramManage.entity.IntfService;

@Mapper
public interface IntfServiceMapper {
	
	int insert(IntfService service);

	IntfService selectByPrimaryKey(String serviceId);

	/**
	 * 修改服务信息
	 * @param intfService
	 * @return int
	 */
	int updateByPrimaryKeySelective(IntfService intfService);
	
	/**
	 * 删除服务
	 * @param serviceId 服务id
	 * @return int
	 */
	int deleteByPrimaryKey(String serviceId);
	
	/**
	 * 新增接口
	 * 
	 * @param service
	 * @return
	 */
	int insertService(IntfService service);
	
	/**
	 * 查询接口是否存在 接口编码唯一
	 * @param serviceEnname
	 * @return
	 * @throws Exception
	 */
	int selectIntfexist(String serviceEnname);
	
	/**
	 * 查询接口信息
	 * @param limit  每页的数量
	 * @param offset 起始下标
	 * @param serviceEnname 服务英文名称
	 * @param serviceCnname 服务中文名称
	 * @param serviceSystem 服务所属系统
	 * @param serviceStatus 服务状态
	 * @return
	 */
	List<HashMap<String,Object>> serviceList(HashMap<String, Object> queryLimit);

	/**
	 * 查询接口信息总量
	 * @param limit  每页的数量
	 * @param offset 起始下标
	 * @param serviceEnname 服务英文名称
	 * @param serviceCnname 服务中文名称
	 * @param serviceSystem 服务所属系统
	 * @param serviceStatus 服务状态
	 * @return int
	 */
	int seriveCount(HashMap<String, Object> queryLimit);
	
	/**
	 * 创建关联表信息
	 * @param sid  主键id
	 * @param source  源id
	 * @param relate  关联对象Id
	 * @param rank   关联类型
	 * @return
	 */
	int insertLink(HashMap<String, String> linkmap);
	
	/**
	 * 修改关联表信息
	 * @param sid  主键id
	 * @param source  源id
	 * @param relate  关联对象Id
	 * @param rank   关联类型
	 * @return
	 */
	int updateLink(HashMap<String, String> linkmap);
	
	/**
	 * 删除关联信息
	 * @param sid
	 * @return
	 */
	int deleteLink(String sid);
	
	
	/**
	 * 查询服务接口调用频率
	 * @return
	 */
	List<HashMap<String, Object>>  selectIntfCallfrqncy(@Param("limit")int limit,
			@Param("offset")int offset,@Param("serviceEnname")String serviceEnname);
	
	/**
	 * 查询被调用服务接口总量
	 * @return
	 */
	int selectIntfCallfrqncyCount();
	
	/**
	 * 查询调用此服务接口的渠道
	 * @param serviceId
	 * @return
	 */
	List<String> selectCalledChnn(String serviceId);
}