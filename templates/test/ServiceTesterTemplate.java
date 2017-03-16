#set ($domain = $!domainName.substring(0,1).toLowerCase()+$!domainName.substring(1))
package $!{packageName}.modules.$!{floder}.service;

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
import com.smartdot.meeting.server.common.entity.$!{domainName};
import com.smartdot.meeting.server.modules.$!{floder}.service.I$!{domainName}Service;

/**
 * $!{domainName} Service测试类
 * 
 * @author 
 * @version 1.0
 */
public class $!{domainName}ServiceTester extends SVCCommonTester {
	@Resource(name = I$!{domainName}Service.SERVICE_NAME)
	private I$!{domainName}Service $!{domain}Service;

	@Test
	public void testSave$!{domainName}() throws Exception {
		$!{domainName} instance = new $!{domainName}();
		//调用Set方法赋值，注意外键不能为空
		boolean flag = $!{domain}Service.save(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testSaveAll$!{domainName}() throws Exception {
		List<$!{domainName}> instanceList = new ArrayList<$!{domainName}>();
		$!{domainName} instance1 = new $!{domainName}();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance1);
		$!{domainName} instance2 = new $!{domainName}();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance2);
		$!{domainName} instance3 = new $!{domainName}();
		//调用Set方法赋值，注意外键不能为空
		instanceList.add(instance3);
		boolean flag = $!{domain}Service.saveAll(instanceList);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAll$!{domainName}() throws Exception {
		List<String> ids = new ArrayList<String>();
		//测试用的对象ID
		String id1 = "";
		ids.add(id1);
		String id2 = "";
		ids.add(id2);
		String id3 = "";
		ids.add(id3);
		boolean flag = $!{domain}Service.deleteAll(ids);
		Assert.assertTrue(flag);
	}

	@Test
	public void testUpdate$!{domainName}() throws Exception {
		$!{domainName} instance = new $!{domainName}();
		//调用Set方法赋入要修改的值
		boolean flag = $!{domain}Service.update$!{domainName}(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDelete$!{domainName}() throws Exception {
		$!{domainName} instance = new $!{domainName}();
		//测试用的对象ID
		instance.setId("");
		boolean flag = $!{domain}Service.remove(instance);
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteMarketServiceEvaluationById() throws Exception {
		//测试用的对象ID
		boolean flag = $!{domain}Service.deleteById("");
		Assert.assertTrue(flag);
	}

	@Test
	public void testFindAll() throws Exception {
		List<$!{domainName}> objectList = $!{domain}Service.findAll();
		if (objectList != null) {
			//0处修改为你自己要测试的对象总数
			Assert.assertEquals(0, objectList.size());
			for ($!{domainName} object : objectList) {
				Assert.assertNotNull(object);
			}
		}
	}

	@Test
	public void testGet$!{domainName}ById() throws Exception {
		//测试用的对象ID
		$!{domainName} instance = $!{domain}Service.get$!{domainName}ById("");
		//括号内应为测试字段值，取出对象的对应字段值
		Assert.assertNotNull(instance);
	}

	@Test
	public void testQuery$!{domainName}Page() throws Exception {
		$!{domainName} $!{domain}= new $!{domainName}();
		
	    Page<$!{domainName}> pageQuery= new  Page<$!{domainName}>();
	    pageQuery.setCurrentPage(1);
	    pageQuery.setPageSize(5);
	    
		DetachedCriteria dc = DetachedCriteria.forClass($!{domainName}.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		Map<String, Object> page = $!{domain}Service.pagedQuery(dc, pageQuery);
		
		List resultData = (List)page.get("resultData");
		Assert.assertNotNull(page);
		Assert.assertNotNull(resultData);
		Assert.assertTrue(resultData.size() > 0);
	}
}
