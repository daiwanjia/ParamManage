package com.dcits.paramManage.entity;

public class User {
    private String userid;

    private String username;

    private String userpwd;

    private String userstatus;

    private String remake;

    private String absOne;

    private String absTwo;

    public String getUserid() {
        return userid;
    }

    public void String(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus == null ? null : userstatus.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    public String getAbsOne() {
        return absOne;
    }

    public void setAbsOne(String absOne) {
        this.absOne = absOne == null ? null : absOne.trim();
    }

    public String getAbsTwo() {
        return absTwo;
    }

    public void setAbsTwo(String absTwo) {
        this.absTwo = absTwo == null ? null : absTwo.trim();
    }

    //重写toString 转为json
	@Override
	public String toString() {
		return "{\"userid\":\"" + userid + "\",\"username\":\"" + username + "\",\"userpwd\":\"" + userpwd
				+ "\",\"userstatus\":\"" + userstatus + "\",\"remake\":\"" + remake + "\",\"absOne\":\"" + absOne
				+ "\",\"absTwo\":\"" + absTwo + "\"}  ";
	}
    
    
}