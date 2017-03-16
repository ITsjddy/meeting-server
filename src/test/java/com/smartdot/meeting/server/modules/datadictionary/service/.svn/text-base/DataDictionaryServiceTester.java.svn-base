package com.smartdot.meeting.server.modules.datadictionary.service;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;

/**
 * DataDictionary Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class DataDictionaryServiceTester extends SVCCommonTester {
	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;

	@Test
	public void testSaveDataDictionary() throws Exception {
		DataDictionary instance = new DataDictionary();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = dataDictionaryService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllDataDictionary() throws Exception {
		List<DataDictionary> instanceList = new ArrayList<DataDictionary>();
		DataDictionary instance1 = new DataDictionary();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		DataDictionary instance2 = new DataDictionary();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		DataDictionary instance3 = new DataDictionary();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = dataDictionaryService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllDataDictionary() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = dataDictionaryService.deleteAll(null);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateDataDictionary() throws Exception {
		DataDictionary instance = new DataDictionary();
		//调用Set方法赋入要修改的值
		boolean flag = dataDictionaryService.updateDataDictionary(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteDataDictionary() throws Exception {
		DataDictionary instance = new DataDictionary();
		//测试用的对象ID
		instance.setId("");
		boolean flag = dataDictionaryService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = dataDictionaryService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<DataDictionary> objectList = dataDictionaryService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (DataDictionary object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetDataDictionaryById() throws Exception {
		//测试用的对象ID
		DataDictionary instance = dataDictionaryService.getDataDictionaryById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQueryDataDictionaryPage() throws Exception {
		DataDictionary dataDictionary= new DataDictionary();
		
	    Page<DataDictionary> pageQuery= new  Page<DataDictionary>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
	    
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		Map<String, Object> page = dataDictionaryService.pagedQuery(dc, pageQuery);
		
		List resultData = (List)page.get("resultData");
		Assert.assertNotNull(page);
		Assert.assertNotNull(resultData);
		Assert.assertTrue(resultData.size() > 0);
	}
}
