package com.smartdot.meeting.server.modules.appversion.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.AppVersion;
import com.smartdot.meeting.server.common.model.Page;

public interface IAppVersionService {
	public final static String SERVICE_NAME = "appVersionService";

	/**
	 * 全部查看AppVersion
	 * 
	 * @return List
	 */
	public List<AppVersion> findAll();

	/**
	 * 根据ID查找AppVersion
	 * 
	 * @param id
	 *            可以根据ID查看AppVersion中的字段
	 * @return AppVersion
	 */
	public AppVersion getAppVersionById(String id);

	/**
	 * 添加AppVersion
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(AppVersion instance);
	
	/**
	 * 添加多个AppVersion对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<AppVersion> instanceList);

	/**
	 * 修改AppVersion
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateAppVersion(AppVersion instance);

	/**
	 * 删除AppVersion
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(AppVersion instance);
	
	/**
	 * 删除AppVersion
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * AppVersion分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<AppVersion> pageQuery);

	/**
	 * AppVersion根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * AppVersion根据条件查询列表;
	 * 
	 * @param appVersion
	 * @return
	 */
	public List<AppVersion> findByHQLAndParams(AppVersion appVersion);
	
	public Map<String, Object> pageList(Page<AppVersion> page, Map<String,Object> searchMap);
	
	public List<AppVersion> getLastVersion(int type);
}
