package com.louis.mango.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysLoginLog;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 系统登录日志 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysLoginLogService extends IService<SysLoginLog> {

	/***
	 * 
	 * @Description 
	 *	分页查询
	 * @param pageRequest
	 * @return
	 */
	IPage<SysLoginLog> findPage(PageRequest pageRequest);
}
