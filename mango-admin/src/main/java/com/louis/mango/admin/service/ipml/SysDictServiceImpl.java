package com.louis.mango.admin.service.ipml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.dao.SysDictMapper;
import com.louis.mango.admin.model.SysDict;
import com.louis.mango.admin.service.ISysDictService;
import com.louis.mango.core.page.MyBatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;
	
	
	@Override
	public List<SysDict> findByLabel(String label) {
		return sysDictMapper.findByLabel(label);
	}


	@Override
	public PageResult findPage(PageRequest pageRequest) {
		Object label = pageRequest.getParam("label");
		if(label != null) {
			return MyBatisPageHelper.findPage(pageRequest, sysDictMapper, "findPageByLabel", label);
		}
		
		return MyBatisPageHelper.findPage(pageRequest, sysDictMapper);
	}


	@Override
	public IPage<SysDict> page1(PageRequest pageRequest) {
		Page<SysDict> page = new Page<SysDict>();
		QueryWrapper<SysDict> wapper = new QueryWrapper<SysDict>();
		Object label = pageRequest.getParam("label");
		if(label != null) {
			wapper.eq("label", label);
		}
		// this.page(page);
		return this.page(page, wapper);
		//return sysDictMapper.selectPage(page, wapper);
	}

	@Override
	public IPage<SysDict> page2(PageRequest pageRequest) {
		Page<SysDict> page = new Page<SysDict>();
		QueryWrapper<SysDict> wapper = new QueryWrapper<SysDict>();
		Object label = pageRequest.getParam("label");
		if(label != null) {
			wapper.eq("label", label);
		}
		// this.page(page);
		return sysDictMapper.myPage(page, label.toString());
	}


	@Override
	public Page<SysDict> test(PageRequest pageRequest) {
		Page<SysDict> page = new Page<SysDict>();
		page.setRecords(sysDictMapper.test(page));
		return page;
	}
}
