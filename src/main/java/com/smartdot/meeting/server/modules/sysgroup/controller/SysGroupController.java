package com.smartdot.meeting.server.modules.sysgroup.controller;


import java.util.ArrayList;
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
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.sysgroup.model.SysGroupForm;
import com.smartdot.meeting.server.modules.sysgroup.model.SysGroupVO;
import com.smartdot.meeting.server.modules.sysgroup.service.ISysGroupService;
import com.smartdot.meeting.server.modules.sysrole.service.ISysRoleService;
import com.smartdot.meeting.server.modules.sysuser.service.ISysUserService;
import com.smartdot.meeting.server.common.entity.GroupRole;
import com.smartdot.meeting.server.common.entity.SysGroup;
import com.smartdot.meeting.server.common.entity.SysRole;
import com.smartdot.meeting.server.common.entity.SysUser;
import com.smartdot.meeting.server.common.jstree.Child;
import com.smartdot.meeting.server.common.jstree.Core;
import com.smartdot.meeting.server.common.jstree.JsTree;
import com.smartdot.meeting.server.common.jstree.State;


/**
 * ClassName: SysGroupController
 * @Description: 分组与管理
 * @author ms
 * @date 2017-1-9 下午3:03:14
 */
@Controller
@RequestMapping(value = "/sysGroup")
public class SysGroupController {

	private static final Logger _LOG = Logger.getLogger(SysGroupController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ISysGroupService.SERVICE_NAME)
	private ISysGroupService sysGroupService;

	@Resource(name = ISysRoleService.SERVICE_NAME)
	private ISysRoleService sysRoleService;

	@Resource(name = ISysUserService.SERVICE_NAME)
	private ISysUserService sysUserService;
	
	
	/**
	 * @Description: 分组列表页面
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "sysgroup/list";
	}
	
	/**
	 * @Description: 分组添加页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		List<SysRole> roleAll = sysRoleService.findAll();
		
		model.addAttribute("roleAll", roleAll);
		return "sysgroup/edit";
	}
	
	/**
	 * @Description: 分组修改页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		List<SysRole> roleAll = sysRoleService.findAll();
		List<GroupRole> lgroupRole = sysGroupService.getRoleByGroupId(id);
		
		model.addAttribute("id", id);
		model.addAttribute("roleAll", roleAll);
		model.addAttribute("lgroupRole", lgroupRole);
		System.out.println(lgroupRole);
		return "sysgroup/edit";
	}

	/**
	 * @Description: 分组详情页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysgroup/details";
	}
	
	/**
	 * @Description: 分页列表
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		dc.add(Restrictions.eq("enable", true));
		
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
	    	dc.add(Restrictions.or(Restrictions.like("groupName", "%"+name+"%"), Restrictions.like("remark", "%"+name+"%")));
	    }
		dc.addOrder(Order.asc("updateTimes"));
		
		return sysGroupService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	/**
	 * @Description: 群组用户列表页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/indexUsers")
	public String indexGroupUsers(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysgroup/indexUsers";
	}
	

	/**
	 * @Description: 授权列表页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/indexRole")
	public String indexRole(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysgroup/indexRole";
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	SysGroupVO queryById(String id) throws Exception {
		SysGroupVO vo = new SysGroupVO();
		SysGroup sysGroup = sysGroupService.getSysGroupById(id);
		if (sysGroup != null) {
			BeanUtils.copyProperties(sysGroup, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/sysRoleQueryAll")
	public @ResponseBody
	List<SysRole> sysRoleQueryAll() throws Exception {
		
		List<SysRole> roleAll = sysRoleService.findAll();
		return roleAll;
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(SysGroupForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SysGroup sysGroup= new SysGroup();
		BeanUtils.copyProperties(form, sysGroup);
		boolean result = false;
		if (StringUtils.isBlank(form.getId())) {
			result = sysGroupService.save(sysGroup);
			sysGroupService.deleteGroupRoleByGroupId(sysGroup.getId());
			if(null != form.getLroleIds()){
				for (String roleId : form.getLroleIds()) {
					GroupRole groupRole = new GroupRole();
					groupRole.setGroupId(sysGroup.getId());
					groupRole.setRoleId(roleId);
					sysGroupService.saveGroupRole(groupRole);
				}
			}
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(SysGroupForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SysGroup sysGroup = new SysGroup();
		BeanUtils.copyProperties(form, sysGroup);
		boolean result = false;
		if (StringUtils.isNotBlank(form.getId())) {
			result = sysGroupService.updateSysGroup(sysGroup);
			/*sysGroupService.deleteGroupRoleByGroupId(sysGroup.getId());
			if(null != form.getLroleIds()){
				for (String roleId : form.getLroleIds()) {
					GroupRole groupRole = new GroupRole();
					groupRole.setGroupId(sysGroup.getId());
					groupRole.setRoleId(roleId);
					sysGroupService.saveGroupRole(groupRole);
				}
			}*/
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = sysGroupService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = sysGroupService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/authorizationSave")
	public @ResponseBody
	ReturnValue authorizationSave(String id, String roleIds) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		if (StringUtils.isNotBlank(id)) {
			sysGroupService.deleteGroupRoleByGroupId(id);
			if(StringUtils.isNotBlank(roleIds)){
				String[] roleIdss = roleIds.split(",");
				for (String roleId : roleIdss) {
					GroupRole groupRole = new GroupRole();
					groupRole.setGroupId(id);
					groupRole.setRoleId(roleId);
					sysGroupService.saveGroupRole(groupRole);
				}
			}
			result = true;
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysGroupController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/getRole")
	public @ResponseBody
	JsTree getRole(String id) throws Exception {
		
		return getGroupRole(id);
	}
	
	@RequestMapping(value = "/getGroupUser")
	public @ResponseBody
	JsTree getGroupUser(String id) throws Exception {
		JsTree s = new JsTree();
		if (StringUtils.isNotBlank(id)) {
			SysGroup sysGroup = sysGroupService.getSysGroup(id);
			s = getGroupUser(sysGroup);
		}
		return s;
	}
	public JsTree getGroupUser(SysGroup sysGroup) {
		JsTree jsTree = new JsTree();
		try {
			List<SysUser> userlist = sysUserService.getListUsers();
			//
			Core core = new Core();
			List<Child> data = new ArrayList<Child>();
			// 一级循环
			for (SysUser syUser : userlist) {
				try {
					//admin为超级管理员，不属于任何组
					if(null != syUser && null != syUser.getUserName() && !"admin".equals(syUser.getUserName())){
						String text = syUser.getName() + " (" + syUser.getUserName() + ")";
						Child child = new Child();
						State state = new State();
						state.setOpened(true);
						child.setState(state);
						child.setText(text);
						child.setId(syUser.getId());
						
						if(String.valueOf(sysGroup.getId()).equals(syUser.getRoleId())){
							state.setSelected(true);
						}
						
						data.add(child);
					}
				} catch (Exception e) {
					_LOG.error(e.getMessage());
				}
			}
			core.setData(data);
			jsTree.setCore(core);
		} catch (Exception e) {
			_LOG.error(e.getMessage());
		}
		return jsTree;
	}
	public JsTree getGroupRole(String id) {
		JsTree jsTree = new JsTree();
		try {
			List<SysRole> roleAll = sysRoleService.findAll();
			//
			List<GroupRole> lgroupRole = sysGroupService.getRoleByGroupId(id);
			//
			Core core = new Core();
			List<Child> data = new ArrayList<Child>();
			// 一级循环
			for (SysRole sysRole : roleAll) {
				try {
					if(null != sysRole){
						String text = sysRole.getName();
						Child child = new Child();
						State state = new State();
						state.setOpened(true);
						child.setState(state);
						child.setText(text);
						child.setId(sysRole.getId());
						
						if(null != lgroupRole && lgroupRole.size() > 0){
							for (GroupRole groupRole : lgroupRole) {
								if(groupRole.getRoleId().equals(sysRole.getId())){
									state.setSelected(true);
									continue;
								}
							}
							
						}
						
						data.add(child);
					}
				} catch (Exception e) {
					_LOG.error(e.getMessage());
				}
			}
			core.setData(data);
			jsTree.setCore(core);
		} catch (Exception e) {
			_LOG.error(e.getMessage());
		}
		return jsTree;
	}
	
}
