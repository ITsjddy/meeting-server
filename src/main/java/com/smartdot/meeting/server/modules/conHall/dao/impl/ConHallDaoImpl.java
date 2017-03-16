package com.smartdot.meeting.server.modules.conHall.dao.impl;


import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.ConHall;
import com.smartdot.meeting.server.modules.conHall.dao.IConHallDao;


@Service(value = IConHallDao.DAO_NAME)
public class ConHallDaoImpl extends GenericDaoHibernateSupport<ConHall> implements IConHallDao {
	
}
