package com.smartdot.meeting.server.modules.department.service;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.department.model.DepartmentFormList;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;

public interface IDepartmentService {
	public final static String SERVICE_NAME = "departmentService";

	/**
	 * 全部查看Department
	 * 
	 * @return List
	 */
	public List<Department> findAll();

	/**
	 * 根据ID查找Department
	 * 
	 * @param id
	 *            可以根据ID查看Department中的字段
	 * @return Department
	 */
	public Department getDepartmentById(String id);

	/**
	 * 添加Department
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(DepartmentFormList instance);
	
	/**
	 * 添加多个Department对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<Department> instanceList);

	/**
	 * 修改Department
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateDepartment(DepartmentFormList instance);
	public boolean updateDept(DepartmentFormList instance);

	/**
	 * 删除Department
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(Department instance);
	
	/**
	 * 删除Department
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * Department分页
	 * 
	 * @param page
	 * @return
	 */
	public  Map<String, Object> findDepartmentByPage(Department department,Page<Department> pageQuery);

	/**
	 * Department根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * Department根据条件查询列表;
	 * 
	 * @param department
	 * @return
	 */
	public List<Department> findByHQLAndParams(Department department);
	public List<Map<String, String>> getAllLanguage();
	public List<MemberLanguage> getMemberLanguage(String memberId);
	/**
	 * Department根据条件查询列表;
	 * 
	 * @param department
	 * @return
	 */
	public List<Department> getListDepartment(String id);
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	新增团时传父编号，返回新增的子团的最大编号（新增时，如果传null就代表是新建一级团）
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 上午10:47:34
	 * </pre>
	 * </p>
	 */
	public String createTimeGroupNum(String parentGroupNum);
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	修改团时传修改后的父编号，和当前团的编号，如果当前团有子就返回unenable，没有子就返回修改后的子团最大id
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 上午10:47:34
	 * </pre>
	 * </p>
	 */
	public String updateTimeGroupNum(String parentGroupNum,String groupNum);
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 *  删除的团的编号， 删除团时如果该团有子就返回unenable没有子团就返回该团编号
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 上午10:47:34
	 * </pre>
	 * </p>
	 */
	public String deleteTimeGroupNum(String groupNum);
}
