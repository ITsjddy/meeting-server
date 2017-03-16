package com.smartdot.meeting.server.modules.systemsetting.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.SystemSetting;

public interface ISystemSettingService {
	public final static String SERVICE_NAME = "systemSettingService";

	/**
	 * 全部查看SystemSetting
	 * 
	 * @return List
	 */
	public List<SystemSetting> findAll();

	/**
	 * 根据ID查找SystemSetting
	 * 
	 * @param id
	 *            可以根据ID查看SystemSetting中的字段
	 * @return SystemSetting
	 */
	public SystemSetting getSystemSettingById(String id);

	/**
	 * 添加SystemSetting
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SystemSetting instance);
	
	/**
	 * 添加多个SystemSetting对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SystemSetting> instanceList);

	/**
	 * 修改SystemSetting
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSystemSetting(SystemSetting instance);

	/**
	 * 删除SystemSetting
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SystemSetting instance);
	
	/**
	 * 删除SystemSetting
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * SystemSetting分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SystemSetting> pageQuery);

	/**
	 * SystemSetting根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SystemSetting根据条件查询列表;
	 * 
	 * @param systemSetting
	 * @return
	 */
	public List<SystemSetting> findByHQLAndParams(SystemSetting systemSetting);
	
	
	public boolean findByDetachedCriteria(String uniqueId);
}
