package com.smartdot.meeting.server.modules.sysrole.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.sysgroup.dao.IGroupRoleDao;
import com.smartdot.meeting.server.modules.sysrole.dao.IRolePrivilegeDao;
import com.smartdot.meeting.server.modules.sysrole.dao.ISysRoleDao;
import com.smartdot.meeting.server.modules.sysrole.service.ISysRoleService;
import com.smartdot.meeting.server.common.entity.RolePrivilege;
import com.smartdot.meeting.server.common.entity.SysRole;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ISysRoleService.SERVICE_NAME)
public class SysRoleServiceImpl implements ISysRoleService {
	
	@Resource(name = ISysRoleDao.DAO_NAME)
	private ISysRoleDao sysRoleDao;
	@Resource(name = IRolePrivilegeDao.DAO_NAME)
	private IRolePrivilegeDao rolePrivilegeDao;
	@Resource(name = IGroupRoleDao.DAO_NAME)
	private IGroupRoleDao groupRoleDao;

	@Override
	public List<SysRole> findAll() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
		dc.add(Restrictions.eq("enable", true));
		return sysRoleDao.findByDetachedCriteria(dc);
	}

	@Override
	public SysRole getSysRoleById(String id) {
		
		return sysRoleDao.findById(id);
	}

	@Audit(module= "权限管理",action= "角色添加",description= "/角色添加说明")
	@Override
	public boolean save(SysRole sysRole) {
		boolean flag = false;
		if (sysRole != null) {
			sysRoleDao.save(sysRole);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<SysRole> sysRoleList) {
		boolean flag = false;
		if (sysRoleList != null) {
			sysRoleDao.saveAll(sysRoleList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "权限管理",action= "角色修改",description= "/角色修改说明")
	@Override
	public boolean updateSysRole(SysRole sysRole) {
		boolean flag = false;
		if (sysRole != null) {
			sysRoleDao.update(sysRole);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SysRole sysRole) {
		boolean flag = false;
		if (sysRole != null) {
			SysRole instance = this.getSysRoleById(sysRole.getId());
			sysRoleDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "权限管理",action= "角色多选删除",description= "/角色多选删除说明")
	@Override
	public boolean deleteAll(String[] ids) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				flag = deleteById(id);
			}
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> findSysRoleByPage(SysRole sysRole,Page<SysRole> pageQuery) {
		Page<SysRole> page = new Page<SysRole>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from SysRole obj where enalbe = true ");
		Object[] params = paramList.toArray();
		return sysRoleDao.pageQueryByHql(page,"select count(obj) " + hql.toString(), hql.toString(), params,params);
	}

	@Audit(module= "权限管理",action= "角色删除",description= "/角色删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (StringUtils.isNotBlank(id)) {
			SysRole sysRole = sysRoleDao.get(id);
			if(null != sysRole){
				sysRole.setEnable(false);
				sysRoleDao.update(sysRole);
				//
				rolePrivilegeDao.deleteRolePrivilegeByRoleId(id);
				//
				groupRoleDao.deleteByRoleId(sysRole.getId());
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public List<SysRole> findByHQLAndParams(SysRole sysRole) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<SysRole> sList = sysRoleDao.findByHQLAndParams("from SysRole where enalbe = true","");
		return sList;
	}

	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysRole> page) {
		
		return sysRoleDao.pagedQuery(detachedCriteria, page);
	}

	@Override
	public List<RolePrivilege> getListPrivilegesByRoleId(String roleId) {
		
		return rolePrivilegeDao.getListPrivilegesByRoleId(roleId);
	}

	@Audit(module= "权限管理",action= "角色授权",description= "/角色授权说明")
	@Override
	public boolean saveRolePrivilege(RolePrivilege rolePrivilege) {
		boolean flag = false;
		if (rolePrivilege != null) {
			rolePrivilegeDao.save(rolePrivilege);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteRolePrivilegeByRoleId(String id) {
		boolean flag = false;
		if (StringUtils.isNotBlank(id)) {
			rolePrivilegeDao.deleteRolePrivilegeByRoleId(id);
			flag = true;
		}
		return flag;
	}
	
}
