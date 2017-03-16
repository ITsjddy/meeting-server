package com.smartdot.meeting.server.modules.jpushentiy.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.smartdot.meeting.server.common.test.SVCCommonTester;
import com.smartdot.meeting.server.common.entity.JpushEntiy;
import com.smartdot.meeting.server.modules.jpushentiy.service.IJpushEntiyService;

/**
 * JpushEntiy Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class JpushEntiyServiceTester extends SVCCommonTester {
	@Resource(name = IJpushEntiyService.SERVICE_NAME)
	private IJpushEntiyService jpushEntiyService;

	@Test
	public void testSaveJpushEntiy() throws Exception {
		JpushEntiy instance = new JpushEntiy();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = jpushEntiyService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllJpushEntiy() throws Exception {
		List<JpushEntiy> instanceList = new ArrayList<JpushEntiy>();
		JpushEntiy instance1 = new JpushEntiy();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		JpushEntiy instance2 = new JpushEntiy();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		JpushEntiy instance3 = new JpushEntiy();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = jpushEntiyService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllJpushEntiy() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = jpushEntiyService.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateJpushEntiy() throws Exception {
		JpushEntiy instance = new JpushEntiy();
		//调用Set方法赋入要修改的值
		boolean flag = jpushEntiyService.updateJpushEntiy(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteJpushEntiy() throws Exception {
		JpushEntiy instance = new JpushEntiy();
		//测试用的对象ID
		instance.setId("");
		boolean flag = jpushEntiyService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = jpushEntiyService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<JpushEntiy> objectList = jpushEntiyService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (JpushEntiy object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetJpushEntiyById() throws Exception {
		//测试用的对象ID
		JpushEntiy instance = jpushEntiyService.getJpushEntiyById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	
}
