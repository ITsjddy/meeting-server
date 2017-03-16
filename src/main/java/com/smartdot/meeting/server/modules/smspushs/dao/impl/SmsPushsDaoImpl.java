package com.smartdot.meeting.server.modules.smspushs.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.smspushs.dao.ISmsPushsDao;
import com.smartdot.meeting.server.common.entity.SmsPushs;
@Repository(value = ISmsPushsDao.DAO_NAME)
public class SmsPushsDaoImpl extends GenericDaoHibernateSupport<SmsPushs> implements ISmsPushsDao {

}
