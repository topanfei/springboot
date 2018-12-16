package com.pf.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pf.demo.entity.User;
import com.pf.demo.mapper.UserMapper;
import com.pf.demo.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}


	@Override
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}


	@Override
	public List<User> getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}


	@Override
	public List<User> getUserByUserName2(User user) {
		return userMapper.getUserByUserName2(user);
	}


	@Override
	public List<User> getUserByUserName3(Map<String, Object> map) {
		return userMapper.getUserByUserName3(map);
	}


	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}


	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}


	@Override
	public void deleteUser(int id) {
		userMapper.deleteUser(id);
	}

}
