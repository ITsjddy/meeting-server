package com.smartdot.meeting.server.modules.testDemo.service.impl;


import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.entity.TestDemo;
import com.smartdot.meeting.server.common.log.Audit;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.testDemo.dao.ITestDao;
import com.smartdot.meeting.server.modules.testDemo.service.ITestService;


@Service(value = ITestService.SERVICE_NAME)
public class TestServiceImpl implements ITestService {
	
	@Resource(name = ITestDao.DAO_NAME)
	private ITestDao testDao;
	
	@Audit(module= "测试模块",action= "测试添加功能",description= "/测试添加功能说明")
	@Override
	public void saveTestDemo(TestDemo testDemo) {
		testDao.save(testDemo);
	}
	@Audit(module= "测试模块",action= "测试分页查询",description= "/测试分页查询")
	@Override
	public Map<String, Object> pageTestList(Page<TestDemo> page, Map<String,Object> searchMap) {
		LinkedHashMap<String,String> orderByMap = new LinkedHashMap<String,String>();
		orderByMap.put("sex", "desc");
		Map<String, Object> pageData = testDao.pageQuery(searchMap,page,orderByMap,false);
		return pageData;
	}
	@Audit(module= "测试模块",action= "测试根据id获取对象",description= "/测试根据id获取对象")
	@Override
	public TestDemo testGetTestDemoById(String id) {
		return testDao.findById(id);
	}
	
}
