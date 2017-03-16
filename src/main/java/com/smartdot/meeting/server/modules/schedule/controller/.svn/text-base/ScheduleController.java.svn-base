package com.smartdot.meeting.server.modules.schedule.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.ConHall;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.Exhibitors;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Media;
import com.smartdot.meeting.server.common.entity.MeetingAndMember;
import com.smartdot.meeting.server.common.entity.Schedule;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.GBKOrder;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.conHall.service.IConHallService;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.datadictionarytype.service.IDataDictionaryTypeService;
import com.smartdot.meeting.server.modules.guest.service.IGuestService;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.schedule.service.IMeetingAndMemberService;
import com.smartdot.meeting.server.modules.schedule.service.IScheduleService;
import com.smartdot.meeting.server.modules.schedule.util.ScheduleHelp;
import com.smartdot.meeting.server.modules.schedule.util.ScheduleListView;
import com.smartdot.meeting.server.modules.testDemo.service.ITestService;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	论坛/日程
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午5:21:42
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {
	
	private static Logger logger = Logger.getLogger(ScheduleController.class);
	
	@Resource(name = IScheduleService.SERVICE_NAME)
	private IScheduleService scheduleService;
	
	@Resource(name = IMeetingAndMemberService.SERVICE_NAME)
	private IMeetingAndMemberService meetingAndMemberService;
	
	@Resource(name = IConHallService.SERVICE_NAME)
	private IConHallService conHallService;
	
	@Resource(name = ITestService.SERVICE_NAME)
	private ITestService testService;
	
	@Resource(name = IGuestService.SERVICE_NAME)
	private IGuestService guestService;
	
	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;
	
	@Resource(name = IDataDictionaryTypeService.SERVICE_NAME)
	private IDataDictionaryTypeService dataDictionaryTypeService;
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 跳转到列表页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:24:11
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toList")
	public String toList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/schedule/scheduleList";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	ajax请求列表数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:24:27
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String conHallId = request.getParameter("conHallId");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Schedule.class);
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (StringUtils.isNotBlank(conHallId)) {
			detachedCriteria.add(Restrictions.eq("conHallId",conHallId));
		}
		detachedCriteria.add(Restrictions.eq("enable", true));
		detachedCriteria.add(Restrictions.eq("language", "zh"));
		detachedCriteria.addOrder(GBKOrder.asc("name"));
		//detachedCriteria.addOrder(Order.desc("name"));
		//返回结果map
//		resultMap.put("pageNow", pageNo);//当前页
//		resultMap.put("pageSize", pageSize);//每页数量
//		resultMap.put("pageTotal", pageTotal);//总页数
//		resultMap.put("totalCount", totalCount);//总数量
//		resultMap.put("resultData", list);//返回结果集
		Map<String, Object> dataMap = scheduleService.pageScheduleList(detachedCriteria,PageUtilExtent.getPageInfo(request));
		if(dataMap == null||dataMap.get("resultData")==null){
			return dataMap;
		}
		List<Schedule> list =  (List<Schedule>) dataMap.get("resultData");
		List<ScheduleListView> listViews = null;
		if(list!=null&&list.size()>0){
			listViews = new ArrayList<ScheduleListView>();
			for (int i = 0; i < list.size(); i++) {
				Schedule schedule = list.get(i);
				ScheduleListView listView = new ScheduleListView();
				listView.setUniqueCode(schedule.getUniqueCode());
				listView.setName(schedule.getName());
				listView.setId(schedule.getId());
				listView.setDateDir(getTimeDir(schedule));
				listView.setConHallName(getConHallName(schedule));
				listViews.add(listView);
			}
			dataMap.put("resultData", listViews);
		}
		return dataMap;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取会场名称
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月16日 上午11:15:21
	 * </pre>
	 * </p>
	 */
	private String getConHallName(Schedule schedule) {
		ConHall conHall = conHallService.getEntityById(schedule.getConHallId());
		return conHall==null?"":conHall.getName();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月16日 上午11:15:09
	 * </pre>
	 * </p>
	 */
	private String getTimeDir(Schedule schedule) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(schedule.getScheduleStartDate())+"-"+format.format(schedule.getScheduleEndDate());
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转到编辑页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:25:03
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String uniqueCode = request.getParameter("uniqueCode");
		String id = request.getParameter("id");
		if(StringUtils.isBlank(uniqueCode)||StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("uniqueCode",uniqueCode);
		model.addAttribute("id",id);
		return "/schedule/scheduleEdit";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	ajax根据id获取实体
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:25:52
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getEntityById")
	@ResponseBody
	public Map<String, Object> getEntityById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return AjaxResult.errorResult("参数为空");
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Schedule schedule = scheduleService.getEntityById(id);
		ScheduleHelp scheduleHelp = new ScheduleHelp();
		BeanUtils.copyProperties(schedule, scheduleHelp);
		scheduleHelp.setStartDate(format.format(schedule.getScheduleStartDate()));
		scheduleHelp.setEndDate(format.format(schedule.getScheduleEndDate()));
		//主论坛信息
		resultMap.put("schedule", scheduleHelp);
		
		List<MeetingAndMember> listMember = meetingAndMemberService.getListScheduleId(schedule.getUniqueCode());//根据唯一标示获取参会嘉宾
		if(listMember!=null&&listMember.size()>0){
			String [] ids = new String[listMember.size()];
			for(int i=0;i<listMember.size();i++){
				ids[i] = listMember.get(i).getMemberUnique();
			}
			DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.isNotNull("member"));
			dc.add(Restrictions.in("id", ids));
			List<Guest> guestList = guestService.findGuestByDC(dc);
			List<Guest> guestResault = new ArrayList<Guest>();
			if(guestList!=null&&guestList.size()>0){
				for (int i = 0; i < ids.length; i++) {
					for (int j = 0; j < guestList.size(); j++) {
						Guest guest = guestList.get(j);
						if (guest.getId().equals(ids[i])) {
							guestResault.add(guest);
						}
					}
				}
			}
			//论坛选人列表信息
			resultMap.put("guestList", guestResault);
		}
		//会场信息
		ConHall conHall = conHallService.getEntityById(scheduleHelp.getConHallId());
		resultMap.put("conHall", conHall);
		return resultMap;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	保存修改的内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:26:07
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/editSave")
	@ResponseBody
	public Map<String, Object> editSave(HttpServletRequest request, HttpServletResponse response,String[] scheduleList,String scheduleMain) throws Exception {
		try {
			boolean flag = false;
			flag = scheduleService.updateMultiEntity(scheduleList,scheduleMain);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
		return AjaxResult.successResult();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取论坛所属板块集合
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月10日 下午5:55:06
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getPlateList")
	@ResponseBody
	public Map<String, Object> getPlateList() throws Exception {
		try {
			//论坛所属板块
			List<DataDictionary> forumplateList  = dataDictionaryService.findAllByTypeLogo("forumplate", DataDictionary.DATA_DICTIONARY_LANGUAGE_ZH);
			return AjaxResult.objectResult(forumplateList);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	组装实体对象
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午9:56:15
	 * </pre>
	 * </p>
	 */
	private Schedule assembleEntity(HttpServletRequest request,Schedule schedule) {
		return null;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转到新增页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:26:22
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/schedule/scheduleAdd";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	ajax保存新增数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:26:50
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/addSave")
	@ResponseBody
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response,String[] scheduleList,String scheduleMain) throws Exception {
		try {
			boolean flag = false;
			flag = scheduleService.saveMultiEntity(scheduleList,scheduleMain);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
		return AjaxResult.successResult();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据id集合删除多个对象
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午10:01:09
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/multiDelete")
	@ResponseBody
	public Map<String, Object> multiDelete(HttpServletRequest request,String[] ids, HttpServletResponse response) throws Exception {
//		String ids = request.getParameter("ids");
//		if(StringUtils.isBlank(ids)){
//			logger.error("方法参数错误");
//			return AjaxResult.errorResult("参数为空");
//		}
//		String[] idArr = ids.split(",");
		for(int i=0;i<ids.length;i++){
			deleteScheduleAndMemberByScheduleId(ids[i]);
		}
		return AjaxResult.successResult();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午10:09:05
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return AjaxResult.errorResult("参数为空");
		}
		deleteScheduleAndMemberByScheduleId(id);
		return AjaxResult.successResult();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据scheduleId逻辑删除论坛数据以及论坛的参会人员
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 下午3:57:35
	 * </pre>
	 * </p>
	 */
	private boolean deleteScheduleAndMemberByScheduleId(String id) {
		try {
			Schedule schedule = scheduleService.getEntityById(id);
			schedule.setEnable(false);
			schedule.setUpdateTimes(System.currentTimeMillis());
			scheduleService.saveEntity(schedule);
			deleteMemberByScheduleId(id);
			return true;
		} catch (Exception e) {
			logger.error("deleteScheduleAndMemberByScheduleId错误信息："+e);
			return false;
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据论坛id逻辑删除参会人员
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 下午4:01:27
	 * </pre>
	 * </p>
	 */
	private boolean deleteMemberByScheduleId(String id) {
		List<MeetingAndMember> mettingMemberList = meetingAndMemberService.getListScheduleId(id);
		if(mettingMemberList!=null&&mettingMemberList.size()>0){
			for(int i=0;i<mettingMemberList.size();i++){
				MeetingAndMember meetingAndMember = mettingMemberList.get(i);
				meetingAndMember.setEnable(false);
				meetingAndMemberService.saveEntity(meetingAndMember);
			}
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转的详情页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午10:01:33
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toDetail")
	public String toDetail(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String uniqueCode = request.getParameter("uniqueCode");
		String id = request.getParameter("id");
		if(StringUtils.isBlank(uniqueCode)||StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("uniqueCode",uniqueCode);
		model.addAttribute("id",id);
		return "/schedule/scheduleDetail";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	嵌入论坛选嘉宾的页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月17日 下午2:09:08
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toMemberList")
	public String toMemberList(HttpServletRequest request, HttpServletResponse response,Model model,String type) throws Exception {
		if(StringUtils.isBlank(type)){
			type = ScheduleController.MEMBER_TYPE_GUEST;
		}
		model.addAttribute("type", type);
		return "/schedule/memberList";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	论坛选嘉宾的分页页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月17日 下午2:09:08
	 * </pre>
	 * </p>
	 */
	private static final String MEMBER_TYPE_GUEST = "guest";
	private static final String MEMBER_TYPE_EXHIBITORS = "exhibitors";
	private static final String MEMBER_TYPE_MEDIA = "media";
	private static final String MEMBER_TYPE_AUDIENCE = "audience";
	private static final String MEMBER_TYPE_SERVICEPERSONNEL = "servicePersonnel";
	@RequestMapping(value = "/memberList")
	@ResponseBody
	public Map<String, Object> memberList(HttpServletRequest request, HttpServletResponse response,Model model,String type) throws Exception {
	//		app用户主（Member）model、嘉宾（Guest） model、展商（Exhibitors）model、媒体（Media）model、
	//		观众（Audience）model、服务人员（ServicePersonnel）model
		if(StringUtils.isBlank(type)){
			type = ScheduleController.MEMBER_TYPE_GUEST;
		}
		String name = request.getParameter("name");
		DetachedCriteria dc = null;
		if(type.equals(ScheduleController.MEMBER_TYPE_GUEST)){
			dc =  DetachedCriteria.forClass(Guest.class);
			//过滤条件自己加
		}else if(type.equals(ScheduleController.MEMBER_TYPE_EXHIBITORS)){
			dc =  DetachedCriteria.forClass(Exhibitors.class);
			//过滤条件自己加
		}else if(type.equals(ScheduleController.MEMBER_TYPE_MEDIA)){
			dc =  DetachedCriteria.forClass(Media.class);
		}else if(type.equals(ScheduleController.MEMBER_TYPE_AUDIENCE)){
			dc =  DetachedCriteria.forClass(Audience.class);
		}else if(type.equals(ScheduleController.MEMBER_TYPE_SERVICEPERSONNEL)){
			dc =  DetachedCriteria.forClass(ServicePersonnel.class);
		}
		if(StringUtils.isNotBlank(name)){
			dc.add(Restrictions.eq("name", name));
		}
		dc.add(Restrictions.isNotNull("member"));
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", "zh"));
		dc.addOrder(Order.asc("updateTimes"));
		return guestService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据嘉宾id集合获取嘉宾列表集合
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月9日 下午3:50:18
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getMemberByIds")
	@ResponseBody
	public Map<String, Object> memberList(HttpServletRequest request, HttpServletResponse response,String[] ids) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.isNotNull("member"));
		dc.add(Restrictions.in("id", ids));
		//查询条件
//		if(null != memberSearch){
//			if(StringUtils.isNotBlank(memberSearch.getMember_name())){
//		    	dc.add(Restrictions.like("name", "%"+memberSearch.getMember_name()+"%"));
//		    }
//			if(StringUtils.isNotBlank(memberSearch.getMember_userName())){
//		    	dc.add(Restrictions.or(Restrictions.like("member.invitationCode", "%"+memberSearch.getMember_userName()+"%"), 
//		    			Restrictions.like("member.userName", "%"+memberSearch.getMember_userName()+"%")));
//		    }
//			if(StringUtils.isNotBlank(memberSearch.getMember_mobile())){
//		    	dc.add(Restrictions.like("member.mobile", "%"+memberSearch.getMember_mobile()+"%"));
//		    }
//			if(StringUtils.isNotBlank(memberSearch.getMember_workPlace())){
//		    	dc.add(Restrictions.or(Restrictions.like("workPlace", "%"+memberSearch.getMember_workPlace()+"%"), 
//		    			Restrictions.like("job", "%"+memberSearch.getMember_workPlace()+"%")));
//		    }
//	    }
		dc.addOrder(Order.asc("updateTimes"));
		List<Guest> guestList = guestService.findGuestByDC(dc);
		return AjaxResult.objectResult(guestList);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月27日 下午5:45:55
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getMemberByIdsOrderByIds")
	@ResponseBody
	public Map<String, Object> getMemberByIdsOrderByIds(HttpServletRequest request, HttpServletResponse response,String[] ids) throws Exception {
		List<Guest> guestList = null;
		if (ids!=null&&ids.length>0) {
			guestList = new ArrayList<Guest>();
			for (int i = 0; i < ids.length; i++) {
				Guest guest = guestService.getGuestById(ids[i]);
				if (guest!=null) {
					guestList.add(guest);
				}
			}
		}
		return AjaxResult.objectResult(guestList);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取论坛语言以及论坛数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月15日 下午5:25:06
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getLanguage")
	@ResponseBody
	public Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> scheduleLanguage = new ArrayList<MemberLanguage>();
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		
		List<Map<String, String>> llanguage = scheduleService.getAllLanguage();
		List<Schedule> lGuest = scheduleService.getListSchedule(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lGuest && lGuest.size() > 0){
					for (Schedule guest : lGuest) {
						if(guest.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							scheduleList.add(guest);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Schedule guest = new Schedule();
					scheduleList.add(guest);
				}
				scheduleLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scheduleList", scheduleList);
		map.put("scheduleLanguage", scheduleLanguage);
		return map;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取会场数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月15日 下午5:54:58
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getConHallList")
	@ResponseBody
	public Map<String,Object> getConHallList() throws Exception {
		List<ConHall> conHallList = conHallService.getConHallList();
		return AjaxResult.objectResult(conHallList);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据唯一标示删除元素
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月15日 上午9:59:59
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/deleteByUniqueCode")
	@ResponseBody
	public Map<String, Object> deleteByUniqueCode(HttpServletRequest request, HttpServletResponse response,String uniqueCode) throws Exception {
		try {
			if(StringUtils.isBlank(uniqueCode)){
				return AjaxResult.errorResult("参数错误");
			}
			scheduleService.deleteByUniqueCode(uniqueCode);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
		return AjaxResult.successResult();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	判断会场是否冲突，嘉宾参加会议的时间是否冲突
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月2日 下午6:09:42
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/judgeWhetherConflict")
	@ResponseBody
	public Map<String, Object> judgeWhetherConflict(HttpServletRequest request, HttpServletResponse response,String idsStr,String conHallId,String startDate,String endDate) throws Exception {
		try {
			List<Map<String, Object>> conHallList = scheduleService.conHallWhetherConflict(conHallId,startDate,endDate);
			List<Map<String, Object>> memberList = new ArrayList<Map<String, Object>>();
			String[]ids= idsStr.split(",");
			if ((ids==null||ids.length<=0)||StringUtils.isBlank(idsStr)) {
			}else{
				memberList = scheduleService.memberWhetherConflict(ids,startDate,endDate);
			}
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("conHallList", conHallList);
			resultMap.put("memberList", memberList);
			return AjaxResult.objectResult(resultMap);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
	}
	
}
