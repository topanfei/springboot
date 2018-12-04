package com.pf.demo.controller.init;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf.demo.entity.ResultData;

@RestController
public class InitController {

	@PostMapping(value="login")
	public ResultData login() {
		
		
		
		return null;
		
	}
}
