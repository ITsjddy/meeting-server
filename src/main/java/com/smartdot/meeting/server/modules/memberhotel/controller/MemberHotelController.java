package com.smartdot.meeting.server.modules.memberhotel.controller;

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
import com.smartdot.meeting.server.modules.infomgmt.service.ISavorPointService;
import com.smartdot.meeting.server.modules.memberhotel.dao.IMemberHotelDao;
import com.smartdot.meeting.server.modules.memberhotel.model.MemberHotelForm;
import com.smartdot.meeting.server.modules.memberhotel.model.MemberHotelPageForm;
import com.smartdot.meeting.server.modules.memberhotel.model.MemberHotelVO;
import com.smartdot.meeting.server.modules.memberhotel.service.IMemberHotelService;
import com.smartdot.meeting.server.modules.stroke.model.StrokePageForm;
import com.smartdot.meeting.server.common.entity.MemberHotel;
import com.smartdot.meeting.server.common.entity.SavorPoint;


@Controller
@RequestMapping(value = "/memberHotel")
public class MemberHotelController {

	private static final Logger _LOG = Logger.getLogger(MemberHotelController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IMemberHotelService.SERVICE_NAME)
	private IMemberHotelService memberHotelService;
	@Resource(name = ISavorPointService.SERVICE_NAME)
	private ISavorPointService savorPointService;
	@Resource(name = IMemberHotelDao.DAO_NAME)
	private IMemberHotelDao memberHotelDao;
	@RequestMapping(value = "/index")
	public String index(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "stroke/stroke";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "memberHotel/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "stroke/hotel";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "memberHotel/details";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	Map<String, Object> hotelInfo(HttpServletRequest request, StrokePageForm req,String id) throws Exception {
		
		MemberHotel memberHotel = new MemberHotel();
		List<SavorPoint> savorPoint = savorPointService.findAll();
		DetachedCriteria dc = DetachedCriteria.forClass(MemberHotel.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		dc.add(Restrictions.eq("memberId", id));
		List<MemberHotel> lis = memberHotelDao.findByDetachedCriteria(dc);
		if(null != lis && lis.size() == 1){
			memberHotel = lis.get(0);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("hotelList", savorPoint);
		map.put("memberHotel", memberHotel);
//		MemberHotel hotelId = memberHotels.get(0);
//		System.out.println("hotelId====:"+hotelId);
//		List<MemberHotelVO> vo = new ArrayList<MemberHotelVO>();
//		MemberHotelVO memberHotelVO = null;
//		for (MemberHotel memberHotel : memberHotels) {
//			memberHotelVO  = new MemberHotelVO();
//			BeanUtils.copyProperties(memberHotel, memberHotelVO);
//			vo.add(memberHotelVO);
//		}
		return map;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(MemberHotelForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		MemberHotel memberHotel= new MemberHotel();
		BeanUtils.copyProperties(form, memberHotel);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = memberHotelService.save(memberHotel);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberHotelController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<MemberHotelForm> form) throws Exception {
	List<MemberHotel> memberHotels=new ArrayList<MemberHotel>();
		ReturnValue returnValue =new ReturnValue();
		MemberHotel entityMemberHotel = new MemberHotel();
		for (MemberHotelForm memberHotel : form) {
			entityMemberHotel=new MemberHotel();
			BeanUtils.copyProperties(memberHotel, entityMemberHotel);
			memberHotels.add(entityMemberHotel);
		}
		boolean result = memberHotelService.saveAll(memberHotels);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberHotelController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = memberHotelService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberHotelController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = memberHotelService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberHotelController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(MemberHotelForm form) throws Exception {
//		DetachedCriteria dc = DetachedCriteria.forClass(MemberHotel.class);
//		dc.add(Restrictions.eq("enable", true));
//		dc.add(Restrictions.eq("memberId", id ));
//		dc.addOrder(Order.desc("updateTimes"));
//		
//		List<MemberHotel> lis= memberHotelDao.findByDetachedCriteria(dc);
		ReturnValue returnValue =new ReturnValue();
			boolean result = false;
			MemberHotel memberHotel = new MemberHotel();
			BeanUtils.copyProperties(form, memberHotel);
			if (StringUtils.isNotEmpty(form.getId())) {
				result = memberHotelService.updateMemberHotel(memberHotel);
			}else if (StringUtils.isEmpty(form.getId())) {
				result = memberHotelService.save(memberHotel);
			}
			if(result){
				returnValue.setSuccess(true);
				returnValue.setMessage(MemberHotelController.SUCCESS_MESSAGE);
		}
		
		return returnValue;
		
		
//		ReturnValue returnValue =new ReturnValue();
//		boolean result = false;
//		MemberHotel memberHotel = new MemberHotel();
//		BeanUtils.copyProperties(form, memberHotel);
		
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	MemberHotelVO queryById(String id) throws Exception {
		MemberHotelVO vo = new MemberHotelVO();
		MemberHotel memberHotel = memberHotelService.getMemberHotelById(id);
		if (memberHotel != null) {
			BeanUtils.copyProperties(memberHotel, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, MemberHotelPageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(MemberHotel.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		
		return memberHotelService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
