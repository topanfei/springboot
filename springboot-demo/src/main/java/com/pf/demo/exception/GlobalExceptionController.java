package com.pf.demo.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 	常见异常有spring异常和servlet异常<br>
 * 	过滤器中抛的异常为servlet异常，controller/service/dao层抛的异常为spring异常<br>
 *   该类主要是处理servlet异常，将springboot自带的处理servlet异常的异常信息重新组装为自己期望的格式
 * @author PF
 *
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalExceptionController extends BasicErrorController{

	public GlobalExceptionController(ErrorAttributes errorAttributes) {
		super(errorAttributes, new ErrorProperties());
	}
	
	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		
		int status_code = (Integer)request.getAttribute("javax.servlet.error.status_code");
		System.out.println("status_code==="+status_code);
		
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.APPLICATION_JSON));
		
		//组装需要的数据
		Map<String, Object> tempMap = new HashMap<String,Object>();
		Set<String> keySet = body.keySet();
		for (String key : keySet) {
			if("status".equals(key)) {
				tempMap.put("code", body.get(key));
			}else if("message".equals(key)) {
				tempMap.put("message", body.get(key));
			}
			tempMap.put("data", null);
		}
		
		//自带的tempMap 为body
		HttpStatus status = getStatus(request);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(tempMap, status);
		return responseEntity;
		
	}

}
