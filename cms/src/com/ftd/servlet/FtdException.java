package com.ftd.servlet;

public class FtdException extends Exception {
	private static final long serialVersionUID = -5315661972489226997L;

	private Throwable cause;

	private String errorCodeId;

	private Object[] args;

	public FtdException(Throwable e, String errorCodeId) {
		super(errorCodeId);
		this.errorCodeId = errorCodeId;
	}

	public FtdException(Throwable e, String errorCodeId, Object... args) {
		super(errorCodeId);
		this.errorCodeId = errorCodeId;
		this.args = args;
	}

	public String getErrorCodeId() {
		return errorCodeId;
	}

	public void setErrorCodeId(String errorCodeId) {
		this.errorCodeId = errorCodeId;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}
