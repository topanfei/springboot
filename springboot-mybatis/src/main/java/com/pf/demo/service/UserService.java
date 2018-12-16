package com.pf.demo.service;

import java.util.List;
import java.util.Map;

import com.pf.demo.entity.User;

public interface UserService {

	/**
	 * 获取所有的用户信息
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * 通过用户名获取用户信息
	 * @param userName
	 * @return
	 */
	public List<User> getUserByUserName(String userName);
	
	/**
	 * 通过用户名获取用户信息2
	 * @param user
	 * @return
	 */
	public List<User> getUserByUserName2(User user);
	
	/**
	 * 通过用户名获取用户信息3
	 * @param map
	 * @return
	 */
	public List<User> getUserByUserName3(Map<String,Object> map);
	
	/**
	 * 新增用户信息
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 删除用户信息
	 * @param id
	 */
	public void deleteUser(int id);
}
