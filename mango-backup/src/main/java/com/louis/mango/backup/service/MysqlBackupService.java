package com.louis.mango.backup.service;

/**
 * 
 * @Description 
 *	Mysql命令行备份恢复服务
 * @author lt
 *
 */
public interface MysqlBackupService {

	/**
	 * 
	 * @Description 
	 *	备份数据源
	 * @param host
	 * @param userName
	 * @param password
	 * @param backupFolderPath
	 * @param fileName
	 * @param database
	 * @return
	 * @throws Exception
	 */
	boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception;
	
	/***
	 * 
	 * @Description 
	 *	恢复数据源
	 * @param restoreFilePath
	 * @param host
	 * @param userName
	 * @param password
	 * @param database
	 * @return
	 * @throws Exception
	 */
	boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception;
}
