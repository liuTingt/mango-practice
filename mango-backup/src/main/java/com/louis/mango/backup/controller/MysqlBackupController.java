package com.louis.mango.backup.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.backup.constans.BackupConstants;
import com.louis.mango.backup.datasource.BackupDataSourceProperties;
import com.louis.mango.backup.service.MysqlBackupService;
import com.louis.mango.backup.utils.HttpResult;
import com.louis.mango.common.utils.FileUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 
 * @Description 
 *	数据库备份还原控制器
 * @author lt
 *
 */
@Api(tags = {"数据备份还原接口"})
@RestController
public class MysqlBackupController {

	@Autowired
	private MysqlBackupService mysqlBackupService;
	
	@Autowired
	private BackupDataSourceProperties properties;
	
	@ApiOperation(value = "数据备份")
	@GetMapping(value = "/backup")
	public HttpResult backup() {
		String backupFolderName = BackupConstants.DEFAULT_BACKUP_NAME + "_" + (new SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
		return backup(backupFolderName);
	}
	
	private HttpResult backup(String backupFodlerName) {
		String host = properties.getHost();
		String userName = properties.getUserName();
		String password = properties.getPassword();
		String database = properties.getDatabase();
		String backupFolderPath = BackupConstants.BACKUP_FOLDER + backupFodlerName + File.separator;
		String fileName = BackupConstants.BACKUP_FILE_NAME;
		
		try {
			boolean success = mysqlBackupService.backup(host, userName, password, backupFolderPath, fileName, database);
			if(!success) {
				return HttpResult.error("数据备份失败");
			}
		} catch (Exception e) {
			return HttpResult.error(500, e.getMessage());
		}
		return HttpResult.ok();
	}
	
	@ApiOperation(value = "数据还原")
	@GetMapping(value = "/restore")
	public HttpResult restore(@RequestParam String name) {
		String host = properties.getHost();
		String userName = properties.getUserName();
		String password = properties.getPassword();
		String database = properties.getDatabase();
		String restoreFilePath = BackupConstants.RESTORE_FOLDER + name;
		try {
			mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
		} catch (Exception e) {
			return HttpResult.error(500, e.getMessage());
		}
		return HttpResult.ok();
	}
	
	@ApiOperation(value = "查找所有备份记录")
	@GetMapping(value = "/findRecords")
	public HttpResult findBackupRecords() {
		if(!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()) {
			// 初始默认备份文件
			backup(BackupConstants.DEFAULT_BACKUP_NAME);
		}
		List<Map<String, String>> backupRecords = new ArrayList<>();
		File restoreFloderFile = new File(BackupConstants.RESTORE_FOLDER);
		if(restoreFloderFile.exists()) {
			for(File file: restoreFloderFile.listFiles()) {
				Map<String, String> backup = new HashMap<String, String>();
				backup.put("name", file.getName());
				backup.put("title", file.getName());
				if(BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())) {
					backup.put("title", "系统默认备份");
				}
				backupRecords.add(backup);
			}
		}
		// 排序，默认备份在最前，然后按时间戳排序，新备份在前面
		backupRecords.sort((o1,o2) -> BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o1.get("name")) ? -1:BackupConstants.DEFAULT_BACKUP_NAME.equals(o2.get("name")) ? 1: o2.get("name").compareTo(o1.get("name")));
		return HttpResult.ok(backupRecords);
	}
	
	@ApiOperation(value = "删除备份记录")
	@GetMapping(value = "/delete")
	public HttpResult deleteBackupRecords(@RequestParam String name) {
		if(BackupConstants.DEFAULT_BACKUP_NAME.equals(name)) {
			return HttpResult.error("系统默认备份无法删除!");
		}
		String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
		try {
			FileUtils.deleteFile(new File(restoreFilePath));
		} catch (Exception e) {
			return HttpResult.error(500, e.getMessage());
		}
		return HttpResult.ok();
	}
}
