package com.louis.mango.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 角色管理 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysRoleService extends IService<SysRole> {

	/***
	 * 
	 * @Description 
	 *	分页查询
	 * @param pageRequest
	 * @return
	 */
	IPage<SysRole> findPage(PageRequest pageRequest);
	
	/***
	 * 
	 * @Description 
	 *	查询角色菜单
	 * @param roleId
	 * @return
	 */
	List<SysMenu> findRoleMenus(Long roleId);
	
	/***
	 * 
	 * @Description 
	 *	保存角色菜单
	 * @param records
	 * @return
	 */
	boolean saveRoleMenus(List<SysRoleMenu> records);
}
