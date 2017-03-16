package com.smartdot.meeting.server.modules.issue.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.Issue;
import com.smartdot.meeting.server.common.model.Page;

public interface IIssueService {
	
	public static final String SERVICE_NAME = "issueService";

	public Map<String, Object> pageIssueList(DetachedCriteria detachedCriteria,Page<Issue> page);

	public Issue getEntityById(String id);

	public void saveEntity(Issue Issue);

	public List<Map<String, String>> getAllLanguage();

	public List<Issue> getListIssue(String id);

	public boolean saveMultiEntity(String[] issueList, String issueMain);

	public boolean updateMultiEntity(String[] issueList, String issueMain);

	public void deleteByUniqueCode(String uniqueCode);

}
