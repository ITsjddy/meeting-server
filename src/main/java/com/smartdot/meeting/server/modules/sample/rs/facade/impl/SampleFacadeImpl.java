package com.smartdot.meeting.server.modules.sample.rs.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.SampleEntity;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.modules.sample.rs.facade.ISampleFacade;
import com.smartdot.meeting.server.modules.sample.rs.facade.model.req.RSSampleSaveReq;
import com.smartdot.meeting.server.modules.sample.service.ISampleService;

@Service(value = ISampleFacade.FACADE_NAME)
public class SampleFacadeImpl implements ISampleFacade {

	@Resource(name = ISampleService.SERVICE_NAME)
	private ISampleService sampleService;
	
	@Override
	public ReturnValue save(RSSampleSaveReq req) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		SampleEntity sampleEntity = new SampleEntity();
		sampleEntity.setName(req.getName());
		sampleService.save(sampleEntity);
		returnValue.setSuccess(true);
		return returnValue;
	}

}
