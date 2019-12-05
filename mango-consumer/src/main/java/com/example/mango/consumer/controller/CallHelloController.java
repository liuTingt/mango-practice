package com.example.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/***
 * 
 * @Description 
 *	使用负载均衡的形式获取服务端提供的服务
 * @author lt
 *
 */
@RestController
public class CallHelloController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	
	/***
	 * 
	 * @Description 
	 *	使用RestTempleate进行远程调用，采用的还是轮询方式
	 *	LoadBalancerClient为负载均衡器，采用的负载均衡策略是轮询
	 * @return
	 */
	@RequestMapping(value = "/call")
	public String call() {
		// 查找服务 -轮询
		ServiceInstance serviceInstance = loadBalancerClient.choose("mango-producer");
		System.out.println("服务名称：" + serviceInstance.getServiceId());
		System.out.println("服务地址：" + serviceInstance.getUri());
		// 调用服务。通过RestTemplate远程调用
		String serviceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
		System.out.println(serviceResult);
		return serviceResult;
	}
}
