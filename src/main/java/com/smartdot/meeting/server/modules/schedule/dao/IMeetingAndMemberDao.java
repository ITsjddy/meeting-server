package com.smartdot.meeting.server.modules.schedule.dao;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.MeetingAndMember;

public interface IMeetingAndMemberDao  extends GenericDao<MeetingAndMember>{
	public static final String DAO_NAME = "meetingAndMemberDao";
}
