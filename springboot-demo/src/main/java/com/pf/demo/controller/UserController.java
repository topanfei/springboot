package com.pf.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf.demo.entity.ResultData;
import com.pf.demo.entity.User;
import com.pf.demo.util.ResultUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@GetMapping(value="list")
	public ResultData list() {
		List<User> userList = new ArrayList<User>();
		User user1 = new User("1001", "张三", 25, new Date());
		User user2 = new User("1002", "李四", 26, new Date());
		User user3 = new User("1003", "王五", 27, new Date());
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		return ResultUtil.success(userList);
	}
}
