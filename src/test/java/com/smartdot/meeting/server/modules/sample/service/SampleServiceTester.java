package com.smartdot.meeting.server.modules.sample.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smartdot.meeting.server.common.entity.SampleEntity;
import com.smartdot.meeting.server.common.test.SVCCommonTester;

public class SampleServiceTester extends SVCCommonTester {

	@Resource(name = ISampleService.SERVICE_NAME)
	private ISampleService sampleService;
	
	@Test
	public void testSave() throws Exception{
		SampleEntity sample = new SampleEntity();
		sample.setName("test" + new Date().toString());
		this.sampleService.save(sample);
	}
	
}
