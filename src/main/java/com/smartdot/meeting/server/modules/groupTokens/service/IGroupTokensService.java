package com.smartdot.meeting.server.modules.groupTokens.service;

import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.GroupTokens;
import com.smartdot.meeting.server.common.model.Page;

public interface IGroupTokensService {
	
	public static final String SERVICE_NAME = "groupTokensService";

	public Map<String, Object> pageGroupTokensList(DetachedCriteria detachedCriteria,Page<GroupTokens> page);

	public GroupTokens getEntityById(String id);

	public void saveEntity(GroupTokens groupTokens);

}
