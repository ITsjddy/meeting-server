package com.smartdot.meeting.server.modules.sysprivilege.controller;

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
import com.smartdot.meeting.server.modules.sysprivilege.model.SysPrivilegeForm;
import com.smartdot.meeting.server.modules.sysprivilege.model.SysPrivilegeSearch;
import com.smartdot.meeting.server.modules.sysprivilege.model.SysPrivilegeVO;
import com.smartdot.meeting.server.modules.sysprivilege.service.ISysPrivilegeService;
import com.smartdot.meeting.server.common.entity.SysPrivilege;


@Controller
@RequestMapping(value = "/sysPrivilege")
public class SysPrivilegeController {

	private static final Logger _LOG = Logger.getLogger(SysPrivilegeController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ISysPrivilegeService.SERVICE_NAME)
	private ISysPrivilegeService sysPrivilegeService;

	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "sysprivilege/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "sysprivilege/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysprivilege/edit";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysprivilege/details";
	}

	@RequestMapping(value = "/sysPrivilegeTree")
	public String sysPrivilegeTree(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysprivilege/indexPrivilegeTree";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<SysPrivilegeVO> queryAll() throws Exception {
		List<SysPrivilege> sysPrivileges = sysPrivilegeService.findAll();
		List<SysPrivilegeVO> vo = new ArrayList<SysPrivilegeVO>();
		SysPrivilegeVO sysPrivilegeVO = null;
		for (SysPrivilege sysPrivilege : sysPrivileges) {
			sysPrivilegeVO  = new SysPrivilegeVO();
			BeanUtils.copyProperties(sysPrivilege, sysPrivilegeVO);
			vo.add(sysPrivilegeVO);
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(SysPrivilegeForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		SysPrivilege sysPrivilege= new SysPrivilege();
		BeanUtils.copyProperties(form, sysPrivilege);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			if(StringUtils.isNotBlank(form.getParentId())){
				SysPrivilege ppri = sysPrivilegeService.getSysPrivilegeById(form.getParentId());
				sysPrivilege.setParent(ppri);
				if(StringUtils.isNotBlank(ppri.getType())){
					if("1".equals(ppri.getType())){
						sysPrivilege.setType("2");
					} else if("2".equals(ppri.getType())){
						sysPrivilege.setType("3");
					}
				}
			} else {
				sysPrivilege.setType("1");
			}
			result = sysPrivilegeService.save(sysPrivilege);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysPrivilegeController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<SysPrivilegeForm> form) throws Exception {
	List<SysPrivilege> sysPrivileges=new ArrayList<SysPrivilege>();
		ReturnValue returnValue =new ReturnValue();
		SysPrivilege entitySysPrivilege = new SysPrivilege();
		for (SysPrivilegeForm sysPrivilege : form) {
			entitySysPrivilege=new SysPrivilege();
			BeanUtils.copyProperties(sysPrivilege, entitySysPrivilege);
			sysPrivileges.add(entitySysPrivilege);
		}
		boolean result = sysPrivilegeService.saveAll(sysPrivileges);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysPrivilegeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = sysPrivilegeService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysPrivilegeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = sysPrivilegeService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysPrivilegeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(SysPrivilegeForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		SysPrivilege sysPrivilege = new SysPrivilege();
		BeanUtils.copyProperties(form, sysPrivilege);
		if (StringUtils.isNotEmpty(form.getId())) {
			if(StringUtils.isNotBlank(form.getParentId())){
				SysPrivilege ppri = sysPrivilegeService.getSysPrivilegeById(form.getParentId());
				sysPrivilege.setParent(ppri);
				if(StringUtils.isNotBlank(ppri.getType())){
					if("1".equals(ppri.getType())){
						sysPrivilege.setType("2");
					} else if("2".equals(ppri.getType())){
						sysPrivilege.setType("3");
					}
				}
			} else {
				sysPrivilege.setType("1");
			}
			result = sysPrivilegeService.updateSysPrivilege(sysPrivilege);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysPrivilegeController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	SysPrivilegeForm queryById(String id) throws Exception {
		SysPrivilegeForm vo = new SysPrivilegeForm();
		SysPrivilege sysPrivilege = sysPrivilegeService.getSysPrivilegeById(id);
		if (sysPrivilege != null) {
			BeanUtils.copyProperties(sysPrivilege, vo);
			SysPrivilege ppri = sysPrivilege.getParent();
			if(null != ppri){
				vo.setParentId(ppri.getId());
				vo.setParentName(ppri.getName());
			}
		}
		return vo;
	}
	
	@RequestMapping(value = "/queryAllPrivilege")
	public @ResponseBody
	List<Map<String, Object>> queryAllPrivilege(String id) throws Exception {
		
		List<Map<String, Object>> priList = sysPrivilegeService.getListMapPrivileges("1,2");
		
		return priList;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, SysPrivilegeSearch searchModel) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(SysPrivilege.class);
		dc.add(Restrictions.eq("enable", true));
		//查询条件
		if(null != searchModel){
			if(StringUtils.isNotBlank(searchModel.getSysPrivilege_name())){
		    	dc.add(Restrictions.or(Restrictions.like("name", "%"+searchModel.getSysPrivilege_name()+"%"), 
		    			Restrictions.like("remark", "%"+searchModel.getSysPrivilege_name()+"%")));
		    }
			if(StringUtils.isNotBlank(searchModel.getSysPrivilege_url())){
		    	dc.add(Restrictions.like("url", "%"+searchModel.getSysPrivilege_url()+"%"));
		    }
			if(StringUtils.isNotBlank(searchModel.getSysPrivilege_type())){
		    	dc.add(Restrictions.eq("type", searchModel.getSysPrivilege_type()));
		    }
			if(StringUtils.isNotBlank(searchModel.getSysPrivilege_display())){
		    	dc.add(Restrictions.eq("display", searchModel.getSysPrivilege_display()));
		    }
			if(StringUtils.isNotBlank(searchModel.getSysPrivilege_parentId())){
				SysPrivilege ppri = sysPrivilegeService.getSysPrivilegeById(searchModel.getSysPrivilege_parentId());
		    	dc.add(Restrictions.eq("parent", ppri));
		    }
		}
		dc.addOrder(Order.asc("level"));
		
		return sysPrivilegeService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
