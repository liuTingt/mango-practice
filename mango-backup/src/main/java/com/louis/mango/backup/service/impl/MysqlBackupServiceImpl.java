package com.louis.mango.backup.service.impl;

import org.springframework.stereotype.Service;

import com.louis.mango.backup.service.MysqlBackupService;
import com.louis.mango.backup.utils.MysqlBackupRestoreUtils;

/***
 * 
 * @Description 
 *	
 * @author lt
 *
 */
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService{

	@Override
	public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName,
			String database) throws Exception {
		return MysqlBackupRestoreUtils.backup(host, userName, password, backupFolderPath, fileName, database);
	}

	@Override
	public boolean restore(String restoreFilePath, String host, String userName, String password, String database)
			throws Exception {
		return MysqlBackupRestoreUtils.restore(restoreFilePath, host, userName, password, database);
	}

}
