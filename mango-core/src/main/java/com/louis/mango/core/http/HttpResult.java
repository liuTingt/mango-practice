package com.louis.mango.core.http;

import lombok.Data;

/***
 * 
 * @Description 
 *	Http结果封装
 * 
 * @author lt
 * @date 2019年10月28日上午9:49:21
 */
@Data
public class HttpResult {

	private int code = 200;
	
	private String msg;
	
	private Object data;
	
	
	/**
	 * 
	 * @Description 
	 *
	 * @return
	 */
	public static HttpResult error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	/**
	 * 
	 * @Description 
	 *
	 * @param msg
	 * @return
	 */
	public static HttpResult error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	/****
	 * 
	 * @Description 
	 *	
	 * @param msg
	 * @param code
	 * @return
	 */
	public static HttpResult error(int code, String msg) {
		HttpResult hr = new HttpResult();
		hr.setMsg(msg);
		hr.setCode(code);
		return hr;
	}
	
	
	public static HttpResult ok(String msg) {
		HttpResult hr = new HttpResult();
		hr.setMsg(msg);
		return hr;
	}
	
	
	public static HttpResult ok(Object data) {
		HttpResult hr = new HttpResult();
		hr.setData(data);
		return hr;
	}
	
	public static HttpResult ok() {
		return new HttpResult();
	}
}
