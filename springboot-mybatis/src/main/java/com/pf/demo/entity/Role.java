package com.pf.demo.entity;

/**
 * 角色表
 * @author PF
 *
 */
public class Role {
	private Integer roleId;//角色id
	private String roleName;//角色名称
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
}
