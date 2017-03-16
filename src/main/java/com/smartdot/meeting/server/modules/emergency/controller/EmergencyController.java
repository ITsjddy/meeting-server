package com.smartdot.meeting.server.modules.emergency.controller;

import java.io.File;
import java.util.HashMap;
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

import com.smartdot.meeting.server.common.entity.Emergency;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.emergency.service.IEmergencyService;



@Controller
@RequestMapping(value = "/emergency")
public class EmergencyController {

	private static final Logger _LOG = Logger.getLogger(EmergencyController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IEmergencyService.SERVICE_NAME)
	private IEmergencyService emergencyService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/emergency/emergencyList";
	}
	
	@RequestMapping(value = "/queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> searchMap = new HashMap<String,Object>();
		String emergencyTel = request.getParameter("emergencyTel");
		if(StringUtils.isNotBlank(emergencyTel)){
			emergencyTel = new String(emergencyTel.getBytes("iso-8859-1"),"UTF-8");
			searchMap.put("emergencyTel", emergencyTel);
		}
		searchMap.put("enable", true);
		return emergencyService.pageList(PageUtilExtent.getPageInfo(request),searchMap);
	}
	
	@RequestMapping(value = "/emergencyAdd")
	public String emergencyAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/emergency/emergencyAdd";
	}
	
	@RequestMapping(value = "/emergencyAddSave", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public String emergencyAddSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String root = request.getRealPath("/");
		String emergencyTel = request.getParameter("emergencyTel");
		String emergencyType = request.getParameter("emergencyType");
		String emergencyPic = request.getParameter("emergencyPic");
		
		Emergency emergency = new Emergency();
		emergency.setEmergencyTel(emergencyTel);
		emergency.setEmergencyType(emergencyType);
		emergency.setEmergencyPic(emergencyPic);
		
		emergencyService.save(emergency);
		
		return "redirect:/dispatcher/emergency/index";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Emergency emergency = emergencyService.getEmergencyById(id);
		if(emergency !=null){
			//删除本地图片，释放资源
			File f = new File(emergency.getEmergencyPic());
			f.delete();
			emergency.setEnable(false);
			emergencyService.save(emergency);
		}
		
		return "redirect:/dispatcher/emergency/index";
	}

	@RequestMapping(value = "/emergencyEdit")
	public String emergencyEdit(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			return null;
		}
		model.addAttribute("id",id);
		return "/emergency/emergencyEdit";
	}
	
	@RequestMapping(value = "/emergencyFindById")
	@ResponseBody
	public Map<String, Object> emergencyFindById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(id)){
			return null;
		}
		Emergency emergency =emergencyService.getEmergencyById(id);
		map.put("id", emergency.getId());
		map.put("emergencyTel", emergency.getEmergencyTel());
		map.put("emergencyType", emergency.getEmergencyType());
		map.put("emergencyPic", emergency.getEmergencyPic());
		return map;
	}
	
	@RequestMapping(value = "/emergencyEditSave", produces = { "application/json" }, method = RequestMethod.POST)
	public String emergencyEditSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String root = request.getRealPath("/");
		String id = request.getParameter("id");
		String emergencyTel = request.getParameter("emergencyTel");
		String emergencyType = request.getParameter("emergencyType");
		String emergencyPic = request.getParameter("emergencyPic");
		
		Emergency emergency = emergencyService.getEmergencyById(id);
		//删除本地图片，释放资源
		File f = new File(emergency.getEmergencyPic());
		f.delete();
		emergency.setEmergencyTel(emergencyTel);
		emergency.setEmergencyType(emergencyType);
		//String emergencyPic = CommonUtil.upload(request, "emergencyPic", root, "emergency","InCo_Emergency");
		emergency.setEmergencyPic(emergencyPic);		
		emergencyService.updateEmergency(emergency);
		return "redirect:/dispatcher/emergency/index";
	}
}
