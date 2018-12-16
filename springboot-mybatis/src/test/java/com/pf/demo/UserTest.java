package com.pf.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pf.demo.entity.User;
import com.pf.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.pf.demo.mapper")
public class UserTest {

	@Autowired
	private UserService userService;
	
	
	@Test
	public void getAllUserTest() {
		List<User> userList = userService.getAllUser();
		for (User user : userList) {
			System.out.println("user="+user);
		}
	}
	
	@Test
	public void getUserById() {
		User user = userService.getUserById(1);
		System.out.println("user="+user);
	}
	
	@Test
	public void getUserByUserName() {
		List<User> userList = userService.getUserByUserName("张三");
		for (User user : userList) {
			System.out.println("user="+user);
		}
	}
	
	@Test
	public void getUserByUserName2() {
		List<User> userList = userService.getUserByUserName2(new User("张三"));
		for (User user : userList) {
			System.out.println("user="+user);
		}
	}

	@Test
	public void getUserByUserName3() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", "张三");
		List<User> userList = userService.getUserByUserName3(map);
		for (User user : userList) {
			System.out.println("user="+user);
		}
	}
	
	@Test
	public void addUser() {
		userService.addUser(new User("刘六", "123456", 25));
	}
	
	@Test
	public void updateUser() {
		userService.updateUser(new User(1, "张三1", "", 23));
	}
	
	@Test
	public void deleteUser() {
		userService.deleteUser(4);
	}
}
