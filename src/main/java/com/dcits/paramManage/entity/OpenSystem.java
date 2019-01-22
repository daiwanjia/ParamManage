package com.dcits.paramManage.entity;

public class OpenSystem {
    private String openModuleId;//开发平台模块唯一标识（uuid）

    private String openEnvironmentId;//模块所在环境id

    private String openModuleName;//模块名称

    private String openModuleUrl;//模块网站访问地址

    private String openModuleServer;//模块所在服务器地址

    private String openModuleAcct;//模块所在服务器ssh账号密码

    private String openModuleUpdatetime;//模块信息更新时间
    
    private Environment environment;//注入环境信息

    public String getOpenModuleId() {
        return openModuleId;
    }

    public void setOpenModuleId(String openModuleId) {
        this.openModuleId = openModuleId == null ? null : openModuleId.trim();
    }

    public String getOpenEnvironmentId() {
        return openEnvironmentId;
    }

    public void setOpenEnvironmentId(String openEnvironmentId) {
        this.openEnvironmentId = openEnvironmentId == null ? null : openEnvironmentId.trim();
    }

    public String getOpenModuleName() {
        return openModuleName;
    }

    public void setOpenModuleName(String openModuleName) {
        this.openModuleName = openModuleName == null ? null : openModuleName.trim();
    }

    public String getOpenModuleUrl() {
        return openModuleUrl;
    }

    public void setOpenModuleUrl(String openModuleUrl) {
        this.openModuleUrl = openModuleUrl == null ? null : openModuleUrl.trim();
    }

    public String getOpenModuleServer() {
        return openModuleServer;
    }

    public void setOpenModuleServer(String openModuleServer) {
        this.openModuleServer = openModuleServer == null ? null : openModuleServer.trim();
    }

    public String getOpenModuleAcct() {
        return openModuleAcct;
    }

    public void setOpenModuleAcct(String openModuleAcct) {
        this.openModuleAcct = openModuleAcct == null ? null : openModuleAcct.trim();
    }

    public String getOpenModuleUpdatetime() {
        return openModuleUpdatetime;
    }

    public void setOpenModuleUpdatetime(String openModuleUpdatetime) {
        this.openModuleUpdatetime = openModuleUpdatetime == null ? null : openModuleUpdatetime.trim();
    }
    
    public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "{\"openModuleId\":\"" + openModuleId + "\",\"openEnvironmentId\":\"" + openEnvironmentId
				+ "\",\"openModuleName\":\"" + openModuleName + "\",\"openModuleUrl\":\"" + openModuleUrl
				+ "\",\"openModuleServer\":\"" + openModuleServer + "\",\"openModuleAcct\":\"" + openModuleAcct
				+ "\",\"openModuleUpdatetime\":\"" + openModuleUpdatetime + "\",\"environmentName\":\"" + environment.getEnvironmentName() + "\"}";
	}

	
    
    
}