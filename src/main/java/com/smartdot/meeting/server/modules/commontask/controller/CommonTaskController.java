package com.smartdot.meeting.server.modules.commontask.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.CommonTask;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.commontask.service.ICommonTaskService;

/**
 * 公共任务
 * @author yanjj
 * @since 2017.2.9
 */
@Controller
@RequestMapping(value = "/commonTask")
public class CommonTaskController {

	private static final Logger _LOG = Logger.getLogger(CommonTaskController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ICommonTaskService.SERVICE_NAME)
	private ICommonTaskService commonTaskService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/commonTask/commonTaskList";
	}
	
	@RequestMapping(value = "/queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> searchMap = new HashMap<String,Object>();
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			name = new String(name.getBytes("iso-8859-1"),"UTF-8");
			searchMap.put("name", name);
		}
		String uniqueCode = request.getParameter("uniqueCode");
		if(StringUtils.isNotBlank(uniqueCode)){
			uniqueCode = new String(uniqueCode.getBytes("iso-8859-1"),"UTF-8");
			searchMap.put("uniqueCode", uniqueCode);
		}
		searchMap.put("enable", true);
		return commonTaskService.pageList(PageUtilExtent.getPageInfo(request),searchMap);
	}
	
	@RequestMapping(value = "/commonTaskAdd")
	public String commonTaskAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/commonTask/commonTaskAdd";
	}
	
	@RequestMapping(value = "/commonTaskAddSave", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public String commonTaskAddSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String uniqueCode = request.getParameter("uniqueCode");
		String sort = request.getParameter("sort");
		String remark = request.getParameter("remark");
		
		CommonTask commonTask = new CommonTask();
		commonTask.setName(name);
		commonTask.setUniqueCode(uniqueCode);
		commonTask.setSort(Integer.valueOf(sort));
		commonTask.setRemark(remark);
		
		commonTaskService.save(commonTask);
		
		return "redirect:/dispatcher/commonTask/index";
	}
	
	@RequestMapping(value = "/commonTaskEdit")
	public String commonTaskEdit(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			return null;
		}
		model.addAttribute("id",id);
		return "/commonTask/commonTaskEdit";
	}
	
	@RequestMapping(value = "/commonTaskFindById")
	@ResponseBody
	public Map<String, Object> commonTaskFindById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(id)){
			return null;
		}
		CommonTask commonTask =commonTaskService.getCommonTaskById(id);
		map.put("id", commonTask.getId());
		map.put("name", commonTask.getName());
		map.put("uniqueCode", commonTask.getUniqueCode());
		map.put("sort", commonTask.getSort());
		map.put("remark", commonTask.getRemark());
		return map;
	}
	
	@RequestMapping(value = "/commonTaskEditSave", produces = { "application/json" }, method = RequestMethod.POST)
	public String commonTaskEditSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String uniqueCode = request.getParameter("uniqueCode");
		String sort = request.getParameter("sort");
		String remark = request.getParameter("remark");
		
		CommonTask commonTask = commonTaskService.getCommonTaskById(id);
		commonTask.setName(name);
		commonTask.setUniqueCode(uniqueCode);
		commonTask.setSort(Integer.valueOf(sort));
		commonTask.setRemark(remark);
		
		commonTaskService.updateCommonTask(commonTask);
		
		return "redirect:/dispatcher/commonTask/index";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		CommonTask commonTask = commonTaskService.getCommonTaskById(id);
		if(commonTask !=null){
			commonTask.setEnable(false);
			commonTaskService.save(commonTask);
		}
		
		return "redirect:/dispatcher/commonTask/index";
	}
	
	@RequestMapping(value = "/findByUniqueCode")
	@ResponseBody
	public Map<String, Object> commonTaskFindByUniqueCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uniqueCode = request.getParameter("uniqueCode");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(uniqueCode)){
			return null;
		}
		List<CommonTask> commonTaskList =commonTaskService.findAllByProperty("UNIQUE_CODE", uniqueCode);
		map.put("count", commonTaskList.size());
		return map;
	}
	
	@RequestMapping(value = "/commonTaskSign")
	public String commonTaskSign(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			return null;
		}
		model.addAttribute("id",id);
		return "/commonTask/commonTaskSignMessge";
	}
	
}
