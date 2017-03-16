package com.smartdot.meeting.server.modules.schedule.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.MeetingAndMember;
import com.smartdot.meeting.server.modules.schedule.dao.IMeetingAndMemberDao;
import com.smartdot.meeting.server.modules.schedule.service.IMeetingAndMemberService;

@Service(value = IMeetingAndMemberService.SERVICE_NAME)
public class MeetingAndMemberServiceImpl implements IMeetingAndMemberService {

	
	@Resource(name = IMeetingAndMemberDao.DAO_NAME)
	private IMeetingAndMemberDao meetingAndMemberDao ;

	@Override
	public List<MeetingAndMember> getListScheduleId(String id) {
		DetachedCriteria dcCriteria = DetachedCriteria.forClass(MeetingAndMember.class);
		dcCriteria.add(Restrictions.eq("enable", true));
		dcCriteria.add(Restrictions.eq("meetingId", id));
		dcCriteria.addOrder(Order.asc("sortNumber"));
		return meetingAndMemberDao.findByDetachedCriteria(dcCriteria);
	}

	@Override
	public void saveEntity(MeetingAndMember meetingAndMember) {
		meetingAndMemberDao.saveOrUpdate(meetingAndMember);
	}


}
