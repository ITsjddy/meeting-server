package com.smartdot.meeting.server.modules.schedule.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.Schedule;
import com.smartdot.meeting.server.common.model.Page;

public interface IScheduleService {
	
	public static final String SERVICE_NAME = "scheduleService";

	public Map<String, Object> pageScheduleList(DetachedCriteria detachedCriteria,Page<Schedule> page);

	public Schedule getEntityById(String id);

	public void saveEntity(Schedule schedule);

	public List<Map<String, String>> getAllLanguage();

	public List<Schedule> getListSchedule(String id);

	public boolean saveMultiEntity(String[] scheduleList, String scheduleMain);

	public void deleteByUniqueCode(String uniqueCode);

	public boolean updateMultiEntity(String[] scheduleList, String scheduleMain);

	public List<Schedule> getScheduleList();

	public List<Map<String, Object>> conHallWhetherConflict(String conHallId, String startDate, String endDate);

	public List<Map<String, Object>> memberWhetherConflict(String[] ids, String startDate, String endDate);

	public List<Map<String, String>> getLanguageDataForPublic();

	public List<Schedule> getAllScheduleData();

}
