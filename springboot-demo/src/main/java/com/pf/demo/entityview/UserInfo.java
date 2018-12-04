package com.pf.demo.entityview;

import java.util.Date;
import java.util.List;

/**
 * 用户视图类
 * @author PF
 *
 */
public class UserInfo {

	private String id;//用户id
	private String userName;//用户名
	private String userPasswd;//用户密码
	private String roleName;//角色名称
	private String roleType;//角色类型
	private Date last_password_reset_date;//上次修改日期
	private List<String> authorityNameList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Date getLast_password_reset_date() {
		return last_password_reset_date;
	}
	public void setLast_password_reset_date(Date last_password_reset_date) {
		this.last_password_reset_date = last_password_reset_date;
	}
	public List<String> getAuthorityNameList() {
		return authorityNameList;
	}
	public void setAuthorityNameList(List<String> authorityNameList) {
		this.authorityNameList = authorityNameList;
	}
}
