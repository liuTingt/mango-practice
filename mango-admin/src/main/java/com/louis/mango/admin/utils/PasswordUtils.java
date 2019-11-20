package com.louis.mango.admin.utils;

import java.util.UUID;

/****
 * 
 * @Description 
 *	密码工具类
 * @author lt
 *
 */
public class PasswordUtils {

	/***
	 * 
	 * @Description 
	 *	密码匹配
	 * @param salt 盐
	 * @param rawPass 明文
	 * @param encPass 密文
	 * @return
	 */
	public static boolean match(String salt, String rawPass, String encPass) {
		return new PasswordEncoder(salt).matches(encPass, rawPass);
	}
	
	/***
	 * 
	 * @Description 
	 *	加密明文密码
	 * @param rawPass 明文密码
	 * @param salt 盐
	 * @return
	 */
	public static String encode(String rawPass, String salt) {
		return new PasswordEncoder(salt).encode(rawPass);
	}
	
	
	/**
	 * 
	 * @Description 
	 *	获取加密盐
	 * @return
	 */
	public static String getSalt() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
	}
}
