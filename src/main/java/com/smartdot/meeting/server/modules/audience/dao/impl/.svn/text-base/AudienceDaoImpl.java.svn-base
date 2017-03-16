package com.smartdot.meeting.server.modules.audience.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.audience.dao.IAudienceDao;
import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.Guest;
@Repository(value = IAudienceDao.DAO_NAME)
public class AudienceDaoImpl extends GenericDaoHibernateSupport<Audience> implements IAudienceDao {

	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				Audience audience = get(id);
				audience.setEnable(false);
				update(audience);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			Audience audience = get(id);
			audience.setEnable(false);
			update(audience);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Audience> getListAudienceByMemberId(String memberId) {

		DetachedCriteria dc = DetachedCriteria.forClass(Audience.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("member", memberId));
		List<Audience> lAudience = findByDetachedCriteria(dc);
		
		return lAudience;
	}

}
