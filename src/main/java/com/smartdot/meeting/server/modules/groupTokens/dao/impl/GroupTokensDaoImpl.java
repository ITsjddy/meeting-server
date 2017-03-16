package com.smartdot.meeting.server.modules.groupTokens.dao.impl;


import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.GroupTokens;
import com.smartdot.meeting.server.modules.groupTokens.dao.IGroupTokensDao;


@Service(value = IGroupTokensDao.DAO_NAME)
public class GroupTokensDaoImpl extends GenericDaoHibernateSupport<GroupTokens> implements IGroupTokensDao {
	
}
