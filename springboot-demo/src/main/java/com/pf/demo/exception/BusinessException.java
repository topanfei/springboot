package com.pf.demo.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Integer code;
	public String message;
	public BusinessException(String messgae) {
		super(messgae);
	}
	public BusinessException(Integer code,String message) {
		super(message);
		this.code = code;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
