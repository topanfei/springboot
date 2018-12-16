package com.pf.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pf.demo.entity.UserRole;
import com.pf.demo.mapper.UserRoleMapper;
import com.pf.demo.service.UserRoleService;
import com.pf.demo.view.UserView;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public void addUserRole(List<UserRole> userRoleList) {
		userRoleMapper.addUserRole(userRoleList);
	}

	@Override
	public List<UserRole> getUserRole(int userId) {
		return userRoleMapper.getUserRole(userId);
	}

	@Override
	public List<UserView> getUserRole2(int userId) {
		return userRoleMapper.getUserRole2(userId);
	}

}
