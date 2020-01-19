package com.yfzx.collect_demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@PostMapping(value = "/test01")
	public String test01(HttpServletRequest request) {
		System.out.println("test01:"+request.getSession().getId());
		return request.getSession().getId();
	}
	
	@GetMapping(value = "/test02")
	public String test02(HttpServletRequest request) {
		System.out.println("test02"+request.getSession().getId());
		return request.getSession().getId();
	}
	
}
