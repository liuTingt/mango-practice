package com.louis.mango.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard // 开启熔断监控支持
public class MangoHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoHystrixApplication.class, args);
	}

}
