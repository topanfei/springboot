package com.pf.demo.entity;

public class User {

	private Integer userId;
	private String userName;
	private String userPass;
	private Integer userAge;
	public User() {}
	public User(String userName) {
		super();
		this.userName = userName;
	}
	public User(String userName, String userPass, Integer userAge) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.userAge = userAge;
	}
	public User(Integer userId, String userName, String userPass, Integer userAge) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.userAge = userAge;
	}
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", userAge=" + userAge + "]";
	}
}
