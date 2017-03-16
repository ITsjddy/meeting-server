package com.smartdot.meeting.server.modules.sysuser.controller;


import java.lang.reflect.Field;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.security.CustomFilterInvocationSecurityMetadataSource;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	
	
	@RequestMapping(value = "/login")
	public String  index(Model model) throws Exception {
		System.out.println("--登录界面进来了！！！--");
		return "login";
	}
	
	/**
	 * 退出操作
	 * */
	@RequestMapping(value = "/loginOut")
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:login");
		
		// 清除session
		Enumeration<String> em = request.getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			request.getSession().removeAttribute(em.nextElement().toString());
		}
		request.getSession().invalidate();
		
		//再把SecurityContext实例从SecurityContextHolder中清空
		SecurityContextHolder.clearContext();
		//通过反射 清除spring security权限信息
		CustomFilterInvocationSecurityMetadataSource cins = new CustomFilterInvocationSecurityMetadataSource();
	    try {
	    	Field f = cins.getClass().getDeclaredField("resourceMap");
	 	    f.setAccessible(true);
			f.set(cins, null);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	    System.out.println("--注销--");
		
		return mav;
	}
	
	/**
	 * 权限不够
	 * */
	@RequestMapping(value = "/acccessDenied")
	public String acccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "403";
	}
	
	/**
	 * 权限不够 ajax
	 * */
	@RequestMapping(value = "/acccessDeniedAjax")
	public @ResponseBody
	ReturnValue acccessDeniedAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		
		returnValue.setSuccess(false);
		returnValue.setMessage("403");
		
		return returnValue;
	}
}
