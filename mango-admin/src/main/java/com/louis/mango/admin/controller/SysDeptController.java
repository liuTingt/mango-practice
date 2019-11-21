package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.SysDept;
import com.louis.mango.admin.service.ISysDeptService;
import com.louis.mango.core.http.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 机构管理 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Api(value = "ddd", tags = {"机构管理接口"})
@RestController
@RequestMapping("/sys-dept")
public class SysDeptController {

	@Autowired
	private ISysDeptService sysDeptService;
	
	@PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
	@ApiOperation(value = "保存机构")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysDept entity) {
		return HttpResult.ok(sysDeptService.save(entity));
	}
	
	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@ApiOperation(value = "删除机构")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysDept> records) {
		return HttpResult.ok();
	}
	
	@PreAuthorize("hasAuthority('sys:dept:view')")
	@ApiOperation(value = "机构树")
	@PostMapping(value = "/findTree")
	public HttpResult findTree() {
		return HttpResult.ok(sysDeptService.findTree());
	}
}
