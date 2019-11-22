package com.louis.mango.backup.utils;

import lombok.Data;

/***
 * 
 * @Description 
 *	Http结果封装
 * @author lt
 *
 */
@Data
public class HttpResult {

	private int code = 200;
	
	private String msg;
	
	private Object data;
	
	public static HttpResult error(int code, String msg) {
		HttpResult result = new HttpResult();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static HttpResult error(String msg) {
		return error(500, msg);
	}
	
	public static HttpResult error() {
		return error(500, "未知异常，请联系管理员"); 
	}
	
	public static HttpResult ok(String msg) {
		HttpResult r = new HttpResult();
		r.setMsg(msg);
		return r;
	}
	
	public static HttpResult ok(Object data) {
		HttpResult r = new HttpResult();
		r.setData(data);
		return r;
	}
	
	public static HttpResult ok() {
		return new HttpResult();
	}
}
