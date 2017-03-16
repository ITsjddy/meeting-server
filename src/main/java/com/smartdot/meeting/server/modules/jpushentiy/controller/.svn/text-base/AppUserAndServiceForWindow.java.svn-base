package com.smartdot.meeting.server.modules.jpushentiy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.Exhibitors;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Media;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.exhibitors.service.IExhibitorsService;
import com.smartdot.meeting.server.modules.guest.service.IGuestService;
import com.smartdot.meeting.server.modules.media.service.IMediaService;
import com.smartdot.meeting.server.modules.servicePersonnel.service.IServicePersonnelService;

/** 
 * @ClassName: AppUserAndServiceForWindow 
 * @Description: App用户 以及服务人员数据  弹窗数据
 * 				极光推送 、 短信模板推送、 融云群组管理
 * @author: haomt
 * @date: 2017年2月20日 下午3:39:31  
 */
@Controller
@RequestMapping(value = "/appUserForWindow")
public class AppUserAndServiceForWindow {
		
	@Resource(name = IGuestService.SERVICE_NAME)
	private IGuestService guestService;
	
	
	@Resource(name = IExhibitorsService.SERVICE_NAME)
	private IExhibitorsService exhibitorsService;
	
	
	@Resource(name = IMediaService.SERVICE_NAME)
	private IMediaService imediaservice;
	
	
	@Resource(name = IServicePersonnelService.SERVICE_NAME)
	private IServicePersonnelService servicePersonnelService;
	
	public final static String GUEST = "guest";
	
	public final static String EXHIBITORS = "exhibitors"; 
	
	public final static String MEDIA = "media"; 
	
	public final static String AUDIENCE = "audience"; 
	
	public final static String SERVICEPERSONNEL = "servicePersonnel"; 
	
	
	
	
	
	
	/** 
	 * @Title: memberList 
	 * @Description: app用户主（Member）model、嘉宾（Guest） model、展商（Exhibitors）model、媒体（Media）model、
	 * 				 观众（Audience）model、服务人员（ServicePersonnel）model
	 * @param request
	 * @param response
	 * @param type
	 * @return
	 * @throws Exception
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value = "/memberList")
	@ResponseBody
	public Map<String, Object> memberList(HttpServletRequest request,
			HttpServletResponse response, String type) throws Exception {
		if (type.equals("asdf")) {
			return new HashMap<String, Object>();
		}
		DetachedCriteria dc = creat(type);
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name) && !name.equals("undefined")) {
			dc.add(Restrictions.eq("name", name));
		}
		dc.add(Restrictions.isNotNull("member"));
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", "zh"));
		dc.addOrder(Order.asc("updateTimes"));
		return createResult(dc, type, request);
	}
	

	private DetachedCriteria creat(String type) {
		DetachedCriteria dc = null;
		switch (type) {
		case AppUserAndServiceForWindow.GUEST:
			dc = DetachedCriteria.forClass(Guest.class);
			break;
		case AppUserAndServiceForWindow.EXHIBITORS:
			dc = DetachedCriteria.forClass(Exhibitors.class);
			break;
		case AppUserAndServiceForWindow.MEDIA:
			dc = DetachedCriteria.forClass(Media.class);
			break;
		case AppUserAndServiceForWindow.AUDIENCE:
			dc = DetachedCriteria.forClass(Audience.class);
			break;
		default:
			break;
		}
		return dc;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> createResult(DetachedCriteria dc, String type,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		switch (type) {
		case AppUserAndServiceForWindow.GUEST:
			map = guestService.pagedQuery(dc,
					PageUtilExtent.getPageInfo(request));
			break;
		case AppUserAndServiceForWindow.EXHIBITORS:
			map = exhibitorsService.pagedQuery(dc,
					PageUtilExtent.getPageInfo(request));
			break;
		case AppUserAndServiceForWindow.MEDIA:
			map = imediaservice.pagedQuery(dc,
					PageUtilExtent.getPageInfo(request));
			break;
		case AppUserAndServiceForWindow.AUDIENCE:
			dc = DetachedCriteria.forClass(Audience.class);
			break;
		default:
			break;
		}
		return map;
	}
	
	
	
	/** 
	 * @Title: queryServicePersonneTree 
	 * @Description: 服务人员
	 * @return
	 * @throws Exception
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value = "/queryServicePersonneTree")
	@ResponseBody
	public List<Map<String, Object>> queryServicePersonneTree()
			throws Exception {
		List<Map<String, Object>> lmap = new ArrayList<Map<String, Object>>();
		lmap = guestService.queryALLServicePersonnel();
		if (lmap.size() > 0) {
			for (Map<String, Object> map : lmap) {
				for (String key : map.keySet()) {
					if (key.equals("type")) {
						if (map.get("type").toString().equals("1")) {
							map.put("nocheck", true);
						}
					}
				}
			}
		}
		return lmap;
	}
	
	/** 
	 * @Title: queryServicePersonneTree 
	 * @Description: 服务人员
	 * @return
	 * @throws Exception
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value = "/findServicePersonnelForName")
	@ResponseBody
	public List<Map<String, Object>> findServicePersonnelForName(String name)
			throws Exception {
		List<Map<String, Object>> lmap = new ArrayList<Map<String, Object>>();
		if (StringUtils.isNotBlank(name)) {
			lmap = guestService.findServicePersonnelForName(name);
		} else {
			lmap = guestService.queryALLServicePersonnel();
		}
		if (lmap.size() > 0) {
			for (Map<String, Object> map : lmap) {
				for (String key : map.keySet()) {
					if (key.equals("type")) {
						if (map.get("type").toString().equals("1")) {
							map.put("nocheck", true);
						}
					}
				}
			}
		}
		return lmap;
	}
}
