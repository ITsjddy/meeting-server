package com.smartdot.meeting.server.modules.media.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Media;
import com.smartdot.meeting.server.modules.media.dao.IMediaDao;

@Repository(value = IMediaDao.DAO_NAME)
public class MediaDaoImpl extends GenericDaoHibernateSupport<Media> implements IMediaDao {

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			Media media = get(id);
			media.setEnable(false);
			update(media);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				Media media = get(id);
				media.setEnable(false);
				update(media);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Media> getListMediaByMemberId(String memberId) {

		DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("member", memberId));
		List<Media> lMedia = findByDetachedCriteria(dc);
		
		return lMedia;
	}
}
