package com.dcits.paramManage.entity;

public class ProvideSystem {
	
	private String systemId;
	
    private String systemEnname;

    private String systemCnname;

    private String remake;

    private String absTwo;
    
    public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSystemEnname(){
    	return systemEnname;
    }
    
    public void setSystemEnname(String systemEnname) {
        this.systemEnname = systemEnname == null ? null : systemEnname.trim();
    }

    public String getSystemCnname() {
        return systemCnname;
    }

    public void setSystemCnname(String systemCnname) {
        this.systemCnname = systemCnname == null ? null : systemCnname.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    public String getAbsTwo() {
        return absTwo;
    }

    public void setAbsTwo(String absTwo) {
        this.absTwo = absTwo == null ? null : absTwo.trim();
    }

	@Override
	public String toString() {
		return "{\"systemId\":\"" + systemId + "\",\"systemEnname\":\"" + systemEnname + "\",\"systemCnname\":\""
				+ systemCnname + "\",\"remake\":\"" + remake + "\",\"absTwo\":\"" + absTwo + "\"}";
	}

	

}