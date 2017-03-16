package com.smartdot.meeting.server.modules.infomgmt.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.SavorPoint;
import com.smartdot.meeting.server.common.model.Page;

public interface ISavorPointService {
	
	public final static String SERVICE_NAME = "savorPointService";
	
	public final static String PAGE_MAIN_PATH = "/savorPoint/list";
	
	public final static String PAGE_ADD_PATH = "/savorPoint/add";
	
	
	public final static String PAGE_EDIT_PATH = "/savorPoint/edit";
	
	
	public final static String PAGE_DETAILS_PATH = "/savorPoint/details";

	/**
	 * 全部查看SavorPoint
	 * 
	 * @return List
	 */
	public List<SavorPoint> findAll();
	/**
	 * 根据ID查找SavorPoint
	 * 
	 * @param id
	 *            可以根据ID查看SavorPoint中的字段
	 * @return SavorPoint
	 */
	public SavorPoint getSavorPointById(String id);

	/**
	 * 添加SavorPoint
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SavorPoint instance);
	
	/**
	 * 添加多个SavorPoint对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SavorPoint> instanceList);

	/**
	 * 修改SavorPoint
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSavorPoint(SavorPoint instance);

	/**
	 * 删除SavorPoint
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SavorPoint instance);
	
	/**
	 * 删除SavorPoint
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);
	
	
	/**
	 * 删除SavorPoint
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAllsavorPoint(List<SavorPoint> savorPoints);
	
	
	

	/**
	 * SavorPoint分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> findSavorPointByPage(Page<SavorPoint> page, Map<String, Object> searchMap);

	/**
	 * SavorPoint根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SavorPoint根据条件查询列表;
	 * 
	 * @param savorPoint
	 * @return
	 */
	public List<SavorPoint> findByHQLAndParams(SavorPoint savorPoint);
	
	/**
	 * SavorPoint根据条件查询列表;
	 * 
	 * @param savorPoint
	 * @return
	 */
	public List<SavorPoint> findAllByProperties(Map<String, Object> map);
	
	
	public List<SavorPoint> findByDetachedCriteria(DetachedCriteria detachedCriteria);
	
	
	
}
