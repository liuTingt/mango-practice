package com.louis.mango.common.utils;

import java.io.Closeable;
import java.io.IOException;

/****
 * 
 * @Description 
 *	相关IO工具类
 * @author lt
 *
 */
public class IOUtils {

	public static void closeQuietly(final Closeable closeable) {
		try {
			if(closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			
		}
	}
}
