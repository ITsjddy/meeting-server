package com.smartdot.meeting.server.modules.datadictionarytype.controller;

import java.util.ArrayList;
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
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.datadictionarytype.model.DataDictionaryTypeForm;
import com.smartdot.meeting.server.modules.datadictionarytype.model.DataDictionaryTypePageForm;
import com.smartdot.meeting.server.modules.datadictionarytype.model.DataDictionaryTypeVO;
import com.smartdot.meeting.server.modules.datadictionarytype.service.IDataDictionaryTypeService;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.DataDictionaryType;


@Controller
@RequestMapping(value = "/dataDictionaryType")
public class DataDictionaryTypeController {

	@SuppressWarnings("unused")
	private static final Logger _LOG = Logger.getLogger(DataDictionaryTypeController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IDataDictionaryTypeService.SERVICE_NAME)
	private IDataDictionaryTypeService dataDictionaryTypeService;
	
	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;
	

	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "dataDictionaryType/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "dataDictionaryType/add";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "dataDictionaryType/add";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "dataDictionaryType/details";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<DataDictionaryTypeVO> queryAll() throws Exception {
		List<DataDictionaryType> dataDictionaryTypes = dataDictionaryTypeService.findAll();
		List<DataDictionaryTypeVO> vo = new ArrayList<DataDictionaryTypeVO>();
		DataDictionaryTypeVO dataDictionaryTypeVO = null;
		for (DataDictionaryType dataDictionaryType : dataDictionaryTypes) {
			dataDictionaryTypeVO  = new DataDictionaryTypeVO();
			BeanUtils.copyProperties(dataDictionaryType, dataDictionaryTypeVO);
			vo.add(dataDictionaryTypeVO);
		}
		return vo;
	}
	
	@RequestMapping(value = "/checkName")
	public @ResponseBody
	boolean checkName(String dTypeName) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionaryType.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dTypeName", dTypeName));
		List<DataDictionaryType> dataDictionaryTypes = new ArrayList<DataDictionaryType>();
		dataDictionaryTypes = dataDictionaryTypeService.findByDetachedCriteria(dc);
		if (dataDictionaryTypes.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	@RequestMapping(value = "/checkOnlyLogo")
	public @ResponseBody
	boolean checkOnlyLogo(String dTypeLogo) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionaryType.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dTypeLogo", dTypeLogo));
		List<DataDictionaryType> dataDictionaryTypes = new ArrayList<DataDictionaryType>();
		dataDictionaryTypes = dataDictionaryTypeService.findByDetachedCriteria(dc);
		if (dataDictionaryTypes.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(DataDictionaryTypeForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		DataDictionaryType dataDictionaryType = new DataDictionaryType();
		BeanUtils.copyProperties(form, dataDictionaryType);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = dataDictionaryTypeService.save(dataDictionaryType);
		} else {
			DataDictionaryType oldDataDictionaryType = new DataDictionaryType();
			oldDataDictionaryType = dataDictionaryTypeService.getDataDictionaryTypeById(form.getId());
			DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("dDTypeLogo", oldDataDictionaryType.getdTypeLogo()));
			List<DataDictionary> dataDictionaries  = dataDictionaryService.findByDetachedCriteria(dc);
			if (dataDictionaries !=null && dataDictionaries.size() > 0) {
				for (DataDictionary dataDictionary : dataDictionaries) {
					dataDictionary.setdDType(form.getDTypeName());
					dataDictionary.setdDTypeLogo(form.getDTypeLogo());
					dataDictionaryService.updateDataDictionary(dataDictionary);
				}
			}
			dataDictionaryTypeService.remove(oldDataDictionaryType);
			result = dataDictionaryTypeService.save(dataDictionaryType);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryTypeController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<DataDictionaryTypeForm> form) throws Exception {
	List<DataDictionaryType> dataDictionaryTypes=new ArrayList<DataDictionaryType>();
		ReturnValue returnValue =new ReturnValue();
		DataDictionaryType entityDataDictionaryType = new DataDictionaryType();
		for (DataDictionaryTypeForm dataDictionaryType : form) {
			entityDataDictionaryType=new DataDictionaryType();
			BeanUtils.copyProperties(dataDictionaryType, entityDataDictionaryType);
			dataDictionaryTypes.add(entityDataDictionaryType);
		}
		boolean result = dataDictionaryTypeService.saveAll(dataDictionaryTypes);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryTypeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		DataDictionaryType oldDataDictionaryType = new DataDictionaryType();
		oldDataDictionaryType = dataDictionaryTypeService.getDataDictionaryTypeById(id);
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dDTypeLogo", oldDataDictionaryType.getdTypeLogo()));
		List<DataDictionary> dataDictionaries =  dataDictionaryService.findByDetachedCriteria(dc);
		if (dataDictionaries != null && dataDictionaries.size() > 0) {
			dataDictionaryService.deleteByList(dataDictionaries);
		}
		boolean result = dataDictionaryTypeService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryTypeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			try {
				for (int i = 0; i < ids.length; i++) {
					DataDictionaryType dictionaryType	= dataDictionaryTypeService.getDataDictionaryTypeById(ids[i]);
					if (StringUtils.isNotBlank(dictionaryType.getdTypeLogo())) {
						DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
						dc.add(Restrictions.eq("enable", true));
						dc.add(Restrictions.eq("dDTypeLogo", dictionaryType.getdTypeLogo()));
						List<DataDictionary> dictionaries = dataDictionaryService.findByDetachedCriteria(dc);
						dataDictionaryService.deleteByList(dictionaries);
					}
					dataDictionaryTypeService.deleteById(ids[i]);
					result = true;
				}
			} catch (Exception e) {
				result = false;
			}
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryTypeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(DataDictionaryTypeForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		DataDictionaryType dataDictionaryType = new DataDictionaryType();
		BeanUtils.copyProperties(form, dataDictionaryType);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = dataDictionaryTypeService.updateDataDictionaryType(dataDictionaryType);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryTypeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	DataDictionaryTypeVO queryById(String id) throws Exception {
		DataDictionaryTypeVO vo = new DataDictionaryTypeVO();
		DataDictionaryType dataDictionaryType = dataDictionaryTypeService.getDataDictionaryTypeById(id);
		if (dataDictionaryType != null) {
			BeanUtils.copyProperties(dataDictionaryType, vo);
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, DataDictionaryTypePageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionaryType.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		if (req != null) {
			if (StringUtils.isNotBlank(req.getDTypeName())) {
				dc.add(Restrictions.like("dTypeName", "%"+ req.getDTypeName() +"%"));
			}
			if (StringUtils.isNotBlank(req.getDTypeLogo())) {
				dc.add(Restrictions.like("dTypeLogo", "%"+ req.getDTypeLogo() +"%"));
			}
			if (StringUtils.isNotBlank(req.getDTypeExplanation())) {
				dc.add(Restrictions.like("dTypeExplanation", "%"+ req.getDTypeExplanation() +"%"));
			}
		}
		return dataDictionaryTypeService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
