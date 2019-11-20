package com.louis.mango.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MyBatisplusConfig {

	/***
	 * 
	 * @Description  配置分页插件 
	 *
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		log.debug("注册分页插件");
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");// 指定数据库类型
		return new PaginationInterceptor();
	}
	
}
