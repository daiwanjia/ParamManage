package com.dcits.paramManage.service;

import java.util.List;

import com.dcits.paramManage.entity.Channel;

public interface ChannelService {
	/**
     * 新增渠道信息
     * @param channel
     * @param systemId
     * @return
     */   
    int insertChannel(Channel channel,String systemId) throws Exception;
    /**
     * 删除渠道信息
     * @param channelId
     * @param sid  关联表id
     * @return
     */
    String deleteByChannelId(String channelId,String sid) throws Exception;
    
    /**
     * 修改渠道信息
     * @param channel
     * @param systemId  系统表id
     * @param sid 关联表id
     * @return
     */
    int updateByChannelId(Channel channel,String systemId,String sid) throws Exception;

    /**
     * 查询渠道list
     * @param queryLimit
     * @return
     */
    String channelList(int limit, int offset, String channelName,String channelStatus, String systemId) throws Exception;
    
    /**
     * 查询所有渠道
     * @return
     * @throws Exception
     */
    List<Channel> allChannelQry() throws Exception;
    
    /**
	 * 查询渠道调用接口清单
	 * @param channelId 渠道id
	 * @return  list
	 */
	String chnnIntfInfoList(String channelId)throws Exception;
	
	/**
	 * 给渠道添加接口
	 * @param map
	 * @return
	 */
	String insertChnnIntf(List<Object> chnnIntfList,String channelId)throws Exception;
	
	/**
	 * 批量删除渠道调用接口
	 * @param sidList
	 * @return
	 */
	int delChnnIntf(List<Object> list)throws Exception;
}
