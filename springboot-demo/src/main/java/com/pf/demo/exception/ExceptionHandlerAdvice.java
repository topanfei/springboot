package com.pf.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pf.demo.entity.ResultData;
import com.pf.demo.enums.ResultEnum;
import com.pf.demo.util.ResultUtil;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{
	/**
	 * 处理请求方法的请求类型是否正确，如GET/POST/PUT/DELETE是否正确
	 */
	@Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
		e.printStackTrace();
		ResultData resultData = null;
		if(ResultEnum.METHOD_ERROR.getCode() == httpStatus.value()){//405异常
			resultData = ResultUtil.error(ResultEnum.METHOD_ERROR.getCode(), ResultEnum.METHOD_ERROR.getMessage());
		}else{
			resultData = ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
		}
        return new ResponseEntity<Object>(resultData, httpStatus);

    }
	
	
	@ExceptionHandler(BusinessException.class)
    public ResultData handleException(BusinessException e) {
		return ResultUtil.error(e.getCode(), e.getMessage());
    }
}
