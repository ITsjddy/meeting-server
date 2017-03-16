package com.smartdot.meeting.server.common.test;

import java.util.Collection;

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

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(value = "classpath*:conf/applicationContext-*.xml")
@ContextConfiguration(locations = {
		"classpath*:conf/applicationContext-default.xml",
		"classpath*:conf/applicationContext-properties.xml",
		"classpath*:conf/applicationContext-datasource.xml",
		"classpath*:conf/applicationContext-hibernate.xml",
		"classpath*:conf/applicationContext-tx.xml",
		"classpath*:conf/applicationContext-cxf.xml",
})
public class SVCCommonTester{
	
	private static final Logger _LOG = Logger.getLogger(SVCCommonTester.class);
	
	@Autowired
	private WebApplicationContext wac;
	
	protected MockMvc mockMvc;
	
	protected void trace(Object o) {
		_LOG.info(JSON.toJSONString(o));
	}
	
	protected void trace(Collection<?> coll) {
		_LOG.info(JSON.toJSONString(coll));
	}
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

}
