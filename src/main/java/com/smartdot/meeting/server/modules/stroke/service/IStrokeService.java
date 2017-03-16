package com.smartdot.meeting.server.modules.stroke.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.stroke.model.StrokeForm;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Stroke;

public interface IStrokeService {
	public final static String SERVICE_NAME = "strokeService";

	/**
	 * 全部查看Stroke
	 * 
	 * @return List
	 */
	public List<Stroke> findAll();

	/**
	 * 根据ID查找Stroke
	 * 
	 * @param id
	 *            可以根据ID查看Stroke中的字段
	 * @return Stroke
	 */
	public Stroke getStrokeById(String id);

	/**
	 * 添加Stroke
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(Stroke instance);
	
	/**
	 * 添加多个Stroke对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<Stroke> instanceList);

	/**
	 * 修改Stroke
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateStroke(Stroke instance);

	/**
	 * 删除Stroke
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(Stroke instance);
	
	/**
	 * 删除Stroke
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * Stroke分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Stroke> pageQuery);

	/**
	 * Stroke根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * Stroke根据条件查询列表;
	 * 
	 * @param stroke
	 * @return
	 */
	public List<Stroke> findByHQLAndParams(Stroke stroke);

	public List<Map<String, String>> getAllLanguage();

	public List<Stroke> getListStoke(String id);

	public boolean update(StrokeForm instance);
	public boolean saveMultiEntity(String strokeMain,String[] strokeList);
}
