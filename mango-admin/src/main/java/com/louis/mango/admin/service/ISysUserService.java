package com.louis.mango.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysUser;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysUserService extends IService<SysUser> {

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
}
