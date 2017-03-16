package com.smartdot.meeting.server.modules.cloudgroup.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import com.smartdot.meeting.server.modules.cloudgroup.model.CloudGroupForm;
import com.smartdot.meeting.server.modules.cloudgroup.model.CloudGroupPageForm;
import com.smartdot.meeting.server.modules.cloudgroup.model.CloudGroupVO;
import com.smartdot.meeting.server.modules.cloudgroup.service.ICloudGroupService;
import com.smartdot.meeting.server.common.entity.CloudGroup;


@Controller
@RequestMapping(value = "/cloudGroup")
public class CloudGroupController {

	@SuppressWarnings("unused")
	private static final Logger _LOG = Logger.getLogger(CloudGroupController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ICloudGroupService.SERVICE_NAME)
	private ICloudGroupService cloudGroupService;
	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		return ICloudGroupService.LIST_P;
	}
	
	
	@RequestMapping(value = "/adet")
	public String adet(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return ICloudGroupService.ADET;
	}
	
	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return ICloudGroupService.DETAILS;
	}
	
	

	@RequestMapping(value = "/saveUpdate")
	public @ResponseBody
	ReturnValue saveUpdate(CloudGroupForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		CloudGroup cloudGroup= new CloudGroup();
		BeanUtils.copyProperties(form, cloudGroup);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = cloudGroupService.save(cloudGroup);
		} else {
			result = cloudGroupService.updateCloudGroup(cloudGroup);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(CloudGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}
	
	@RequestMapping(value = "/checkName")
	public @ResponseBody
	boolean checkName(String fgName) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(CloudGroup.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("fgName", fgName));
		List<CloudGroup> dataDictionaryTypes = new ArrayList<CloudGroup>();
		dataDictionaryTypes = cloudGroupService.findByDetachedCriteria(dc);
		if (dataDictionaryTypes.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<CloudGroupForm> form) throws Exception {
	List<CloudGroup> cloudGroups=new ArrayList<CloudGroup>();
		ReturnValue returnValue =new ReturnValue();
		CloudGroup entityCloudGroup = new CloudGroup();
		for (CloudGroupForm cloudGroup : form) {
			entityCloudGroup=new CloudGroup();
			BeanUtils.copyProperties(cloudGroup, entityCloudGroup);
			cloudGroups.add(entityCloudGroup);
		}
		boolean result = cloudGroupService.saveAll(cloudGroups);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(CloudGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = cloudGroupService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(CloudGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = cloudGroupService.deleteAll( Arrays.asList(ids));
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(CloudGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(CloudGroupForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		CloudGroup cloudGroup = new CloudGroup();
		BeanUtils.copyProperties(form, cloudGroup);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = cloudGroupService.updateCloudGroup(cloudGroup);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(CloudGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	CloudGroupVO queryById(String id) throws Exception {
		CloudGroupVO vo = new CloudGroupVO();
		CloudGroup cloudGroup = cloudGroupService.getCloudGroupById(id);
		if (cloudGroup != null) {
			BeanUtils.copyProperties(cloudGroup, vo);
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, CloudGroupPageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(CloudGroup.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		if (req != null) {
			if (StringUtils.isNotBlank(req.getFgName())) {
				dc.add(Restrictions.like("fgName", "%"+ req.getFgName() +"%"));
			}
		}
		return cloudGroupService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
