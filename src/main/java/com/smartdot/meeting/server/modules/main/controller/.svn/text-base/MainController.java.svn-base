package com.smartdot.meeting.server.modules.main.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smartdot.meeting.server.modules.sysprivilege.service.ISysPrivilegeService;


@Controller
@RequestMapping(value = "/main")
public class MainController {
	
	private static Logger logger = Logger.getLogger(MainController.class);
	
	@Resource(name = ISysPrivilegeService.SERVICE_NAME)
	private ISysPrivilegeService  sysPrivilegeService;
	
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) throws Exception {
	
		return "main";
	}
	@RequestMapping(value = "/menu")
	public List<Map<String, String>> menu(HttpServletRequest request, Model model) throws Exception {
		Principal userPrincipal = request.getUserPrincipal();
		String userName = userPrincipal.getName();
		//一级菜单
		List<Map<String, String>> priListParent = sysPrivilegeService.getPrivilegesByUserName(userName,"1",true);
		//二级菜单
		List<Map<String, String>> priListChild = sysPrivilegeService.getPrivilegesByUserName(userName,"2",true);
		priListParent.addAll(priListChild);
		return priListParent;
	}
}
