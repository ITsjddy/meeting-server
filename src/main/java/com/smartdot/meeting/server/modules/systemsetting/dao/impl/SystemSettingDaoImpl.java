package com.smartdot.meeting.server.modules.systemsetting.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.systemsetting.dao.ISystemSettingDao;
import com.smartdot.meeting.server.common.entity.SystemSetting;
@Repository(value = ISystemSettingDao.DAO_NAME)
public class SystemSettingDaoImpl extends GenericDaoHibernateSupport<SystemSetting> implements ISystemSettingDao {

}
