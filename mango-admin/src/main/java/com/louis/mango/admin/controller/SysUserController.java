package com.louis.mango.admin.controller;


import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.ISysUserService;
import com.louis.mango.admin.utils.PasswordUtils;
import com.louis.mango.common.utils.FileUtils;
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
	@ApiOperation(value = "分页查询,系统封装的分页查询方法")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysUserService.findPage(pageRequest));
	}
	
	@ApiOperation(value = "Mybatis Plus 分页查询")
	@PostMapping(value = "/getPage")
	public HttpResult getPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysUserService.getPage(pageRequest));
	}
	
	@ApiOperation(value = "保存用户")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysUser record) {
		SysUser user = sysUserService.getById(record.getId());
		if(user != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
				return HttpResult.error("超级管理员不允许修改");
			}
		}
		
		if(record.getPassword() != null) {
			String salt = PasswordUtils.getSalt();
			if(user == null) {// 新增用户
				if(sysUserService.getByName(record.getName()) != null) {
					return HttpResult.error("用户名不能重复");
				}
				String encPass = PasswordUtils.encode(record.getPassword(), salt);
				record.setSalt(salt);
				record.setPassword(encPass);
				//
			}else { // 修改用户
				if(!record.getPassword().equals(user.getPassword())) { // 修改了密码
					String password = PasswordUtils.encode(record.getPassword(), salt);
					record.setSalt(salt);
					record.setPassword(password);
					//
				}
			}
		}
		return HttpResult.ok(sysUserService.saveUser(record));
	}
	
	
	
	@ApiOperation(value = "删除用户")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysUser> records) {
		for (SysUser record : records) {
			SysUser sysUser = sysUserService.getById(record.getId());
			if(sysUser != null && sysUser.getName().equals(SysConstants.ADMIN)) {
				return HttpResult.error("超级管理员不允许删除!");
			}
		}
		return HttpResult.ok(sysUserService.removeByIds(records));
	}
	
	@ApiOperation(value = "根据名称查询用户")
	@PostMapping(value = "/findByName")
	public HttpResult findByName(@RequestParam String name) {
		return HttpResult.ok(sysUserService.findByName(name));
	}
	
	
	@ApiOperation(value = "查询用户权限")
	@PostMapping(value = "/findPermissions")
	public HttpResult findPermissions(@RequestParam String name) {
		return HttpResult.ok(sysUserService.findPermissions(name));
	}
	
	
	@ApiOperation(value = "查询用户角色")
	@PostMapping(value = "/findUserRoles")
	public HttpResult findUserRoles(@RequestParam Long userId) {
		return HttpResult.ok(sysUserService.findUserRoles(userId));
	}
	
	
	/***
	 * 
	 * @Description 
	 *	下载文件
	 * @param pageRequest
	 * @param response
	 */
	@ApiOperation(value = "下载文件")
	@PostMapping(value = "/exportExcelUser")
	public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse response) {
		File file = sysUserService.createUserExcelFile(pageRequest);
		FileUtils.downloadFile(response, file, file.getName());
	}
	
	
	@ApiOperation(value = "关联查询用户信息")
	@PostMapping(value = "/extendUserPage")
	public HttpResult extendUserPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysUserService.getExtendPage(pageRequest));
	}
}
