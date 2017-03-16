package com.smartdot.meeting.server.modules.servicePersonnel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.EncodEncryUtil;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.department.service.IDepartmentService;
import com.smartdot.meeting.server.modules.member.model.MemberSearch;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.modules.servicePersonnel.model.ServicePersonnelForm;
import com.smartdot.meeting.server.modules.servicePersonnel.service.IServicePersonnelService;
import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;


/**
 * ClassName: ServicePersonnelController
 * @Description: 展商管理
 * @author ms
 * @date 2017-2-07 下午3:03:14
 */
@Controller
@RequestMapping(value = "/servicePersonnel")
public class ServicePersonnelController {

	private static final Logger _LOG = Logger.getLogger(ServicePersonnelController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IServicePersonnelService.SERVICE_NAME)
	private IServicePersonnelService servicePersonnelService;
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;
	@Resource(name = IDepartmentService.SERVICE_NAME)
	private IDepartmentService departmentService;
	
	/**
	 * @Description: 服务人员列表页面
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/index")
	public String index(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "servicePersonnel/list";
	}
	
	/**
	 * @Description: 服务人员添加页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "servicePersonnel/edit";
	}
	
	/**
	 * @Description: 服务人员修改页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "servicePersonnel/edit";
	}
	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "servicePersonnel/details";
	}
	
	@RequestMapping(value = "/groupTree")
	public String groupTree() throws Exception {
		
		return "servicePersonnel/indexGuestTree";
	}
	/**
	 * @Description: 服务人员分页列表
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, MemberSearch memberSearch) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(ServicePersonnel.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.isNotNull("member"));
		//查询条件
		if(null != memberSearch){
			if(StringUtils.isNotBlank(memberSearch.getMember_userName())||
					StringUtils.isNotBlank(memberSearch.getMember_mobile())){
				DetachedCriteria dcmember = DetachedCriteria.forClass(Member.class);
				dcmember.add(Restrictions.eq("enable", true));	
				if(StringUtils.isNotBlank(memberSearch.getMember_userName())){
					dcmember.add(Restrictions.or(Restrictions.like("invitationCode", "%"+memberSearch.getMember_userName()+"%"), 
			    			Restrictions.like("userName", "%"+memberSearch.getMember_userName()+"%")));
			    }
				List<Member> lmember = memberService.findByDetachedCriteria(dcmember);
				if(null != lmember && lmember.size() > 0){
					dc.add(Restrictions.in("member", lmember));
				} else {
					dc.add(Restrictions.isNull("member"));
				}
			}
			if(StringUtils.isNotBlank(memberSearch.getMember_name())){
		    	dc.add(Restrictions.like("name", "%"+memberSearch.getMember_name()+"%"));
		    }
//			if(StringUtils.isNotBlank(memberSearch.getMember_userName())){
//				dc.add(Restrictions.or(Restrictions.like("invitationCode", "%"+memberSearch.getMember_userName()+"%"), 
//		    			Restrictions.like("userName", "%"+memberSearch.getMember_userName()+"%")));
//		    }
			if(StringUtils.isNotBlank(memberSearch.getMember_workPlace())){
		    	dc.add(Restrictions.like("company", "%"+memberSearch.getMember_workPlace()+"%"));
		    }
//			if(StringUtils.isNotBlank(memberSearch.getMember_workPlace())){
//		    	dc.add(Restrictions.or(Restrictions.like("workPlace", "%"+memberSearch.getMember_workPlace()+"%"), 
//		    			Restrictions.like("job", "%"+memberSearch.getMember_workPlace()+"%")));
//		    }
	    }
		dc.addOrder(Order.desc("updateTimes"));
//		Map<String, Object> map =servicePersonnelService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
//		String groupid =map.get("groupId").toString();
		return servicePersonnelService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	/**
	 * @Description: 服务人员分页列表
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/pageQueryForGroup")
	public @ResponseBody
	Map<String, Object> pageQueryForGroup(HttpServletRequest request, MemberSearch memberSearch,String groupid) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(ServicePersonnel.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.isNotNull("member"));
		dc.add(Restrictions.eq("groupId", groupid));
		//查询条件
		if(null != memberSearch){
			if(StringUtils.isNotBlank(memberSearch.getMember_name())){
		    	dc.add(Restrictions.like("name", "%"+memberSearch.getMember_name()+"%"));
		    }
			if(StringUtils.isNotBlank(memberSearch.getMember_userName())){
		    	dc.add(Restrictions.or(Restrictions.like("member.invitationCode", "%"+memberSearch.getMember_userName()+"%"), 
		    			Restrictions.like("member.userName", "%"+memberSearch.getMember_userName()+"%")));
		    }
			if(StringUtils.isNotBlank(memberSearch.getMember_mobile())){
		    	dc.add(Restrictions.like("member.mobile", "%"+memberSearch.getMember_mobile()+"%"));
		    }
			if(StringUtils.isNotBlank(memberSearch.getMember_workPlace())){
		    	dc.add(Restrictions.or(Restrictions.like("workPlace", "%"+memberSearch.getMember_workPlace()+"%"), 
		    			Restrictions.like("job", "%"+memberSearch.getMember_workPlace()+"%")));
		    }
	    }
		dc.addOrder(Order.asc("updateTimes"));
		
		return servicePersonnelService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	/**
	 * @Description: 服务人员添加保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(ServicePersonnelForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		Member member = new Member();
		String password = form.getPassword();
		ServicePersonnel servicePersonnel = new ServicePersonnel();
		BeanUtils.copyProperties(form, servicePersonnel);
		BeanUtils.copyProperties(form, member);
		if (StringUtils.isBlank(form.getId())) {
			servicePersonnel.setMember(member);
			
			if(StringUtils.isNotBlank(password)){
				Map<String,String> map=EncodEncryUtil.generateSalt(password);//SALT+MD5加密
				String salt = map.get("salt");
				member.setSalt(EncodEncryUtil.compressData2(salt));//对盐进行base64加密
				password = map.get("saltpassword");
				member.setPassword(password);
			}
			result = memberService.save(member);
			result = servicePersonnelService.save(servicePersonnel);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ServicePersonnelController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	/**
	 * @Description: 服务人员修改保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(ServicePersonnelForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		Member member = new Member();
		ServicePersonnel servicePersonnel = new ServicePersonnel();
		BeanUtils.copyProperties(form, servicePersonnel);
		BeanUtils.copyProperties(form, member);
		if (StringUtils.isNotBlank(form.getId())) {
			servicePersonnel.setMember(member);
			result = memberService.update(member);
			result = servicePersonnelService.update(servicePersonnel);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ServicePersonnelController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	/**
	 * @Description: 服务人员删除
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = servicePersonnelService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ServicePersonnelController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = servicePersonnelService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ServicePersonnelController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	ServicePersonnelForm queryById(String id) throws Exception {
		ServicePersonnelForm servicePersonnelForm = new ServicePersonnelForm();
		ServicePersonnel servicePersonnel = servicePersonnelService.getServicePersonnelById(id);
		Member member = servicePersonnel.getMember();
		BeanUtils.copyProperties(member, servicePersonnelForm);
		BeanUtils.copyProperties(servicePersonnel, servicePersonnelForm);
		String groupId = servicePersonnel.getGroupId();
		if(StringUtils.isNotBlank(groupId)){
			Department group = departmentService.getDepartmentById(groupId);
			if(null!=group){
				servicePersonnelForm.setGroupName(group.getDepartname());
			}
			
		}
		return servicePersonnelForm;
	}
	@RequestMapping(value = "/queryGroupTree")
	public @ResponseBody
	List<Department> queryGroupTree() throws Exception {
		
		List<Department> ldepart = departmentService.findAll();
		
		return ldepart;
	}
	
}
