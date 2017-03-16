package com.smartdot.meeting.server.modules.systemsetting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.test.GUICommonTester;
import com.smartdot.meeting.server.common.entity.SystemSetting;

public class SystemSettingControllerTester extends GUICommonTester {

	private static final Logger _LOG = Logger.getLogger(SystemSettingControllerTester.class);

	@Test
	public void testQueryAll() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/queryAll").accept(MediaType.APPLICATION_JSON));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}

	@Test
	public void testQueryById() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/queryById").accept(MediaType.APPLICATION_JSON).param("id", ""));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}

	@Test
	public void testPageQuery() throws Exception {
	    Page<SystemSetting> pageQuery= new  Page<SystemSetting>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/pageQuery").accept(MediaType.APPLICATION_JSON).param("domain","").param("pageQuery",""));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}

	@Test
	public void testDelete() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/delete").accept(MediaType.APPLICATION_JSON));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}

	@Test
	public void testDeleteById() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/deleteById").accept(MediaType.APPLICATION_JSON).param("id", ""));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}

	@Test
	public void testDeleteList() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/deleteList").accept(MediaType.APPLICATION_JSON).param("ids", ""));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}

	@Test
	public void testUpdate() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/update").accept(MediaType.APPLICATION_JSON));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}
	
	@Test
	public void testSave() throws Exception {
		ResultActions ra = this.mockMvc.perform(post("/systemSetting/save").accept(MediaType.APPLICATION_JSON));
		Object o = ra.andReturn().getResponse().getContentAsString();
		_LOG.info(o);
	}
	
}
