package com.smartdot.meeting.server.modules.testDemo.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.TestDemo;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.department.service.IDepartmentService;
import com.smartdot.meeting.server.modules.testDemo.service.ITestService;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	测试增删改查样例
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月5日 下午3:40:30
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	private static Logger logger = Logger.getLogger(TestController.class);
	
	@Resource(name = ITestService.SERVICE_NAME)
	private ITestService testService;
	
	@Resource(name = IDepartmentService.SERVICE_NAME)
	private IDepartmentService departmentService;
	@RequestMapping(value = "/testDept")
	@ResponseBody
	public Map<String, Object> testDept(HttpServletRequest request, HttpServletResponse response,String deptNum,String type,String pdeptNum) throws Exception {
		if (type.equals("create")) {
			return AjaxResult.objectResult(departmentService.createTimeGroupNum(pdeptNum));
		}else if(type.equals("update")){
			return AjaxResult.objectResult(departmentService.updateTimeGroupNum(pdeptNum,deptNum));
		}else if(type.equals("delete")){
			return AjaxResult.objectResult(departmentService.deleteTimeGroupNum(pdeptNum));
		}
		return null;
	}
	/**
	 * test
	 * */
	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/test/testDemoList";
	}
	@RequestMapping(value = "/testList")
	@ResponseBody
	public Map<String, Object> testList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> searchMap = new HashMap<String,Object>();
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			name = new String(name.getBytes("iso-8859-1"),"UTF-8");
			searchMap.put("name", name);
		}
		searchMap.put("enable", true);
		return testService.pageTestList(PageUtilExtent.getPageInfo(request),searchMap);
	}
	@RequestMapping(value = "/testEdit")
	public String testEdit(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("TestController中testGetTestDemoById方法参数错误");
			return null;
		}
		model.addAttribute("id",id);
		//TestDemo testDemo = testService.testGetTestDemoById(id);
	//	model.addAttribute("testDemo", testDemo);
		return "/test/testEdit";
	}
	@RequestMapping(value = "/testGetTestDemoById")
	@ResponseBody
	public Map<String, Object> testGetTestDemoById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(id)){
			logger.error("TestController中testGetTestDemoById方法参数错误");
			return null;
		}
		TestDemo testDemo = testService.testGetTestDemoById(id);
		map.put("id", testDemo.getId());
		map.put("name", testDemo.getName());
		map.put("sex", testDemo.getSex());
		return map;
	}
	@RequestMapping(value = "/testEditSave")
	public String testEditSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			name = new String(name.getBytes("iso-8859-1"),"UTF-8");
		}
		String sex = request.getParameter("sex");
		TestDemo testDemo = testService.testGetTestDemoById(id);
		testDemo.setName(name);
		testDemo.setSex(sex);
		testService.saveTestDemo(testDemo);
		return "redirect:/dispatcher/test/test";
	}
	@RequestMapping(value = "/testAdd")
	public String testAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/test/testAdd";
	}
	@RequestMapping(value = "/testAddSave")
	@ResponseBody
	public Map<String, Object> testSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			name = new String(name.getBytes("iso-8859-1"),"UTF-8");
		}
		String sex = request.getParameter("sex");
		TestDemo testDemo = new TestDemo();
		testDemo.setName(name);
		testDemo.setSex(sex);
		testDemo.setUnCode(UUID.randomUUID().toString());
		testService.saveTestDemo(testDemo);
		resultMap.put("success", true);
		return resultMap;
	}
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		TestDemo testDemo = testService.testGetTestDemoById(id);
		testDemo.setEnable(false);
		testService.saveTestDemo(testDemo);
		return "redirect:/dispatcher/test/test";
	}
	@RequestMapping(value = "/todetail")
	public String todetail(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("TestController中testGetTestDemoById方法参数错误");
			return null;
		}
		model.addAttribute("id",id);
		return "/test/testDetail";
	}
	@RequestMapping(value = "/addData")
	public String addData(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		for(int i=0;i<155;i++){
			TestDemo testDemo = new TestDemo();
			testDemo.setName("name"+(i+1));
			testDemo.setSex(i%2==0?"0":"1");
			testDemo.setUnCode(UUID.randomUUID().toString());
			testService.saveTestDemo(testDemo);
		}
		return null;
	}
}
