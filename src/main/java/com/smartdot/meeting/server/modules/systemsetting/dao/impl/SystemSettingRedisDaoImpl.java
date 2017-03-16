package com.smartdot.meeting.server.modules.systemsetting.dao.impl;


import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.dao.BaseRedisDaoImpl;
import com.smartdot.meeting.server.common.entity.SystemSetting;
import com.smartdot.meeting.server.modules.systemsetting.dao.ISystemSettingRedisDao;


@Service(value = ISystemSettingRedisDao.DAO_NAME)
public class SystemSettingRedisDaoImpl extends BaseRedisDaoImpl<SystemSetting> implements ISystemSettingRedisDao {

	
}
