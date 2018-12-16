package com.pf.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pf.demo.entity.UserRole;
import com.pf.demo.service.UserRoleService;
import com.pf.demo.view.UserView;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value="com.pf.demo.mapper")
public class UserRoleTest {

	@Autowired
	private UserRoleService userRoleService;
	
	@Test
	public void addUserRole() {
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		userRoleList.add(new UserRole(1, 1001));
		userRoleList.add(new UserRole(1, 1002));
		userRoleService.addUserRole(userRoleList);
	}
	
	@Test
	public void getUserRole() {
		List<UserRole> userRoleList = userRoleService.getUserRole(1);
		for (UserRole userRole : userRoleList) {
			System.out.println("userRole="+userRole);
		}
	}
	
	@Test
	public void getUserRole2() {
		List<UserView> UserViewList = userRoleService.getUserRole2(1);
		for (UserView userView : UserViewList) {
			System.out.println("userView="+userView);
		}
	}
}
