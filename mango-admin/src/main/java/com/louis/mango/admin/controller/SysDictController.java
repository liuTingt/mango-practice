package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.SysDict;
import com.louis.mango.admin.service.ISysDictService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Api(tags = {"字典接口"})
@RestController
@RequestMapping("/sys-dict")
public class SysDictController {
	
	
	@Autowired
	private ISysDictService sysDictService;
	
	
	@PostMapping(value = "/page")
	@ApiOperation(value = "Mybatis plus自带分页 使用条件")
	public HttpResult page(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysDictService.page1(pageRequest));
	}
	
	@PostMapping(value = "/page2")
	@ApiOperation(value = "Mybatis plus自带分页 执行sql语句")
	public HttpResult page2(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysDictService.page1(pageRequest));
	}
	
	@PostMapping(value = "/test")
	@ApiOperation(value = "测试@Select 多表查询")
	public HttpResult test(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysDictService.test(pageRequest));
	}
	
	//------------
	@PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
	@PostMapping(value = "/save")
	@ApiOperation(value = "保存")
	public HttpResult save(@RequestBody SysDict entity) {
		return HttpResult.ok(sysDictService.saveOrUpdate(entity));
	}
	
	@PreAuthorize("hasAuthority('sys:dict:delete')")
	@PostMapping(value = "/delete")
	@ApiOperation(value = "删除")
	public HttpResult delete(@RequestBody List<SysDict> records) {
		return HttpResult.ok(sysDictService.removeByIds(records));
	}
	
	@PreAuthorize("hasAuthority('sys:dict:view')")
	@PostMapping(value = "/findPage")
	@ApiOperation(value = "按照参数label分页查询,系统封装分页方法")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		
		return HttpResult.ok(sysDictService.findPage(pageRequest));
	}
	
	@PreAuthorize("hasAuthority('sys:dict:view')")
	@GetMapping(value = "/findByLabel")
	@ApiOperation(value = "按照参数label查询")
	public HttpResult findByLabel(@RequestParam String label) {
		return HttpResult.ok(sysDictService.findByLabel(label));
	}
}
