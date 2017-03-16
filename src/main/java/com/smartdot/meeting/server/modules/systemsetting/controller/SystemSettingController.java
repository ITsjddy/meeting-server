package com.smartdot.meeting.server.modules.systemsetting.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.systemsetting.model.SystemSettingForm;
import com.smartdot.meeting.server.modules.systemsetting.model.SystemSettingPageForm;
import com.smartdot.meeting.server.modules.systemsetting.model.SystemSettingVO;
import com.smartdot.meeting.server.modules.systemsetting.service.ISystemSettingService;
import com.smartdot.meeting.server.common.entity.SystemSetting;


@Controller
@RequestMapping(value = "/systemSetting")
public class SystemSettingController {

	@SuppressWarnings("unused")
	private static final Logger _LOG = Logger.getLogger(SystemSettingController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ISystemSettingService.SERVICE_NAME)
	private ISystemSettingService systemSettingService;

	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		return "systemSetting/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		return "systemSetting/add";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "systemSetting/edit";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "systemSetting/details";
	}
	
	
	@RequestMapping(value = "/checkOnlyLogo")
	public @ResponseBody
	boolean checkOnlyLogo(String uniqueId) throws Exception {
		return systemSettingService.findByDetachedCriteria(uniqueId);
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(SystemSettingForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SystemSetting systemSetting= new SystemSetting();
		BeanUtils.copyProperties(form, systemSetting);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = systemSettingService.save(systemSetting);
		} else {
			result = systemSettingService.updateSystemSetting(systemSetting);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SystemSettingController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}
	
	@RequestMapping(value = "/changeStatus")
	public @ResponseBody
	ReturnValue changeStatus(SystemSettingForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SystemSetting systemSetting= new SystemSetting();
		BeanUtils.copyProperties(form, systemSetting);
		boolean result = false;
		if (StringUtils.isNotBlank(form.getId())) {
			if (form.getStatus().equals("enabled")) {
				systemSetting.setStatus("disabled");
			}
			if (form.getStatus().equals("disabled")) {
				systemSetting.setStatus("enabled");
			}
			result = systemSettingService.updateSystemSetting(systemSetting);
		} else {
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SystemSettingController.SUCCESS_MESSAGE);
		}
		return returnValue;
		
	}

	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = systemSettingService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SystemSettingController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = systemSettingService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SystemSettingController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}


	@RequestMapping(value = "/queryById")
	public @ResponseBody
	SystemSettingVO queryById(String id) throws Exception {
		SystemSettingVO vo = new SystemSettingVO();
		SystemSetting systemSetting = systemSettingService.getSystemSettingById(id);
		if (systemSetting != null) {
			BeanUtils.copyProperties(systemSetting, vo);
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, SystemSettingPageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(SystemSetting.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		if (req != null) {
			if (StringUtils.isNotBlank(req.getStatus())) {
				dc.add(Restrictions.like("status", "%"+ req.getStatus() +"%"));
			}
			if (StringUtils.isNotBlank(req.getUniqueId())) {
				dc.add(Restrictions.like("uniqueId", "%"+ req.getUniqueId() +"%"));
			}
			if (StringUtils.isNotBlank(req.getContent())) {
				dc.add(Restrictions.like("content", "%"+ req.getContent() +"%"));
			}
		}
		Map<String, Object> map = systemSettingService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
		return map;
	}
}
