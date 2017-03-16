package com.smartdot.meeting.server.modules.systemConfig.dao;


import com.smartdot.meeting.server.common.dao.BaseRedisDao;
import com.smartdot.meeting.server.common.entity.SystemConfig;

public interface ISystemConfigRedisDao extends BaseRedisDao<SystemConfig>{
	
	public static final String DAO_NAME = "systemConfigRedisDao";
	
}
