package com.pf.demo.util;

import com.pf.demo.entity.ResultData;
import com.pf.demo.enums.ResultEnum;

public class ResultUtil {

	/**
	 * 返回成功
	 * @param obj
	 * @return
	 */
	public static ResultData success(Object obj) {
		ResultData result = new ResultData();
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMessage(ResultEnum.SUCCESS.getMessage());
		result.setData(obj);
		return result;
	}
	
	/**
	 * 返回成功
	 * @return
	 */
	public static ResultData success() {
		return success(null);
	}
	
	/**
	 * 返回异常信息
	 * @param code
	 * @param message
	 * @return
	 */
	public static ResultData error(Integer code,String message) {
		ResultData result = new ResultData();
		result.setCode(code);
		result.setMessage(message);
		result.setData(null);
		return result;
	}
	
	/**
	 * 返回异常信息
	 * @param message
	 * @param obj
	 * @return
	 */
	public static ResultData error(String message) {
		return error(ResultEnum.ERROR.getCode(), message);
	}
	
	/**
	 * 返回异常信息
	 * @return
	 */
	public static ResultData error() {
		return error(ResultEnum.ERROR.getMessage());
	}
}
