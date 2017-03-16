package com.smartdot.meeting.server.modules.systemsetting.dao;


import com.smartdot.meeting.server.common.dao.BaseRedisDao;
import com.smartdot.meeting.server.common.entity.SystemSetting;

public interface ISystemSettingRedisDao extends BaseRedisDao<SystemSetting>{
	
	public static final String DAO_NAME = "systemSettingRedisDao";
	
}
