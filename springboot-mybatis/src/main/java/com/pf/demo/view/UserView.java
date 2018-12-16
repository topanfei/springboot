package com.pf.demo.view;

import java.util.List;

import com.pf.demo.entity.Address;
import com.pf.demo.entity.Role;

public class UserView {
	private Integer userId;//用户id
	private String userName;//用户名
	private String userPass;//用户密码
	private Integer userAge;//用户年龄
	private Address address;//地址信息
	private List<Role> roleList;//角色列表信息
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	@Override
	public String toString() {
		return "UserView [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", userAge="
				+ userAge + ", address=" + address + ", roleList=" + roleList + "]";
	}
}
