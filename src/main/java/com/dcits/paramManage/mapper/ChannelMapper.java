package com.dcits.paramManage.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dcits.paramManage.entity.Channel;
@Mapper
public interface ChannelMapper {

    /**
     * 新增渠道信息
     * @param channel
     * @return
     */   
    int insertChannel(Channel channel);
    /**
     * 删除渠道信息
     * @param channelId
     * @return
     */
    int deleteByChannelId(String channelId);
    
    /**
     * 修改渠道信息
     * @param channel
     * @return
     */
    int updateByChannelId(Channel channel);

    /**
     * 查询渠道list
     * @param queryLimit
     * @return
     */
    List<HashMap<String,Object>> channelList(HashMap<String, Object> queryLimit);
    
    /**
     * 查询渠道count
     * @param queryLimit
     * @return
     */
    int channelCount(HashMap<String,Object> queryLimit);
    
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
	 * 查询所有渠道
	 * @return
	 */
	List<Channel> allChannelQry();

	/**
	 * 查询渠道调用接口清单
	 * @param channelId 渠道id
	 * @return  list
	 */
	List<HashMap<String, Object>> chnnIntfInfoList(String channelId);
	
	/**
     * 查询渠道调用接口count
     * @param channelId
     * @return
     */
    int chnnIntfCount(String channelId);
	
	/**
	 * 给渠道添加接口
	 * @param map
	 * @return
	 */
	int insertChnnIntf(List<HashMap<String, Object>> chnnIntfList);
	
	/**
	 * 根据渠道查询对应的接口
	 * @param channelId
	 * @return
	 */
	List<String> selectChnnIntfId(String channelId);
	
	/**
	 * 批量删除渠道调用接口
	 * @param sidList
	 * @return
	 */
	int delChnnIntf(List<String> sidList);
}