package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.admin.service.ISysLoginLogService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 系统登录日志 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Api(tags = {"系统登录日志"})
@RestController
@RequestMapping("/sys-login-log")
public class SysLoginLogController {

	@Autowired
	private ISysLoginLogService sysLoginLogService;
	
	@PreAuthorize("hasAuthority('sys:loginlog:view')")
	@ApiOperation(value = "分页查询")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysLoginLogService.findPage(pageRequest));
	} 
	
	@PreAuthorize("hasAuthority('sys:loginlog:delete')")
	@ApiOperation(value = "删除操作日志")
	@PostMapping(value = "/delete")
	public HttpResult save(@RequestBody List<SysConfig> records) {
		return HttpResult.ok(sysLoginLogService.removeByIds(records));
	} 
}
