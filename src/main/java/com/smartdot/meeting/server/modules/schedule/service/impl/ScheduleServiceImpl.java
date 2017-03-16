package com.smartdot.meeting.server.modules.schedule.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.MeetingAndMember;
import com.smartdot.meeting.server.common.entity.Schedule;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.util.GBKOrder;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.schedule.dao.IMeetingAndMemberDao;
import com.smartdot.meeting.server.modules.schedule.dao.IScheduleDao;
import com.smartdot.meeting.server.modules.schedule.service.IScheduleService;
import com.smartdot.meeting.server.modules.schedule.util.ScheduleHelp;


@Service(value = IScheduleService.SERVICE_NAME)
public class ScheduleServiceImpl implements IScheduleService {
	
	@Resource(name = IScheduleDao.DAO_NAME)
	private IScheduleDao scheduleDao;
	
	@Resource(name = IMeetingAndMemberDao.DAO_NAME)
	private IMeetingAndMemberDao meetingAndMemberDao;
	
	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;
	@Override
	public Map<String, Object> pageScheduleList(DetachedCriteria detachedCriteria,Page<Schedule> page) {
		Map<String, Object> pageData = scheduleDao.pagedQuery(detachedCriteria,page);
		return pageData;
	}

	@Override
	public Schedule getEntityById(String id) {
		return scheduleDao.findById(id);
	}

	@Override
	public void saveEntity(Schedule schedule) {
		scheduleDao.saveOrUpdate(schedule);
	}

	@Override
	public List<Map<String, String>> getAllLanguage() {
		//查询数据字典的语言
		return getLanguageDataForPublic();
	
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 获取语言数据字典数据的方法
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月14日 上午10:19:42
	 * </pre>
	 * </p>
	 */
	public List<Map<String, String>> getLanguageDataForPublic(){
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		List<DataDictionary> dataDictionaries = dataDictionaryService.findAllByTypeLogo("languagetype", DataDictionary.DATA_DICTIONARY_LANGUAGE_ZH);
		if (dataDictionaries!=null&&dataDictionaries.size()>0) {
			for (int i = 0; i < dataDictionaries.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				DataDictionary dataDictionary = dataDictionaries.get(i);
				map.put("uneIdent", dataDictionary.getdDLogo());
				map.put("name", dataDictionary.getdDName());
				resultList.add(map);
			}
		}
		return resultList;
	}
	@Override
	public List<Schedule> getListSchedule(String id) {
		DetachedCriteria dc = DetachedCriteria.forClass(Schedule.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", id));
		return scheduleDao.findByDetachedCriteria(dc);
	
	}

	@Override
	public boolean saveMultiEntity(String[] scheduleList, String scheduleMain) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean result = false;
		String unique = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(scheduleMain)){
			ScheduleHelp scheduleHelp = JSON.parseObject(String.valueOf(scheduleMain), ScheduleHelp.class);
			Schedule schedule = new Schedule();
			BeanUtils.copyProperties(scheduleHelp, schedule);
			if (StringUtils.isBlank(schedule.getId())) {
				schedule.setUniqueCode(unique);
				try {
					schedule.setScheduleEndDate(format.parse(scheduleHelp.getEndDate()));
					schedule.setScheduleStartDate(format.parse(scheduleHelp.getStartDate()));
				} catch (Exception e) {
					System.out.println("===========论坛日期格式转换有问题==========");
				}
				scheduleDao.save(schedule);
				boolean flag = saveMemberForSchedule(schedule.getUniqueCode(),scheduleHelp.getMemberIds());
				if(null != scheduleList && scheduleList.length > 0){
					for (String str : scheduleList) {
						if(StringUtils.isNotBlank(str)){
							Schedule scheduleLan = JSON.parseObject(str, Schedule.class);
							if(null != scheduleLan && StringUtils.isNotBlank(scheduleLan.getLanguage()) && StringUtils.isNotBlank(scheduleLan.getName())){
								scheduleLan.setConHallId(schedule.getConHallId());
								scheduleLan.setCoUnit(schedule.getCoUnit());
								scheduleLan.setDockingPerson(schedule.getDockingPerson());
								scheduleLan.setHostContact(schedule.getHostContact());
								scheduleLan.setHostPerson(schedule.getHostPerson());
								scheduleLan.setHostUnit(schedule.getHostUnit());
								scheduleLan.setPlateId(schedule.getPlateId());
								scheduleLan.setResponsibilityContact(schedule.getResponsibilityContact());
								scheduleLan.setResponsibilityMobile(schedule.getResponsibilityMobile());
								scheduleLan.setResponsibilityUnit(schedule.getResponsibilityUnit());
								scheduleLan.setScheduleEndDate(schedule.getScheduleEndDate());
								scheduleLan.setScheduleStartDate(schedule.getScheduleStartDate());
								scheduleLan.setSupportUnit(schedule.getSupportUnit());
								scheduleLan.setUndertakingContact(schedule.getUndertakingContact());
								scheduleLan.setUndertakingMobile(schedule.getUndertakingMobile());
								scheduleLan.setUndertakingUnit(schedule.getUndertakingUnit());
								scheduleLan.setUniqueCode(schedule.getUniqueCode());
								scheduleDao.save(scheduleLan);
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	
	
	}

	@Override
	public boolean updateMultiEntity(String[] scheduleList, String scheduleMain) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean result = false;
		if(StringUtils.isNotBlank(scheduleMain)){
			ScheduleHelp scheduleHelp = JSON.parseObject(String.valueOf(scheduleMain), ScheduleHelp.class);
			Schedule schedule = new Schedule();
			BeanUtils.copyProperties(scheduleHelp, schedule);
			if (StringUtils.isNotBlank(schedule.getId())) {
				try {
					schedule.setScheduleEndDate(format.parse(scheduleHelp.getEndDate()));
					schedule.setScheduleStartDate(format.parse(scheduleHelp.getStartDate()));
				} catch (Exception e) {
					System.out.println("===========论坛日期格式转换有问题==========");
				}
				scheduleDao.update(schedule);
				boolean flag = saveMemberForSchedule(schedule.getUniqueCode(),scheduleHelp.getMemberIds());
				if(null != scheduleList && scheduleList.length > 0){
					for (String str : scheduleList) {
						if(StringUtils.isNotBlank(str)){
							Schedule scheduleLan = JSON.parseObject(str, Schedule.class);
							if(StringUtils.isNotBlank(scheduleLan.getId())){
								if(null != scheduleLan && StringUtils.isNotBlank(scheduleLan.getLanguage()) && StringUtils.isNotBlank(scheduleLan.getName())){
									scheduleLan.setConHallId(schedule.getConHallId());
									scheduleLan.setCoUnit(schedule.getCoUnit());
									scheduleLan.setDockingPerson(schedule.getDockingPerson());
									scheduleLan.setHostContact(schedule.getHostContact());
									scheduleLan.setHostPerson(schedule.getHostPerson());
									scheduleLan.setHostUnit(schedule.getHostUnit());
									scheduleLan.setPlateId(schedule.getPlateId());
									scheduleLan.setResponsibilityContact(schedule.getResponsibilityContact());
									scheduleLan.setResponsibilityMobile(schedule.getResponsibilityMobile());
									scheduleLan.setResponsibilityUnit(schedule.getResponsibilityUnit());
									scheduleLan.setScheduleEndDate(schedule.getScheduleEndDate());
									scheduleLan.setScheduleStartDate(schedule.getScheduleStartDate());
									scheduleLan.setSupportUnit(schedule.getSupportUnit());
									scheduleLan.setUndertakingContact(schedule.getUndertakingContact());
									scheduleLan.setUndertakingMobile(schedule.getUndertakingMobile());
									scheduleLan.setUndertakingUnit(schedule.getUndertakingUnit());
									scheduleLan.setUniqueCode(schedule.getUniqueCode());
								}else{
									scheduleLan.setEnable(false);
								}
								scheduleDao.update(scheduleLan);
							}else{
								if(null != scheduleLan && StringUtils.isNotBlank(scheduleLan.getLanguage()) && StringUtils.isNotBlank(scheduleLan.getName())){
									scheduleLan.setConHallId(schedule.getConHallId());
									scheduleLan.setCoUnit(schedule.getCoUnit());
									scheduleLan.setDockingPerson(schedule.getDockingPerson());
									scheduleLan.setHostContact(schedule.getHostContact());
									scheduleLan.setHostPerson(schedule.getHostPerson());
									scheduleLan.setHostUnit(schedule.getHostUnit());
									scheduleLan.setPlateId(schedule.getPlateId());
									scheduleLan.setResponsibilityContact(schedule.getResponsibilityContact());
									scheduleLan.setResponsibilityMobile(schedule.getResponsibilityMobile());
									scheduleLan.setResponsibilityUnit(schedule.getResponsibilityUnit());
									scheduleLan.setScheduleEndDate(schedule.getScheduleEndDate());
									scheduleLan.setScheduleStartDate(schedule.getScheduleStartDate());
									scheduleLan.setSupportUnit(schedule.getSupportUnit());
									scheduleLan.setUndertakingContact(schedule.getUndertakingContact());
									scheduleLan.setUndertakingMobile(schedule.getUndertakingMobile());
									scheduleLan.setUndertakingUnit(schedule.getUndertakingUnit());
									scheduleLan.setUniqueCode(schedule.getUniqueCode());
									scheduleDao.save(scheduleLan);
								}
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	删除以前的选人数据，新增所有新增的嘉宾
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月16日 上午10:34:58
	 * </pre>
	 * </p>
	 */
	private boolean saveMemberForSchedule(String uniqueCode, String[] memberIds) {
		DetachedCriteria dcCriteria = DetachedCriteria.forClass(MeetingAndMember.class);
		dcCriteria.add(Restrictions.eq("enable",true));
		dcCriteria.add(Restrictions.eq("meetingId",uniqueCode));
		List<MeetingAndMember> list = meetingAndMemberDao.findByDetachedCriteria(dcCriteria);
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				MeetingAndMember meetingAndMember = list.get(i);
				meetingAndMember.setEnable(false);
				meetingAndMemberDao.update(meetingAndMember);
			}
		}
		if(memberIds!=null&&memberIds.length>0){
			for(int i=0;i<memberIds.length;i++){
				String memberId = memberIds[i];
				MeetingAndMember mam = new MeetingAndMember();
				mam.setSortNumber((i+1));
				mam.setSource(MeetingAndMember.MEETING_MEMBER_SOURCE_SCHEDULE);
				mam.setMeetingId(uniqueCode);
				mam.setType(MeetingAndMember.MEETING_MEMBER_TYPE_GUEST);
				mam.setMemberUnique(memberId);
				meetingAndMemberDao.save(mam);
			}
		}
		return true;
	}

	@Override
	public void deleteByUniqueCode(String uniqueCode) {
		DetachedCriteria dc = DetachedCriteria.forClass(Schedule.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", uniqueCode));
		List<Schedule> scheduleList = scheduleDao.findByDetachedCriteria(dc);
		if(scheduleList != null&&scheduleList.size()>0){
			for(int i = 0;i<scheduleList.size();i++){
				Schedule schedule = scheduleList.get(i);
				schedule.setEnable(false);
				scheduleDao.update(schedule);
			}
		}
		DetachedCriteria dcMam = DetachedCriteria.forClass(MeetingAndMember.class);
		dcMam.add(Restrictions.eq("enable", true));
		dcMam.add(Restrictions.eq("meetingId", uniqueCode));
		dcMam.add(Restrictions.eq("source",MeetingAndMember.MEETING_MEMBER_SOURCE_SCHEDULE));
		List<MeetingAndMember> mamList = meetingAndMemberDao.findByDetachedCriteria(dcMam);
		if(mamList != null&&mamList.size()>0){
			for(int i = 0;i<mamList.size();i++){
				MeetingAndMember mam = mamList.get(i);
				mam.setEnable(false);
				meetingAndMemberDao.update(mam);
			}
		}
	
	}
	@Override
	public List<Schedule> getScheduleList() {
		DetachedCriteria dc = DetachedCriteria.forClass(Schedule.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", "zh"));
		dc.addOrder(GBKOrder.desc("name"));
		return scheduleDao.findByDetachedCriteria(dc);
	
	}

	@Override
	public List<Map<String, Object>> conHallWhetherConflict(String conHallId, String startDate, String endDate) {
		return scheduleDao.conHallWhetherConflict(conHallId,startDate,endDate);
	}

	@Override
	public List<Map<String, Object>> memberWhetherConflict(String[] ids, String startDate, String endDate) {
		return scheduleDao.memberWhetherConflict(ids,startDate,endDate);
	}

	@Override
	public List<Schedule> getAllScheduleData() {
		DetachedCriteria dc = DetachedCriteria.forClass(Schedule.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", "zh"));
		dc.addOrder(GBKOrder.desc("updateTimes"));
		return scheduleDao.findByDetachedCriteria(dc);
	}
	
}
