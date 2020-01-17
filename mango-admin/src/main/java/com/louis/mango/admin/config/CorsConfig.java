package com.louis.mango.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * 
 * @Description 
 *	1、解决swagger2配置后访问报错404问题
 *	因为swagger-ui.html在springfox-swagger-ui.jar下的META-INF下的resources包下，应该是资源路径不对的原因导致了无法访问
 *	
 *	Spring Boot2.*之后实现WebMvcConfigurer类，重写addResourceHandlers方法
 *	Spring Boot2.*之前是继承WebMvcConfigurationSupport，重写addResourceHandlers方法
 *
 *	2、CORS实现跨域
 *	
 *	
 * @author lt
 *
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("*********************************跨域设置被使用**************************");
		registry.addMapping("/**")// 允许跨域访问的路径
		.allowedOrigins("http://localhost:8081")// 允许跨域访问源
		.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")// 允许请求方法
		.allowCredentials(true)// 是否发送cookie
		.allowedHeaders("*")// 允许头部设置
		.maxAge(168000)// 预检请求间隔时间
		;
	}

	/**
	 * 注意：当withCredentials属性设置为true，需要response header中的'Access-Control-Allow-Origin'为一个确定的域名，
	 * 	而不能使用'*'这样的通配
	 */
	
	
}
