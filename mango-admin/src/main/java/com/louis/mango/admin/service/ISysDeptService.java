package com.louis.mango.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.mango.admin.model.SysDept;

/**
 * <p>
 * 机构管理 服务类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface ISysDeptService extends IService<SysDept> {

	/***
	 * 
	 * @Description 
	 *	保存机构
	 * @param entity
	 * @return
	 */
	boolean saveDept(SysDept entity);
	
	/***
	 * 
	 * @Description 
	 *	批量删除
	 * @param records
	 * @return
	 */
	boolean delete(List<SysDept> records);
	
	/**
	 * 
	 * @Description 
	 *	机构树
	 * @return
	 */
	List<SysDept> findTree();
}
