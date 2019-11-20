package com.louis.mango.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-11-20
 */
public interface ISysConfigService extends IService<SysConfig> {

	/***
	 * 
	 * @Description 
	 *	分页查询
	 * @param pageRequest
	 * @return
	 */
	IPage<SysConfig> findPage(PageRequest pageRequest);
	
	/***
	 * 
	 * @Description 
	 *	根据标签查询
	 * @param label
	 * @return
	 */
	List<SysConfig> findByLabel(String label);
}
