package com.example.mango.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MangoProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoProducerApplication.class, args);
	}

}
