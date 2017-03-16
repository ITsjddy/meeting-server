package com.smartdot.meeting.server.modules.sample.service;

import com.smartdot.meeting.server.common.entity.SampleEntity;

public interface ISampleService {
	
	public static final String SERVICE_NAME = "sampleService";
	
	public void save(SampleEntity sample);
	
}
