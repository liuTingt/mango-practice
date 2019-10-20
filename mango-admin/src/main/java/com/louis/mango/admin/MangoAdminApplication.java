package com.louis.mango.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.louis.mango"})
@MapperScan("com.louis.mango.**.dao")
public class MangoAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoAdminApplication.class, args);
	}

}
