package com.smartdot.meeting.server.modules.media.controller;

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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.media.model.MediaForm;
import com.smartdot.meeting.server.modules.media.model.MemberMedia;
import com.smartdot.meeting.server.modules.media.service.IMediaService;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.member.model.MemberSearch;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.common.entity.Media;
import com.smartdot.meeting.server.common.entity.Member;


/**
 * ClassName: MediaController
 * @Description: 媒体管理
 * @author ms
 * @date 2017-2-07 下午3:03:14
 */
@Controller
@RequestMapping(value = "/media")
public class MediaController {

	private static final Logger _LOG = Logger.getLogger(MediaController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IMediaService.SERVICE_NAME)
	private IMediaService mediaService;
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;
	
	
	/**
	 * @Description: 媒体列表页面
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "media/list";
	}
	
	/**
	 * @Description: 媒体添加页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "media/edit";
	}
	
	/**
	 * @Description: 媒体修改页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "media/edit";
	}
	
	/**
	 * @Description: 媒体分页列表
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, MemberSearch memberSearch) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Media.class);
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
		dc.addOrder(Order.desc("updateTimes"));
		
		return mediaService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}

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
//		dc.addOrder(Order.asc("updateTimes"));
//		
//		return mediaService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
//	}
//	

	/**
	 * @Description: 嘉宾分页列表
	 * @author zl
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/pageQueryForGroup")
	public @ResponseBody
	Map<String, Object> pageQueryForGroup(HttpServletRequest request, MemberSearch memberSearch,String groupid) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Media.class);
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
		dc.addOrder(Order.desc("updateTimes"));
		
		return mediaService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	
	/**
	 * @Description: 媒体添加保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(MediaForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberMedia()){
			return returnValue;
		}
		result = mediaService.save(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}
//	ReturnValue save(MediaForm form) throws Exception {
//		ReturnValue returnValue =new ReturnValue();
//		boolean result = false;
//		Member member = new Member();
//		Media media = new Media();
//		BeanUtils.copyProperties(form, media);
//		BeanUtils.copyProperties(form, member);
//		if (StringUtils.isBlank(form.getId())) {
//			media.setMember(member);
//			result = memberService.save(member);
//			result = mediaService.save(media);
//		}
//		if(result){
//			returnValue.setSuccess(true);
//			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
//		}
//		return returnValue;
//
//	}

	/**
	 * @Description: 媒体修改保存
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(MediaForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberMedia()){
			return returnValue;
		}
		result = mediaService.update(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
//	ReturnValue update(MediaForm form) throws Exception {
//		ReturnValue returnValue =new ReturnValue();
//		boolean result = false;
//		Member member = new Member();
//		Media media = new Media();
//		BeanUtils.copyProperties(form, media);
//		BeanUtils.copyProperties(form, member);
//		if (StringUtils.isNotBlank(form.getId())) {
//			media.setMember(member);
//			result = memberService.update(member);
//			result = mediaService.update(media);
//		}
//		if(result){
//			returnValue.setSuccess(true);
//			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
//		}
//		return returnValue;
//	}
	
	/**
	 * @Description: 媒体删除
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = mediaService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = mediaService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
//	ReturnValue deleteById(String id) throws Exception {
//		ReturnValue returnValue =new ReturnValue();
//		boolean result = mediaService.deleteById(id);
//		if(result){
//			returnValue.setSuccess(true);
//			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
//		}
//		return returnValue;
//	}
//	@RequestMapping(value = "/deleteList")
//	public @ResponseBody
//	ReturnValue deleteList(String ids) throws Exception {
//		ReturnValue returnValue =new ReturnValue();
//		String[] idArray = ids.split(",");
//		List<String> idList = Arrays.asList(idArray);
//		boolean result = mediaService.deleteAll(idList);
//		if(result){
//			returnValue.setSuccess(true);
//			returnValue.setMessage(MediaController.SUCCESS_MESSAGE);
//		}
//		return returnValue;
//	}
//	
//	@RequestMapping(value = "/queryById")
//	public @ResponseBody
//	MediaForm queryById(String id) throws Exception {
//		MediaForm mediaForm = new MediaForm();
//		Media media = mediaService.getMediaById(id);
//		Member member = media.getMember();
//		BeanUtils.copyProperties(member, mediaForm);
//		BeanUtils.copyProperties(media, mediaForm);
//		
//		return mediaForm;
//	}
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	MemberMedia queryById(String id) throws Exception {
		MemberMedia memberMedia = new MemberMedia();
		Media media = mediaService.getMediaById(id);
		Member member = media.getMember();
		BeanUtils.copyProperties(member, memberMedia);
		BeanUtils.copyProperties(media, memberMedia);
		
		return memberMedia;
	}
	@RequestMapping(value = "/getLanguage")
	public @ResponseBody
	Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Media> lMedia2 = new ArrayList<Media>();
		
		List<Map<String, String>> llanguage = mediaService.getAllLanguage();
		List<Media> lMedia = mediaService.getListMedia(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lMedia && lMedia.size() > 0){
					for (Media media : lMedia) {
						if(media.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							lMedia2.add(media);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Media media = new Media();
					lMedia2.add(media);
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lMedia", lMedia2);
		map.put("memberLanguage", lMemberLanguage);
		
		return map;
	}
}
