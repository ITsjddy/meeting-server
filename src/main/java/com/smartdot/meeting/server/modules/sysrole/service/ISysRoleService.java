package com.smartdot.meeting.server.modules.sysrole.service;


import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.RolePrivilege;
import com.smartdot.meeting.server.common.entity.SysRole;


public interface ISysRoleService {
	public final static String SERVICE_NAME = "sysRoleService";

	/**
	 * 全部查看SysRole
	 * 
	 * @return List
	 */
	public List<SysRole> findAll();

	/**
	 * 根据ID查找SysRole
	 * 
	 * @param id
	 *            可以根据ID查看SysRole中的字段
	 * @return SysRole
	 */
	public SysRole getSysRoleById(String id);

	/**
	 * 添加SysRole
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SysRole instance);
	
	/**
	 * 添加多个SysRole对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SysRole> instanceList);

	/**
	 * 修改SysRole
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSysRole(SysRole instance);

	/**
	 * 删除SysRole
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SysRole instance);
	
	/**
	 * 删除SysRole
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * SysRole分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> findSysRoleByPage(SysRole sysRole,Page<SysRole> pageQuery);

	/**
	 * SysRole根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SysRole根据条件查询列表;
	 * 
	 * @param sysRole
	 * @return
	 */
	public List<SysRole> findByHQLAndParams(SysRole sysRole);
	
	/**
	 * SysRole根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysRole> page);
	
	public List<RolePrivilege> getListPrivilegesByRoleId(String roleId);
	
	/**
	 * 添加RolePrivilege
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveRolePrivilege(RolePrivilege rolePrivilege);
	

	/**
	 * 删除RolePrivilege
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteRolePrivilegeByRoleId(String id);
}
