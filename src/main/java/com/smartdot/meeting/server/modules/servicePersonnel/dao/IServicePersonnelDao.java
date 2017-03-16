package com.smartdot.meeting.server.modules.servicePersonnel.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;

public interface IServicePersonnelDao extends GenericDao<ServicePersonnel> {
	
	public final static String DAO_NAME="servicePersonnelDao";
	
	public boolean delete(String id);
	
	public List<ServicePersonnel> findByDetachedCriteria(DetachedCriteria detachedCriteria);
}
