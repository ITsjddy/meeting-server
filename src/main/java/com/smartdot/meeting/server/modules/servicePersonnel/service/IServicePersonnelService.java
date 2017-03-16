package com.smartdot.meeting.server.modules.servicePersonnel.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;


public interface IServicePersonnelService {
	
	public final static String SERVICE_NAME = "servicePersonnelService";


	/**
	 * 根据ID查找ServicePersonnel
	 * 
	 * @param id
	 *            可以根据ID查看ServicePersonnel中的字段
	 * @return ServicePersonnel
	 */
	public ServicePersonnel getServicePersonnelById(String id);
	
	/**
	 * 添加ServicePersonnel
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(ServicePersonnel instance);
	
	/**
	 * 修改ServicePersonnel
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean update(ServicePersonnel instance);
	
	/**
	 * ServicePersonnel根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * 删除ServicePersonnel
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);
	
	/**
	 * ServicePersonnel根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<ServicePersonnel> page);
	
	
	public List<ServicePersonnel> findByDetachedCriteria(DetachedCriteria detachedCriteria);
	
}
