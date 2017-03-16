package com.smartdot.meeting.server.modules.news.dao.impl;


import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.News;
import com.smartdot.meeting.server.modules.news.dao.INewsDao;


@Service(value = INewsDao.DAO_NAME)
public class NewsDaoImpl extends GenericDaoHibernateSupport<News> implements INewsDao {
	
}
