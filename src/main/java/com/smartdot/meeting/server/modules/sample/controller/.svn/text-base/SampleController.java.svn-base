package com.smartdot.meeting.server.modules.sample.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.SampleEntity;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.modules.sample.service.ISampleService;

@Controller
@RequestMapping(value = "/sample")
public class SampleController {
	
	@Resource(name = ISampleService.SERVICE_NAME)
	private ISampleService sampleService;
	
	@RequestMapping(value = "/index")
	public String  index(Model model) throws Exception {
		return "sample/sampleIndex";
	}
	
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(String name, HttpSession session) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		SampleEntity sample = new SampleEntity();
		sample.setName(name);
		sampleService.save(sample);
		returnValue.setSuccess(true);
		//returnValue.setMessage("保存成功");
		return returnValue;
	}
}
