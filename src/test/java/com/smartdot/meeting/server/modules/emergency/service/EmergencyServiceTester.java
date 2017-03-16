package com.smartdot.meeting.server.modules.emergency.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.test.SVCCommonTester;
import com.smartdot.meeting.server.common.entity.Emergency;
import com.smartdot.meeting.server.modules.emergency.service.IEmergencyService;

/**
 * Emergency Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class EmergencyServiceTester extends SVCCommonTester {
	@Resource(name = IEmergencyService.SERVICE_NAME)
	private IEmergencyService emergencyService;

	@Test
	public void testSaveEmergency() throws Exception {
		Emergency instance = new Emergency();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = emergencyService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllEmergency() throws Exception {
		List<Emergency> instanceList = new ArrayList<Emergency>();
		Emergency instance1 = new Emergency();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		Emergency instance2 = new Emergency();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		Emergency instance3 = new Emergency();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = emergencyService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllEmergency() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = emergencyService.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateEmergency() throws Exception {
		Emergency instance = new Emergency();
		//调用Set方法赋入要修改的值
		boolean flag = emergencyService.updateEmergency(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteEmergency() throws Exception {
		Emergency instance = new Emergency();
		//测试用的对象ID
		instance.setId("");
		boolean flag = emergencyService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = emergencyService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<Emergency> objectList = emergencyService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (Emergency object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetEmergencyById() throws Exception {
		//测试用的对象ID
		Emergency instance = emergencyService.getEmergencyById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQueryEmergencyPage() throws Exception {
		Emergency emergency= new Emergency();
	    Page<Emergency> pageQuery= new  Page<Emergency>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
		Page<Emergency> page = emergencyService.findEmergencyByPage(emergency,pageQuery);
		Assert.assertNotNull(page);
		Assert.assertNotNull(page.getResult());
		Assert.assertTrue(page.getResult().size() > 0);
	}
}
