package com.smartdot.meeting.server.modules.news.dao;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.News;

public interface INewsDao extends GenericDao<News>{
	
	public static final String DAO_NAME = "newsDao";
}
