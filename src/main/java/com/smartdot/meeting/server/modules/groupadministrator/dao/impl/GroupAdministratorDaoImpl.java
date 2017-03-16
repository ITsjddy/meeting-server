package com.smartdot.meeting.server.modules.groupadministrator.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.groupadministrator.dao.IGroupAdministratorDao;
import com.smartdot.meeting.server.common.entity.GroupAdministrator;
@Repository(value = IGroupAdministratorDao.DAO_NAME)
public class GroupAdministratorDaoImpl extends GenericDaoHibernateSupport<GroupAdministrator> implements IGroupAdministratorDao {

}
