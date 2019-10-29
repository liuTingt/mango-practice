package com.louis.mango.core.page;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.mango.common.utils.ReflectionUtils;

/***
 * 
 * @Description 
 *	对MyBatis的分页查询业务代码进行统一封装
 * @author lt
 *
 */
public class MyBatisPageHelper {

	public static final String findPage = "findPage";
	
	/***
	 * 
	 * @Description 
	 *	分页查询，约定查询方法名称为“findPage”
	 * @param request 分页请求
	 * @param mapper DAO对象，Mybatis的Mapper
	 * @return 
	 */
	public static PageResult findPage(PageRequest request, Object mapper) {
		return findPage(request, mapper, findPage);
	}
	
	
	/**
	 * 
	 * @Description 
	 *	调用分页插件进行分页查询
	 * @param request 分页请求
	 * @param mapper mapper DAO对象，Mybatis的mapper
	 * @param queryMethodName 要分页的查询方法
	 * @param args 方法参数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static PageResult findPage(PageRequest request, Object mapper, String queryMethodName, Object... args) {
		// 设置分页
		int pageNum = request.getPageNum();
		int pageSize = request.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		// 利用反射调用查询方法
		Object result = ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(request, new PageInfo((List)result));
	}
	
	
	/***
	 * 
	 * @Description 
	 *	将分页信息封装到统一的接口
	 * @param pageRequest
	 * @param pageInfo
	 * @return
	 */
	public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
		PageResult pageResult = new PageResult();
		pageResult.setPageNum(pageInfo.getPageNum());
		pageResult.setPageSize(pageInfo.getPageSize());
		pageResult.setTotalSize(pageInfo.getTotal());
		pageResult.setTotalPages(pageInfo.getPages());
		pageResult.setContent(pageInfo.getList());
		return pageResult;
	}
	
}
