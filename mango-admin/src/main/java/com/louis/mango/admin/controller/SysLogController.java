package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.SysLog;
import com.louis.mango.admin.service.ISysLogService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 系统操作日志 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Api(tags = {"系统操作日志"})
@RestController
@RequestMapping("/sys-log")
public class SysLogController {

	@Autowired
	private ISysLogService sysLogService;
	
	@PreAuthorize("hasAuthority('sys:log:view')")
	@ApiOperation(value = "分页查询")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysLogService.findPage(pageRequest));
	} 
	
	@PreAuthorize("hasAuthority('sys:log:delete')")
	@ApiOperation(value = "删除操作日志")
	@PostMapping(value = "/delete")
	public HttpResult save(@RequestBody List<SysLog> records) {
		return HttpResult.ok(sysLogService.removeByIds(records));
	} 
}
