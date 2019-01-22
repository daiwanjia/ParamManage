package com.dcits.paramManage.entity;

public class Channel {
    private String channelId;

    private String channelName;
    
    private String channelStatus;

    private String manager;

    private String startTime;

    private String onlineTime;

    private String sdkVersion;

    private String remake;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(String channelStatus) {
		this.channelStatus = channelStatus;
	}

	public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime == null ? null : onlineTime.trim();
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion == null ? null : sdkVersion.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

	@Override
	public String toString() {
		return "{\"channelId\":\"" + channelId + "\",\"channelName\":\"" + channelName + "\",\"channelStatus\":\""
				+ channelStatus + "\",\"manager\":\"" + manager + "\",\"startTime\":\"" + startTime
				+ "\",\"onlineTime\":\"" + onlineTime + "\",\"sdkVersion\":\"" + sdkVersion + "\",\"remake\":\""
				+ remake + "\"}";
	}
    
    
}