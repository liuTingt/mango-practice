package com.louis.mango.core.service;

import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

/**
 * 
 * @Description 
 *	通用业务接口定义，但其实MyBatis plus已经封装了很多通用的方法，在此只封装分页方法
 * @author lt
 *
 */
public interface CurdService<T> {

	/**
	 * 
	 * @Description 
	 *	保存
	 * @param record
	 * @return
	 */
	//int save(T record);
	
	/***
	 * 
	 * @Description 
	 *	删除
	 * @param record
	 * @return
	 */
	//int delete(T record);
	
	/***
	 * 
	 * @Description 
	 *	批量删除
	 * @param records
	 * @return
	 */
	//int delete(List<T> records);
	
	/****
	 * 
	 * @Description 
	 * 
	 * @param id
	 * @return
	 */
	//T findById(Long id);
	
	/**
	 * 
	 * @Description 
	 * 分页查询
	 * @param pageRequest
	 * @return
	 */
	PageResult  findPage(PageRequest pageRequest);
}
