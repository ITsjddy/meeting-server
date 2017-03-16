package com.smartdot.meeting.server.modules.systemsetting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.test.SVCCommonTester;
import com.smartdot.meeting.server.common.entity.SystemSetting;
import com.smartdot.meeting.server.modules.systemsetting.service.ISystemSettingService;

/**
 * SystemSetting Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class SystemSettingServiceTester extends SVCCommonTester {
	@Resource(name = ISystemSettingService.SERVICE_NAME)
	private ISystemSettingService systemSettingService;

	@Test
	public void testSaveSystemSetting() throws Exception {
		SystemSetting instance = new SystemSetting();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = systemSettingService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllSystemSetting() throws Exception {
		List<SystemSetting> instanceList = new ArrayList<SystemSetting>();
		SystemSetting instance1 = new SystemSetting();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		SystemSetting instance2 = new SystemSetting();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		SystemSetting instance3 = new SystemSetting();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = systemSettingService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllSystemSetting() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
//		boolean flag = systemSettingService.deleteAll(ids);
//		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateSystemSetting() throws Exception {
		SystemSetting instance = new SystemSetting();
		//调用Set方法赋入要修改的值
		boolean flag = systemSettingService.updateSystemSetting(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteSystemSetting() throws Exception {
		SystemSetting instance = new SystemSetting();
		//测试用的对象ID
		instance.setId("");
		boolean flag = systemSettingService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = systemSettingService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<SystemSetting> objectList = systemSettingService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (SystemSetting object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetSystemSettingById() throws Exception {
		//测试用的对象ID
		SystemSetting instance = systemSettingService.getSystemSettingById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQuerySystemSettingPage() throws Exception {
		SystemSetting systemSetting= new SystemSetting();
		
	    Page<SystemSetting> pageQuery= new  Page<SystemSetting>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
	    
		DetachedCriteria dc = DetachedCriteria.forClass(SystemSetting.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		Map<String, Object> page = systemSettingService.pagedQuery(dc, pageQuery);
		
		List resultData = (List)page.get("resultData");
		Assert.assertNotNull(page);
		Assert.assertNotNull(resultData);
		Assert.assertTrue(resultData.size() > 0);
	}
}
