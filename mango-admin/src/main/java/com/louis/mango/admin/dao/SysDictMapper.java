package com.louis.mango.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.louis.mango.admin.model.SysDict;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

	List<SysDict> findPage();
	
	List<SysDict> findByLabel(@Param(value ="label") String label);
	
	List<SysDict> findPageByLabel(@Param(value="label") String label);
	
	/**
	 * 
	 * @Description 
	 *	mybatis plus 分页
	 * @param page
	 * @param label
	 * @return
	 */
	IPage<SysDict> myPage(Page<SysDict> page, @Param("label") String label);
	
	@Select("select * from sys_dict")
	List<SysDict> test(Page<SysDict> page);
}
