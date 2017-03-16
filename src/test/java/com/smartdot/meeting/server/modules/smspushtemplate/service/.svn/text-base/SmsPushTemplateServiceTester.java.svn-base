package com.smartdot.meeting.server.modules.smspushtemplate.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.smartdot.meeting.server.common.test.SVCCommonTester;
import com.smartdot.meeting.server.common.entity.SmsPushTemplate;
import com.smartdot.meeting.server.modules.smspushtemplate.service.ISmsPushTemplateService;

/**
 * SmsPushTemplate Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class SmsPushTemplateServiceTester extends SVCCommonTester {
	@Resource(name = ISmsPushTemplateService.SERVICE_NAME)
	private ISmsPushTemplateService smsPushTemplateService;

	@Test
	public void testSaveSmsPushTemplate() throws Exception {
		SmsPushTemplate instance = new SmsPushTemplate();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = smsPushTemplateService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllSmsPushTemplate() throws Exception {
		List<SmsPushTemplate> instanceList = new ArrayList<SmsPushTemplate>();
		SmsPushTemplate instance1 = new SmsPushTemplate();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		SmsPushTemplate instance2 = new SmsPushTemplate();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		SmsPushTemplate instance3 = new SmsPushTemplate();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = smsPushTemplateService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllSmsPushTemplate() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = smsPushTemplateService.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateSmsPushTemplate() throws Exception {
		SmsPushTemplate instance = new SmsPushTemplate();
		//调用Set方法赋入要修改的值
		boolean flag = smsPushTemplateService.updateSmsPushTemplate(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteSmsPushTemplate() throws Exception {
		SmsPushTemplate instance = new SmsPushTemplate();
		//测试用的对象ID
		instance.setId("");
		boolean flag = smsPushTemplateService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = smsPushTemplateService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<SmsPushTemplate> objectList = smsPushTemplateService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (SmsPushTemplate object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetSmsPushTemplateById() throws Exception {
		//测试用的对象ID
		SmsPushTemplate instance = smsPushTemplateService.getSmsPushTemplateById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

}
