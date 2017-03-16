package com.smartdot.meeting.server.modules.systemConfig.dao.impl;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.SystemConfig;
import com.smartdot.meeting.server.modules.systemConfig.dao.ISystemConfigDao;


@Service(value = ISystemConfigDao.DAO_NAME)
public class SystemConfigDaoImpl extends GenericDaoHibernateSupport<SystemConfig> implements ISystemConfigDao {

	@Override
	public List<SystemConfig> getAllList() {
		
		DetachedCriteria dc =DetachedCriteria.forClass(SystemConfig.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.ne("uneIdent", ""));
		dc.add(Restrictions.isNotNull("uneIdent"));
		List<SystemConfig> lsys = findByDetachedCriteria(dc);
		
		return lsys;
	}
	
}
