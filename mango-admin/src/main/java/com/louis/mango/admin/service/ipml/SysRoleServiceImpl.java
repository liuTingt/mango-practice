package com.louis.mango.admin.service.ipml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.dao.SysMenuMapper;
import com.louis.mango.admin.dao.SysRoleMapper;
import com.louis.mango.admin.dao.SysRoleMenuMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.admin.service.ISysRoleService;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public IPage<SysRole> findPage(PageRequest pageRequest) {
		Page<SysRole> page = new Page<SysRole>();
		page.setCurrent(pageRequest.getPageNum());
		page.setSize(pageRequest.getPageSize());
		return this.page(page);
	}

	@Override
	public List<SysMenu> findRoleMenus(Long roleId) {
		SysRole role = this.getById(roleId);
		if(SysConstants.ADMIN.equals(role.getName())) {
			// 管理员返回全部
			return sysMenuMapper.selectList(null);
		}
		return sysMenuMapper.findRoleMenus(roleId);
	}

	@Override
	public boolean saveRoleMenus(List<SysRoleMenu> records) {
		if(null == records || records.isEmpty()) {
			return true;
		}
		sysRoleMenuMapper.deleteByRoleId(records.get(0).getRoleId());
		for (SysRoleMenu sysRoleMenu : records) {
			sysRoleMenuMapper.insert(sysRoleMenu);
		}
		return true;
	}

}
