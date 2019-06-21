package com.trj.demo2.bean;

public class RespBean<T> {
	//返回状态：1代表成功；0代表失败；
	private int state;
	//错误状态
	private int errorCode;
	//返回信息
	private String msg;
	
	private T data;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
