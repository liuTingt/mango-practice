package com.louis.mango.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 
 * @Description 
 *	日期时间工具类
 * @author lt
 *
 */
public class DateTimeUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	/***
	 * 
	 * @Description 
	 *	获取当前标准格式化日期时间
	 * @return
	 */
	public static String getDateTime() {
		return getDateTime(new Date());
	}
	
	/***
	 * 
	 * @Description 
	 *	标准格式化日期时间
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date) {
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
}
