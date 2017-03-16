package com.smartdot.meeting.server.modules.systemConfig.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.smartdot.meeting.server.common.entity.SystemConfig;
import com.smartdot.meeting.server.modules.systemConfig.service.ISystemConfigService;
import java.text.SimpleDateFormat;


/**
 * @author ms
 *	系统配置管理
 */
@Controller
@RequestMapping(value = "/systemConfig")
public class SystemConfigController {
	
	private static Logger logger = Logger.getLogger(SystemConfigController.class);
	
	@Autowired
	private ISystemConfigService systemConfigService;
	
	
	/**
	 * 系统配置列表页面
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("systemConfig/list");
		//列表查询条件
		String method = request.getMethod();
		String sysser_uneIdent = request.getParameter("sysser_uneIdent");
		if(StringUtils.isNotBlank(sysser_uneIdent)&&(method.equals("get")||method.equals("GET"))){
			sysser_uneIdent = new String(sysser_uneIdent.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_content = request.getParameter("sysser_content");
		if(StringUtils.isNotBlank(sysser_content)&&(method.equals("get")||method.equals("GET"))){
			sysser_content = new String(sysser_content.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_state = request.getParameter("sysser_state");
		if(StringUtils.isNotBlank(sysser_state)&&(method.equals("get")||method.equals("GET"))){
			sysser_state = new String(sysser_state.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_type = request.getParameter("sysser_type");
		if(StringUtils.isNotBlank(sysser_type)&&(method.equals("get")||method.equals("GET"))){
			sysser_type = new String(sysser_type.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_beginTimes = request.getParameter("sysser_beginTimes");
		String sysser_endTimes = request.getParameter("sysser_endTimes");
		
	    DetachedCriteria dc = DetachedCriteria.forClass(SystemConfig.class);
	    dc.add(Restrictions.eq("enable", true));
	    
	    if(StringUtils.isNotBlank(sysser_beginTimes)){
	    	dc.add(Restrictions.ge("createTimes", getTrTime(sysser_beginTimes)));
	    }
	    if(StringUtils.isNotBlank(sysser_endTimes)){
	    	dc.add(Restrictions.le("createTimes",getTrTime(sysser_endTimes)));
	    }
	    if(StringUtils.isNotBlank(sysser_uneIdent)){
	    	dc.add(Restrictions.like("uneIdent", "%"+sysser_uneIdent+"%"));
	    }
	    if(StringUtils.isNotBlank(sysser_content)){
	    	dc.add(Restrictions.like("content", "%"+sysser_content+"%"));
	    }
	    if(StringUtils.isNotBlank(sysser_state)){
	    	dc.add(Restrictions.eq("state", Integer.valueOf(sysser_state)));
	    }
	    if(StringUtils.isNotBlank(sysser_type)){
	    	dc.add(Restrictions.eq("type", sysser_type));
	    }
	    dc.addOrder(Order.desc("createTimes"));
	    List<SystemConfig> page = systemConfigService.redisGetAll();
		
	    mav.addObject("sysser_uneIdent", sysser_uneIdent);
	    mav.addObject("sysser_beginTimes", sysser_beginTimes);
	    mav.addObject("sysser_endTimes", sysser_endTimes);
	    mav.addObject("sysser_content", sysser_content);
	    mav.addObject("sysser_state", sysser_state);
	    mav.addObject("sysser_type", sysser_type);
	    
	    mav.addObject("result", page);
		return mav;
	}
	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("systemConfig/edit");
		//列表查询条件
		String method = request.getMethod();
		String sysser_uneIdent = request.getParameter("sysser_uneIdent");
		if(StringUtils.isNotBlank(sysser_uneIdent)&&(method.equals("get")||method.equals("GET"))){
			sysser_uneIdent = new String(sysser_uneIdent.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_content = request.getParameter("sysser_content");
		if(StringUtils.isNotBlank(sysser_content)&&(method.equals("get")||method.equals("GET"))){
			sysser_content = new String(sysser_content.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_state = request.getParameter("sysser_state");
		if(StringUtils.isNotBlank(sysser_state)&&(method.equals("get")||method.equals("GET"))){
			sysser_state = new String(sysser_state.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_type = request.getParameter("sysser_type");
		if(StringUtils.isNotBlank(sysser_type)&&(method.equals("get")||method.equals("GET"))){
			sysser_type = new String(sysser_type.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_beginTimes = request.getParameter("sysser_beginTimes");
		String sysser_endTimes = request.getParameter("sysser_endTimes");
	    
		SystemConfig systemConfig=new SystemConfig();
		try {
			String idStr = request.getParameter("id");
			if (StringUtils.isNotBlank(idStr)) {
				systemConfig = systemConfigService.redisGet(idStr);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		//列表查询条件
	    mav.addObject("sysser_uneIdent", sysser_uneIdent);
	    mav.addObject("sysser_beginTimes", sysser_beginTimes);
	    mav.addObject("sysser_endTimes", sysser_endTimes);
	    mav.addObject("sysser_content", sysser_content);
	    mav.addObject("sysser_state", sysser_state);
	    mav.addObject("sysser_type", sysser_type);
		mav.addObject("systemConfig", systemConfig);
		return mav;
	}
	
	@RequestMapping(value = "/del")
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:index");
		//列表查询条件
		String method = request.getMethod();
		String sysser_uneIdent = request.getParameter("sysser_uneIdent");
		if(StringUtils.isNotBlank(sysser_uneIdent)&&(method.equals("get")||method.equals("GET"))){
			sysser_uneIdent = new String(sysser_uneIdent.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_content = request.getParameter("sysser_content");
		if(StringUtils.isNotBlank(sysser_content)&&(method.equals("get")||method.equals("GET"))){
			sysser_content = new String(sysser_content.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_state = request.getParameter("sysser_state");
		if(StringUtils.isNotBlank(sysser_state)&&(method.equals("get")||method.equals("GET"))){
			sysser_state = new String(sysser_state.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_type = request.getParameter("sysser_type");
		if(StringUtils.isNotBlank(sysser_type)&&(method.equals("get")||method.equals("GET"))){
			sysser_type = new String(sysser_type.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_beginTimes = request.getParameter("sysser_beginTimes");
		String sysser_endTimes = request.getParameter("sysser_endTimes");
		
		String id_Str = request.getParameter("id");
		try {
			if (StringUtils.isNotBlank(id_Str)) {
				String[] idStrs = id_Str.split(",");
				for(int i=0;i<idStrs.length;i++){
					if(StringUtils.isNotBlank(idStrs[i])){
						systemConfigService.redisDelete(idStrs[i]);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		//列表查询条件
	    mav.addObject("sysser_uneIdent", sysser_uneIdent);
	    mav.addObject("sysser_beginTimes", sysser_beginTimes);
	    mav.addObject("sysser_endTimes", sysser_endTimes);
	    mav.addObject("sysser_content", sysser_content);
	    mav.addObject("sysser_state", sysser_state);
	    mav.addObject("sysser_type", sysser_type);
		return mav;
	}
	
	@RequestMapping(value = "/details")
	public ModelAndView details(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("systemConfig/details");
		//列表查询条件
		String method = request.getMethod();
		String sysser_uneIdent = request.getParameter("sysser_uneIdent");
		if(StringUtils.isNotBlank(sysser_uneIdent)&&(method.equals("get")||method.equals("GET"))){
			sysser_uneIdent = new String(sysser_uneIdent.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_content = request.getParameter("sysser_content");
		if(StringUtils.isNotBlank(sysser_content)&&(method.equals("get")||method.equals("GET"))){
			sysser_content = new String(sysser_content.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_state = request.getParameter("sysser_state");
		if(StringUtils.isNotBlank(sysser_state)&&(method.equals("get")||method.equals("GET"))){
			sysser_state = new String(sysser_state.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_type = request.getParameter("sysser_type");
		if(StringUtils.isNotBlank(sysser_type)&&(method.equals("get")||method.equals("GET"))){
			sysser_type = new String(sysser_type.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_beginTimes = request.getParameter("sysser_beginTimes");
		String sysser_endTimes = request.getParameter("sysser_endTimes");
		
		SystemConfig systemConfig=new SystemConfig();
		try {
			String idStr = request.getParameter("id");
			if (StringUtils.isNotBlank(idStr)) {
				systemConfig = systemConfigService.redisGet(idStr);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		//列表查询条件
	    mav.addObject("sysser_uneIdent", sysser_uneIdent);
	    mav.addObject("sysser_beginTimes", sysser_beginTimes);
	    mav.addObject("sysser_endTimes", sysser_endTimes);
	    mav.addObject("sysser_content", sysser_content);
	    mav.addObject("sysser_state", sysser_state);
	    mav.addObject("sysser_type", sysser_type);
	    
	    mav.addObject("systemConfig", systemConfig);
		return mav;
	}
	
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:index");
		//列表查询条件
		String method = request.getMethod();
		String sysser_uneIdent = request.getParameter("sysser_uneIdent");
		if(StringUtils.isNotBlank(sysser_uneIdent)&&(method.equals("get")||method.equals("GET"))){
			sysser_uneIdent = new String(sysser_uneIdent.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_content = request.getParameter("sysser_content");
		if(StringUtils.isNotBlank(sysser_content)&&(method.equals("get")||method.equals("GET"))){
			sysser_content = new String(sysser_content.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_state = request.getParameter("sysser_state");
		if(StringUtils.isNotBlank(sysser_state)&&(method.equals("get")||method.equals("GET"))){
			sysser_state = new String(sysser_state.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_type = request.getParameter("sysser_type");
		if(StringUtils.isNotBlank(sysser_type)&&(method.equals("get")||method.equals("GET"))){
			sysser_type = new String(sysser_type.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_beginTimes = request.getParameter("sysser_beginTimes");
		String sysser_endTimes = request.getParameter("sysser_endTimes");
		
		try {
			SystemConfig systemConfig = getSystemConfig(request);
			if(null != systemConfig){
				systemConfigService.redisSave(systemConfig);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		//列表查询条件
	    mav.addObject("sysser_uneIdent", sysser_uneIdent);
	    mav.addObject("sysser_beginTimes", sysser_beginTimes);
	    mav.addObject("sysser_endTimes", sysser_endTimes);
	    mav.addObject("sysser_content", sysser_content);
	    mav.addObject("sysser_state", sysser_state);
	    mav.addObject("sysser_type", sysser_type);
		return mav;
	}
	
	private SystemConfig getSystemConfig(HttpServletRequest request) {
		SystemConfig systemConfig = new SystemConfig();
		try {
			String idStr = request.getParameter("id");
			String type = request.getParameter("type");
			String state = request.getParameter("state");
			String uneIdent = request.getParameter("uneIdent");
			String content = request.getParameter("content");
			String explain = request.getParameter("explain");

			if(StringUtils.isNotBlank(idStr)){
				systemConfig = systemConfigService.redisGet(idStr);
			}

			systemConfig.setType(type);
			if(StringUtils.isNotBlank(state)){
				systemConfig.setState(Integer.valueOf(state));
			}else{
				systemConfig.setState(null);
			}
			systemConfig.setUneIdent(uneIdent);
			systemConfig.setContent(content);
			systemConfig.setExplain(explain);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return systemConfig;
	}
	
	@RequestMapping(value = "/enable")
	public ModelAndView enable(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:systemConfig.do");
		//列表查询条件
		String method = request.getMethod();
		String sysser_uneIdent = request.getParameter("sysser_uneIdent");
		if(StringUtils.isNotBlank(sysser_uneIdent)&&(method.equals("get")||method.equals("GET"))){
			sysser_uneIdent = new String(sysser_uneIdent.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_content = request.getParameter("sysser_content");
		if(StringUtils.isNotBlank(sysser_content)&&(method.equals("get")||method.equals("GET"))){
			sysser_content = new String(sysser_content.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_state = request.getParameter("sysser_state");
		if(StringUtils.isNotBlank(sysser_state)&&(method.equals("get")||method.equals("GET"))){
			sysser_state = new String(sysser_state.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_type = request.getParameter("sysser_type");
		if(StringUtils.isNotBlank(sysser_type)&&(method.equals("get")||method.equals("GET"))){
			sysser_type = new String(sysser_type.getBytes("iso-8859-1"), "utf-8");
	    }
		String sysser_beginTimes = request.getParameter("sysser_beginTimes");
		String sysser_endTimes = request.getParameter("sysser_endTimes");

		String id = request.getParameter("id");
		String state = request.getParameter("state");
		if(StringUtils.isNotBlank(id)){
			SystemConfig systemConfig = systemConfigService.redisGet(id);
			if(StringUtils.isNotBlank(state)){
				systemConfig.setState(Integer.valueOf(state));
				systemConfigService.redisSave(systemConfig);
			}			
		}
		
		//列表查询条件
	    mav.addObject("sysser_uneIdent", sysser_uneIdent);
	    mav.addObject("sysser_beginTimes", sysser_beginTimes);
	    mav.addObject("sysser_endTimes", sysser_endTimes);
	    mav.addObject("sysser_content", sysser_content);
	    mav.addObject("sysser_state", sysser_state);
	    mav.addObject("sysser_type", sysser_type);
		return mav;
	}
	
	/**
	 * 唯一标识判重
	 */
	@RequestMapping(value = "/typeUniqueCheck")
	public ModelAndView typeUniqueCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String id = request.getParameter("id");
		DetachedCriteria configdeta = DetachedCriteria.forClass(SystemConfig.class);
		configdeta.add(Restrictions.eq("enable", true));
		if(StringUtils.isNotBlank(id)){
			configdeta.add(Restrictions.ne("id",id));
		}
		configdeta.add(Restrictions.eq(name, value));
		List<SystemConfig> allconfig = new ArrayList<SystemConfig>();//systemConfigService.findByDetachedCriteria(configdeta);
		String jg="0";
		if(allconfig.size()>0){
			jg="1";
		}
		PrintWriter writer = response.getWriter();
		writer.write(jg);
		return null;
	}
	
	
	private  Timestamp getTrTime(String date) {
	      Timestamp currentTime2 = null;
	      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      // String转Timestamp    
	      try {
	          if (StringUtils.isNotBlank(date)) {
	              Date currentdate = format.parse(date);
	              currentTime2 = new Timestamp(currentdate.getTime());
	          }
	      } catch (Exception e) {
	          e.getMessage();
	      }
	      return currentTime2;
	}
	
	
}