package com.smartdot.meeting.server.modules.sample.rs;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.smartdot.meeting.server.common.dao.RSCommonTester;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.rs.facade.CommonFacade;
import com.smartdot.meeting.server.modules.sample.rs.facade.model.req.RSSampleSaveReq;

public class SampleRSTester extends RSCommonTester {
	private static final Logger _LOG = Logger.getLogger(SampleRSTester.class);
	
	@Test
	public void testQueryJSONWithAuthorization() {
		
		client.replacePath(path("sample/save"));
		
		client.accept(MediaType.APPLICATION_JSON_TYPE);
		client.header("Content-Type", CommonFacade.APPLICATION_JSON_UTF_8);
		
		RSSampleSaveReq req = new RSSampleSaveReq();
		req.setName("Jack44444");

		ReturnValue resp = client.post(req, ReturnValue.class);
		Assert.assertNotNull(resp);
		Assert.assertTrue(resp.isSuccess());
	}
}
