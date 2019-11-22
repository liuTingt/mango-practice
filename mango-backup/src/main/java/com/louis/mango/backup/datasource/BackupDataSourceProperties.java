package com.louis.mango.backup.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * @Description 
 *	数据源
 * @author lt
 *
 */
@Component
@ConfigurationProperties(prefix = "mango.backup.datasource")
@Data
public class BackupDataSourceProperties {

	private String host;
	
	private String userName;
	
	private String password;
	
	private String database;
	
}
