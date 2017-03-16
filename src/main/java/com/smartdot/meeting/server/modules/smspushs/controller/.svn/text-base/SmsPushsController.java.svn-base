package com.smartdot.meeting.server.modules.smspushs.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.smspushs.model.SmsPushsForm;
import com.smartdot.meeting.server.modules.smspushs.model.SmsPushsPageForm;
import com.smartdot.meeting.server.modules.smspushs.model.SmsPushsVO;
import com.smartdot.meeting.server.modules.smspushs.service.ISmsPushsService;
import com.smartdot.meeting.server.common.entity.SmsPushs;


@Controller
@RequestMapping(value = "/smsPushs")
public class SmsPushsController {

	private static final Logger _LOG = Logger.getLogger(SmsPushsController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ISmsPushsService.SERVICE_NAME)
	private ISmsPushsService smsPushsService;

	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<SmsPushsVO> queryAll() throws Exception {
		List<SmsPushs> smsPushss = smsPushsService.findAll();
		List<SmsPushsVO> vo = new ArrayList<SmsPushsVO>();
		SmsPushsVO smsPushsVO = null;
		for (SmsPushs smsPushs : smsPushss) {
			smsPushsVO  = new SmsPushsVO();
			BeanUtils.copyProperties(smsPushs, smsPushsVO);
			vo.add(smsPushsVO);
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(SmsPushsForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SmsPushs smsPushs= new SmsPushs();
		BeanUtils.copyProperties(form, smsPushs);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = smsPushsService.save(smsPushs);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushsController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<SmsPushsForm> form) throws Exception {
	List<SmsPushs> smsPushss=new ArrayList<SmsPushs>();
		ReturnValue returnValue =new ReturnValue();
		SmsPushs entitySmsPushs = new SmsPushs();
		for (SmsPushsForm smsPushs : form) {
			entitySmsPushs=new SmsPushs();
			BeanUtils.copyProperties(smsPushs, entitySmsPushs);
			smsPushss.add(entitySmsPushs);
		}
		boolean result = smsPushsService.saveAll(smsPushss);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushsController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = smsPushsService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushsController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = smsPushsService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushsController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(SmsPushsForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		SmsPushs smsPushs = new SmsPushs();
		BeanUtils.copyProperties(form, smsPushs);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = smsPushsService.updateSmsPushs(smsPushs);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushsController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	SmsPushsVO queryById(String id) throws Exception {
		SmsPushsVO vo = new SmsPushsVO();
		SmsPushs smsPushs = smsPushsService.getSmsPushsById(id);
		if (smsPushs != null) {
			BeanUtils.copyProperties(smsPushs, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, SmsPushsPageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(SmsPushs.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return smsPushsService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
