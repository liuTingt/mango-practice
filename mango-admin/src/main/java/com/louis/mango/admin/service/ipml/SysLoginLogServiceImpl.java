package com.louis.mango.admin.service.ipml;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.dao.SysLoginLogMapper;
import com.louis.mango.admin.model.SysLoginLog;
import com.louis.mango.admin.service.ISysLoginLogService;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 系统登录日志 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

	@Override
	public IPage<SysLoginLog> findPage(PageRequest pageRequest) {
		Page<SysLoginLog> page = new Page<SysLoginLog>();
		page.setCurrent(pageRequest.getPageNum());
		page.setSize(pageRequest.getPageSize());
		return this.page(page, null);
	}

}
