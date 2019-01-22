package com.dcits.paramManage.common;

@SuppressWarnings("serial")
public class CustomException extends Exception{
	
	public String msg;
	
	public CustomException(String msg){
		super(msg);
		this.msg=msg;
	}
	
	public CustomException(String msg,Throwable cause){
		super(msg,cause);
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
