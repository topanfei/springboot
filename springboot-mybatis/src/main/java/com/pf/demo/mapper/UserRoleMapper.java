package com.pf.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pf.demo.entity.UserRole;
import com.pf.demo.view.UserView;

public interface UserRoleMapper {

	/**
	 * 新增用户角色信息
	 * @param userRoleList
	 */
	public void addUserRole(@Param("userRoleList") List<UserRole> userRoleList);
	
	/**
	 * 通过用户id查看用户角色信息
	 * @param userId
	 */
	public List<UserRole> getUserRole(int userId);
	
	/**
	 * 通过第二种方式：一对多形式查询
	 * 根据用户id查看用户角色信息
	 * @param userId
	 * @return
	 */
	public List<UserView> getUserRole2(int userId);
}
