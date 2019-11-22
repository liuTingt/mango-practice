package com.louis.mango.backup.utils;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @Description 
 *	Mysql备份还原工具类
 * @author lt
 *
 */
public class MysqlBackupRestoreUtils {

	/***
	 * 
	 * @Description 
	 *	备份数据库
	 * @param host
	 * @param userName
	 * @param password
	 * @param backupFolderPath
	 * @param fileName
	 * @param database
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws IOException, InterruptedException {
		File backupFolderFile = new File(backupFolderPath);
		if(!backupFolderFile.exists()) {
			backupFolderFile.mkdirs();
		}
		if(!backupFolderPath.endsWith(File.separator) && !backupFolderPath.endsWith("/")) {
			backupFolderPath = backupFolderPath + File.separator;
		}
		// 拼接命令
		String backupFilePath = backupFolderPath + fileName;
		StringBuffer  buffer = new StringBuffer();
		//buffer.append("mysqldump --opt ").append(" --add-drop-database ").append(" --add-drop-table ");
		buffer.append("mysqldump -h").append(host).append(" -u").append(userName).append(" -p").append(password);
		buffer.append(" ").append(database).append(" > ").append(backupFilePath);
		// 调用外部执行 exe文件的Java API
		Process process = Runtime.getRuntime().exec(getCommand(buffer.toString()));
		System.out.println(buffer.toString());
		if(process.waitFor() == 0) {
			// 0表示线程正常终止
			System.out.println("数据库已备份到 " + backupFilePath + " 文件中");
			return true;
		}
		return false;
	}
	
	/***
	 * 
	 * @Description 
	 *	还原数据库
	 * @param restoreFilePath
	 * @param host
	 * @param userName
	 * @param password
	 * @param database
	 * @return
	 */
	public static boolean restore(String restoreFilePath, String host, String userName, String password, String database) {
		File restoreFile = new File(restoreFilePath);
		if(restoreFile.isDirectory()) {
			for(File file: restoreFile.listFiles()) {
				if(file.exists() && file.getPath().endsWith(".sql")) {
					restoreFilePath = file.getAbsolutePath();
					break;
				}
			}
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("mysql -h").append(host).append(" -u").append(userName).append(" -p").append(password);
		buffer.append(" ").append(database).append(" < ").append(restoreFilePath);
		try {
			Process process = Runtime.getRuntime().exec(getCommand(buffer.toString()));
			if(process.waitFor() == 0) {
				System.out.println("数据已从 " + restoreFilePath + " 导入到数据库中");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String[] getCommand(String command) {
		String os = System.getProperty("os.name");
		String shell = "/bin/bash";
		String c = "-c";
		if(os.toLowerCase().startsWith("win")) {
			shell = "cmd";
			c = "/c";
		}
		String[] cmd = {shell, c, command};
		return cmd;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String host = "localhost";
		String userName = "root";
		String password = "root";
		String database = "mango";
		
		System.out.println("开始备份");
		String backupFolderPath = "D:\\WorkSpace\\eclipse-photon\\mango-practice\\database-backup";
		String fileName = "mango.sql";
		backup(host, userName, password, backupFolderPath, fileName, database);
		System.out.println("备份成功");
		
		System.out.println("开始还原");
		String restoreFilePath = "D:\\WorkSpace\\eclipse-photon\\mango-practice\\database-backup\\mango.sql";
		restore(restoreFilePath, host, userName, password, database);
		System.out.println("还原成功");
	}
}
