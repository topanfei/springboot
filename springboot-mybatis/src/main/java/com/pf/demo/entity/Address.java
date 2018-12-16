package com.pf.demo.entity;
/**
 * 地址信息（和用户是一对一关系） 
 * @author PF
 *
 */
public class Address {

	private Integer addressId;//地址id
	private String addressName;//地址名称
	private Integer userId;//用户id
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressName=" + addressName + ", userId=" + userId + "]";
	}
	
}
