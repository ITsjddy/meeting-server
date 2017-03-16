package com.smartdot.meeting.server.modules.issue.service.impl;


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
import com.smartdot.meeting.server.common.entity.Issue;
import com.smartdot.meeting.server.common.entity.MeetingAndMember;
import com.smartdot.meeting.server.common.entity.Schedule;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.issue.dao.IIssueDao;
import com.smartdot.meeting.server.modules.issue.service.IIssueService;
import com.smartdot.meeting.server.modules.issue.util.IssueHelp;
import com.smartdot.meeting.server.modules.schedule.dao.IMeetingAndMemberDao;
import com.smartdot.meeting.server.modules.schedule.service.IScheduleService;


@Service(value = IIssueService.SERVICE_NAME)
public class IssueServiceImpl implements IIssueService {
	
	@Resource(name = IIssueDao.DAO_NAME)
	private IIssueDao issueDao;

	@Resource(name = IMeetingAndMemberDao.DAO_NAME)
	private IMeetingAndMemberDao meetingAndMemberDao;
	
	@Resource(name = IScheduleService.SERVICE_NAME)
	private IScheduleService scheduleService;
	@Override
	public Map<String, Object> pageIssueList(DetachedCriteria detachedCriteria, Page<Issue> page) {
		Map<String, Object> pageData = issueDao.pagedQuery(detachedCriteria,page);
		return pageData;
	}

	@Override
	public Issue getEntityById(String id) {
		return issueDao.findById(id);
	}

	@Override
	public void saveEntity(Issue Issue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, String>> getAllLanguage() {
		//查询数据字典的语言
//		List<Map<String,String>> llangage = new ArrayList<Map<String,String>>();
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("uneIdent", "en");
//		map.put("name", "英文");
//		Map<String,String> map2 = new HashMap<String,String>();
//		map2.put("uneIdent", "arab");
//		map2.put("name", "阿拉伯文");
//		Map<String,String> map3 = new HashMap<String,String>();
//		map3.put("uneIdent", "german");
//		map3.put("name", "德语");
//		llangage.add(map);
//		llangage.add(map2);
//		llangage.add(map3);
//		return llangage;
		return scheduleService.getLanguageDataForPublic();
	
	
	}
	@Override
	public List<Issue> getListIssue(String id) {
		DetachedCriteria dc = DetachedCriteria.forClass(Issue.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", id));
		return issueDao.findByDetachedCriteria(dc);
	
	
	}

	@Override
	public boolean saveMultiEntity(String[] issueList, String issueMain) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean result = false;
		String unique = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(issueMain)){
			IssueHelp issueHelp = JSON.parseObject(String.valueOf(issueMain), IssueHelp.class);
			Issue issue = new Issue();
			BeanUtils.copyProperties(issueHelp, issue);
			if (StringUtils.isBlank(issue.getId())) {
				issue.setUniqueCode(unique);
				try {
					issue.setStartDate(format.parse(issueHelp.getIssueStartDate()));
					issue.setEndDate(format.parse(issueHelp.getIssueEndDate()));
				} catch (Exception e) {
					System.out.println("===========议题日期格式转换有问题==========");
				}
				issueDao.save(issue);
				boolean flag = saveMemberForIssue(issue.getUniqueCode(),issueHelp.getMemberIds());
				if(null != issueList && issueList.length > 0){
					for (String str : issueList) {
						if(StringUtils.isNotBlank(str)){
							Issue issueLan = JSON.parseObject(str, Issue.class);
							if(null != issueLan && StringUtils.isNotBlank(issueLan.getLanguage()) && StringUtils.isNotBlank(issueLan.getIssueName())){
								issueLan.setScheduleId(issue.getScheduleId());
								issueLan.setStartDate(issue.getStartDate());
								issueLan.setEndDate(issue.getEndDate());
								issueLan.setConHallId(issue.getConHallId());
								issueLan.setUniqueCode(issue.getUniqueCode());
								issueDao.save(issueLan);
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
	public boolean updateMultiEntity(String[] issueList, String issueMain) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean result = false;
		if(StringUtils.isNotBlank(issueMain)){
			IssueHelp issueHelp = JSON.parseObject(String.valueOf(issueMain), IssueHelp.class);
			Issue issue = new Issue();
			BeanUtils.copyProperties(issueHelp, issue);
			if (StringUtils.isNotBlank(issue.getId())) {
				try {
					issue.setStartDate(format.parse(issueHelp.getIssueStartDate()));
					issue.setEndDate(format.parse(issueHelp.getIssueEndDate()));
				} catch (Exception e) {
					System.out.println("===========议题日期格式转换有问题==========");
				}
				issueDao.update(issue);
				boolean flag = saveMemberForIssue(issue.getUniqueCode(),issueHelp.getMemberIds());
				if(null != issueList && issueList.length > 0){
					for (String str : issueList) {
						if(StringUtils.isNotBlank(str)){
							Issue issueLan = JSON.parseObject(str, Issue.class);
							if(StringUtils.isNotBlank(issueLan.getId())){
								if(null != issueLan && StringUtils.isNotBlank(issueLan.getLanguage()) && StringUtils.isNotBlank(issueLan.getIssueName())){
									issueLan.setScheduleId(issue.getScheduleId());
									issueLan.setStartDate(issue.getStartDate());
									issueLan.setEndDate(issue.getEndDate());
									issueLan.setConHallId(issue.getConHallId());
									issueLan.setUniqueCode(issue.getUniqueCode());
									issueDao.update(issueLan);
								}else{
									issueLan.setEnable(false);
									issueDao.update(issueLan);
								}
							}else{
								if(null != issueLan && StringUtils.isNotBlank(issueLan.getLanguage()) && StringUtils.isNotBlank(issueLan.getIssueName())){
									issueLan.setScheduleId(issue.getScheduleId());
									issueLan.setStartDate(issue.getStartDate());
									issueLan.setEndDate(issue.getEndDate());
									issueLan.setConHallId(issue.getConHallId());
									issueLan.setUniqueCode(issue.getUniqueCode());
									issueDao.save(issueLan);
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
	private boolean saveMemberForIssue(String uniqueCode, String[] memberIds) {

		DetachedCriteria dcCriteria = DetachedCriteria.forClass(MeetingAndMember.class);
		dcCriteria.add(Restrictions.eq("enable",true));
		dcCriteria.add(Restrictions.eq("meetingId",uniqueCode));
		dcCriteria.add(Restrictions.eq("source",MeetingAndMember.MEETING_MEMBER_SOURCE_ISSUE));
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
				mam.setSource(MeetingAndMember.MEETING_MEMBER_SOURCE_ISSUE);
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
		DetachedCriteria dc = DetachedCriteria.forClass(Issue.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", uniqueCode));
		List<Issue> issueList = issueDao.findByDetachedCriteria(dc);
		if(issueList != null&&issueList.size()>0){
			for(int i = 0;i<issueList.size();i++){
				Issue issue = issueList.get(i);
				issue.setEnable(false);
				issueDao.update(issue);
			}
		}
		DetachedCriteria dcMam = DetachedCriteria.forClass(MeetingAndMember.class);
		dcMam.add(Restrictions.eq("enable", true));
		dcMam.add(Restrictions.eq("meetingId", uniqueCode));
		dcMam.add(Restrictions.eq("source",MeetingAndMember.MEETING_MEMBER_SOURCE_ISSUE));
		List<MeetingAndMember> mamList = meetingAndMemberDao.findByDetachedCriteria(dcMam);
		if(mamList != null&&mamList.size()>0){
			for(int i = 0;i<mamList.size();i++){
				MeetingAndMember mam = mamList.get(i);
				mam.setEnable(false);
				meetingAndMemberDao.update(mam);
			}
		}
	}

}
