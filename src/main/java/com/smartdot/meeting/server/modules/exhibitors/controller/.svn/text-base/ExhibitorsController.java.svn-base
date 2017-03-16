package com.smartdot.meeting.server.modules.exhibitors.controller;

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
import com.smartdot.meeting.server.modules.audience.controller.AudienceController;
import com.smartdot.meeting.server.modules.audience.model.MemberAudience;
import com.smartdot.meeting.server.modules.exhibitors.model.ExhibitorsForm;
import com.smartdot.meeting.server.modules.exhibitors.model.MemberExhibitors;
import com.smartdot.meeting.server.modules.exhibitors.service.IExhibitorsService;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.member.model.MemberSearch;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.Exhibitors;
import com.smartdot.meeting.server.common.entity.Member;


/**
 * ClassName: ExhibitorsController
 * @Description: 展商管理
 * @author ms
 * @date 2017-2-07 下午3:03:14
 */
@Controller
@RequestMapping(value = "/exhibitors")
public class ExhibitorsController {

	private static final Logger _LOG = Logger.getLogger(ExhibitorsController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IExhibitorsService.SERVICE_NAME)
	private IExhibitorsService exhibitorsService;
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;
	
	
	/**
	 * @Description: 展商列表页面
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "exhibitors/list";
	}
	
	/**
	 * @Description: 展商添加页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "exhibitors/edit";
	}
	
	/**
	 * @Description: 展商修改页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "exhibitors/edit";
	}
	@RequestMapping(value = "/detail")
	public String detail(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "exhibitors/detail";
	}
	/**
	 * @Description: 展商分页列表
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, MemberSearch memberSearch) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Exhibitors.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", "zh"));
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
		
		return exhibitorsService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	/**
	 * @Description: 展商添加保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(ExhibitorsForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberExhibitors()){
			return returnValue;
		}
		result = exhibitorsService.save(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ExhibitorsController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	/**
	 * @Description: 展商修改保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(ExhibitorsForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberExhibitors()){
			return returnValue;
		}
		result = exhibitorsService.update(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ExhibitorsController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	/**
	 * @Description: 展商删除
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = exhibitorsService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ExhibitorsController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = exhibitorsService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(ExhibitorsController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	MemberExhibitors queryById(String id) throws Exception {
		MemberExhibitors memberExhibitors = new MemberExhibitors();
		Exhibitors exhibitors = exhibitorsService.getExhibitorsById(id);
		Member member = exhibitors.getMember();
		BeanUtils.copyProperties(member, memberExhibitors);
		BeanUtils.copyProperties(exhibitors, memberExhibitors);
		return memberExhibitors;
	}
	@RequestMapping(value = "/getLanguage")
	public @ResponseBody
	Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Exhibitors> lExhibitors2 = new ArrayList<Exhibitors>();
		List<Map<String, String>> llanguage = exhibitorsService.getAllLanguage();
		List<Exhibitors> lExhibitors = exhibitorsService.getListExhibitors(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lExhibitors && lExhibitors.size() > 0){
					for (Exhibitors exhibitors : lExhibitors) {
						if(exhibitors.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							lExhibitors2.add(exhibitors);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Exhibitors exhibitors = new Exhibitors();
					lExhibitors2.add(exhibitors);
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lExhibitors", lExhibitors2);
		map.put("memberLanguage", lMemberLanguage);
		
		return map;
	}
	
	
}
