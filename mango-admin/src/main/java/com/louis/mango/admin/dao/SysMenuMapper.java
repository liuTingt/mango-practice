package com.louis.mango.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.mango.admin.model.SysMenu;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 
	 * @Description 
	 *	根据用户名查找权限
	 * @param userName
	 * @return
	 */
	List<SysMenu> findByUserName(@Param("userName") String userName);
	
	/***
	 * 
	 * @Description 
	 *	查询角色菜单
	 * @param roleId
	 * @return
	 */
	List<SysMenu> findRoleMenus(@Param("roleId") Long roleId);
}
