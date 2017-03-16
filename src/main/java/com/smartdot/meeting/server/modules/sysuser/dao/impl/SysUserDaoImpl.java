package com.smartdot.meeting.server.modules.sysuser.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.sysuser.dao.ISysUserDao;
import com.smartdot.meeting.server.common.entity.SysUser;
@Repository(value = ISysUserDao.DAO_NAME)
public class SysUserDaoImpl extends GenericDaoHibernateSupport<SysUser> implements ISysUserDao {

	@Override
	public SysUser getUserName(String userName) {
		SysUser user = new SysUser();
		DetachedCriteria dc=DetachedCriteria.forClass(SysUser.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("userName", userName));
		dc.add(Restrictions.eq("state", 1));
		List<SysUser> users = findByDetachedCriteria(dc);
		if(null != users && users.size() > 0){
			user = users.get(0);
		}
		return user;
	}

	@Override
	public List<SysUser> getListUsers() {
		DetachedCriteria dc=DetachedCriteria.forClass(SysUser.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("state", 1));
		List<SysUser> users = findByDetachedCriteria(dc);
		return users;
	}
	
}
