package com.example.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mango.consumer.service.MangoProducerService;

/***
 * 
 * @Description 
 *	注入MangoProducerService。就可以像使用本地方法一样进行调用了
 *
 *	Feign是声明式调用，会产生一些相关的Feign定义接口，所以建议将Feign定义的接口都统一防止管理，以区别内部服务
 * @author lt
 *
 */
@RestController
public class FeignHelloController {

	@Autowired
	private MangoProducerService mangoProducerService;
	
	
	@RequestMapping(value = "/feign/call")
	public String call() {
		// 像调用本地服务一样
		return mangoProducerService.hello();
	}
	
	
}
