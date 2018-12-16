package com.pf.demo.service;

import java.util.List;

import com.pf.demo.entity.UserRole;
import com.pf.demo.view.UserView;

public interface UserRoleService {

	/**
	 * 新增用户角色信息
	 * @param userRoleList
	 */
	public void addUserRole(List<UserRole> userRoleList);
	
	/**
	 * 通过用户id查看用户角色信息
	 * @param userId 用户id
	 * @return
	 */
	public List<UserRole> getUserRole(int userId);
	
	/**
	 * 通过用户id查看用户角色信息
	 * @param userId 用户id
	 * @return
	 */
	public List<UserView> getUserRole2(int userId);
}
