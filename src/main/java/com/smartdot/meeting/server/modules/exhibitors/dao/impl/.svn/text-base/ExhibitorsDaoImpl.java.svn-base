package com.smartdot.meeting.server.modules.exhibitors.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.exhibitors.dao.IExhibitorsDao;
import com.smartdot.meeting.server.common.entity.Exhibitors;
import com.smartdot.meeting.server.common.entity.Guest;

@Repository(value = IExhibitorsDao.DAO_NAME)
public class ExhibitorsDaoImpl extends GenericDaoHibernateSupport<Exhibitors> implements IExhibitorsDao {

	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				Exhibitors exhibitors = get(id);
				exhibitors.setEnable(false);
				update(exhibitors);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			Exhibitors exhibitors = get(id);
			exhibitors.setEnable(false);
			update(exhibitors);
			flag = true;
		}
		return flag;
	}
}
