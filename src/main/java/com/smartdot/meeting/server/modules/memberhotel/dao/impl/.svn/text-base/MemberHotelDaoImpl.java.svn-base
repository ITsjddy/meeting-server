package com.smartdot.meeting.server.modules.memberhotel.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.MemberHotel;
import com.smartdot.meeting.server.modules.memberhotel.dao.IMemberHotelDao;
@Repository(value = IMemberHotelDao.DAO_NAME)
public class MemberHotelDaoImpl extends GenericDaoHibernateSupport<MemberHotel> implements IMemberHotelDao {
	public List<MemberHotel> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return (List<MemberHotel>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}
}
