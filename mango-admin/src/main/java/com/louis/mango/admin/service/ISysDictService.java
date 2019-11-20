package com.louis.mango.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysDict;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.service.CurdService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysDictService extends IService<SysDict>, CurdService<SysDict> {

	List<SysDict> findByLabel(String label);
	
	/****
	 * 
	 * @Description 
	 *	mybatis plus 分页插件
	 * @return
	 */
	IPage<SysDict> page1(PageRequest pageRequest);

	/***
	 * 
	 * @Description 
	 *	编写SQL语句 分页
	 * @param pageRequest
	 * @return
	 */
	IPage<SysDict> page2(PageRequest pageRequest);
	
	/**
	 * 
	 * @Description 
	 *	测试@Select和多表查询
	 * @param pageRequest
	 * @return
	 */
	IPage<SysDict> test(PageRequest pageRequest);
}
