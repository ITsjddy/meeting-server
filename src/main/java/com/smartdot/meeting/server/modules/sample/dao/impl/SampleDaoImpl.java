package com.smartdot.meeting.server.modules.sample.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.SampleEntity;
import com.smartdot.meeting.server.modules.sample.dao.ISampleDao;

@Repository(value = ISampleDao.DAO_NAME)
public class SampleDaoImpl extends GenericDaoHibernateSupport<SampleEntity> implements ISampleDao {


}
