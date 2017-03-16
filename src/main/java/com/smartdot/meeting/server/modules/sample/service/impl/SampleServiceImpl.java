package com.smartdot.meeting.server.modules.sample.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.SampleEntity;
import com.smartdot.meeting.server.modules.sample.dao.ISampleDao;
import com.smartdot.meeting.server.modules.sample.service.ISampleService;

@Service(value = ISampleService.SERVICE_NAME)
public class SampleServiceImpl implements ISampleService {

	@Resource(name = ISampleDao.DAO_NAME)
	private ISampleDao sampleDao;
	
	@Override
	public void save(SampleEntity sample) {
		this.sampleDao.save(sample);
	}

}
