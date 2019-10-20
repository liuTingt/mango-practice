package com.louis.mango.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = {"测试接口"})
public class HelloController {

	@ApiOperation(value = "hello")
	@GetMapping(value = "/hello")
	public String hello() {
		return "hello mango";
	}
}
