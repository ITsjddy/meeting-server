package com.smartdot.meeting.server.modules.log.service.impl;


import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.Log;
import com.smartdot.meeting.server.modules.log.service.ILogService;


@Service(value = ILogService.SERVICE_NAME)
public class LogServiceImpl extends GenericDaoHibernateSupport<Log> implements ILogService {
	
	
}
