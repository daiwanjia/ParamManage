package com.dcits.paramManage.entity;

public class Ipaddress {
	private String ipId;

	private String ipadr;

	private String ipEnvironment;

	private String ipChannel;

	private String ipRemake;
	
	public Ipaddress(){}
	
	public Ipaddress(String ipId, String ipadr, String ipEnvironment, String ipChannel, String ipRemake) {
		super();
		this.ipId=ipId;
		this.ipadr=ipadr;
		this.ipEnvironment=ipEnvironment;
		this.ipChannel=ipChannel;
		this.ipRemake=ipRemake;
	}

	public String getIpId() {
		return ipId;
	}

	public void setIpId(String ipId) {
		this.ipId = ipId == null ? null : ipId.trim();
	}

	public String getIpadr() {
		return ipadr;
	}

	public void setIpadr(String ipadr) {
		this.ipadr = ipadr == null ? null : ipadr.trim();
	}

	public String getIpEnvironment() {
		return ipEnvironment;
	}

	public void setIpEnvironment(String ipEnvironment) {
		this.ipEnvironment = ipEnvironment == null ? null : ipEnvironment.trim();
	}

	public String getIpChannel() {
		return ipChannel;
	}

	public void setIpChannel(String ipChannel) {
		this.ipChannel = ipChannel == null ? null : ipChannel.trim();
	}

	public String getIpRemake() {
		return ipRemake;
	}

	public void setIpRemake(String ipRemake) {
		this.ipRemake = ipRemake == null ? null : ipRemake.trim();
	}
}