package com.smartdot.meeting.server.modules.appversion.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.AppVersion;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.appversion.model.AppVersionPageForm;
import com.smartdot.meeting.server.modules.appversion.service.IAppVersionService;


@Controller
@RequestMapping(value = "/appVersion")
public class AppVersionController {

	@SuppressWarnings("unused")
	private static final Logger _LOG = Logger.getLogger(AppVersionController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IAppVersionService.SERVICE_NAME)
	private IAppVersionService appVersionService;

	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		return "/appVersion/appVersionList";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		return "appVersion/appVersionAdd";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "appVersion/appVersionEdit";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "appVersion/appVersionDetails";
	}
	
	@RequestMapping(value = "/appVersionAddSave", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public ReturnValue appVersionAddSave(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnValue returnValue = new ReturnValue();
//		String root = request.getSession().getServletContext().getRealPath("/");
		AppVersion appVersion = new AppVersion();
		String type = request.getParameter("type");
		if (StringUtils.isNotBlank(type)) {
			appVersion.setType(Integer.parseInt(type));
		}
		String versionName = request.getParameter("versionName");
		if (StringUtils.isNotBlank(versionName)) {
			appVersion.setVersionName(versionName);
			
		}
		String versionNum = request.getParameter("versionNum");
		if (StringUtils.isNotBlank(versionNum)) {
			appVersion.setVersionNum(Integer.parseInt(versionNum));
			
		}
		String remark = request.getParameter("remark");
		if (StringUtils.isNotBlank(remark)) {
			appVersion.setRemark(remark);
		}
		
//		String fiName = "";
//		String plistName = "";
//		String picName = "";
//		String picName2 = "";
//		String add = "";
//		String ewmName = UploadNameUtils.hy_ewm_name;
//		
//		if ("1".equals(type)) { // 安卓
//			fiName = UploadNameUtils.hy_android_name;
//			add = "azb/an";
//		}
		if ("2".equals(type)) { // ios
//			fiName = UploadNameUtils.hy_ios_name;
//			plistName = UploadNameUtils.hy_plist_name;
//			picName = UploadNameUtils.hy_picture_name1;
//			picName2 = UploadNameUtils.hy_picture_name2;
//			add = "azb/ios";
			appVersion.setPlist(request.getParameter("plist"));
			appVersion.setPicOne(request.getParameter("picOne"));
			appVersion.setPicTwo(request.getParameter("picTwo"));
//			appVersion.setPlist(CommonUtil.upload(request, "plist", root, add, plistName));
//			appVersion.setPicOne(CommonUtil.upload(request, "picOne", root, add, picName));
//			appVersion.setPicTwo(CommonUtil.upload(request, "picTwo", root, add, picName2));
		}
		
		appVersion.setPath(request.getParameter("path"));
		appVersion.setQr_code(request.getParameter("qrCode"));
//		appVersion.setPath(CommonUtil.upload(request, "path", root, add, fiName));
//		appVersion.setQr_code(CommonUtil.upload(request, "qrCode", root, "azb/ewm", ewmName));
		String id = request.getParameter("id");
		boolean result = false;
		if (StringUtils.isNotBlank(id)) {
			appVersion.setId(id);
			result = appVersionService.updateAppVersion(appVersion);
		} else {
			result = appVersionService.save(appVersion);
		}
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(AppVersionController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = appVersionService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(AppVersionController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteList")
	@ResponseBody
	public ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = appVersionService.deleteAll(Arrays.asList(ids));
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(AppVersionController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}


	@RequestMapping(value = "/appVersionFindById")
	@ResponseBody
	public AppVersion queryById(String id) throws Exception {
		AppVersion appVersion = appVersionService.getAppVersionById(id);
		return appVersion;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pageQuery")
	@ResponseBody
	public Map<String, Object> pageQuery(HttpServletRequest request, AppVersionPageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(AppVersion.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		if (req != null) {
			if (StringUtils.isNotBlank(req.getVersionName())) {
				dc.add(Restrictions.like("versionName", "%"+ req.getVersionName() +"%"));
			}
			if (req.getType() != null) {
				dc.add(Restrictions.eq("type", req.getType()));
			}
		}
		return appVersionService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	@RequestMapping(value = "/getMaxVersionNum")
	@ResponseBody
	public Map<String, Object> getMaxVersionNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(type)){
			return null;
		}
		int version = 1;
		List<AppVersion> appVersionList = appVersionService.getLastVersion(Integer.parseInt(type));
		if(appVersionList.size()==0){
			map.put("versionNum", version);
		} else{
			AppVersion appVersion = appVersionList.get(0);
			map.put("versionNum", appVersion.getVersionNum() + 1);
		}
		
		return map;
	}
}
