package com.pf.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pf.demo.entity.User;

public interface UserMapper {

	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * 根据id获取某个用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * 根据用户名获取某个用户信息（传一个参数，在该sql处如果要写判断，必须加@param注解）
	 * @param userName
	 */
	public List<User> getUserByUserName(@Param("userName")String userName);
	
	/**
	 * 通过用户名称查看用户信息（和getUserByUserName一样，只不过此时传实体对象）
	 * @param user
	 * @return
	 */
	public List<User> getUserByUserName2(User user);
	
	/**
	 * 通过用户名称查看用户信息（和getUserByUserName一样，只不过此时传map对象）
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
