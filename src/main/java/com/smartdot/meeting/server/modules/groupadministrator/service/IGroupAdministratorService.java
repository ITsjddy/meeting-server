package com.smartdot.meeting.server.modules.groupadministrator.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.GroupAdministrator;
import com.smartdot.meeting.server.common.entity.Guest;

public interface IGroupAdministratorService {
	public final static String SERVICE_NAME = "groupAdministratorService";

	/**
	 * 全部查看GroupAdministrator
	 * 
	 * @return List
	 */
	public List<GroupAdministrator> findAll();

	/**
	 * 根据ID查找GroupAdministrator
	 * 
	 * @param id
	 *            可以根据ID查看GroupAdministrator中的字段
	 * @return GroupAdministrator
	 */
	public GroupAdministrator getGroupAdministratorById(String id);

	/**
	 * 添加GroupAdministrator
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(GroupAdministrator instance);
	
	/**
	 * 添加多个GroupAdministrator对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<GroupAdministrator> instanceList);

	/**
	 * 修改GroupAdministrator
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateGroupAdministrator(GroupAdministrator instance);

	/**
	 * 删除GroupAdministrator
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(GroupAdministrator instance);
	
	/**
	 * 删除GroupAdministrator
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * GroupAdministrator分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<GroupAdministrator> findGroupAdministratorByPage(GroupAdministrator groupAdministrator,Page<GroupAdministrator> pageQuery);

	/**
	 * GroupAdministrator根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * GroupAdministrator根据条件查询列表;
	 * 
	 * @param groupAdministrator
	 * @return
	 */
	public List<GroupAdministrator> findByHQLAndParams(GroupAdministrator groupAdministrator);
	/**
	 * GroupAdministrator根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<GroupAdministrator> page);
}
