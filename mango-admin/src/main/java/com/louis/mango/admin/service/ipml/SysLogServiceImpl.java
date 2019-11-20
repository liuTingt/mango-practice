package com.louis.mango.admin.service.ipml;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.dao.SysLogMapper;
import com.louis.mango.admin.model.SysLog;
import com.louis.mango.admin.service.ISysLogService;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	@Override
	public IPage<SysLog> findPage(PageRequest pageRequest) {
		Page<SysLog> page = new Page<SysLog>();
		page.setCurrent(pageRequest.getPageNum());
		page.setSize(pageRequest.getPageSize());
		return this.page(page, null);
	}

}
