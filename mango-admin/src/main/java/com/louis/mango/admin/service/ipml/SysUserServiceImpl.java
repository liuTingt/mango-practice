package com.louis.mango.admin.service.ipml;

import com.louis.mango.admin.dao.SysUserMapper;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Override
	public List<SysUser> findAll() {
		return sysUserMapper.findAll();
	}

	@Override
	public List<SysUser> findAll2() {
		List<SysUser> list = sysUserMapper.selectList(null);
		for (SysUser sysUser : list) {
			System.out.println(sysUser.getName());
		}
		return list;
	}

	

}
