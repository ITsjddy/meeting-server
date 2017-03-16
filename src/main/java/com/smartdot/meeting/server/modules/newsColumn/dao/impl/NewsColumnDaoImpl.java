package com.smartdot.meeting.server.modules.newsColumn.dao.impl;


import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.NewsColumn;
import com.smartdot.meeting.server.modules.newsColumn.dao.INewsColumnDao;


@Service(value = INewsColumnDao.DAO_NAME)
public class NewsColumnDaoImpl extends GenericDaoHibernateSupport<NewsColumn> implements INewsColumnDao {
	
}
