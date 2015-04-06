package com.ftd.servlet;

public class FtdException extends Exception {
	private static final long serialVersionUID = -5315661972489226997L;

	private int errorCode;
	private String desc;

	public FtdException(Exception e) {
		super(e);
	}

	public FtdException(int errorCode) {
		this.errorCode = errorCode;
	}

	public FtdException(int errorCode, String desc) {
		this.errorCode = errorCode;
		this.desc = desc;
	}

	public FtdException(Exception e, int errorCode, String desc) {
		super(e);
		this.errorCode = errorCode;
		this.desc = desc;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
