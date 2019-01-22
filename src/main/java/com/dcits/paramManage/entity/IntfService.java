package com.dcits.paramManage.entity;

public class IntfService {
	
    private String serviceId;

    private String serviceNo;
    
    private String serviceEnname;

    private String serviceCnname;

    private String serviceDesc;

    private String serviceStatus;

    private String serviceVersion;

    private String serviceUpdatetime;

	public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getServiceEnname() {
        return serviceEnname;
    }

    public void setServiceEnname(String serviceEnname) {
        this.serviceEnname = serviceEnname == null ? null : serviceEnname.trim();
    }

    public String getServiceCnname() {
        return serviceCnname;
    }

    public void setServiceCnname(String serviceCnname) {
        this.serviceCnname = serviceCnname == null ? null : serviceCnname.trim();
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc == null ? null : serviceDesc.trim();
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus == null ? null : serviceStatus.trim();
    }

    public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getServiceUpdatetime() {
        return serviceUpdatetime;
    }

    public void setServiceUpdatetime(String serviceUpdatetime) {
        this.serviceUpdatetime = serviceUpdatetime == null ? null : serviceUpdatetime.trim();
    }

	@Override
	public String toString() {
		return "{\"serviceId\":\"" + serviceId + "\",\"serviceNo\":\"" + serviceNo + "\",\"serviceEnname\":\""
				+ serviceEnname + "\",\"serviceCnname\":\"" + serviceCnname + "\",\"serviceDesc\":\"" + serviceDesc
				+ "\",\"serviceStatus\":\"" + serviceStatus + "\",\"serviceVersion\":\"" + serviceVersion
				+ "\",\"serviceUpdatetime\":\"" + serviceUpdatetime + "\"}";
	}

	
    
    
}