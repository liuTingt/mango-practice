package com.louis.mango.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysLog;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 系统操作日志 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysLogService extends IService<SysLog> {

	/***
	 * 
	 * @Description 
	 *	分页查询
	 * @param pageRequest
	 * @return
	 */
	IPage<SysLog> findPage(PageRequest pageRequest);
}
