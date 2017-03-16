package com.smartdot.meeting.server.modules.sysgroup.service.impl;


import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.sysgroup.dao.IGroupRoleDao;
import com.smartdot.meeting.server.modules.sysgroup.dao.ISysGroupDao;
import com.smartdot.meeting.server.modules.sysgroup.service.ISysGroupService;
import com.smartdot.meeting.server.common.entity.GroupRole;
import com.smartdot.meeting.server.common.entity.SysGroup;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ISysGroupService.SERVICE_NAME)
public class SysGroupServiceImpl implements ISysGroupService {
	
	@Resource(name = ISysGroupDao.DAO_NAME)
	private ISysGroupDao sysGroupDao;
	@Resource(name = IGroupRoleDao.DAO_NAME)
	private IGroupRoleDao groupRoleDao;

	
	@Override
	public List<SysGroup> findAll() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		dc.add(Restrictions.eq("enable", true));
		return sysGroupDao.findByDetachedCriteria(dc);
	}

	@Override
	public SysGroup getSysGroupById(String id) {
		
		return sysGroupDao.findById(id);
	}

	@Audit(module= "权限管理",action= "分组添加",description= "/分组添加说明")
	@Override
	public boolean save(SysGroup sysGroup) {
		boolean flag = false;
		if (sysGroup != null) {
			sysGroupDao.save(sysGroup);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<SysGroup> sysGroupList) {
		boolean flag = false;
		if (sysGroupList != null) {
			sysGroupDao.saveAll(sysGroupList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "权限管理",action= "分组修改",description= "/分组修改说明")
	@Override
	public boolean updateSysGroup(SysGroup sysGroup) {
		boolean flag = false;
		if (sysGroup != null) {
			sysGroupDao.update(sysGroup);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SysGroup sysGroup) {
		boolean flag = false;
		if (sysGroup != null) {
			SysGroup instance = this.getSysGroupById(sysGroup.getId());
			sysGroupDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "权限管理",action= "分组多选删除",description= "/分组多选删除说明")
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysGroup> page) {
		
		return sysGroupDao.pagedQuery(detachedCriteria, page);
	}

	@Audit(module= "权限管理",action= "分组删除",description= "/分组删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			SysGroup sysGroup = sysGroupDao.get(id);
			if (sysGroup != null) {
				sysGroup.setEnable(false);
				sysGroupDao.update(sysGroup);
				//
				groupRoleDao.deleteByGroupId(sysGroup.getId());
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public List<SysGroup> findByHQLAndParams(SysGroup sysGroup) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<SysGroup> sList = sysGroupDao.findByHQLAndParams("from SysGroup where enalbe = true","");
		return sList;
	}

	@Override
	public boolean deleteGroupRoleByGroupId(String groupId) {

		return groupRoleDao.deleteByGroupId(groupId);
	}
	
	@Audit(module= "权限管理",action= "分组授权",description= "/分组授权说明")
	@Override
	public boolean saveGroupRole(GroupRole groupRole) {
		boolean flag = false;
		if (groupRole != null) {
			groupRoleDao.save(groupRole);
			flag = true;
		}
		return flag;
	}

	@Override
	public SysGroup getSysGroup(String id) {

		return sysGroupDao.get(id);
	}

	@Override
	public List<GroupRole> getRoleByGroupId(String groupId) {
		
		return groupRoleDao.getRoleByGroupId(groupId);
	}
	
}
