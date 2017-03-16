package com.smartdot.meeting.server.modules.cloudgroup.service;

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
import com.smartdot.meeting.server.common.entity.CloudGroup;
import com.smartdot.meeting.server.modules.cloudgroup.service.ICloudGroupService;

/**
 * CloudGroup Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class CloudGroupServiceTester extends SVCCommonTester {
	@Resource(name = ICloudGroupService.SERVICE_NAME)
	private ICloudGroupService cloudGroupService;

	@Test
	public void testSaveCloudGroup() throws Exception {
		CloudGroup instance = new CloudGroup();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = cloudGroupService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllCloudGroup() throws Exception {
		List<CloudGroup> instanceList = new ArrayList<CloudGroup>();
		CloudGroup instance1 = new CloudGroup();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		CloudGroup instance2 = new CloudGroup();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		CloudGroup instance3 = new CloudGroup();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = cloudGroupService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllCloudGroup() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = cloudGroupService.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateCloudGroup() throws Exception {
		CloudGroup instance = new CloudGroup();
		//调用Set方法赋入要修改的值
		boolean flag = cloudGroupService.updateCloudGroup(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteCloudGroup() throws Exception {
		CloudGroup instance = new CloudGroup();
		//测试用的对象ID
		instance.setId("");
		boolean flag = cloudGroupService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = cloudGroupService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<CloudGroup> objectList = cloudGroupService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (CloudGroup object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetCloudGroupById() throws Exception {
		//测试用的对象ID
		CloudGroup instance = cloudGroupService.getCloudGroupById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQueryCloudGroupPage() throws Exception {
		CloudGroup cloudGroup= new CloudGroup();
		
	    Page<CloudGroup> pageQuery= new  Page<CloudGroup>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
	    
		DetachedCriteria dc = DetachedCriteria.forClass(CloudGroup.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		Map<String, Object> page = cloudGroupService.pagedQuery(dc, pageQuery);
		
		List resultData = (List)page.get("resultData");
		Assert.assertNotNull(page);
		Assert.assertNotNull(resultData);
		Assert.assertTrue(resultData.size() > 0);
	}
}
