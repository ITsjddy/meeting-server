package com.smartdot.meeting.server.modules.schedule.dao;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.Schedule;

public interface IScheduleDao extends GenericDao<Schedule>{
	
	public static final String DAO_NAME = "scheduleDao";

	List<Map<String, Object>> conHallWhetherConflict(String conHallId, String startDate, String endDate);

	List<Map<String, Object>> memberWhetherConflict(String[] ids, String startDate, String endDate);
}
