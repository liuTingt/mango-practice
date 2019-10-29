package com.louis.mango.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MangoCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoCoreApplication.class, args);
	}

}
