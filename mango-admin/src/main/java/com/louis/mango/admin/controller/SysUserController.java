package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.service.ISysUserService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Api(tags = {"用户信息接口"})
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
	
	@Autowired
	private ISysUserService sysUserService;
	
	@ApiOperation(value = "查询所有")
	@GetMapping(value = "/findAll")
	public Object findAll() {
		return sysUserService.findAll();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "查询所有2")
	@GetMapping(value = "/findAll2")
	public List findAll2() {
		return sysUserService.findAll2();
	}

	
	@PostMapping(value = "/findPage")
	@ApiOperation(value = "分页查询")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysUserService.findPage(pageRequest));
	}
}
