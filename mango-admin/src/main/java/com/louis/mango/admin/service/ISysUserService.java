package com.louis.mango.admin.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.service.CurdService;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysUserService extends IService<SysUser> , CurdService<SysUser>{

	/***
	 * 
	 * @Description 
	 *	使用sql语句拼接
	 * @return
	 */
	List<SysUser> findAll();
	
	
	
	/***
	 * 
	 * @Description 
	 *	使用MyBatis plus
	 * @return
	 */
	List<SysUser> findAll2();
	
	/****
	 * 
	 * @Description 
	 *	根据名称获取用户
	 * @param userName
	 * @return
	 */
	SysUser getByName(String userName);
	
	
	/***
	 * 
	 * @Description 
	 *	MyBatis Plus 分页查询
	 * @param pageRequest
	 * @return
	 */
	IPage<SysUser> getPage(PageRequest pageRequest);
	
	
	/***
	 * 
	 * @Description 
	 *	业务保存用户
	 * @param entity
	 * @return
	 */
	boolean saveUser(SysUser entity);
	
	/**
	 * 
	 * @Description 
	 *	根据名称查询用户
	 * @param name
	 * @return
	 */
	SysUser findByName(String name);
	
	/***
	 * 
	 * @Description 
	 *	根据名称查询用户权限
	 * @param name
	 * @return
	 */
	Set<String> findPermissions(String userName);
	
	/****
	 * 
	 * @Description 
	 *	根据用户id查找角色
	 * @param userId
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);
	
	/***
	 * 
	 * @Description 
	 *	生成文件
	 * @param pageRequest
	 * @return
	 */
	File createUserExcelFile(PageRequest pageRequest);
	
	
	/****
	 * 
	 * @Description 
	 *	查询扩展后的用户信息
	 * @param pageRequest
	 * @return
	 */
	IPage<SysUser> getExtendPage(PageRequest pageRequest);
	
}
