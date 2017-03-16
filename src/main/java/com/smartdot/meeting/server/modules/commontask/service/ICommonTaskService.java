package com.smartdot.meeting.server.modules.commontask.service;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.entity.CommonTask;
import com.smartdot.meeting.server.common.model.Page;

public interface ICommonTaskService {
	public final static String SERVICE_NAME = "commonTaskService";

	/**
	 * 全部查看CommonTask
	 * 
	 * @return List
	 */
	public List<CommonTask> findAll();

	/**
	 * 根据ID查找CommonTask
	 * 
	 * @param id
	 *            可以根据ID查看CommonTask中的字段
	 * @return CommonTask
	 */
	public CommonTask getCommonTaskById(String id);

	/**
	 * 添加CommonTask
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(CommonTask instance);
	
	/**
	 * 添加多个CommonTask对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<CommonTask> instanceList);

	/**
	 * 修改CommonTask
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateCommonTask(CommonTask instance);

	/**
	 * 删除CommonTask
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(CommonTask instance);
	
	/**
	 * 删除CommonTask
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * CommonTask分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<CommonTask> findCommonTaskByPage(CommonTask commonTask,Page<CommonTask> pageQuery);

	/**
	 * CommonTask根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * CommonTask根据条件查询列表;
	 * 
	 * @param commonTask
	 * @return
	 */
	public List<CommonTask> findByHQLAndParams(CommonTask commonTask);
	
	public Map<String, Object> pageList(Page<CommonTask> page, Map<String,Object> searchMap);
	
	public List<CommonTask> findAllByProperty(String propertyName, Object propertyValue);
}
