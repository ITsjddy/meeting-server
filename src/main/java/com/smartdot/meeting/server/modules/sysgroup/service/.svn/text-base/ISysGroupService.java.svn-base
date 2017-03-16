package com.smartdot.meeting.server.modules.sysgroup.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.GroupRole;
import com.smartdot.meeting.server.common.entity.SysGroup;


public interface ISysGroupService {
	
	public final static String SERVICE_NAME = "sysGroupService";

	/**
	 * 全部查看SysGroup
	 * 
	 * @return List
	 */
	public List<SysGroup> findAll();

	/**
	 * 根据ID查找SysGroup
	 * 
	 * @param id
	 *            可以根据ID查看SysGroup中的字段
	 * @return SysGroup
	 */
	public SysGroup getSysGroupById(String id);

	/**
	 * 添加SysGroup
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SysGroup instance);
	
	/**
	 * 添加多个SysGroup对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SysGroup> instanceList);

	/**
	 * 修改SysGroup
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSysGroup(SysGroup instance);

	/**
	 * 删除SysGroup
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SysGroup instance);
	
	/**
	 * 删除SysGroup
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);
	
	/**
	 * SysGroup根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysGroup> page);

	/**
	 * SysGroup根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SysGroup根据条件查询列表;
	 * 
	 * @param sysGroup
	 * @return
	 */
	public List<SysGroup> findByHQLAndParams(SysGroup sysGroup);
	
	/**
	 * 删除GroupR	ole
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteGroupRoleByGroupId(String groupId);

	/**
	 * 保存GroupR	ole
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean saveGroupRole(GroupRole groupRole);
	
	public SysGroup getSysGroup(String id);
	
	public List<GroupRole> getRoleByGroupId(String groupId);
}
