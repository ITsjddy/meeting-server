package com.smartdot.meeting.server.modules.sysuser.controller;

import java.util.ArrayList;
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
import com.smartdot.meeting.server.modules.sysuser.model.SysUserForm;
import com.smartdot.meeting.server.modules.sysuser.model.SysUserSearch;
import com.smartdot.meeting.server.modules.sysuser.model.SysUserVO;
import com.smartdot.meeting.server.modules.sysuser.service.ISysUserService;
import com.smartdot.meeting.server.common.entity.SysRole;
import com.smartdot.meeting.server.common.entity.SysUser;

/**
 * ClassName: SysUserController
 * @Description: 系统用户管理
 * @author ms
 * @date 2017-3-4 下午10:03:14
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController {

	private static final Logger _LOG = Logger.getLogger(SysUserController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ISysUserService.SERVICE_NAME)
	private ISysUserService sysUserService;
	
	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "sysuser/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "sysuser/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysuser/edit";
	}
	
	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysuser/details";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<SysUserVO> queryAll() throws Exception {
		List<SysUser> sysUsers = sysUserService.findAll();
		List<SysUserVO> vo = new ArrayList<SysUserVO>();
		SysUserVO sysUserVO = null;
		for (SysUser sysUser : sysUsers) {
			sysUserVO  = new SysUserVO();
			BeanUtils.copyProperties(sysUser, sysUserVO);
			vo.add(sysUserVO);
		}
		return vo;
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(SysUserForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SysUser sysUser= new SysUser();
		BeanUtils.copyProperties(form, sysUser);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = sysUserService.save(sysUser);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysUserController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(SysUserForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(form, sysUser);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = sysUserService.updateSysUser(sysUser);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysUserController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = sysUserService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysUserController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = sysUserService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysUserController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/savePassword")
	public @ResponseBody
	ReturnValue savePassword(String id, String password) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(password)){
			result = sysUserService.savePassword(id, password);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysUserController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, SysUserSearch searchModel) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
		dc.add(Restrictions.eq("enable", true));
		//查询条件
		if(null != searchModel){
			if(StringUtils.isNotBlank(searchModel.getSysUser_name())){
		    	dc.add(Restrictions.or(Restrictions.like("name", "%"+searchModel.getSysUser_name()+"%"), 
		    			Restrictions.like("mobile", "%"+searchModel.getSysUser_name()+"%")));
		    }
			if(StringUtils.isNotBlank(searchModel.getSysUser_userName())){
		    	dc.add(Restrictions.like("userName", "%"+searchModel.getSysUser_userName()+"%"));
		    }
			if(null != searchModel.getSysUser_state()){
		    	dc.add(Restrictions.eq("state", searchModel.getSysUser_state()));
		    }
			if(StringUtils.isNotBlank(searchModel.getSysUser_roleId())){
		    	dc.add(Restrictions.eq("roleId", searchModel.getSysUser_roleId()));
		    }
		}
		dc.addOrder(Order.desc("updateTimes"));
		
		return sysUserService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	SysUserVO queryById(String id) throws Exception {
		SysUserVO vo = new SysUserVO();
		SysUser sysUser = sysUserService.getSysUserById(id);
		if (sysUser != null) {
			BeanUtils.copyProperties(sysUser, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/queryAllSysRole")
	public @ResponseBody
	List<SysRole> queryAllSysRole() throws Exception {
		
		List<SysRole> lSysRole = sysUserService.findAllSysRole();
		
		return lSysRole;
	}
	
}
