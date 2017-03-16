package com.smartdot.meeting.server.common.test;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * The common test class, every case for Controller test needs to extend this class.
 * 
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:conf/dispatcher-servlet.xml",  
								   "classpath*:conf/applicationContext-default.xml",
								   "classpath*:conf/applicationContext-properties.xml",
								   "classpath*:conf/applicationContext-datasource.xml",
								   "classpath*:conf/applicationContext-hibernate.xml",
								   "classpath*:conf/applicationContext-tx.xml",
								   "classpath*:conf/applicationContext-redis.xml"})
public class GUICommonTester {
	
	private static final Logger _LOG = Logger.getLogger(GUICommonTester.class);

	@Autowired
	private WebApplicationContext wac;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	public void trace(Object o) {
		_LOG.info(o);
	}
}
