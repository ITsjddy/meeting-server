package com.smartdot.meeting.server.modules.smspushs.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.SmsPushs;

public interface ISmsPushsService {
	public final static String SERVICE_NAME = "smsPushsService";

	/**
	 * 全部查看SmsPushs
	 * 
	 * @return List
	 */
	public List<SmsPushs> findAll();

	/**
	 * 根据ID查找SmsPushs
	 * 
	 * @param id
	 *            可以根据ID查看SmsPushs中的字段
	 * @return SmsPushs
	 */
	public SmsPushs getSmsPushsById(String id);

	/**
	 * 添加SmsPushs
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SmsPushs instance);
	
	/**
	 * 添加多个SmsPushs对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SmsPushs> instanceList);

	/**
	 * 修改SmsPushs
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSmsPushs(SmsPushs instance);

	/**
	 * 删除SmsPushs
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SmsPushs instance);
	
	/**
	 * 删除SmsPushs
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * SmsPushs分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SmsPushs> pageQuery);

	/**
	 * SmsPushs根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SmsPushs根据条件查询列表;
	 * 
	 * @param smsPushs
	 * @return
	 */
	public List<SmsPushs> findByHQLAndParams(SmsPushs smsPushs);
}
