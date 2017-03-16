package com.smartdot.meeting.server.modules.issue.controller;
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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.ConHall;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Issue;
import com.smartdot.meeting.server.common.entity.MeetingAndMember;
import com.smartdot.meeting.server.common.entity.Schedule;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.GBKOrder;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.conHall.service.IConHallService;
import com.smartdot.meeting.server.modules.guest.service.IGuestService;
import com.smartdot.meeting.server.modules.issue.service.IIssueService;
import com.smartdot.meeting.server.modules.issue.util.IssueHelp;
import com.smartdot.meeting.server.modules.issue.util.IssueListView;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.schedule.service.IMeetingAndMemberService;
import com.smartdot.meeting.server.modules.schedule.service.IScheduleService;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	议题
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午5:21:42
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value = "/issue")
public class IssueController {
	
	private static Logger logger = Logger.getLogger(IssueController.class);
	
	@Resource(name = IIssueService.SERVICE_NAME)
	private IIssueService issueService;
	
	@Resource(name = IConHallService.SERVICE_NAME)
	private IConHallService conHallService;
	
	@Resource(name = IScheduleService.SERVICE_NAME)
	private IScheduleService scheduleService;
	
	@Resource(name = IMeetingAndMemberService.SERVICE_NAME)
	private IMeetingAndMemberService meetingAndMemberService;
	
	@Resource(name = IGuestService.SERVICE_NAME)
	private IGuestService guestService;
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
		return "/issue/issueList";
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
		String scheduleId = request.getParameter("scheduleId");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Issue.class);
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("issueName", "%" + name + "%"));
		}
		if (StringUtils.isNotBlank(conHallId)) {
			detachedCriteria.add(Restrictions.eq("conHallId",conHallId));
		}
		if (StringUtils.isNotBlank(scheduleId)) {
			detachedCriteria.add(Restrictions.eq("scheduleId",scheduleId));
		}
		detachedCriteria.add(Restrictions.eq("enable", true));
		detachedCriteria.add(Restrictions.eq("language", "zh"));
		detachedCriteria.addOrder(GBKOrder.asc("issueName"));
		Map<String, Object> dataMap = issueService.pageIssueList(detachedCriteria,PageUtilExtent.getPageInfo(request));
		if(dataMap == null||dataMap.get("resultData")==null){
			return dataMap;
		}
		List<Issue> list =  (List<Issue>) dataMap.get("resultData");
		List<IssueListView> listViews = null;
		if(list!=null&&list.size()>0){
			listViews = new ArrayList<IssueListView>();
			for (int i = 0; i < list.size(); i++) {
				Issue issue = list.get(i);
				IssueListView listView = new IssueListView();
				listView.setUniqueCode(issue.getUniqueCode());
				listView.setName(issue.getIssueName());
				listView.setId(issue.getId());
				listView.setDateDir(getTimeDir(issue));
				listView.setConHallName(getConHallName(issue));
				listView.setScheduleName(getScheduleName(issue));
				listViews.add(listView);
			}
			dataMap.put("resultData", listViews);
		}
		return dataMap;
	}
	private String getScheduleName(Issue issue) {
		Schedule schedule = scheduleService.getEntityById(issue.getScheduleId());
		return schedule == null?"":schedule.getName();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取会场名字
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月16日 下午5:49:04
	 * </pre>
	 * </p>
	 */
	private String getConHallName(Issue issue) {
		ConHall conHall = conHallService.getEntityById(issue.getConHallId());
		return conHall==null?"":conHall.getName();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取议题截止时间
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月16日 下午5:49:24
	 * </pre>
	 * </p>
	 */
	private String getTimeDir(Issue issue) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(issue.getStartDate())+"-"+format.format(issue.getEndDate());
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
		return "/issue/issueEdit";
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
	public Map<String, Object> editSave(HttpServletRequest request, HttpServletResponse response,String[] issueList,String issueMain) throws Exception {
		try {
			boolean flag = false;
			flag = issueService.updateMultiEntity(issueList,issueMain);
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
	 * 	所有论坛信息
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月15日 上午9:53:44
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getAllScheduleData")
	@ResponseBody
	public Map<String, Object> getAllScheduleData() throws Exception {
		try {
			List<Schedule> scheduleList = scheduleService.getAllScheduleData();
			return AjaxResult.objectResult(scheduleList);
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
	private Issue assembleEntity(HttpServletRequest request,Issue issue) {
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
		return "/issue/issueAdd";
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
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response,String[] issueList,String issueMain) throws Exception {
		try {
			boolean flag = false;
			flag = issueService.saveMultiEntity(issueList,issueMain);
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
			Issue issue = issueService.getEntityById(ids[i]);
			issueService.deleteByUniqueCode(issue.getUniqueCode());
//			issue.setEnable(false);
//			issueService.saveEntity(issue);
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
		Issue issue = issueService.getEntityById(id);
		issue.setEnable(false);
		issueService.saveEntity(issue);
		return AjaxResult.successResult();
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
		return "/issue/issueDetail";
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
	 * 	获取论坛数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月17日 上午10:09:31
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getScheduleList")
	@ResponseBody
	public Map<String,Object> getScheduleList() throws Exception {
		List<Schedule> scheduleList = scheduleService.getScheduleList();
		return AjaxResult.objectResult(scheduleList);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	删除议题
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月17日 下午3:02:21
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/deleteByUniqueCode")
	@ResponseBody
	public Map<String,Object> deleteByUniqueCode(HttpServletRequest request, HttpServletResponse response,String uniqueCode) throws Exception {
		try {
			if(StringUtils.isBlank(uniqueCode)){
				return AjaxResult.errorResult("参数错误");
			}
			issueService.deleteByUniqueCode(uniqueCode);
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
	 * 获取议题数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月17日 上午11:20:46
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getEntityById")
	@ResponseBody
	public Map<String,Object> getEntityById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return AjaxResult.errorResult("参数为空");
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Issue issue = issueService.getEntityById(id);
		IssueHelp issueHelp = new IssueHelp();
		BeanUtils.copyProperties(issue, issueHelp);
		issueHelp.setIssueStartDate(format.format(issue.getStartDate()));
		issueHelp.setIssueEndDate(format.format(issue.getEndDate()));
		//主论坛信息
		resultMap.put("issue", issueHelp);
		
		List<MeetingAndMember> listMember = meetingAndMemberService.getListScheduleId(issue.getUniqueCode());//根据唯一标示获取参会嘉宾
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
		ConHall conHall = conHallService.getEntityById(issueHelp.getConHallId());
		resultMap.put("conHall", conHall);
		return resultMap;
	
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取议题语言以及议题数据
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
		List<Issue> scheduleList = new ArrayList<Issue>();
		
		List<Map<String, String>> llanguage = issueService.getAllLanguage();
		List<Issue> lGuest = issueService.getListIssue(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lGuest && lGuest.size() > 0){
					for (Issue guest : lGuest) {
						if(guest.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							scheduleList.add(guest);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Issue guest = new Issue();
					scheduleList.add(guest);
				}
				scheduleLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("issueList", scheduleList);
		map.put("issueLanguage", scheduleLanguage);
		return map;
	}
}
