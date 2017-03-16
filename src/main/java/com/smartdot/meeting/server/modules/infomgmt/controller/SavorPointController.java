package com.smartdot.meeting.server.modules.infomgmt.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.CommonUtil;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.SavorPoint;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.infomgmt.model.SavorPointByAdd;
import com.smartdot.meeting.server.modules.infomgmt.model.SavorPointForm;
import com.smartdot.meeting.server.modules.infomgmt.model.SpAddressName;
import com.smartdot.meeting.server.modules.infomgmt.service.ISavorPointService;
@Controller
@RequestMapping(value = "/savorPoint")
public class SavorPointController {

	@SuppressWarnings("unused")
	private static final Logger _LOG = Logger.getLogger(SavorPointController.class);

	public static final String SUCCESS_MESSAGE = "操作成功";

	@Resource(name = ISavorPointService.SERVICE_NAME)
	private ISavorPointService savorPointService;
	

	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;
	
	
	@RequestMapping(value = "/openPage")
	public String openPage() throws Exception {
		return ISavorPointService.PAGE_MAIN_PATH;
	}
	
	@RequestMapping(value = "/addPage")
	public String addPage() throws Exception {
		return ISavorPointService.PAGE_ADD_PATH;
	}
	
	
	@RequestMapping(value = "/editPage")
	public String addAndEditPage(String spUniteId, Model model) throws Exception {
		model.addAttribute("spUniteId", spUniteId);
		return ISavorPointService.PAGE_EDIT_PATH;
	}
	@RequestMapping(value = "/details")
	public String details(String spUniteId, Model model) throws Exception {
		model.addAttribute("spUniteId", spUniteId);
		return ISavorPointService.PAGE_DETAILS_PATH;
	}
	
	
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("enable", true);
		String spName = request.getParameter("spName");
		if (StringUtils.isNotBlank(spName)) {
			searchMap.put("spName", spName);
		}
		String spOnly = request.getParameter("spOnly");
		if (StringUtils.isNotBlank(spOnly)) {
			searchMap.put("spOnly", spOnly);
		}
		String spLanguageType = request.getParameter("spLanguageType");
		if (StringUtils.isNotBlank(spLanguageType)) {
			searchMap.put("spLanguageType", spLanguageType);
		} else {
			searchMap.put("spLanguageType", "zh");
		}
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		Page<SavorPoint> page = new Page<SavorPoint>();
		page.setCurrentPage(Integer.parseInt(StringUtils.isNotBlank(pageNow) ? pageNow : "1"));
		page.setPageSize(Integer.parseInt(StringUtils.isNotBlank(pageSize) ? pageSize : "15"));
		return savorPointService.findSavorPointByPage(page, searchMap);
	}

	
	@RequestMapping(value = "/checkOnlyLogo")
	public @ResponseBody
	boolean checkOnlyLogo(String spOnly) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(SavorPoint.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("spOnly", spOnly));
		List<SavorPoint> dataDictionaryTypes = new ArrayList<SavorPoint>();
		dataDictionaryTypes = savorPointService.findByDetachedCriteria(dc);
		if (dataDictionaryTypes.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public ReturnValue save(SavorPointForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		SavorPoint savorPoint = new SavorPoint();
		BeanUtils.copyProperties(form, savorPoint);
		boolean result;
		if (StringUtils.isEmpty(form.getId())) {
			result = savorPointService.save(savorPoint);
		} else {
			result = savorPointService.updateSavorPoint(savorPoint);
		}
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SavorPointController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}
	
	@RequestMapping(value = "/updateLogo", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public ReturnValue appVersionAddSave(HttpServletRequest request,
			HttpServletResponse response){
		ReturnValue returnValue = new ReturnValue();
		try {
			returnValue.setSuccess(true);
			returnValue.setMessage(CommonUtil.upload(request, "small_icon", request.getSession().getServletContext().getRealPath("/"), "savor/logo", "hotelInfo"));
		} catch (Exception e) {
			returnValue.setSuccess(false);
		}
		return returnValue;
		
	}
	@RequestMapping(value = "/saveList")
	@ResponseBody
	public ReturnValue saveList(SavorPointByAdd jbh) throws Exception {
		if (StringUtils.isNotEmpty(jbh.getSpUniteId())) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("spUniteId", jbh.getSpUniteId());
			savorPointService.deleteAllsavorPoint(savorPointService.findAllByProperties(map));
		} else {
			jbh.setSpUniteId(UUID.randomUUID().toString().replace("-", ""));
		}
		List<SavorPoint> savorPoints = new ArrayList<SavorPoint>();
		ReturnValue returnValue = new ReturnValue();
		String[] languages = jbh.getSpAddressNames();
		List<String> spAddressNames = Arrays.asList(languages);
		SavorPoint zhSp = createSp(jbh,null);
		zhSp.setSpName(jbh.getSpName());
		zhSp.setSpLogo(jbh.getSpLogo());
		zhSp.setSpAddress(jbh.getSpAddress());
		zhSp.setSpLanguageType("zh");
		savorPoints.add(zhSp);
		if (!spAddressNames.get(0).equals("{\"topicType\":\"\"}")&&!spAddressNames.get(0).equals("{}")) {
			if (spAddressNames.get(0).indexOf("spName") == -1
					|| spAddressNames.get(0).indexOf("topicType") == -1
					|| spAddressNames.get(0).indexOf("spAddress") == -1) {
				String asd = spAddressNames.get(0) + ","+spAddressNames.get(1) + ","+ spAddressNames.get(2);
				addSavorPoint(jbh, JSONObject.parseObject(asd, SpAddressName.class), savorPoints);
			} else {
				for (int i = 0; i < spAddressNames.size(); i++) {
					addSavorPoint(jbh, JSONObject.parseObject(spAddressNames.get(i), SpAddressName.class), savorPoints);
				}
			}
		}
		boolean result = savorPointService.saveAll(savorPoints);
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SavorPointController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	private void addSavorPoint(SavorPointByAdd jbh,SpAddressName jpushEntiy, List<SavorPoint> savorPoints){
		if (jpushEntiy.getTopicType() != "") {
			SavorPoint savorPoint = createSp(jbh,jpushEntiy);
			savorPoints.add(savorPoint);
		}
	}
	
	private SavorPoint createSp(SavorPointByAdd jbh,SpAddressName jpushEntiy ){
		SavorPoint savorPoint = new SavorPoint();
		savorPoint.setSpUniteId(jbh.getSpUniteId());
		savorPoint.setSpType(jbh.getSpType());
		savorPoint.setSpOnly(jbh.getSpOnly());
		savorPoint.setSpLogo(jbh.getSpLogo());
		savorPoint.setSpDimensions(jbh.getSpDimensions());
		savorPoint.setSpLongitude(jbh.getSpLongitude());
		if (jpushEntiy != null ) {
			savorPoint.setSpName(jpushEntiy.getSpName());
			savorPoint.setSpAddress(jpushEntiy.getSpAddress());
			savorPoint.setSpLanguageType(jpushEntiy.getTopicType());
		}
		return savorPoint;
	}

	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public ReturnValue deleteById(String spUniteId) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("spUniteId", spUniteId);
		boolean result = savorPointService.deleteAllsavorPoint(savorPointService.findAllByProperties(map));;
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SavorPointController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public ReturnValue deleteAll(String[] ids) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = savorPointService.deleteAll(Arrays.asList(ids));
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SavorPointController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	

	@RequestMapping(value = "/update")
	@ResponseBody
	public ReturnValue update(SavorPointForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		SavorPoint savorPoint = new SavorPoint();
		BeanUtils.copyProperties(form, savorPoint);
		boolean result = savorPointService.updateSavorPoint(savorPoint);
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SavorPointController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	@ResponseBody
	public SavorPointByAdd queryById(String spUniteId) throws Exception {
		SavorPointByAdd vo = new SavorPointByAdd();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> spAddressNames = new ArrayList<String>();
		map.put("spUniteId", spUniteId);
		List<SavorPoint> sPoints = savorPointService.findAllByProperties(map);
		for (SavorPoint savorPoint : sPoints) {
			if (savorPoint.getSpLanguageType().equals("zh")) {
				BeanUtils.copyProperties(savorPoint, vo);
			} else {
				SpAddressName spAddressName = new SpAddressName();
				spAddressName.setSpAddress(savorPoint.getSpAddress());
				spAddressName.setSpName(savorPoint.getSpName());
				spAddressName.setTopicType(savorPoint.getSpLanguageType());
				String jsonStr = JSONObject.toJSONString(spAddressName);
				spAddressNames.add(jsonStr);
			}
		}
		String[] arr = (String[])spAddressNames.toArray(new String[spAddressNames.size()]);
		vo.setSpAddressNames(arr);
		return vo;
	}
	
	@RequestMapping(value = "/savorPointType")
	@ResponseBody
	public List<DataDictionary>  savorPointTypes(String savorPointType) throws Exception {
		List<DataDictionary> dataDictionaries = new ArrayList<DataDictionary>();
		dataDictionaries = dataDictionaryService.findAllByTypeLogo(savorPointType, null);
		return dataDictionaries;
	}
	
	@RequestMapping(value = "/languageType")
	@ResponseBody
	public List<DataDictionary>  languageTypes(String languageType) throws Exception {
		List<DataDictionary> dataDictionaries = new ArrayList<DataDictionary>();
		dataDictionaries = dataDictionaryService.findAllByTypeLogo(languageType, null);
		return dataDictionaries;
	}

}
