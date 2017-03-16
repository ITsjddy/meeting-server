package com.smartdot.meeting.server.modules.department.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.CommonUtil;
import com.smartdot.meeting.server.modules.department.model.DepartmentForm;
import com.smartdot.meeting.server.modules.department.model.DepartmentFormList;
import com.smartdot.meeting.server.modules.department.model.DepartmentVO;
import com.smartdot.meeting.server.modules.department.service.IDepartmentService;
import com.smartdot.meeting.server.modules.guest.controller.GuestController;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.member.model.MemberSearch;



@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

	private static final Logger _LOG = Logger.getLogger(DepartmentController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IDepartmentService.SERVICE_NAME)
	private IDepartmentService departmentService;

	@RequestMapping(value = "/index")
	public 	String index(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取登陆人的账号，并根据人的角色权限获取所管理团的树
		System.out.println(CommonUtil.getAuthenticatedUsername());
		request.setAttribute("groupid","2c948f9b5aa2f713015aa2fc1fb50006");
		return "deptment/deptment";
	}
	
	@RequestMapping(value = "/indexdetial")
	public 	String indexdetial(HttpServletRequest request,HttpServletResponse response,String groupid) throws Exception {
		request.setAttribute("groupid",groupid);
		return "deptment/deptmentdetile";
	}
	
	@RequestMapping(value = "/indexinclude")
	public 	String indexinclude(HttpServletRequest request,HttpServletResponse response,String groupid) throws Exception {
		request.setAttribute("groupid",groupid);
		return "deptment/deptmentInclude";
	}
	
	@RequestMapping(value = "/indexguest")
	public 	String indexguest(HttpServletRequest request,HttpServletResponse response ,String groupid) throws Exception {
		request.setAttribute("groupid",groupid);
		return "deptment/deptmentGuset";
	}
	
	@RequestMapping(value = "/indexserver")
	public 	String indexserver(HttpServletRequest request,HttpServletResponse response ,String groupid) throws Exception {
		request.setAttribute("groupid",groupid);
		return "deptment/deptmentServer";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		System.out.println(id);
		model.addAttribute("id", id);
		return "deptment/edit";
	}
	
	@RequestMapping(value = "/newdepartment")
	public String newdepartment(String id, Model model) throws Exception {
		Department departemt = departmentService.getDepartmentById(id);
		model.addAttribute("id", id);
		if(departemt!=null){
			model.addAttribute("parentname", departemt.getDepartname());
		}
		return "deptment/newdepartment";
	}
	
	@RequestMapping(value = "/editdepartment")
	public String editdepartment(String id,String groupid, Model model) throws Exception {
		Department departemt = departmentService.getDepartmentById(id);
		Department departemt2 = departmentService.getDepartmentById(departemt.getParentid());
		model.addAttribute("groupid", groupid);
		model.addAttribute("id", id);
		model.addAttribute("parentid", departemt.getParentid());
		model.addAttribute("parentname", departemt2.getDepartname());
		return "deptment/editdepartment";
	}
	@RequestMapping(value = "/deptztree")
	public String ztreecreate()throws Exception{
		return "deptment/deptztree";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<DepartmentVO> queryAll(String id) throws Exception {
		List<Department> departments = departmentService.findAll();
		List<DepartmentVO> vo = new ArrayList<DepartmentVO>();
		List<Department> childList=treeMenuList(departments,"2c948f9b5aa2f713015aa2f8cdce0000","省市团");
		Department departmentss = departmentService.getDepartmentById("2c948f9b5aa2f713015aa2f8cdce0000");
		childList.add(departmentss);
		departmenttemp=new ArrayList<Department>();
		DepartmentVO departmentVO = null;
		for (Department department : departments) {
			departmentVO  = new DepartmentVO();
			BeanUtils.copyProperties(department, departmentVO);
			vo.add(departmentVO);
		}
		return vo;
	}
	
	@RequestMapping(value = "/getLanguage")
	public @ResponseBody
	Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Department> lDepartment2 = new ArrayList<Department>();
		
		List<Map<String, String>> llanguage = departmentService.getAllLanguage();
		List<Department> lDepartment = departmentService.getListDepartment(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lDepartment && lDepartment.size() > 0){
					for (Department department : lDepartment) {
						if(department.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							lDepartment2.add(department);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					Department department = new Department();
					lDepartment2.add(department);
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lDepartment", lDepartment2);
		map.put("memberLanguage", lMemberLanguage);
		
		return map;
	}
	
	@RequestMapping(value = "/queryAllHierarchy")
	public @ResponseBody
	List<DepartmentVO> queryAllHierarchy(HttpServletRequest request,String groupid) throws Exception {
		List<Department> departments = departmentService.findAll();
		Department departmentunique = departmentService.getDepartmentById(groupid);
		List<DepartmentVO> vo = new ArrayList<DepartmentVO>();
		List<Department> childList=treeMenuList(departments,groupid,departmentunique.getDepartname());
		departmenttemp=new ArrayList<Department>();
		Collections.reverse(childList);
		DepartmentVO departmentVO = null;
		for (Department department : childList) {
			departmentVO  = new DepartmentVO();
			BeanUtils.copyProperties(department, departmentVO);
			vo.add(departmentVO);
		}
		return vo;
	}
	static  List<Department> departmenttemp=new ArrayList<Department>();
	public static List<Department> treeMenuList( List<Department> departmentList, String pid,String name){  
        for(Department department: departmentList){  
            //遍历出父id等于参数的id，add进子节点集合  
            if(department.getParentid().equals(pid)){  
                //递归遍历下一级  
                treeMenuList(departmentList,department.getId(),department.getDepartname());  
                department.setParentname(name);
                departmenttemp.add(department);  
            }  
        }  
    return departmenttemp;  
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(DepartmentFormList form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberGuest()){
			return returnValue;
		}
		result = departmentService.save(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<DepartmentForm> form) throws Exception {
	List<Department> departments=new ArrayList<Department>();
		ReturnValue returnValue =new ReturnValue();
		Department entityDepartment = new Department();
		for (DepartmentForm department : form) {
			entityDepartment=new Department();
			BeanUtils.copyProperties(department, entityDepartment);
			departments.add(entityDepartment);
		}
		boolean result = departmentService.saveAll(departments);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DepartmentController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}


	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = departmentService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DepartmentController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = departmentService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DepartmentController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(DepartmentFormList form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberGuest()){
			return returnValue;
		}
		result = departmentService.updateDepartment(form);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/updatedept")
	public @ResponseBody
	ReturnValue updatedept(DepartmentFormList form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		if(null == form || null == form.getMemberGuest()){
			return returnValue;
		}
		Department department = JSON.parseObject(String.valueOf(form.getMemberGuest()), Department.class);
		Department pdepartment = departmentService.getDepartmentById(department.getParentid());
		if(department.getPgroupnumber().equals(pdepartment.getGroupnumber())){
			result = departmentService.updateDepartment(form);
		}else{
			result = departmentService.updateDept(form);
		}
		
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(GuestController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	DepartmentVO queryById(String id) throws Exception {
		DepartmentVO vo = new DepartmentVO();
		Department department = departmentService.getDepartmentById(id);
		if (department != null) {
			BeanUtils.copyProperties(department, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, MemberSearch memberSearch,String groupid) throws Exception {
		Department departmentunique = departmentService.getDepartmentById(groupid);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Department> departments = departmentService.findAll();
		List<DepartmentVO> vo = new ArrayList<DepartmentVO>();
		List<Department> childList=treeMenuList(departments,groupid,departmentunique.getDepartname());
		//Collections.reverse(childList);
		departmenttemp=new ArrayList<Department>();
		DepartmentVO departmentVO = null;
		for (Department department : childList) {
			departmentVO  = new DepartmentVO();
			BeanUtils.copyProperties(department, departmentVO);
			vo.add(departmentVO);
		}
		resultMap.put("pageNow", 1);//当前页
		resultMap.put("pageSize", 1000);//每页数量
		resultMap.put("pageTotal", 1);//总页数
		resultMap.put("totalCount", 1000);//总数量
		resultMap.put("resultData", vo);//返回结果集
		return resultMap;
	}
}
