package com.dcits.paramManage.entity;

import javax.validation.constraints.NotBlank;

public class Environment {
    private String environmentId;

    @NotBlank(message="{environment.name.isBlank}")
    private String environmentName;//环境名称
    
    @NotBlank(message="{environment.url.isBlank}")
    private String environmentUrl;//环境地址
    
    private String environmentDns;//环境域名

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId == null ? null : environmentId.trim();
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName == null ? null : environmentName.trim();
    }

    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    public void setEnvironmentUrl(String environmentUrl) {
        this.environmentUrl = environmentUrl == null ? null : environmentUrl.trim();
    }
    
    public String getEnvironmentDns() {
		return environmentDns;
	}

	public void setEnvironmentDns(String environmentDns) {
		this.environmentDns = environmentDns;
	}

	@Override
	public String toString() {
		return "{\"environmentId\":\"" + environmentId + "\",\"environmentName\":\"" + environmentName
				+ "\",\"environmentUrl\":\"" + environmentUrl + "\",\"environmentDns\":\"" + environmentDns + "\"}";
	}

	
    
}