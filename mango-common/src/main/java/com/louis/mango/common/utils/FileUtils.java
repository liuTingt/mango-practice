package com.louis.mango.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Description 
 *	文件相关操作
 * @author lt
 *
 */
public class FileUtils {

	/***
	 * 
	 * @Description 
	 *	下载文件
	 * @param response
	 * @param file
	 * @param newFileName
	 */
	public static void downloadFile(HttpServletResponse response, File file, String newFileName) {
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(newFileName.getBytes("ISO-8859-1"), "UTF-8"));
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			InputStream input = new FileInputStream(file.getAbsolutePath());
			BufferedInputStream bis = new BufferedInputStream(input);
			int length = 0;
			byte[] temp = new byte[1 * 1024 * 10];
			while((length = bis.read(temp)) != -1) {
				bos.write(temp, 0, length);
			}
			bos.flush();
			bis.close();
			bos.close();
			input.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description 
	 *	读取txt文件中的内容
	 * @param file 想要读取的文件路径
	 * @return
	 */
	public static String readFile(String file) {
		return readFile(new File(file));
	}
	
	/****
	 * 
	 * @Description 
	 *	读取txt文件中的内容
	 * @param file 想要读取的文件对象
	 * @return
	 */
	public static String readFile(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			while((s = br.readLine()) != null) {
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/***
	 * 
	 * @Description 
	 *	递归删除文件
	 */
	public static void deleteFile(File file) {
		// 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
		if(file.isDirectory()) {
			for(File sub: file.listFiles()) {
				deleteFile(sub);
			}
		}
		file.delete();
	}
	
	/****
	 * 
	 * @Description 
	 *	获取项目根路径
	 * @return
	 */
	public static String getProjectPath() {
		return new File(getClassPath()).getParentFile().getParentFile().getAbsolutePath();
	}
	
	/***
	 * 
	 * @Description 
	 *	获取类路径
	 * @return
	 */
	public static String getClassPath() {
		String classPath = FileUtils.class.getClassLoader().getResource("").getPath();
		return classPath;
	}
	
	public static void main(String[] args) {
		System.out.println(getProjectPath());
	}
}
