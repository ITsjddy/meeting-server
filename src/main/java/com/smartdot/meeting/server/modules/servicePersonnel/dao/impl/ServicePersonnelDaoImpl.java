package com.smartdot.meeting.server.modules.servicePersonnel.dao.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.servicePersonnel.dao.IServicePersonnelDao;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;

@Repository(value = IServicePersonnelDao.DAO_NAME)
public class ServicePersonnelDaoImpl extends GenericDaoHibernateSupport<ServicePersonnel> implements IServicePersonnelDao {
	
	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			ServicePersonnel servicePersonnel = get(id);
			servicePersonnel.setEnable(false);
			update(servicePersonnel);
			flag = true;
		}
		return flag;
	}
}
