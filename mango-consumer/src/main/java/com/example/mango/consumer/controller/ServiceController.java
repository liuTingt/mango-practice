package com.example.mango.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @Description 
 *	服务消费
 * @author lt
 *
 */
@RestController
public class ServiceController {

	@Autowired
	private  LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	
	/***
	 * 
	 * @Description 
	 *	获取所有服务
	 * @return
	 */
	@RequestMapping(value = "/services")
	public Object services() {
		List<String> services = discoveryClient.getServices();
		for (String string : services) {
			System.out.println("service: " + string);
		}
		return discoveryClient.getInstances("mango-producer");
	}
	
	
	/***
	 * 
	 * @Description 
	 *	从所有服务中选择一个服务（轮询的方式）
	 * @return
	 */
	@RequestMapping(value = "/discover")
	public Object discover() {
		return loadBalancerClient.choose("mango-producer").getUri().toString();
	}
}
