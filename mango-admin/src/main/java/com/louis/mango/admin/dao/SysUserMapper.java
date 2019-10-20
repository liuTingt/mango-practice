package com.louis.mango.admin.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.mango.admin.model.SysUser;

/**
 * <p>
 * 用户管理 Mapper 接口
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 
	 * @Description 
	 *	查询所有
	 * @return
	 */
	List<SysUser> findAll(); 
}
