package com.example.mango.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mango.consumer.callback.MangoProducerHystrix;

/**
 * 
 * @Description 
 *	添加跟调用目标方法一样的方法声明，只需要方法声明，不需要具体实现，注意跟目标方法定义保持一致
 *
 *	@FeignClient(name =“调用的服务名称”)
 * @author lt
 *
 */
@FeignClient(name = "mango-producer", fallback = MangoProducerHystrix.class)
public interface MangoProducerService {
	
	@RequestMapping(value = "/hello")
	public String hello();

}
