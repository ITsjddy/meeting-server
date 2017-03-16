package com.smartdot.meeting.server.modules.emergency.service;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.Emergency;

public interface IEmergencyService {
	public final static String SERVICE_NAME = "emergencyService";

	/**
	 * 全部查看Emergency
	 * 
	 * @return List
	 */
	public List<Emergency> findAll();

	/**
	 * 根据ID查找Emergency
	 * 
	 * @param id
	 *            可以根据ID查看Emergency中的字段
	 * @return Emergency
	 */
	public Emergency getEmergencyById(String id);

	/**
	 * 添加Emergency
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(Emergency instance);
	
	/**
	 * 添加多个Emergency对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<Emergency> instanceList);

	/**
	 * 修改Emergency
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateEmergency(Emergency instance);

	/**
	 * 删除Emergency
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(Emergency instance);
	
	/**
	 * 删除Emergency
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * Emergency分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<Emergency> findEmergencyByPage(Emergency emergency,Page<Emergency> pageQuery);

	/**
	 * Emergency根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * Emergency根据条件查询列表;
	 * 
	 * @param emergency
	 * @return
	 */
	public List<Emergency> findByHQLAndParams(Emergency emergency);
	
	
	public Map<String, Object> pageList(Page<Emergency> page, Map<String,Object> searchMap);
}
