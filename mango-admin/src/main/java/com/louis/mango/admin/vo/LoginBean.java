package com.louis.mango.admin.vo;

import lombok.Data;

/**
 * 
 * @Description 
 *	登录接口封装对象
 * @author lt
 *
 */
@Data
public class LoginBean {

	private String account;
	
	private String password;
	
	private String captcha;
}
