package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class Consule01Application {
	  @Value("${server.port}")
	  private String serverPort;
	  
	   @RequestMapping("/getMember")
	   public String getMember() {
	         return "会员服务prot:"+serverPort;
	   }
	   
	public static void main(String[] args) {
		SpringApplication.run(Consule01Application.class, args);
	}

}
