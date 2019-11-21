package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.admin.service.ISysConfigService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 系统配置表 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Api(tags = {"系统配置表接口"})
@RestController
@RequestMapping("/sys-config")
public class SysConfigController {

	@Autowired
	private ISysConfigService sysConfigService;
	
	@PreAuthorize("hasAuthority('sys:config:add') AND hasAuthority('sys:user:edit')")
	@ApiOperation(value = "保存配置")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysConfig entity) {
		return HttpResult.ok(sysConfigService.saveOrUpdate(entity));
	}
	
	@PreAuthorize("hasAuthority('sys:config:delete')")
	@ApiOperation(value = "删除字典")
	@PostMapping(value = "/delete")
	public HttpResult save(@RequestBody List<SysConfig> records) {
		return HttpResult.ok(sysConfigService.removeByIds(records));
	} 
	
	@PreAuthorize("hasAuthority('sys:config:view')")
	@ApiOperation(value = "分页查询")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysConfigService.findPage(pageRequest));
	} 
	
	@PreAuthorize("hasAuthority('sys:config:view')")
	@ApiOperation(value = "根据标签查询")
	@PostMapping(value = "/findByLabel")
	public HttpResult findByLabel(@RequestParam String label) {
		return HttpResult.ok(sysConfigService.findByLabel(label));
	} 
}
