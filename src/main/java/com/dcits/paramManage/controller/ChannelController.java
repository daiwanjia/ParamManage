package com.dcits.paramManage.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dcits.paramManage.entity.Channel;
import com.dcits.paramManage.service.ChannelService;

@Controller
@RequestMapping(value = "/channel")
public class ChannelController {
	
	@Autowired
	private ChannelService chnnService;

	private static Log log=LogFactory.getLog(ChannelController.class);
	
	/**
	 * 分页查询渠道信息
	 * 
	 * @param limit
	 *            每页数据限制量
	 * @param page
	 *            页码
	 * @param channelName
	 *            渠道名称
	 * @param systemId
	 *            系统id
	 * @return
	 */
	@RequestMapping(value = "/channelInfo", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String channelInfo(@RequestParam int limit, @RequestParam int page,
			@RequestParam(required = false) String channelName,@RequestParam(required = false) String channelStatus,
			@RequestParam(required = false) String systemId)throws Exception {
		int offset= limit * (page - 1);// 获取当前页第一位下标
		return chnnService.channelList(limit, offset, channelName, channelStatus, systemId);		 
	}
	
	/**
	 * 新增渠道
	 * @param channel 渠道信息
	 * @param systemId 提供方系统id
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/addChannel",method={RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	public String addChannel(@RequestBody Channel channel, String systemId)throws Exception{
		JSONObject jsonMsg=new JSONObject();
		
		if(StringUtils.isBlank(channel.getChannelName())){
			jsonMsg.put("msg", "渠道名称不能为空！");
			return jsonMsg.toJSONString();
		}
		if(StringUtils.isBlank(systemId)){
			jsonMsg.put("msg", "提供方系统不能为空！");
			return jsonMsg.toJSONString();
		}
		//新增
		int insertFlag=chnnService.insertChannel(channel, systemId);
		
		if(insertFlag==1){
			jsonMsg.put("msg", "数据添加成功！");
		}else{
			jsonMsg.put("msg", "数据添加失败！");
		}
		return jsonMsg.toJSONString();
	}
	
	/**
	 * 修改渠道信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/updateChannel",method={RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String updateChannel(@RequestBody Map<String, String> map) throws Exception{
		
		//创建channel对象
		Channel channel=new Channel();
		channel.setChannelId(map.get("channelId"));
		channel.setChannelName(map.get("channelName"));
		channel.setChannelStatus(map.get("channelStatus"));
		channel.setManager(map.get("manager"));
		channel.setStartTime(map.get("startTime"));
		channel.setOnlineTime(map.get("onlineTime"));
		channel.setSdkVersion(map.get("sdkVersion"));
		channel.setRemake(map.get("remake"));
		
		String sid=map.get("sid");//关联表id
		String systemId=map.get("systemId");//提供方系统
		JSONObject jsonMsg=new JSONObject();
		
		if(StringUtils.isBlank(channel.getChannelName())){
			jsonMsg.put("msg", "渠道名称不能为空！");
			return jsonMsg.toJSONString();
		}
		if(StringUtils.isBlank(systemId)){
			jsonMsg.put("msg", "提供方系统不能为空！");
			return jsonMsg.toJSONString();
		}
		int uadateFlag=chnnService.updateByChannelId(channel, systemId, sid);
		
		
		if(uadateFlag==1){
			jsonMsg.put("msg", "数据修改成功！");
		}else{
			jsonMsg.put("msg", "数据修改失败！"+new Exception());
		}
		return jsonMsg.toJSONString();
	}
	
	/**
	 * 删除渠道信息
	 * @param channelId
	 * @param sid
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/delChannel",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String delChannel(String channelId, String sid) throws Exception{
		
		return chnnService.deleteByChannelId(channelId, sid);
	}
	
	/**
	 * 查询全量渠道信息
	 * @return 
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/allChannel",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String allChannel() throws Exception{
		return chnnService.allChannelQry().toString();
	}
	
	/**
	 * 查询
	 * @param service
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/selectChnnIntf",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String selectChnnIntf(String channelId) throws Exception{
		JSONObject jsonMsg=new JSONObject();
		if(StringUtils.isBlank(channelId)){
			jsonMsg.put("msg", "未获取到渠道标识！");
			return jsonMsg.toJSONString();
		}
		return chnnService.chnnIntfInfoList(channelId);
		
	}
	
	/**
	 * 删除渠道调用接口
	 * @param sids
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/delChnnIntf",method={RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String delChnnIntf(@RequestBody List<Object> list) throws Exception{
		JSONObject jsonMsg=new JSONObject();
		long start=System.currentTimeMillis();
		int delFlag=chnnService.delChnnIntf(list);
		long end=System.currentTimeMillis();
		long runTime=end-start;
		log.debug("批量删除执行时间："+runTime+"ms");
		if(delFlag>=1){
			jsonMsg.put("msg", "数据删除成功！");
		}else{
			jsonMsg.put("msg", "数据删除失败！"+new Exception());
		}
		return jsonMsg.toJSONString();
	}
	
	/**
	 * 新增渠道调用接口
	 * @param sids
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/addChnnIntf",method={RequestMethod.POST,RequestMethod.GET},produces="application/json;charset=UTF-8")
	public String addChnnIntf(@RequestBody List<Object> chnnIntfList,@RequestParam(value="channelId") String channelId) throws Exception{
		JSONObject jsonMsg=new JSONObject();
		if(StringUtils.isBlank(channelId)){
			jsonMsg.put("msg", "未获取到渠道标识！");
			return jsonMsg.toJSONString();
		}
		return  chnnService.insertChnnIntf(chnnIntfList, channelId);
		
		
	}
	
}
