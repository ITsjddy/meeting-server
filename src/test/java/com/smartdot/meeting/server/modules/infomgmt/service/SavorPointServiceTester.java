package com.smartdot.meeting.server.modules.infomgmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.smartdot.meeting.server.common.test.SVCCommonTester;
import com.smartdot.meeting.server.common.entity.SavorPoint;
import com.smartdot.meeting.server.modules.infomgmt.service.ISavorPointService;

/**
 * SavorPoint Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class SavorPointServiceTester extends SVCCommonTester {
	@Resource(name = ISavorPointService.SERVICE_NAME)
	private ISavorPointService savorPointService;

	@Test
	public void testSaveSavorPoint() throws Exception {
		SavorPoint instance = new SavorPoint();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = savorPointService.save(instance);
		Assert.assertTrue(flag);
	}
/*
	@Test
	public void testSaveAllSavorPoint() throws Exception {
		List<SavorPoint> instanceList = new ArrayList<SavorPoint>();
		SavorPoint instance1 = new SavorPoint();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		SavorPoint instance2 = new SavorPoint();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		SavorPoint instance3 = new SavorPoint();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = savorPointService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllSavorPoint() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = savorPointService.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateSavorPoint() throws Exception {
		SavorPoint instance = new SavorPoint();
		//调用Set方法赋入要修改的值
		boolean flag = savorPointService.updateSavorPoint(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteSavorPoint() throws Exception {
		SavorPoint instance = new SavorPoint();
		//测试用的对象ID
		instance.setId("");
		boolean flag = savorPointService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = savorPointService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<SavorPoint> objectList = savorPointService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (SavorPoint object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetSavorPointById() throws Exception {
		//测试用的对象ID
		SavorPoint instance = savorPointService.getSavorPointById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}*/

}
