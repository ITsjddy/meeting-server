package com.smartdot.meeting.server.modules.sysrole.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.sysrole.dao.IRolePrivilegeDao;
import com.smartdot.meeting.server.common.entity.RolePrivilege;
import com.smartdot.meeting.server.common.entity.SysPrivilege;


@Repository(value = IRolePrivilegeDao.DAO_NAME)
public class RolePrivilegeDaoImpl extends GenericDaoHibernateSupport<RolePrivilege> implements IRolePrivilegeDao {

	
	@Override
	public List<RolePrivilege> getListPrivilegesByRoleId(String roleId) {
		
		List<RolePrivilege> allRolePrivilege = new ArrayList<RolePrivilege>();
		if(StringUtils.isBlank(roleId)){
			return allRolePrivilege;
		}
		DetachedCriteria rolePriviledeta = DetachedCriteria.forClass(RolePrivilege.class);
		rolePriviledeta.add(Restrictions.eq("enable", true));
		rolePriviledeta.add(Restrictions.ne("priId", ""));
		rolePriviledeta.add(Restrictions.isNotNull("priId"));
		rolePriviledeta.add(Restrictions.eq("roleId", roleId));
		allRolePrivilege = findByDetachedCriteria(rolePriviledeta);
		
		return allRolePrivilege;
	}
	
	@Override
	public boolean deleteRolePrivilegeByRoleId(String id) {
		boolean flag = false;
		if (StringUtils.isNotBlank(id)) {
			DetachedCriteria rolePriviledeta = DetachedCriteria.forClass(RolePrivilege.class);
			rolePriviledeta.add(Restrictions.eq("enable", true));
			rolePriviledeta.add(Restrictions.eq("roleId", id));
			List<RolePrivilege> allRolePrivilege = findByDetachedCriteria(rolePriviledeta);
			if(null != allRolePrivilege && allRolePrivilege.size() > 0){
				for (RolePrivilege rolePrivilege : allRolePrivilege) {
					rolePrivilege.setEnable(false);
					update(rolePrivilege);
				}
			}
			flag = true;
		}
		return flag;
	}

}
