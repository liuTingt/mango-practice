package com.example.mango.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/***
 * 
 * @Description 
 *	@EnableFeignClients 注解，开启扫描Spring Cloud Feign客户端的功能
 * @author lt
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MangoConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoConsumerApplication.class, args);
	}
	
	@Bean
	@LoadBalanced // 用于拦截请求
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	/**
	 * 
	 * @Description 
	 *	此配置是为了服务监控而配置，与服务容错本身无关
	 *	ServletRegistrationBean因为springboot的默认路径不是“/hystrix.stream”
	 *	只要在自己的项目里配置下面的servlet就可以了
	 * @return
	 */
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricasStreamServlet");
		return registrationBean;
	}
}
