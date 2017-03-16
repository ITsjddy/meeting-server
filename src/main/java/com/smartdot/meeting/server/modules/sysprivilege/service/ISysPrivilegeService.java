package com.smartdot.meeting.server.modules.sysprivilege.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.SysPrivilege;

public interface ISysPrivilegeService {
	
	public final static String SERVICE_NAME = "sysPrivilegeService";

	/**
	 * 全部查看SysPrivilege
	 * 
	 * @return List
	 */
	public List<SysPrivilege> findAll();

	/**
	 * 根据ID查找SysPrivilege
	 * 
	 * @param id
	 *            可以根据ID查看SysPrivilege中的字段
	 * @return SysPrivilege
	 */
	public SysPrivilege getSysPrivilegeById(String id);

	/**
	 * 添加SysPrivilege
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SysPrivilege instance);
	
	/**
	 * 添加多个SysPrivilege对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SysPrivilege> instanceList);

	/**
	 * 修改SysPrivilege
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSysPrivilege(SysPrivilege instance);

	/**
	 * 删除SysPrivilege
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SysPrivilege instance);
	
	/**
	 * 删除SysPrivilege
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * SysPrivilege分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysPrivilege> pageQuery);

	/**
	 * SysPrivilege根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SysPrivilege根据条件查询列表;
	 * 
	 * @param sysPrivilege
	 * @return
	 */
	public List<SysPrivilege> findByHQLAndParams(SysPrivilege sysPrivilege);
	
	public Map<String, String> getResources();
	
	public List<Map<String, String>> getUserPrivilegesByUserName(String userName);
	
	public List<Map<String, String>> getPrivilegesByUserName(String userName,String type,boolean state);

	public List<SysPrivilege> getListPrivileges(List<String> types);
	
	public boolean saveRolePrivilege(List<String> authchedkList, String roleId);

	public List<Map<String, Object>> getListMapPrivileges(String types) throws Exception;
	
}
