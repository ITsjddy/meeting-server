package com.smartdot.meeting.server.common.dao;

import org.springframework.transaction.annotation.Transactional;

import com.smartdot.meeting.server.common.test.SVCCommonTester;

/*import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iss.cloud.portal.common.model.Page;
import com.iss.cloud.portal.common.test.SVCCommonTester;
import com.iss.cloud.portal.modules.sample.dao.IUserDao;
import com.iss.cloud.portal.modules.sample.entity.TUser;*/

/**
 * the cases in tester should cover all the methods defined in class
 * GenericDaoHibernateSupport
 * 
 * @see com.iss.cloud.portal.common.dao.GenericDaoHibernateSupport
 * 
 */
@Transactional(readOnly=false)
public class GenericDaoHibernateSupportTester extends SVCCommonTester {
/*
	private static final Logger _LOG = Logger.getLogger(GenericDaoHibernateSupportTester.class);

	@Resource(name = "dataSource")
	private DataSource dataSource;
		
	
	
	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void testSave() {
		TUser user = new TUser();
		user.setId(100001L);
		user.setUsername("Tom");
		userDao.save(user);
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testFindAll() {
		List<TUser> users = userDao.findAll();
		trace(users);
		Assert.assertEquals(5, users.size());
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void testDelete() {
		TUser user = new TUser();
		user.setId(7000001L);
		userDao.delete(user);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void testUpdate() {
		TUser user = new TUser();
		user.setId(7000002L);
		user.setUsername("Jack");
		userDao.update(user);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void testDeleteById() {
		userDao.deleteById(7000001L);
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testExcuteQuery() {
		try {
			ResultSet rs = userDao.executeQuery("select id,username from T_User");
			int i=0;
			while (rs.next()) {
				_LOG.info("id:" + rs.getObject("id") + "   " + "username:" + rs.getObject("username"));
				i++;
			}
			Assert.assertEquals(10, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testFindAllByProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 7000001L);
		map.put("username", "U_1");
		List<TUser> users = userDao.findAllByProperties(map);
		trace(users);
		Assert.assertEquals(1, users.size());
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testFindAllByProperty() {
		List<TUser> users = userDao.findAllByProperty("username", "U_1");
		trace(users);
		Assert.assertEquals(1, users.size());
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testFindByHQL() {
		List<TUser> users = userDao.findByHQL("from TUser");
		trace(users);
		Assert.assertEquals(10, users.size());
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testFindById() {
		TUser user = userDao.findById(7000001L);
		trace(user);
		Assert.assertEquals("U_1", user.getUsername());
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testFindColumnCount() {
		int count = userDao.findColumnCount("username", "U_2");
		_LOG.info("COUNT:" + count);
		Assert.assertEquals(1, count);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void testSaveAll() {
		List<TUser> users = new ArrayList<TUser>();
		for (int i = 0; i < 10; i++) {
			TUser user = new TUser();
			user.setId(10001L + i);
			user.setUsername("Jim" + i);
			users.add(user);
		}
		userDao.saveAll(users);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void testDeleteAll() {
		List<TUser> users = new ArrayList<TUser>();
		for (int i = 0; i < 10; i++) {
			TUser user = new TUser();
			user.setId(10001L + i);
			user.setUsername("Jim" + i);
			users.add(user);
		}
		userDao.deleteAll(users);
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testSelectBySqlCondition() {
		List<TUser> users = userDao.selectBySqlCondition("id::=7000003", "username::='U_3'");
		trace(users);
		Assert.assertEquals(1,users.size());
	}

	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testSelectBySqlConditionOr() {
		List<TUser> users = userDao.selectBySqlConditionOr("id::=7000002", "username::='U_1'");
		trace(users);
		Assert.assertEquals(2,users.size());
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testPageQuery(){
		Page<TUser> page = new Page<TUser>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		page = userDao.pageQuery(page);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		trace(page.getResult());
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(10,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testPageQueryOverLoad1(){
		Page<TUser> page = new Page<TUser>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U_1");
		page = userDao.pageQuery(conditionMap, page);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		trace(page.getResult());	
		
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(1,page.getTotalCount());
		Assert.assertEquals(5,page.getPageSize());
		Assert.assertEquals(1,page.getResult().size());
	
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testPageQueryOverLoad2(){
		Page<TUser> page = new Page<TUser>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		LinkedHashMap<String,String> orderBy=new LinkedHashMap<String,String>();
		orderBy.put("id", "desc");
		page = userDao.pageQuery(page, orderBy);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		trace(page.getResult());	
		List<TUser> users=page.getResult();
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(10,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
		for(int i=0;i<page.getResult().size()-1;i++){
			Assert.assertTrue(users.get(i).getId()>users.get(i+1).getId());
		}
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testPageQueryOverLoad3(){
		Page<TUser> page = new Page<TUser>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("id", "desc");
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U_1");
		page = userDao.pageQuery(conditionMap, page, orderby);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		trace(page.getResult());	
		
		List<TUser> users=page.getResult();
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(1,page.getTotalCount());
		Assert.assertEquals(5,page.getPageSize());
		Assert.assertEquals(1,page.getResult().size());
		
		for(int i=0;i<page.getResult().size()-1;i++){
			Assert.assertTrue(users.get(i).getId()>users.get(i+1).getId());
		}
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testPageQueryOverLoad4(){
		Page<TUser> page = new Page<TUser>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U");
		page = userDao.pageQuery(conditionMap, page, true);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		trace(page.getResult());	
		
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(10,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testPageQueryOverLoad5(){
		Page<TUser> page = new Page<TUser>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U");
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("id", "desc");
		page = userDao.pageQuery(conditionMap, page, orderby, true);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		trace(page.getResult());	
		List<TUser> users=page.getResult();
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(10,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
		for(int i=0;i<page.getResult().size()-1;i++){
			Assert.assertTrue(users.get(i).getId()>users.get(i+1).getId());
		}
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void testLoadById(){
		TUser queryUser=userDao.findById(7000001L);
		TUser loadUser=userDao.loadById(7000001L);
		trace(loadUser);
		Assert.assertEquals(queryUser, loadUser);
		Assert.assertEquals("U_1", loadUser.getUsername());		
	}
	
	@After
	public void destroy(){
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.execute("DELETE FROM T_User");
	}
*/
}
