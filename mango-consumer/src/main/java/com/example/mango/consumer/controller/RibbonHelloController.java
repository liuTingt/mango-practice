package com.example.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonHelloController {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 
	 * @Description 
	 *	实现ribbon的负载均衡
	 * @return
	 */
	@RequestMapping(value = "/ribbon/call")
	public String call() {
		// 调用服务，mango-producer为注册的服务名称
		// LoadBalancerInterceptor会拦截调用并根据服务名找到对应的服务
		String serviceResult = restTemplate.getForObject("http://mango-producer/hello", String.class);
		return serviceResult;
	}
}
