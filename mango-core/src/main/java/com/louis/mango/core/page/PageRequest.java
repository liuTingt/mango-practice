package com.louis.mango.core.page;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * 
 * @Description 
 *	对分页请求的参数进行封装，传入分页查询的页码和数量即可
 * @author lt
 *
 */
@Data
public class PageRequest {

	/**
	 * 当前页码
	 */
	private int pageNum = -1;
	
	/**
	 * 每页数量
	 */
	private int pageSize = 10;
	
	/**
	 * 查询参数
	 */
	private Map<String, Object> params = new HashMap<>();
}
