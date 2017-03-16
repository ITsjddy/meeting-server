package com.smartdot.meeting.server.modules.importtemplate.controller;

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
import com.smartdot.meeting.server.modules.importtemplate.model.ImportTemplateForm;
import com.smartdot.meeting.server.modules.importtemplate.model.ImportTemplatePageForm;
import com.smartdot.meeting.server.modules.importtemplate.model.ImportTemplateVO;
import com.smartdot.meeting.server.modules.importtemplate.service.IImportTemplateService;
import com.smartdot.meeting.server.common.entity.ImportTemplate;


@Controller
@RequestMapping(value = "/importTemplate")
public class ImportTemplateController {

	private static final Logger _LOG = Logger.getLogger(ImportTemplateController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IImportTemplateService.SERVICE_NAME)
	private IImportTemplateService importTemplateService;

	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "importTemplate/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "importTemplate/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "importTemplate/edit";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "importTemplate/details";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<ImportTemplateVO> queryAll() throws Exception {
		List<ImportTemplate> importTemplates = importTemplateService.findAll();
		List<ImportTemplateVO> vo = new ArrayList<ImportTemplateVO>();
		ImportTemplateVO importTemplateVO = null;
		for (ImportTemplate importTemplate : importTemplates) {
			importTemplateVO  = new ImportTemplateVO();
			BeanUtils.copyProperties(importTemplate, importTemplateVO);
			vo.add(importTemplateVO);
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(ImportTemplateForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		ImportTemplate importTemplate= new ImportTemplate();
		BeanUtils.copyProperties(form, importTemplate);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = importTemplateService.save(importTemplate);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ImportTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<ImportTemplateForm> form) throws Exception {
	List<ImportTemplate> importTemplates=new ArrayList<ImportTemplate>();
		ReturnValue returnValue =new ReturnValue();
		ImportTemplate entityImportTemplate = new ImportTemplate();
		for (ImportTemplateForm importTemplate : form) {
			entityImportTemplate=new ImportTemplate();
			BeanUtils.copyProperties(importTemplate, entityImportTemplate);
			importTemplates.add(entityImportTemplate);
		}
		boolean result = importTemplateService.saveAll(importTemplates);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ImportTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = importTemplateService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ImportTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = importTemplateService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ImportTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(ImportTemplateForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		ImportTemplate importTemplate = new ImportTemplate();
		BeanUtils.copyProperties(form, importTemplate);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = importTemplateService.updateImportTemplate(importTemplate);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ImportTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	ImportTemplateVO queryById(String id) throws Exception {
		ImportTemplateVO vo = new ImportTemplateVO();
		ImportTemplate importTemplate = importTemplateService.getImportTemplateById(id);
		if (importTemplate != null) {
			BeanUtils.copyProperties(importTemplate, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, ImportTemplatePageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(ImportTemplate.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		
		return importTemplateService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
