package com.smartdot.meeting.server.modules.sysgroup.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.sysgroup.dao.ISysGroupDao;
import com.smartdot.meeting.server.common.entity.SysGroup;


@Repository(value = ISysGroupDao.DAO_NAME)
public class SysGroupDaoImpl extends GenericDaoHibernateSupport<SysGroup> implements ISysGroupDao {
	
	
	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				SysGroup sysGroup = get(id);
				sysGroup.setEnable(false);
				update(sysGroup);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			SysGroup sysGroup = get(id);
			sysGroup.setEnable(false);
			update(sysGroup);
			flag = true;
		}
		return flag;
	}
	
}
