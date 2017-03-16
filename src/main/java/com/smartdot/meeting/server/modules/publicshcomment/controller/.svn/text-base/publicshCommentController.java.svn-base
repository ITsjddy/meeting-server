package com.smartdot.meeting.server.modules.publicshcomment.controller;

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
import com.smartdot.meeting.server.modules.guest.service.IGuestService;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.modules.publicshcomment.model.PublicSerch;
import com.smartdot.meeting.server.modules.publicshcomment.model.PublicshCommentList;
import com.smartdot.meeting.server.modules.publicshcomment.model.publicshCommentForm;
import com.smartdot.meeting.server.modules.publicshcomment.service.IpublicshCommentService;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.entity.PublicshComment;


@Controller
@RequestMapping(value = "/publicshComment")
public class publicshCommentController {

	private static final Logger _LOG = Logger.getLogger(publicshCommentController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IpublicshCommentService.SERVICE_NAME)
	private IpublicshCommentService publicshCommentService;
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;
	@Resource(name = IGuestService.SERVICE_NAME)
	private IGuestService guestService;
	
	
	/**
	 * 主题未审核列表页面跳转
	 * */
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "publicshComment/list";
	}
	/**
	 * 评论未审核列表页面跳转
	 * */
	@RequestMapping(value = "/indexs")
	public String indexs() throws Exception {
		
		return "publicshComment/listOfPl";
	}
	/**
	 * 已审核列表页面跳转
	 * */
	@RequestMapping(value = "/checkList")
	public String checkedList() throws Exception {
		
		return "publicshComment/checkedList";
	}
	@RequestMapping(value = "/checkListofpl")
	public String checkedListofpl() throws Exception {
		
		return "publicshComment/checkedListofpl";
	}
	
	/**
	 * 主题详情页面跳转
	 * */
	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "publicshComment/details";
	}
	/**
	 * 评论详情页面跳转
	 * */
	@RequestMapping(value = "/detailsofpl")
	public String detailsofpl(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "publicshComment/detailsOfpl";
	}
	
	/**
	 * 审核页面跳转
	 * */
	@RequestMapping(value = "/auditing")
	public String auditing(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "publicshComment/auditing";
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(publicshCommentForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		PublicshComment publicshComment= new PublicshComment();
		BeanUtils.copyProperties(form, publicshComment);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = publicshCommentService.save(publicshComment);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(publicshCommentController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}
	/**
	 * 主题/评论 审核
	 * */
	@RequestMapping(value = "/audited")
	public @ResponseBody
	PublicshCommentList audited(String id,String status,String auditReason) throws Exception {
		PublicshCommentList publicshCom= new PublicshCommentList();
		if(StringUtils.isNotBlank(id)){
			PublicshCommentList pcm= publicshCommentService.getpublicshComment(id,status,auditReason);
			BeanUtils.copyProperties(pcm, publicshCom);
		}
		return publicshCom;
	}
	/**
	 * 列表（未审核）
	 * */
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, PublicSerch publicSerch,String type) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PublicshComment.class);
		detachedCriteria.add(Restrictions.eq("enable", true));
		detachedCriteria.addOrder(Order.desc("createTimes"));
		detachedCriteria.add(Restrictions.eq("status", "1"));
		detachedCriteria.add(Restrictions.eq("type", type));
		if(null != publicSerch){
			if(StringUtils.isNotBlank(publicSerch.getPublic_member()) || StringUtils.isNotBlank(publicSerch.getPublic_hfmember())){
				DetachedCriteria dcpc = DetachedCriteria.forClass(Guest.class);
				dcpc.add(Restrictions.eq("enable", true));
				if(StringUtils.isNotBlank(publicSerch.getPublic_member())){
					dcpc.add(Restrictions.like("name","%"+publicSerch.getPublic_member()+"%"));
				}		
				List<Guest> lguest = guestService.findByDetachedCriteria(dcpc);
				if(null != lguest && lguest.size() > 0){
					detachedCriteria.add(Restrictions.in("member", lguest));
				} else {
					DetachedCriteria dcp = DetachedCriteria.forClass(Member.class);
					dcp.add(Restrictions.eq("enable", true));			
					if(StringUtils.isNotBlank(publicSerch.getPublic_member())){
						dcp.add(Restrictions.or(Restrictions.like("invitationCode", "%"+publicSerch.getPublic_member()+"%"), 
				    			Restrictions.like("userName", "%"+publicSerch.getPublic_member()+"%")));
				    }
					List<Member> lmember = memberService.findByDetachedCriteria(dcp);
					if(null != lmember && lmember.size() > 0){
						dcp.add(Restrictions.in("member", lmember));
					}else{
						detachedCriteria.add(Restrictions.isNull("member"));
					}
				}
			}
			if(StringUtils.isNotBlank(publicSerch.getPublic_message())){
				detachedCriteria.add(Restrictions.like("message", "%"+publicSerch.getPublic_message()+"%"));
		    }
			if(StringUtils.isNotBlank(publicSerch.getPublic_type())){
				detachedCriteria.add(Restrictions.eq("type",publicSerch.getPublic_type()));
		    }
			if(StringUtils.isNotBlank(publicSerch.getPublic_status())){
				detachedCriteria.add(Restrictions.like("status",publicSerch.getPublic_status()));
			}
		}
		return publicshCommentService.pagedQuery(detachedCriteria, PageUtilExtent.getPageInfo(request));
}
	
	/**
	 * 列表（已审核）
	 * */
	@RequestMapping(value = "/pageQueryByStatus")
	public @ResponseBody
	Map<String, Object> pageQueryByStatus(HttpServletRequest request,PublicSerch publicSerch,String type) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PublicshComment.class);
		detachedCriteria.add(Restrictions.eq("enable",true));
		detachedCriteria.add(Restrictions.ne("status","1"));
		detachedCriteria.addOrder(Order.desc("createTimes"));
		detachedCriteria.add(Restrictions.eq("type", type));
		if(null != publicSerch){
				if(StringUtils.isNotBlank(publicSerch.getPublic_member()) || StringUtils.isNotBlank(publicSerch.getPublic_hfmember())){
					DetachedCriteria dcpc = DetachedCriteria.forClass(Guest.class);
					dcpc.add(Restrictions.eq("enable", true));
					if(StringUtils.isNotBlank(publicSerch.getPublic_member())){
						dcpc.add(Restrictions.like("name","%"+publicSerch.getPublic_member()+"%"));
					}		
					List<Guest> lguest = guestService.findByDetachedCriteria(dcpc);
					if(null != lguest && lguest.size() > 0){
						detachedCriteria.add(Restrictions.in("member", lguest));
					} else {
						DetachedCriteria dcp = DetachedCriteria.forClass(Member.class);
						dcp.add(Restrictions.eq("enable", true));			
						if(StringUtils.isNotBlank(publicSerch.getPublic_member())){
							dcp.add(Restrictions.or(Restrictions.like("invitationCode", "%"+publicSerch.getPublic_member()+"%"), 
					    			Restrictions.like("userName", "%"+publicSerch.getPublic_member()+"%")));
					    }
						List<Member> lmember = memberService.findByDetachedCriteria(dcp);
						if(null != lmember && lmember.size() > 0){
							dcp.add(Restrictions.in("member", lmember));
						}else{
							detachedCriteria.add(Restrictions.isNull("member"));
						}
					}
			
				}
				if(StringUtils.isNotBlank(publicSerch.getPublic_message())){
					detachedCriteria.add(Restrictions.like("message", "%"+publicSerch.getPublic_message()+"%"));
			    }
				if(StringUtils.isNotBlank(publicSerch.getPublic_type())){
					detachedCriteria.add(Restrictions.eq("type",publicSerch.getPublic_type()));
			    }
				if(StringUtils.isNotBlank(publicSerch.getPublic_status())){
					detachedCriteria.add(Restrictions.like("status",publicSerch.getPublic_status()));
				}
			}
		return publicshCommentService.pagedQuery(detachedCriteria, PageUtilExtent.getPageInfo(request));
	}
	
	@RequestMapping(value = "/queryBySubjectId")
	public @ResponseBody
	PublicshCommentList querySubjectById(String id) throws Exception {
		PublicshCommentList publicshCom = publicshCommentService.getpublicshCommentById(id);
		if(!"".equals(publicshCom.getPublicsh().getSubject()) || publicshCom.getPublicsh().getSubject()!= null){
			List<PublicshComment> pc = publicshCommentService.getPublicshBySubject(publicshCom.getPublicsh().getSubject().getId());
			publicshCom.setSubject(pc);
		}
		return publicshCom;
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	PublicshCommentList queryById(String id) throws Exception {
		PublicshCommentList publicshCom = publicshCommentService.getpublicshCommentById(id);
		return publicshCom;
	}
}
