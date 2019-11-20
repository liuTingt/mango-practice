package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.admin.service.ISysRoleService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Api(tags = {"角色管理接口"})
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

	@Autowired
	private ISysRoleService sysRoleService; 
	
	@ApiOperation(value = "保存角色")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysRole entity) {
		return HttpResult.ok(sysRoleService.saveOrUpdate(entity));
	}
	
	@ApiOperation(value = "删除角色")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysRole> records) {
		return HttpResult.ok(sysRoleService.removeByIds(records));
	}
	
	@ApiOperation(value = "分页查询")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysRoleService.findPage(pageRequest));
	}
	
	@ApiOperation(value = "查询全部")
	@PostMapping(value = "/findAll")
	public HttpResult findAll() {
		return HttpResult.ok(sysRoleService.list());
	}
	
	@ApiOperation(value = "查询角色菜单")
	@PostMapping(value = "/findRoleMenus")
	public HttpResult findRoleMenus(@RequestParam Long roleId) {
		return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
	}
	
	@ApiOperation(value = "保存角色菜单")
	@PostMapping(value = "/saveRoleMenus")
	public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
		return HttpResult.ok(sysRoleService.saveRoleMenus(records));
	}
}
