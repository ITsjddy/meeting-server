package com.smartdot.meeting.server.modules.smspushs.service;

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
import com.smartdot.meeting.server.common.entity.SmsPushs;
import com.smartdot.meeting.server.modules.smspushs.service.ISmsPushsService;

/**
 * SmsPushs Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class SmsPushsServiceTester extends SVCCommonTester {
	@Resource(name = ISmsPushsService.SERVICE_NAME)
	private ISmsPushsService smsPushsService;

	@Test
	public void testSaveSmsPushs() throws Exception {
		SmsPushs instance = new SmsPushs();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = smsPushsService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllSmsPushs() throws Exception {
		List<SmsPushs> instanceList = new ArrayList<SmsPushs>();
		SmsPushs instance1 = new SmsPushs();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		SmsPushs instance2 = new SmsPushs();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		SmsPushs instance3 = new SmsPushs();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = smsPushsService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllSmsPushs() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = smsPushsService.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateSmsPushs() throws Exception {
		SmsPushs instance = new SmsPushs();
		//调用Set方法赋入要修改的值
		boolean flag = smsPushsService.updateSmsPushs(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteSmsPushs() throws Exception {
		SmsPushs instance = new SmsPushs();
		//测试用的对象ID
		instance.setId("");
		boolean flag = smsPushsService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = smsPushsService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<SmsPushs> objectList = smsPushsService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (SmsPushs object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetSmsPushsById() throws Exception {
		//测试用的对象ID
		SmsPushs instance = smsPushsService.getSmsPushsById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQuerySmsPushsPage() throws Exception {
		SmsPushs smsPushs= new SmsPushs();
		
	    Page<SmsPushs> pageQuery= new  Page<SmsPushs>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
	    
		DetachedCriteria dc = DetachedCriteria.forClass(SmsPushs.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		Map<String, Object> page = smsPushsService.pagedQuery(dc, pageQuery);
		
		List resultData = (List)page.get("resultData");
		Assert.assertNotNull(page);
		Assert.assertNotNull(resultData);
		Assert.assertTrue(resultData.size() > 0);
	}
}
