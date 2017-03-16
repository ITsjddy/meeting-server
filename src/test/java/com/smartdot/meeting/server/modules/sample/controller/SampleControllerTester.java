package com.smartdot.meeting.server.modules.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.smartdot.meeting.server.common.test.GUICommonTester;


public class SampleControllerTester extends GUICommonTester {
	
	private static final Logger _LOG = Logger.getLogger(SampleControllerTester.class);
	@Test
	public void testSave() throws Exception {
		
		try {
			ResultActions ra=this.mockMvc.perform(post("/sample/save").accept(MediaType.APPLICATION_JSON)
																	 .param("name", "jim"));
			Object o = ra.andReturn().getResponse().getContentAsString();
			_LOG.info(o);
			ra.andExpect(status().isOk())
			  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			  .andExpect(jsonPath("$.success").value(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
