package com.smartdot.meeting.server.modules.groupTokens.dao;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.GroupTokens;

public interface IGroupTokensDao extends GenericDao<GroupTokens>{
	
	public static final String DAO_NAME = "groupTokensDao";
}
