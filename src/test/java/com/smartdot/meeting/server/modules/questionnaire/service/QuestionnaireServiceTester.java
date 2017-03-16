package com.smartdot.meeting.server.modules.questionnaire.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.test.SVCCommonTester;
import com.smartdot.meeting.server.common.entity.Questionnaire;
import com.smartdot.meeting.server.modules.questionnaire.service.IQuestionnaireService;

/**
 * Questionnaire Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class QuestionnaireServiceTester extends SVCCommonTester {
	@Resource(name = IQuestionnaireService.SERVICE_NAME)
	private IQuestionnaireService questionnaireService;

	@Test
	public void testSaveQuestionnaire() throws Exception {
		Questionnaire instance = new Questionnaire();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = questionnaireService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllQuestionnaire() throws Exception {
		List<Questionnaire> instanceList = new ArrayList<Questionnaire>();
		Questionnaire instance1 = new Questionnaire();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		Questionnaire instance2 = new Questionnaire();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		Questionnaire instance3 = new Questionnaire();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = questionnaireService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllQuestionnaire() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = questionnaireService.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateQuestionnaire() throws Exception {
		Questionnaire instance = new Questionnaire();
		//调用Set方法赋入要修改的值
		boolean flag = questionnaireService.updateQuestionnaire(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteQuestionnaire() throws Exception {
		Questionnaire instance = new Questionnaire();
		//测试用的对象ID
		instance.setId("");
		boolean flag = questionnaireService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = questionnaireService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<Questionnaire> objectList = questionnaireService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (Questionnaire object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetQuestionnaireById() throws Exception {
		//测试用的对象ID
		Questionnaire instance = questionnaireService.getQuestionnaireById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQueryQuestionnairePage() throws Exception {
		Questionnaire questionnaire= new Questionnaire();
	    Page<Questionnaire> pageQuery= new  Page<Questionnaire>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
		Page<Questionnaire> page = questionnaireService.findQuestionnaireByPage(questionnaire,pageQuery);
		Assert.assertNotNull(page);
		Assert.assertNotNull(page.getResult());
		Assert.assertTrue(page.getResult().size() > 0);
	}
}
