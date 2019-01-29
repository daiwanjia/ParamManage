package com.dcits.paramManage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.common.JsonData;
import com.dcits.paramManage.entity.Channel;
import com.dcits.paramManage.mapper.ChannelMapper;
import com.dcits.paramManage.service.ChannelService;
import com.dcits.paramManage.util.UUIDUtil;

@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelMapper chnnMapper;

	// 添加
	@Override
	public int insertChannel(Channel channel, String systemId) throws Exception {
		// 设置主键uuid
		String channelId = UUIDUtil.getUUID();
		channel.setChannelId(channelId);
		// 插入渠道信息表
		if (chnnMapper.insertChannel(channel) == 1) {
			// 创建link 关联关系
			HashMap<String, String> linkmap = new HashMap<>();
			linkmap.put("sid", UUIDUtil.getUUID());// link主键
			linkmap.put("source", channelId);// 源id
			linkmap.put("relate", systemId);// 关联对象id
			linkmap.put("rank", "chnn_sys");// 关联关系
			return chnnMapper.insertLink(linkmap);
		}
		return 0;
	}

	// 删除
	@Override
	public String deleteByChannelId(String channelId, String sid) throws Exception {
		JSONObject jsonObject=new JSONObject();
		//判断渠道是否有调用接口，若有则禁止删除
		if(chnnMapper.chnnIntfCount(channelId)>0){
			jsonObject.put("msg", "此渠道下存在调用接口，禁止删除！");
			return jsonObject.toJSONString();
		}
		if (chnnMapper.deleteByChannelId(channelId) == 1) {
			if(chnnMapper.deleteLink(sid)==1){
				jsonObject.put("msg", "删除成功！");
				return jsonObject.toJSONString();
			}
		}
		jsonObject.put("msg", "删除失败！"+new Exception());
		return jsonObject.toJSONString();
	}

	// 更新
	@Override
	public int updateByChannelId(Channel channel, String systemId, String sid) throws Exception {
		if (chnnMapper.updateByChannelId(channel) == 1) {
			// 创建linkmap 关联关系
			HashMap<String, String> linkmap = new HashMap<>();
			linkmap.put("sid", sid);// link主键
			linkmap.put("source", channel.getChannelId());// 源id
			linkmap.put("relate", systemId);// 关联对象id
			return chnnMapper.updateLink(linkmap);
		}
		return 0;
	}

	// 查询
	@Override
	public String channelList(int limit, int offset, String channelName, String channelStatus, String systemId)
			throws Exception {
		// 创建查询条件 map
		HashMap<String, Object> queryMap = new HashMap<>();
		queryMap.put("limit", limit);
		queryMap.put("offset", offset);
		queryMap.put("channelName", channelName);
		queryMap.put("channelStatus", channelStatus);
		queryMap.put("systemId", systemId);

		// 查询分页数据
		List<HashMap<String, Object>> querylist = chnnMapper.channelList(queryMap);
		JSONArray serviceArray = JSONArray.parseArray(JSON.toJSONString(querylist));
		// 数据总量
		int count = chnnMapper.channelCount(queryMap);
		// 查询数据总量 ，为了组装成layui_table的固定格式
		return JsonData.toTableData(count, serviceArray);
	}

	// 查询所有渠道
	@Override
	public List<Channel> allChannelQry() throws Exception {
		return chnnMapper.allChannelQry();
	}

	// 查询渠道调用接口
	@Override
	public String chnnIntfInfoList(String channelId) throws Exception {
		List<HashMap<String, Object>> chnnIntfInfoList = chnnMapper.chnnIntfInfoList(channelId);
		JSONArray dataArray = JSONArray.parseArray(JSON.toJSONString(chnnIntfInfoList));
		// 数据总量
		int count = chnnMapper.chnnIntfCount(channelId);
		// 查询数据总量 ，为了组装成layui_table的固定格式
		return JsonData.toTableData(count, dataArray);
	}

	// 批量新增渠道调用接口
	@Override
	public String insertChnnIntf(List<Object> chnnIntfList, String channelId) throws Exception {
		JSONObject jsonMsg = new JSONObject();

		// 查询该渠道已有接口
		List<String> existIntfIds = chnnMapper.selectChnnIntfId(channelId);
		// 前端传递的渠道ids
		List<String> inIntfIds = new ArrayList<>();
		for (int i = 0; i < chnnIntfList.size(); i++) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> intfmap = (HashMap<String, String>) chnnIntfList.get(i);
			inIntfIds.add(intfmap.get("serviceId"));// 对象中获取serviceId存入数组
		}
		// 数组取差得需要新增的接口
		inIntfIds.removeAll(existIntfIds);
		//如果差为空直接返回
		if (inIntfIds.size()== 0) {
			jsonMsg.put("msg", "无新增数据！");
			return jsonMsg.toJSONString();
		}
			// 创建管理表link新增map数组
			List<HashMap<String, Object>> linkList = new ArrayList<>();
			// 遍历接口对象数组，组装link数组
			for (int i = 0; i < inIntfIds.size(); i++) {

				// 创建新增接口关联关系map
				HashMap<String, Object> linkmap = new HashMap<>();

				linkmap.put("sid", UUIDUtil.getUUID());// 设置link表id
				linkmap.put("source",inIntfIds.get(i) );// 源 接口id
				linkmap.put("relate", channelId);// 关联对象 渠道id
				linkmap.put("rank", "intf_chnn");// 关系 服务接口-渠道

				linkList.add(linkmap);
			}
			int insertFlag = chnnMapper.insertChnnIntf(linkList);
			if(insertFlag>=1){
				jsonMsg.put("msg", "数据添加成功！");
			}else{
				jsonMsg.put("msg", "数据添加失败！"+new Exception());
			}
			return jsonMsg.toJSONString();

	}

	// 批量删除渠道调用接口
	@Override
	public int delChnnIntf(List<Object> list) throws Exception {
		// 创建sid数组
		List<String> sids = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) list.get(i);
			String sid = map.get("sid");
			sids.add(sid);
		}

		return chnnMapper.delChnnIntf(sids);
	}

}
