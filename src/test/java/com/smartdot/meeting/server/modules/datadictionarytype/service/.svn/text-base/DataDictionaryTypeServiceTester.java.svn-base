package com.smartdot.meeting.server.modules.datadictionarytype.service;

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
import com.smartdot.meeting.server.common.entity.DataDictionaryType;
import com.smartdot.meeting.server.modules.datadictionarytype.service.IDataDictionaryTypeService;

/**
 * DataDictionaryType Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class DataDictionaryTypeServiceTester extends SVCCommonTester {
	@Resource(name = IDataDictionaryTypeService.SERVICE_NAME)
	private IDataDictionaryTypeService dataDictionaryTypeService;

	@Test
	public void testSaveDataDictionaryType() throws Exception {
		DataDictionaryType instance = new DataDictionaryType();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = dataDictionaryTypeService.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAllDataDictionaryType() throws Exception {
		List<DataDictionaryType> instanceList = new ArrayList<DataDictionaryType>();
		DataDictionaryType instance1 = new DataDictionaryType();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		DataDictionaryType instance2 = new DataDictionaryType();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		DataDictionaryType instance3 = new DataDictionaryType();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = dataDictionaryTypeService.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAllDataDictionaryType() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = dataDictionaryTypeService.deleteAll(null);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdateDataDictionaryType() throws Exception {
		DataDictionaryType instance = new DataDictionaryType();
		//调用Set方法赋入要修改的值
		boolean flag = dataDictionaryTypeService.updateDataDictionaryType(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteDataDictionaryType() throws Exception {
		DataDictionaryType instance = new DataDictionaryType();
		//测试用的对象ID
		instance.setId("");
		boolean flag = dataDictionaryTypeService.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = dataDictionaryTypeService.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<DataDictionaryType> objectList = dataDictionaryTypeService.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for (DataDictionaryType object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGetDataDictionaryTypeById() throws Exception {
		//测试用的对象ID
		DataDictionaryType instance = dataDictionaryTypeService.getDataDictionaryTypeById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQueryDataDictionaryTypePage() throws Exception {
		DataDictionaryType dataDictionaryType= new DataDictionaryType();
		
	    Page<DataDictionaryType> pageQuery= new  Page<DataDictionaryType>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
	    
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionaryType.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		Map<String, Object> page = dataDictionaryTypeService.pagedQuery(dc, pageQuery);
		
		List resultData = (List)page.get("resultData");
		Assert.assertNotNull(page);
		Assert.assertNotNull(resultData);
		Assert.assertTrue(resultData.size() > 0);
	}
}
