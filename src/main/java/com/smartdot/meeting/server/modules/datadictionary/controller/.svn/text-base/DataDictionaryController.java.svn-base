package com.smartdot.meeting.server.modules.datadictionary.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.alibaba.fastjson.JSONObject;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.datadictionary.model.DataDictionaryAddEdit;
import com.smartdot.meeting.server.modules.datadictionary.model.DataDictionaryForm;
import com.smartdot.meeting.server.modules.datadictionary.model.DataDictionaryLanguage;
import com.smartdot.meeting.server.modules.datadictionary.model.DataDictionaryPageForm;
import com.smartdot.meeting.server.modules.datadictionary.model.DataDictionaryVO;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.datadictionarytype.service.IDataDictionaryTypeService;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.DataDictionaryType;


@Controller
@RequestMapping(value = "/dataDictionary")
public class DataDictionaryController {

	@SuppressWarnings("unused")
	private static final Logger _LOG = Logger.getLogger(DataDictionaryController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;
	
	@Resource(name = IDataDictionaryTypeService.SERVICE_NAME)
	private IDataDictionaryTypeService dataDictionaryTypeService;

	
	@RequestMapping(value = "/index")
	public String index(String typeName, String dDTypeLogo, Model model)
			throws Exception {
		model.addAttribute("typeName", typeName);
		model.addAttribute("dDTypeLogo", dDTypeLogo);
		return "dataDictionary/list";
	}

	@RequestMapping(value = "/add")
	public String add(String typeName, String dDTypeLogo, Model model)
			throws Exception {
		model.addAttribute("typeName", typeName);
		model.addAttribute("dDTypeLogo", dDTypeLogo);
		return "dataDictionary/add";
	}

	@RequestMapping(value = "/edit")
	public String edit(String id, String typeName, String dDTypeLogo,
			Model model) throws Exception {
		model.addAttribute("typeName", typeName);
		model.addAttribute("dDTypeLogo", dDTypeLogo);
		model.addAttribute("id", id);
		return "dataDictionary/edit";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "dataDictionary/details";
	}

	@RequestMapping(value = "/indexAll")
	public String indexAll() throws Exception {
		return "dataDictionary/allList";
	}
	
	@RequestMapping(value = "/allAdd")
	public String allAdd(String typeName, String dDTypeLogo, Model model) throws Exception {
		model.addAttribute("typeName", typeName);
		model.addAttribute("dDTypeLogo", dDTypeLogo);
		return "dataDictionary/allAdd";
	}
	
	@RequestMapping(value = "/allEdit")
	public String allEdit(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "dataDictionary/allEdit";
	}

	@RequestMapping(value = "/allDetails")
	public String allDetails(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "dataDictionary/allDetails";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<DataDictionaryVO> queryAll() throws Exception {
		List<DataDictionary> dataDictionarys = dataDictionaryService.findAll();
		List<DataDictionaryVO> vo = new ArrayList<DataDictionaryVO>();
		DataDictionaryVO dataDictionaryVO = null;
		for (DataDictionary dataDictionary : dataDictionarys) {
			dataDictionaryVO  = new DataDictionaryVO();
			BeanUtils.copyProperties(dataDictionary, dataDictionaryVO);
			vo.add(dataDictionaryVO);
		}
		return vo;
	}
	
	@RequestMapping(value = "/checkName")
	public @ResponseBody
	boolean checkName(String dDName, String languageType, String dDTypeLogo) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dDName", dDName));
		if (!languageType.equals("dDName")) {
			dc.add(Restrictions.eq("dDLanguageType", languageType));
		} else {
			dc.add(Restrictions.eq("dDLanguageType", "zh"));
		}
		if (StringUtils.isNotBlank(dDTypeLogo)) {
			dc.add(Restrictions.eq("dDTypeLogo", dDTypeLogo));
		}
		List<DataDictionary> dataDictionaryTypes = new ArrayList<DataDictionary>();
		dataDictionaryTypes = dataDictionaryService.findByDetachedCriteria(dc);
		if (dataDictionaryTypes.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	@RequestMapping(value = "/checkOnlyLogo")
	public @ResponseBody
	boolean checkOnlyLogo(String dDLogo, String dDTypeLogo) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dDLogo", dDLogo));
		if (StringUtils.isNotBlank(dDTypeLogo)) {
			dc.add(Restrictions.eq("dDTypeLogo", dDTypeLogo));
		}
		List<DataDictionary> dataDictionaryTypes = new ArrayList<DataDictionary>();
		dataDictionaryTypes = dataDictionaryService.findByDetachedCriteria(dc);
		if (dataDictionaryTypes.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(DataDictionaryAddEdit form) throws Exception {
	List<DataDictionary> dataDictionarys=new ArrayList<DataDictionary>();
		ReturnValue returnValue =new ReturnValue();
		String commId = form.getdDcommId();
		if (StringUtils.isNotEmpty(form.getdDcommId())) {
			DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("dDcommId", form.getdDcommId()));
			List<DataDictionary> dataDictionaries = dataDictionaryService.findByDetachedCriteria(dc);
			if (dataDictionaries.size() > 0) {
				for (DataDictionary dataDictionary : dataDictionaries) {
					dataDictionaryService.remove(dataDictionary);
				}
			}
		} else {
			 commId = UUID.randomUUID().toString().replace("-", "");
		}
		List<String> list =  Arrays.asList(form.getDataDictionary());
		String firString = list.get(0);
		if (firString.indexOf("dDName") == -1
				|| firString.indexOf("dDType") == -1
				|| firString.indexOf("dDexplanation") == -1
				|| firString.indexOf("dDTypeLogo") == -1
				|| firString.indexOf("dDNum") == -1
				|| firString.indexOf("dDLogo") == -1 || firString.indexOf("dDLanguageType") == -1) {
			DataDictionary cDataDictionary = JSONObject.parseObject(
					list.get(0) + "," + list.get(1) + "," + list.get(2) + ","
							+ list.get(3)+ "," + list.get(4)+ "," + list.get(5)+ "," + list.get(6), DataDictionary.class);
			cDataDictionary.setdDcommId(commId);
			cDataDictionary.setdDNum(judgeTheSortNumber(cDataDictionary));
			dataDictionarys.add(cDataDictionary);
		} else {
			for (String string : list) {
				DataDictionary cDataDictionary = JSONObject.parseObject(string, DataDictionary.class);
				cDataDictionary.setdDNum(judgeTheSortNumber(cDataDictionary));
				cDataDictionary.setdDcommId(commId);
				dataDictionarys.add(cDataDictionary);
			}
		}
		boolean result = dataDictionaryService.saveAll(dataDictionarys);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	private Integer judgeTheSortNumber(DataDictionary cDictionary){
		saveTheSortNumber(cDictionary.getdDNum(), cDictionary.getdDLanguageType(), "none");
		return cDictionary.getdDNum();
	}
	
	private Integer saveTheSortNumber(Integer dDNum, String language, String id) {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(DataDictionary.class);
		dCriteria.add(Restrictions.eq("enable", true));
		dCriteria.add(Restrictions.eq("dDLanguageType", "zh"));
		dCriteria.add(Restrictions.eq("dDNum", dDNum));
		dCriteria.add(Restrictions.isNotNull("id"));
		dCriteria.add(Restrictions.ne("id",id));
		List<DataDictionary> list = dataDictionaryService.findByDetachedCriteria(dCriteria);
		if(list !=null&&list.size()>0){
			DataDictionary news = list.get(0);
			news.setdDNum(news.getdDNum()+1);
			dataDictionaryService.save(news);
			return saveTheSortNumber(news.getdDNum(),language,news.getId());
		}else{
			return dDNum;
		}
	}
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String dDcommId) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dDcommId", dDcommId));
		boolean result = dataDictionaryService.deleteByList(dataDictionaryService.findByDetachedCriteria(dc));
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryController.SUCCESS_MESSAGE);
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
				for (String id : ids) {
					DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
					dc.add(Restrictions.eq("enable", true));
					dc.add(Restrictions.eq("dDcommId", id));
					dataDictionaryService.deleteByList(dataDictionaryService.findByDetachedCriteria(dc));
					result = true;
				}
			} catch (Exception e) {
				result =false;
			}
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(DataDictionaryForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		DataDictionary dataDictionary = new DataDictionary();
		BeanUtils.copyProperties(form, dataDictionary);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = dataDictionaryService.updateDataDictionary(dataDictionary);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DataDictionaryController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	DataDictionaryVO queryById(String id) throws Exception {
		DataDictionaryVO vo = new DataDictionaryVO();
		List<String> ddlsList = new ArrayList<String>();
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dDcommId", id));
		List<DataDictionary> dataDictionaries = new ArrayList<DataDictionary>();
		dataDictionaries = dataDictionaryService.findByDetachedCriteria(dc);
		for (DataDictionary dataDictionary : dataDictionaries) {
			if (StringUtils.isNotBlank(dataDictionary.getdDLanguageType())&& dataDictionary.getdDLanguageType().equals("zh")) {
				BeanUtils.copyProperties(dataDictionary, vo);
			} else {
				DataDictionaryLanguage ddl = new DataDictionaryLanguage();
				ddl.setTopicType(dataDictionary.getdDLanguageType());
				ddl.setdDName(dataDictionary.getdDName());
				ddlsList.add(JSONObject.toJSONString(ddl));
			}
		}
		vo.setLanguages((String[])ddlsList.toArray(new String[ddlsList.size()]));
		return vo;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, String dDTypeLogo, DataDictionaryPageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		if (StringUtils.isNotBlank(dDTypeLogo) && !dDTypeLogo.equals("undefined")) {
			dc.add(Restrictions.eq("dDTypeLogo", dDTypeLogo));
		}
		if (req != null) {
			if (StringUtils.isNotBlank(req.getDDName())) {
				dc.add(Restrictions.like("dDName", "%"+ req.getDDName() +"%"));
			}
			if (StringUtils.isNotBlank(req.getDDLogo())) {
				dc.add(Restrictions.like("dDLogo", "%"+ req.getDDLogo() +"%"));
			}
			if (StringUtils.isNotBlank(req.getDDType())) {
				dc.add(Restrictions.like("dDType", "%"+ req.getDDType() +"%"));
			}
		}
		dc.add(Restrictions.eq("dDLanguageType", "zh"));
		dc.addOrder(Order.desc("updateTimes"));
		return dataDictionaryService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	@RequestMapping(value = "/findAllType")
	public @ResponseBody
	List<DataDictionaryType>  findAllType() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionaryType.class);
		dc.add(Restrictions.eq("enable", true));
		List<DataDictionaryType> dataDictionaries = new ArrayList<DataDictionaryType>();
		dataDictionaries = dataDictionaryTypeService.findByDetachedCriteria(dc);
		return dataDictionaries;
	}
	
	@RequestMapping(value = "/findAllDataDictionary")
	@ResponseBody
	public Integer  findAllDataDictionary(String dDTypeLogo) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("dDLanguageType", "zh"));
		dc.add(Restrictions.eq("dDTypeLogo", dDTypeLogo));
		List<DataDictionary> dataDictionaries = new ArrayList<DataDictionary>();
		dataDictionaries = dataDictionaryService.findByDetachedCriteria(dc);
		if (dataDictionaries.size() == 0) {
			return 0+1;
		}
		return dataDictionaries.size()+1;
	}
	
	@RequestMapping(value = "/languageType")
	@ResponseBody
	public List<DataDictionary>  languageTypes(String languageType) throws Exception {
		List<DataDictionary> dataDictionaries = new ArrayList<DataDictionary>();
		dataDictionaries = dataDictionaryService.findAllByTypeLogo(languageType, null);
		return dataDictionaries;
	}
	
}
