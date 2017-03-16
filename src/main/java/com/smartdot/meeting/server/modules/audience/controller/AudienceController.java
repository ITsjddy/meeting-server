package com.smartdot.meeting.server.modules.audience.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.audience.model.AudienceForm;
import com.smartdot.meeting.server.modules.audience.model.MemberAudience;
import com.smartdot.meeting.server.modules.audience.service.IAudienceService;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.member.model.MemberSearch;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.Member;


@Controller
@RequestMapping(value = "/audience")
public class AudienceController {

	private static final Logger _LOG = Logger.getLogger(AudienceController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IAudienceService.SERVICE_NAME)
	private IAudienceService audienceService;
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;

	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "audience/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "audience/add";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "audience/add";
	}
	
	@RequestMapping(value = "/detail")
	public String detail(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "audience/detail";
	}
	
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, MemberSearch memberSearch) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Audience.class);
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
	    }
		dc.addOrder(Order.asc("updateTimes"));
		
		return audienceService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(AudienceForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberAudience()){
			return returnValue;
		}
		result = audienceService.save(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(AudienceController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(AudienceForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberAudience()){
			return returnValue;
		}
		result = audienceService.update(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(AudienceController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = audienceService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(AudienceController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = audienceService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(AudienceController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	MemberAudience queryById(String id) throws Exception {
		MemberAudience memberAudience = new MemberAudience();
		Audience audience = audienceService.getAudienceById(id);
		Member member = audience.getMember();
		BeanUtils.copyProperties(member, memberAudience);
		BeanUtils.copyProperties(audience, memberAudience);
		return memberAudience;
	}
	@RequestMapping(value = "/getLanguage")
	public @ResponseBody
	Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Audience> lAudience2 = new ArrayList<Audience>();
		List<Map<String, String>> llanguage = audienceService.getAllLanguage();
		List<Audience> lAudience = audienceService.getListAudience(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lAudience && lAudience.size() > 0){
					for (Audience audience : lAudience) {
						if(audience.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							lAudience2.add(audience);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Audience audience = new Audience();
					lAudience2.add(audience);
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lAudience", lAudience2);
		map.put("memberLanguage", lMemberLanguage);
		
		return map;
	}
	
}
