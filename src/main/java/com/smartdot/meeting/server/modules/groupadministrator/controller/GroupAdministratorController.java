package com.smartdot.meeting.server.modules.groupadministrator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.smartdot.meeting.server.common.entity.GroupAdministrator;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.groupadministrator.model.GroupAdministratorForm;
import com.smartdot.meeting.server.modules.groupadministrator.model.GroupAdministratorVO;
import com.smartdot.meeting.server.modules.groupadministrator.service.IGroupAdministratorService;
import com.smartdot.meeting.server.modules.member.service.IMemberService;



@Controller
@RequestMapping(value = "/groupAdministrator")
public class GroupAdministratorController {

	private static final Logger _LOG = Logger.getLogger(GroupAdministratorController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IGroupAdministratorService.SERVICE_NAME)
	private IGroupAdministratorService groupAdministratorService;
	
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;
	
	@RequestMapping(value = "/addAdmin")
	public String addAdmin(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "groupadministrator/add";
	}
	
	@RequestMapping(value = "/newAdmin")
	public String newAdmin(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "groupadministrator/new";
	}
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<GroupAdministratorVO> queryAll() throws Exception {
		List<GroupAdministrator> groupAdministrators = groupAdministratorService.findAll();
		List<GroupAdministratorVO> vo = new ArrayList<GroupAdministratorVO>();
		GroupAdministratorVO groupAdministratorVO = null;
		for (GroupAdministrator groupAdministrator : groupAdministrators) {
			groupAdministratorVO  = new GroupAdministratorVO();
			BeanUtils.copyProperties(groupAdministrator, groupAdministratorVO);
			vo.add(groupAdministratorVO);
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(GroupAdministratorForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		Member member = new Member();
		GroupAdministrator groupAdministrator= new GroupAdministrator();
		BeanUtils.copyProperties(form, groupAdministrator);
		BeanUtils.copyProperties(form, member);
		boolean result=false;;
		if (StringUtils.isEmpty(form.getId())) {
			groupAdministrator.setMember(member);
			result = memberService.save(member);
			result = groupAdministratorService.save(groupAdministrator);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GroupAdministratorController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<GroupAdministratorForm> form) throws Exception {
	List<GroupAdministrator> groupAdministrators=new ArrayList<GroupAdministrator>();
		ReturnValue returnValue =new ReturnValue();
		GroupAdministrator entityGroupAdministrator = new GroupAdministrator();
		for (GroupAdministratorForm groupAdministrator : form) {
			entityGroupAdministrator=new GroupAdministrator();
			BeanUtils.copyProperties(groupAdministrator, entityGroupAdministrator);
			groupAdministrators.add(entityGroupAdministrator);
		}
		boolean result = groupAdministratorService.saveAll(groupAdministrators);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GroupAdministratorController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}


	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = groupAdministratorService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GroupAdministratorController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = groupAdministratorService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GroupAdministratorController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(GroupAdministratorForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		GroupAdministrator groupAdministrator = new GroupAdministrator();
		BeanUtils.copyProperties(form, groupAdministrator);
		boolean result = groupAdministratorService.updateGroupAdministrator(groupAdministrator);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GroupAdministratorController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	GroupAdministratorVO queryById(String id) throws Exception {
		GroupAdministratorVO vo = new GroupAdministratorVO();
		GroupAdministrator groupAdministrator = groupAdministratorService.getGroupAdministratorById(id);
		if (groupAdministrator != null) {
			BeanUtils.copyProperties(groupAdministrator, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request,String groupid) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(GroupAdministrator.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("groupId", groupid));
		dc.addOrder(Order.asc("updateTimes"));
		return groupAdministratorService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
