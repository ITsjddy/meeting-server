package com.smartdot.meeting.server.modules.member.controller;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.common.entity.Member;


/**
 * ClassName: MemberController
 * @Description: app用户主表管理
 * @author ms
 * @date 2017-1-19 下午3:03:14
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {

	private static final Logger _LOG = Logger.getLogger(MemberController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService memberService;

	
	/**
	 * @Description: app用户详情页
	 * @author ms
	 * @date 2017-1-18 下午3:03:14
	 */
	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "member/details";
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(Member member) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		if (StringUtils.isNotBlank(member.getMemberId())) {
			result = memberService.update(member);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(Member member) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		if (StringUtils.isBlank(member.getMemberId())) {
			result = memberService.save(member);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = memberService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = memberService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	

	@RequestMapping(value = "/savePassword")
	public @ResponseBody
	ReturnValue savePassword(String id, String password) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(password)){
			result = memberService.savePassword(id, password);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(MemberController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/checkRepeat")
	public @ResponseBody
	boolean checkRepeat(String id, String value) throws Exception {
		boolean result = false;
		if(StringUtils.isNotBlank(value)){
			result = memberService.checkRepeat(id, value);
		}
		return result;
	}
	
}
