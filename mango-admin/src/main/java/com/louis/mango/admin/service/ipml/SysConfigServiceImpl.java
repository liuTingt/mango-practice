package com.louis.mango.admin.service.ipml;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.dao.SysConfigMapper;
import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.admin.service.ISysConfigService;
import com.louis.mango.core.page.PageRequest;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-11-20
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

	
	@Override
	public IPage<SysConfig> findPage(PageRequest pageRequest) {
		Page<SysConfig> page = new Page<SysConfig>();
		page.setCurrent(pageRequest.getPageNum());
		page.setSize(pageRequest.getPageSize());
		return this.page(page, null);
	}

	@Override
	public List<SysConfig> findByLabel(String label) {
		QueryWrapper<SysConfig> wrapper = new QueryWrapper<SysConfig>();
		wrapper.like("label", label);
		return this.list(wrapper);
	}

}
