package com.smartdot.meeting.server.modules.sysrole.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.sysprivilege.service.ISysPrivilegeService;
import com.smartdot.meeting.server.modules.sysrole.model.SysRoleForm;
import com.smartdot.meeting.server.modules.sysrole.model.SysRoleVO;
import com.smartdot.meeting.server.modules.sysrole.service.ISysRoleService;
import com.smartdot.meeting.server.modules.sysuser.service.ISysUserService;
import com.smartdot.meeting.server.common.entity.GroupRole;
import com.smartdot.meeting.server.common.entity.RolePrivilege;
import com.smartdot.meeting.server.common.entity.SysGroup;
import com.smartdot.meeting.server.common.entity.SysPrivilege;
import com.smartdot.meeting.server.common.entity.SysRole;
import com.smartdot.meeting.server.common.entity.SysUser;
import com.smartdot.meeting.server.common.jstree.Child;
import com.smartdot.meeting.server.common.jstree.Core;
import com.smartdot.meeting.server.common.jstree.JsTree;
import com.smartdot.meeting.server.common.jstree.State;


/**
 * ClassName: RoleController
 * @Description: 角色管理
 * @author ms
 * @date 2017-1-9 下午3:03:14
 */
@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController {
	
	private static final Logger _LOG = Logger.getLogger(SysRoleController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ISysRoleService.SERVICE_NAME)
	private ISysRoleService sysRoleService;

	@Resource(name = ISysPrivilegeService.SERVICE_NAME)
	private ISysPrivilegeService  sysPrivilegeService;
	
	@Resource(name = ISysUserService.SERVICE_NAME)
	private ISysUserService sysUserService;

	
	/**
	 * @Description: 角色列表页面
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "sysrole/list";
	}
	
	/**
	 * @Description: 角色添加页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/add")
	public String add() throws Exception {
		
		return "sysrole/edit";
	}
	
	/**
	 * @Description: 角色修改页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysrole/edit";
	}

	/**
	 * @Description: 角色详情页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysrole/details";
	}
	
	/**
	 * @Description: 角色授权页面
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/authorization")
	public String authorization(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysrole/authorization";
	}
	
	/**
	 * @Description: 权限用户列表页
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/indexUsers")
	public String indexGroupUsers(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "sysrole/indexUsers";
	}
	
	/**
	 * @Description: 根据角色id获取角色信息
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/getAuthorization")
	public @ResponseBody
	JsTree getAuthorization(String id) throws Exception {
		JsTree s = new JsTree();
		if (StringUtils.isNotBlank(id)) {
			SysRole entity = sysRoleService.getSysRoleById(id);
			s = getUserPrivilege(entity);
		}
		return s;
	}
	
	/**
	 * @Description: 授权
	 * @author ms
	 * @date 2017-1-9 下午3:03:14
	 */
	@RequestMapping(value = "/authorizationSave")
	public @ResponseBody
	ReturnValue authorizationSave(String id, String authchecked) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		
		if(StringUtils.isNotBlank(id)){
			//先删除
			sysRoleService.deleteRolePrivilegeByRoleId(id);
			//在添加权限
			if(StringUtils.isNotBlank(authchecked)){
				String[] authchedkids = authchecked.split(",");
				List<String> authchedkList = Arrays.asList(authchedkids);
				result = sysPrivilegeService.saveRolePrivilege(authchedkList, id);
				/*for (String string : authchedkids) {
					//添加角色对应菜单权限
					RolePrivilege rolePrivilege = new RolePrivilege();
					rolePrivilege.setPriId(string);
					rolePrivilege.setRoleId(id);
					sysRoleService.saveRolePrivilege(rolePrivilege);
				}*/
			} else {
				result = true;
			}
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	/**
	 * 获取用户授权的JSON字符串
	 * @param role
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public JsTree getUserPrivilege(SysRole role) throws Exception {
		return adminjosn(role);
	}
	
	/**
	 * @param role
	 * @throws Exception 
	 */
	public JsTree adminjosn(SysRole role) throws Exception {
		String[] priIds = null;
		List<RolePrivilege> allRolePrivilege = sysRoleService.getListPrivilegesByRoleId(role.getId());
		if(null != allRolePrivilege && allRolePrivilege.size() > 0){
			priIds = new String[allRolePrivilege.size()];
			for (int i = 0; i < allRolePrivilege.size(); i++) {
				priIds[i] = allRolePrivilege.get(i).getPriId();
			}
		}
		
		//一级
		List<String> priL1 = new ArrayList<String>(); priL1.add("1");
		List<SysPrivilege> prlist = sysPrivilegeService.getListPrivileges(priL1);
		//二级
		List<String> priL2 = new ArrayList<String>(); priL2.add("2");
		List<SysPrivilege> prlist2 = sysPrivilegeService.getListPrivileges(priL2);
		//三级
		List<String> priL3 = new ArrayList<String>(); priL3.add("3");
		List<SysPrivilege> prlist3 = sysPrivilegeService.getListPrivileges(priL3);
		
		JsTree jsTree = new JsTree();
		Core core = new Core();
		List<Child> data = new ArrayList<Child>();
		// 一级菜单
		for (SysPrivilege pri : prlist) {
			Child child = new Child();
			child.setId(pri.getId());
			State state = new State();
			state.setOpened(true);
			child.setState(state);
			child.setText(pri.getName());
			List<Child> childs = new ArrayList<Child>();
			// 二级菜单
			for (SysPrivilege pri2 : prlist2) {
				if(null != pri2.getParent()){
					if (pri2.getParent().getId() == pri.getId()) {
						Child child2 = new Child();
						State state2 = new State();
						state2.setOpened(true);
						child2.setState(state2);
						child2.setText(pri2.getName());

						childs.add(child2);
						child2.setId(pri2.getId());
						List<Child> childs2 = new ArrayList<Child>();
						if(null != priIds){
							for (String priId : priIds) {
								if (pri2.getId().equals(priId)) {
									state2.setSelected(true);
								}
							}
						}
						// 三级菜单
						for (SysPrivilege pri3 : prlist3) {
							if(null != pri3.getParent()){
								if (pri3.getParent().getId() == pri2.getId()) {
									Child child3 = new Child();
									State state3 = new State();
									state3.setOpened(true);
									child3.setState(state3);
									child3.setText(pri3.getName());
									child3.setId(pri3.getId());
									
									childs2.add(child3);
									List<Child> childs3 = new ArrayList<Child>();
									if(null != priIds){
										for (String priId : priIds) {
											if (pri3.getId().equals(priId)) {
												state3.setSelected(true);
											}
										}
									}
									child3.setChildren(childs3);
								}
							}
						}
						child2.setChildren(childs2);
					}
				}
			}
			child.setChildren(childs);
			data.add(child);
		}
		core.setData(data);
		jsTree.setCore(core);
		return jsTree;
	}
	
	boolean contains(String[] strs, String s) {
		if (strs == null || s == null)
			return false;
		for (String str : strs) {
			if (str != null && str.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(SysRoleForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SysRole sysRole= new SysRole();
		BeanUtils.copyProperties(form, sysRole);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = sysRoleService.save(sysRole);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysRoleController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(SysRoleForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(form, sysRole);
		boolean result = sysRoleService.updateSysRole(sysRole);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysRoleController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = sysRoleService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysRoleController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = sysRoleService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(SysRoleController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	SysRoleVO queryById(String id) throws Exception {
		SysRoleVO vo = new SysRoleVO();
		SysRole sysRole = sysRoleService.getSysRoleById(id);
		if (sysRole != null) {
			BeanUtils.copyProperties(sysRole, vo);
		}
		return vo;
	}
	
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
		dc.add(Restrictions.eq("enable", true));
		
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
	    	dc.add(Restrictions.or(Restrictions.like("name", "%"+name+"%"), Restrictions.like("remark", "%"+name+"%")));
	    }
		dc.addOrder(Order.asc("updateTimes"));
		
		return sysRoleService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	@RequestMapping(value = "/getRoleUser")
	public @ResponseBody
	JsTree getRoleUser(String id) throws Exception {
		JsTree s = new JsTree();
		if (StringUtils.isNotBlank(id)) {
			SysRole role = sysRoleService.getSysRoleById(id);
			s = getRoleUser(role);
		}
		return s;
	}
	public JsTree getRoleUser(SysRole role) {
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
						
						if(String.valueOf(role.getId()).equals(syUser.getRoleId())){
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
	
}
