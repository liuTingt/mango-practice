package com.louis.mango.admin.service.ipml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mango.admin.dao.SysUserMapper;
import com.louis.mango.admin.dao.SysUserRoleMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.admin.service.ISysMenuService;
import com.louis.mango.admin.service.ISysUserService;
import com.louis.mango.common.utils.DateTimeUtils;
import com.louis.mango.common.utils.PoiUtils;
import com.louis.mango.core.page.MyBatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private ISysMenuService sysMenuService;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	
	@Override
	public List<SysUser> findAll() {
		return sysUserMapper.findAll();
	}

	@Override
	public List<SysUser> findAll2() {
		List<SysUser> list = sysUserMapper.selectList(null);
		for (SysUser sysUser : list) {
			System.out.println(sysUser.getName());
		}
		return list;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MyBatisPageHelper.findPage(pageRequest, sysUserMapper);
	}

	@Override
	public SysUser getByName(String userName) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
		wrapper.like("name", userName);
		return this.getOne(wrapper);
	}

	@Override
	public IPage<SysUser> getPage(PageRequest pageRequest) {
		Page<SysUser> page = new Page<SysUser>();
		page.setSize(pageRequest.getPageSize());
		page.setCurrent(pageRequest.getPageNum());
		QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
		Object name = pageRequest.getParam("name");
		if(name != null) wrapper.like("name", name);
		return this.page(page, wrapper);
	}

	@Override
	public boolean saveUser(SysUser entity) {
		if(entity == null || entity.getId() == 0) {// 新增用户
			this.save(entity);
		}else { // 更新用户
			this.updateById(entity);
		}
		// 更新用户角色。。
		// TODO。。
		
		return true;
	}

	@Override
	public SysUser findByName(String name) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
		if(name != null) wrapper.like("name", name);
		return this.getOne(wrapper);
	}

	@Override
	public Set<String> findPermissions(String userName) {
		Set<String> perms = new HashSet<String>();
		List<SysMenu> menus = sysMenuService.findByUser(userName);
		for (SysMenu sysMenu : menus) {
			if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())){
				perms.add(sysMenu.getPerms());
			}
		}
		return perms;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		QueryWrapper<SysUserRole> wrapper = new QueryWrapper<SysUserRole>();
		wrapper.eq("user_id", userId);
		return sysUserRoleMapper.selectList(wrapper);
	}

	@Override
	public File createUserExcelFile(PageRequest pageRequest) {
		IPage<SysUser> page = getPage(pageRequest);
		return createUserExcelFile(page.getRecords());
	}

	
	public static File createUserExcelFile(List<SysUser> records) {
		if(records == null) records = new ArrayList<SysUser>();
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row0 = sheet.createRow(0);
		int columnIndex = 0;
		row0.createCell(columnIndex).setCellValue("No");
		row0.createCell(++columnIndex).setCellValue("ID");
		row0.createCell(++columnIndex).setCellValue("用户名");
		row0.createCell(++columnIndex).setCellValue("昵称");
		row0.createCell(++columnIndex).setCellValue("机构");
		row0.createCell(++columnIndex).setCellValue("角色");
		row0.createCell(++columnIndex).setCellValue("邮箱");
		row0.createCell(++columnIndex).setCellValue("手机号");
		row0.createCell(++columnIndex).setCellValue("状态");
		row0.createCell(++columnIndex).setCellValue("头像");
		row0.createCell(++columnIndex).setCellValue("创建人");
		row0.createCell(++columnIndex).setCellValue("创建时间");
		row0.createCell(++columnIndex).setCellValue("最后更新人");
		row0.createCell(++columnIndex).setCellValue("最后更新时间");
		for(int i = 0; i < records.size(); i++) {
			SysUser sysUser = records.get(i);
			columnIndex = 0;
			Row row = sheet.createRow(i + 1);
			
			row.createCell(columnIndex).setCellValue(i + 1);
			row.createCell(++columnIndex).setCellValue(sysUser.getId());
			row.createCell(++columnIndex).setCellValue(sysUser.getName());
			row.createCell(++columnIndex).setCellValue(sysUser.getNickName());
			row.createCell(++columnIndex).setCellValue(sysUser.getDeptName());
			row.createCell(++columnIndex).setCellValue(sysUser.getRoleNames());
			row.createCell(++columnIndex).setCellValue(sysUser.getEmail());
			row.createCell(++columnIndex).setCellValue(sysUser.getMobile());
			row.createCell(++columnIndex).setCellValue(sysUser.getStatus());
			row.createCell(++columnIndex).setCellValue(sysUser.getAvatar());
			row.createCell(++columnIndex).setCellValue(sysUser.getCreateBy());
			row.createCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(sysUser.getCreateTime()));
			row.createCell(++columnIndex).setCellValue(sysUser.getLastUpdateBy());
			row.createCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(sysUser.getLastUpdateTime()));
		}
		return PoiUtils.createExcelFile(workbook, "down_user");
	}

	@Override
	public Page<SysUser> getExtendPage(PageRequest pageRequest) {
		Page<SysUser> page = new Page<SysUser>();
		page.setSize(pageRequest.getPageSize());
		page.setCurrent(pageRequest.getPageNum());
		page.setRecords(sysUserMapper.getExtendPage(page));
		return page;
	}
	
}
