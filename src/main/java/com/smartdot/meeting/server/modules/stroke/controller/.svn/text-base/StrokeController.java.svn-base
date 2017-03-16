package com.smartdot.meeting.server.modules.stroke.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.guest.controller.GuestController;
import com.smartdot.meeting.server.modules.infomgmt.service.ISavorPointService;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.stroke.model.StrokeForm;
import com.smartdot.meeting.server.modules.stroke.model.StrokePageForm;
import com.smartdot.meeting.server.modules.stroke.model.StrokeVO;
import com.smartdot.meeting.server.modules.stroke.service.IStrokeService;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Stroke;
import com.smartdot.meeting.server.common.entity.MemberHotel;
import com.smartdot.meeting.server.common.entity.SavorPoint;


@Controller
@RequestMapping(value = "/stroke")
public class StrokeController {

	private static final Logger _LOG = Logger.getLogger(StrokeController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IStrokeService.SERVICE_NAME)
	private IStrokeService strokeService;
	@Resource(name = ISavorPointService.SERVICE_NAME)
	private ISavorPointService savorPointService;
	
	/**
	 * @Description: 嘉宾修改页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/indexs")
	public String stroke(String id,String member, Model model) throws Exception {
		
		model.addAttribute("id", id);
		model.addAttribute("member", member);
		return "stroke/stroke";
	}
	
	@RequestMapping(value = "/index")
	public String index(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "stroke/stroke";
	}
	@RequestMapping(value = "/hotel")
	public String hotel(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "stroke/hotel";
	}
	@RequestMapping(value = "/edit")
	public String edit(String id,String ids,String member, Model model) throws Exception {
		model.addAttribute("id", id);
		model.addAttribute("ids", ids);
		model.addAttribute("member", member);
		return "stroke/edit";
	}
	@RequestMapping(value = "/details")
	public String details(String id,String ids,String member, Model model) throws Exception {
		
		model.addAttribute("id", id);
		model.addAttribute("ids", ids);
		model.addAttribute("member", member);
		return "stroke/details";
	}
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<StrokeVO> queryAll() throws Exception {
		List<Stroke> strokes = strokeService.findAll();
		List<StrokeVO> vo = new ArrayList<StrokeVO>();
		StrokeVO strokeVO = null;
		for (Stroke stroke : strokes) {
			strokeVO  = new StrokeVO();
			BeanUtils.copyProperties(stroke, strokeVO);
			vo.add(strokeVO);
		}
		return vo;
	}

	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	保存修改的内容
	 * <b>作者：</b>
	 * 	sunjd(刘东海)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:26:07
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response,String strokeMain,String[] strokeList) throws Exception {
		try {
			boolean flag = false;
			flag = strokeService.saveMultiEntity(strokeMain,strokeList);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
		return AjaxResult.successResult();
	}
	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<StrokeForm> form) throws Exception {
	List<Stroke> strokes=new ArrayList<Stroke>();
		ReturnValue returnValue =new ReturnValue();
		Stroke entityStroke = new Stroke();
		for (StrokeForm stroke : form) {
			entityStroke=new Stroke();
			BeanUtils.copyProperties(stroke, entityStroke);
			strokes.add(entityStroke);
		}
		boolean result = strokeService.saveAll(strokes);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(StrokeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = strokeService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(StrokeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = strokeService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(StrokeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(StrokeForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberStroke()){
			return returnValue;
		}
		result = strokeService.update(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
		
		
//		form.getStartStroketime();
//		ReturnValue returnValue =new ReturnValue();
//		boolean result = false;
//		Stroke stroke = new Stroke();
//		BeanUtils.copyProperties(form, stroke);
//		if (StringUtils.isNotEmpty(form.getId())) {
//			result = strokeService.updateStroke(stroke);
//		}
//		if(result){
//			returnValue.setSuccess(true);
//			returnValue.setMessage(StrokeController.SUCCESS_MESSAGE);
//		}
//		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	StrokeVO queryById(String id) throws Exception {
		StrokeVO vo = new StrokeVO();
		Stroke stroke = strokeService.getStrokeById(id);
		if (stroke != null) {
			BeanUtils.copyProperties(stroke, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, StrokePageForm req,String id) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Stroke.class);
		if(StringUtils.isNotBlank(request.getParameter("stroke_title"))){
	    	dc.add(Restrictions.like("title", "%"+request.getParameter("stroke_title")+"%"));
	    }
		
		if(StringUtils.isNotBlank(request.getParameter("stroke_date"))){
	    	dc.add(Restrictions.like("startStrokedate", "%"+request.getParameter("stroke_date")+"%"));
	    }
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		dc.add(Restrictions.eq("memberId", id));
		dc.add(Restrictions.eq("language", "zh"));
		return strokeService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
//	酒店信息
//	@RequestMapping(value = "/hotelInfo")
//	public @ResponseBody
//	Map<String, Object> hotelInfo(HttpServletRequest request, StrokePageForm req,String id) throws Exception {
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<SavorPoint> savorPoint = savorPointService.findAll();
//		List<MemberHotel> memHotel = strokeService.hotelIntroduction();
////		DetachedCriteria dc = DetachedCriteria.forClass(MemberHotel.class);
////		dc.add(Restrictions.eq("enable", true));
////		dc.addOrder(Order.asc("updateTimes"));
////		
//		
////		dc.add(Restrictions.eq("memberId", id));
//		return strokeService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
//	}
	
	
	@RequestMapping(value = "/getLanguage")
	public @ResponseBody
	Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Stroke> lGuest2 = new ArrayList<Stroke>();
		
		List<Map<String, String>> llanguage = strokeService.getAllLanguage();
		List<Stroke> lGuest = strokeService.getListStoke(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lGuest && lGuest.size() > 0){
					for (Stroke stroke : lGuest) {
						if(stroke.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							lGuest2.add(stroke);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Stroke stroke = new Stroke();
					lGuest2.add(stroke);
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lGuest", lGuest2);
		map.put("memberLanguage", lMemberLanguage);
		
		return map;
	}
}
