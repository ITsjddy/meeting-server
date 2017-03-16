package com.smartdot.meeting.server.modules.sysgroup.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.GroupRole;
import com.smartdot.meeting.server.modules.sysgroup.dao.IGroupRoleDao;


@Repository(value = IGroupRoleDao.DAO_NAME)
public class GroupRoleDaoImpl extends GenericDaoHibernateSupport<GroupRole> implements IGroupRoleDao {
	
	
	@Override
	public boolean deleteByGroupId(String groupId) {
		boolean flag = false;
		if (StringUtils.isNotBlank(groupId)) {
			DetachedCriteria dc = DetachedCriteria.forClass(GroupRole.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("groupId", groupId));
			List<GroupRole> lgroupRole = findByDetachedCriteria(dc);
			if(null != lgroupRole && lgroupRole.size() > 0){
				for (GroupRole groupRole : lgroupRole) {
					groupRole.setEnable(false);
					update(groupRole);
				}
			}
			flag = true;
		}
		
		return flag;
	}
	
	@Override
	public boolean deleteByRoleId(String roleId) {
		boolean flag = false;
		if (StringUtils.isNotBlank(roleId)) {
			DetachedCriteria dc = DetachedCriteria.forClass(GroupRole.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("roleId", roleId));
			List<GroupRole> lgroupRole = findByDetachedCriteria(dc);
			if(null != lgroupRole && lgroupRole.size() > 0){
				for (GroupRole groupRole : lgroupRole) {
					groupRole.setEnable(false);
					update(groupRole);
				}
			}
			flag = true;
		}
		
		return flag;
	}

	@Override
	public List<GroupRole> getRoleByGroupId(String groupId) {
		List<GroupRole> lgroupRole = new ArrayList<GroupRole>();
		if (StringUtils.isNotBlank(groupId)) {
			DetachedCriteria dc = DetachedCriteria.forClass(GroupRole.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("groupId", groupId));
			lgroupRole = findByDetachedCriteria(dc);
		}
		return lgroupRole;
	}
	
}
