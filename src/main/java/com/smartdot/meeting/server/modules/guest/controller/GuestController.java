package com.smartdot.meeting.server.modules.guest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.guest.service.IGuestService;
import com.smartdot.meeting.server.modules.department.service.IDepartmentService;
import com.smartdot.meeting.server.modules.guest.model.GuestAndServicePersonnelForm;
import com.smartdot.meeting.server.modules.guest.model.GuestForm;
import com.smartdot.meeting.server.modules.guest.model.MemberGuest;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.member.model.MemberSearch;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Member;


/**
 * ClassName: GuestController
 * @Description: 嘉宾管理
 * @author ms
 * @date 2017-2-07 下午3:03:14
 */
@Controller
@RequestMapping(value = "/guest")
public class GuestController {
	
	private static final Logger _LOG = Logger.getLogger(GuestController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IGuestService.SERVICE_NAME)
	private IGuestService guestService;
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;
	@Resource(name = IDepartmentService.SERVICE_NAME)
	private IDepartmentService departmentService;
	
	
	/**
	 * @Description: 嘉宾列表页面
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "guest/list";
	}
	
	/**
	 * @Description: 嘉宾添加页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "guest/edit";
	}
	
	/**
	 * @Description: 嘉宾修改页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "guest/edit";
	}

	/**
	 * @Description: 嘉宾服务人员设置页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/servicePersonnelIndex")
	public String servicePersonnelIndex(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "guest/serviceStaffing";
	}

	/**
	 * @Description: 服务人员tree页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/servicePersonnelTree")
	public String servicePersonnelTree(String i, Model model) throws Exception {
		
		model.addAttribute("i", i);
		return "guest/indexServicePersonnelTree";
	}

	/**
	 * @Description: 团tree页
	 * @author ms
	 * @date 2017-1-20 下午4:18:14
	 */
	@RequestMapping(value = "/groupTree")
	public String groupTree() throws Exception {
		
		return "guest/indexGuestTree";
	}
	
	/**
	 * @Description: 嘉宾分页列表
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, MemberSearch memberSearch) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.isNotNull("member"));
		dc.add(Restrictions.eq("language", "zh"));
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
				if(StringUtils.isNotBlank(memberSearch.getMember_mobile())){
					dcmember.add(Restrictions.like("mobile", "%"+memberSearch.getMember_mobile()+"%"));
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
			if(StringUtils.isNotBlank(memberSearch.getMember_workPlace())){
		    	dc.add(Restrictions.or(Restrictions.like("workPlace", "%"+memberSearch.getMember_workPlace()+"%"), 
		    			Restrictions.like("job", "%"+memberSearch.getMember_workPlace()+"%")));
		    }
			if(StringUtils.isNotBlank(memberSearch.getMember_vip())){
		    	dc.add(Restrictions.eq("vip", memberSearch.getMember_vip()));
		    }
	    }
		dc.add(Restrictions.sqlRestriction(" 1=1 order by convert(name using gbk)"));
		
		return guestService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	/**
	 * @Description: 嘉宾分页列表
	 * @author zl
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/pageQueryForGroup")
	public @ResponseBody
	Map<String, Object> pageQueryForGroup(HttpServletRequest request, MemberSearch memberSearch,String groupid) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.isNotNull("member"));
		dc.add(Restrictions.eq("language", "zh"));
		dc.add(Restrictions.eq("groupId", groupid));
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
				if(StringUtils.isNotBlank(memberSearch.getMember_mobile())){
					dcmember.add(Restrictions.like("mobile", "%"+memberSearch.getMember_mobile()+"%"));
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
			if(StringUtils.isNotBlank(memberSearch.getMember_workPlace())){
		    	dc.add(Restrictions.or(Restrictions.like("workPlace", "%"+memberSearch.getMember_workPlace()+"%"), 
		    			Restrictions.like("job", "%"+memberSearch.getMember_workPlace()+"%")));
		    }
	    }
		dc.add(Restrictions.sqlRestriction(" 1=1 order by convert(name using gbk)"));
		
		return guestService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	/**
	 * @Description: 嘉宾添加保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(GuestForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberGuest()){
			return returnValue;
		}
		result = guestService.save(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	/**
	 * @Description: 嘉宾修改保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(GuestForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberGuest()){
			return returnValue;
		}
		result = guestService.update(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	/**
	 * @Description: 嘉宾删除
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = guestService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = guestService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	/**
	 * @Description: 嘉宾修改所属服务人员保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/servicePersonnelSave")
	public @ResponseBody
	ReturnValue servicePersonnelSave(HttpServletRequest request) throws Exception {
		String guestId = request.getParameter("guestId");
		String[] lguestSer = request.getParameterValues("lguestSer");
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		//
		if(StringUtils.isNotBlank(guestId)){
			result = guestService.saveGuestAndServicePersonnel(guestId, lguestSer);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	MemberGuest queryById(String id) throws Exception {
		MemberGuest memberGuest = new MemberGuest();
		Guest guest = guestService.getGuestById(id);
		Member member = guest.getMember();
		BeanUtils.copyProperties(member, memberGuest);
		BeanUtils.copyProperties(guest, memberGuest);
		String groupId = guest.getGroupId();
		if(StringUtils.isNotBlank(groupId)){
			Department group = departmentService.getDepartmentById(groupId);
			memberGuest.setGroupName(group.getDepartname());
		}
		
		return memberGuest;
	}

	@RequestMapping(value = "/queryServicePersonnel")
	public @ResponseBody
	List<GuestAndServicePersonnelForm> queryServicePersonnel(String id) throws Exception {
		
		List<GuestAndServicePersonnelForm> serAll = guestService.getServicePersonnelByGuestId(id);
		
		return serAll;
	}
	
	@RequestMapping(value = "/queryServicePersonneTree")
	public @ResponseBody
	List<Map<String,Object>> queryServicePersonneTree() throws Exception {
		
		List<Map<String,Object>> lmap = guestService.queryALLServicePersonnel();
		
		return lmap;
	}
	

	@RequestMapping(value = "/queryGroupTree")
	public @ResponseBody
	List<Department> queryGroupTree() throws Exception {
		
		List<Department> ldepart = departmentService.findAll();
		
		return ldepart;
	}
	
	@RequestMapping(value = "/getLanguage")
	public @ResponseBody
	Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Guest> lGuest2 = new ArrayList<Guest>();
		List<DataDictionary> llanguage = memberService.getDatadictionary("languagetype");
		List<Guest> lGuest = guestService.getListGuest(id);
		if(null != llanguage && llanguage.size() > 0){
			for (DataDictionary maps : llanguage) {
				int i = 0;
				String uneIdent = maps.getdDLogo();
				String name = maps.getdDName();
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lGuest && lGuest.size() > 0){
					for (Guest guest : lGuest) {
						if(guest.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							lGuest2.add(guest);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Guest guest = new Guest();
					lGuest2.add(guest);
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		//证件类型
		List<DataDictionary> lIdType = memberService.getDatadictionary("idType");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lGuest", lGuest2);
		map.put("memberLanguage", lMemberLanguage);
		map.put("lIdType", lIdType);
		
		return map;
	}
	
}
