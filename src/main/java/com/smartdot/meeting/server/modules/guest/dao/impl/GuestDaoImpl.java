package com.smartdot.meeting.server.modules.guest.dao.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.guest.dao.IGuestDao;
import com.smartdot.meeting.server.common.entity.Guest;

@Repository(value = IGuestDao.DAO_NAME)
public class GuestDaoImpl extends GenericDaoHibernateSupport<Guest> implements IGuestDao {

	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				Guest guest = get(id);
				guest.setEnable(false);
				update(guest);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			Guest guest = get(id);
			guest.setEnable(false);
			update(guest);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Guest> getListGuestByMemberId(String memberId) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("member", memberId));
		List<Guest> lGuest = findByDetachedCriteria(dc);
		
		return lGuest;
	}

}
