package com.louis.mango.admin.service.ipml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.dao.SysDeptMapper;
import com.louis.mango.admin.model.SysDept;
import com.louis.mango.admin.service.ISysDeptService;

/**
 * <p>
 * 机构管理 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

	@Override
	public boolean saveDept(SysDept entity) {
		if(entity.getId() == null || entity.getId() == 0) {
			return this.save(entity);
		}
		return this.updateById(entity);
	}

	@Override
	public boolean delete(List<SysDept> records) {
		return this.removeByIds(records);
	}

	@Override
	public List<SysDept> findTree() {
		List<SysDept> depts = this.list();
		/*for (SysDept sysDept : depts) {
			if(sysDept.getParentId() == null) {
				QueryWrapper<SysDept> wrapper = new QueryWrapper<SysDept>();
				wrapper.eq("parent_id", sysDept.getId());
				sysDept.setChildren(this.list(wrapper));
			}
		}*/
		List<SysDept> parents = new ArrayList<SysDept>();
		for (SysDept sysDept : depts) {
			if(sysDept.getParentId() == null || sysDept.getParentId() == 0) {
				parents.add(sysDept);
			}
		}
		findChildren(depts, parents);
		return parents;
	}
	
	public void findChildren(List<SysDept> depts, List<SysDept> parents){
		for (SysDept parent : parents) {
			List<SysDept> childrens = new ArrayList<SysDept>();
			for (SysDept sysDept : depts) {
				if(parent.getId() != null && parent.getId().equals(sysDept.getParentId())){
					sysDept.setParentName(parent.getName());
					childrens.add(sysDept);
				}
			}
			parent.setChildren(childrens);
			findChildren(depts, childrens);
		}
	}

}
