package com.louis.mango.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysMenu;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysMenuService extends IService<SysMenu> {

	/***
	 * 
	 * @Description 
	 *	根据用户名查询权限
	 * @param userName
	 * @return
	 */
	List<SysMenu> findByUser(String userName);
	
	/**
	 * 
	 * @Description 
	 *	保存或修改菜单
	 * @param entity
	 * @return
	 */
	boolean saveOrUpdates(SysMenu entity);
	
	/***
	 * 
	 * @Description 
	 *	批量删除
	 * @param records
	 * @return
	 */
	boolean deletes(List<SysMenu> records);
	
	/**
	 * 
	 * @Description 
	 *	查找菜单树
	 * @param userName
	 * @param menuType
	 * @return
	 */
	List<SysMenu> findTree(String userName, int menuType);
	
}
