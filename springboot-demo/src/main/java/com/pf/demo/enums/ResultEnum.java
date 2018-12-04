package com.pf.demo.enums;

/**
 * 返回值的枚举信息
 * @author PF
 *
 */
public enum ResultEnum {

	SUCCESS(200,"成功"),
	METHOD_ERROR(405,"方法请求方式不正确"),
	ERROR(500,"系统异常");
	
	private Integer code;
	private String message;
	private ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
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
