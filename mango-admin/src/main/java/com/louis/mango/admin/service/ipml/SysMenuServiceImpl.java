package com.louis.mango.admin.service.ipml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.dao.SysMenuMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.service.ISysMenuService;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public List<SysMenu> findByUser(String userName) {
		if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
			return sysMenuMapper.selectList(null);
		}
		return sysMenuMapper.findByUserName(userName);
	}

	@Override
	public boolean saveOrUpdates(SysMenu entity) {
		if(entity.getId() == null || entity.getId() == 0) {
			return this.save(entity);
		}
		return this.updateById(entity);
	}

	@Override
	public boolean deletes(List<SysMenu> records) {
		for (SysMenu sysMenu : records) {
			this.removeById(sysMenu.getId());
		}
		return true;
	}

	@Override
	public List<SysMenu> findTree(String userName, int menuType) {
		List<SysMenu> results = new ArrayList<SysMenu>();
		List<SysMenu> menus = this.findByUser(userName);
		for (SysMenu sysMenu : menus) {
			if(sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
				results.add(sysMenu);
			}
		}
		results.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(results, menus, menuType);
		return null;
	}

	public void findChildren(List<SysMenu> results, List<SysMenu> menus, int menuType) {
		for (SysMenu parent : results) {
			List<SysMenu> children = new ArrayList<SysMenu>();
			for (SysMenu menu : menus) {
				// ？？？？？？？？？？  怎么能同时即等于1又等于2呢？？？
				if(menuType == 1 && menuType == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue;
				}
				
				if(menu.getId() != null && menu.getParentId().equals(parent.getId())) {
					menu.setParentName(parent.getName());
					children.add(menu);
				}
			}
			parent.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}
	
	
	
}
