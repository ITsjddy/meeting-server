package com.smartdot.meeting.server.modules.groupTokens.service.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.GroupTokens;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.groupTokens.dao.IGroupTokensDao;
import com.smartdot.meeting.server.modules.groupTokens.service.IGroupTokensService;


@Service(value = IGroupTokensService.SERVICE_NAME)
public class GroupTokensServiceImpl implements IGroupTokensService {
	
	@Resource(name = IGroupTokensDao.DAO_NAME)
	private IGroupTokensDao groupTokensDao;

	@Override
	public Map<String, Object> pageGroupTokensList(DetachedCriteria detachedCriteria,Page<GroupTokens> page) {
		Map<String, Object> pageData = groupTokensDao.pagedQuery(detachedCriteria,page);
		return pageData;
	}

	@Override
	public GroupTokens getEntityById(String id) {
		return groupTokensDao.findById(id);
	}

	@Override
	public void saveEntity(GroupTokens groupTokens) {
		groupTokensDao.saveOrUpdate(groupTokens);
	}
	
}
