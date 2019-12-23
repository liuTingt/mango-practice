package com.example.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @Description 
 *	配置中心控制器
 * @author lt
 *
 */
@RefreshScope
@RestController
public class SpringConfigController {

	@Value("${hello}")
	private String hello;
	
	@RequestMapping(value = "/hello")
	public String from() {
		return this.hello;
	}
}
