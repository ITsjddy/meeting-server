package com.smartdot.meeting.server.modules.stroke.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.stroke.dao.IStrokeDao;
import com.smartdot.meeting.server.common.entity.Stroke;
@Repository(value = IStrokeDao.DAO_NAME)
public class StrokeDaoImpl extends GenericDaoHibernateSupport<Stroke> implements IStrokeDao {

}
