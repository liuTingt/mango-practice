package com.louis.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.service.ISysMenuService;
import com.louis.mango.core.http.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@RestController
@Api(tags = {"菜单管理接口"})
@RequestMapping("/sys-menu")
public class SysMenuController {

	@Autowired
	private ISysMenuService sysMenuService;
	
	@ApiOperation(value = "保存菜单")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysMenu entity) {
		//return HttpResult.ok(sysMenuService.saveOrUpdate(entity));
		return HttpResult.ok(sysMenuService.saveOrUpdates(entity));
	}
	
	@ApiOperation(value = "删除菜单")
	@PostMapping(value = "/delete")
	public HttpResult save(@RequestBody List<SysMenu> records) {
		return HttpResult.ok(sysMenuService.deletes(records));
	} 
	
	@ApiOperation(value = "查询导航菜单树")
	@PostMapping(value = "/findNavTree")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(sysMenuService.findTree(userName, 1));
	} 
	
	@ApiOperation(value = "查询菜单树")
	@PostMapping(value = "/findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(sysMenuService.findTree(null, 0));
	} 
	
	
}
