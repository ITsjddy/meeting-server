package com.smartdot.meeting.server.modules.datadictionarytype.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.DataDictionaryType;

public interface IDataDictionaryTypeService {
	public final static String SERVICE_NAME = "dataDictionaryTypeService";

	
	/**
	 * 全部查看DataDictionaryType
	 * 
	 * @return List
	 */
	public List<DataDictionaryType> findAll();

	/**
	 * 根据ID查找DataDictionaryType
	 * 
	 * @param id
	 *            可以根据ID查看DataDictionaryType中的字段
	 * @return DataDictionaryType
	 */
	public DataDictionaryType getDataDictionaryTypeById(String id);

	/**
	 * 添加DataDictionaryType
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(DataDictionaryType instance);
	
	/**
	 * 添加多个DataDictionaryType对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<DataDictionaryType> instanceList);

	/**
	 * 修改DataDictionaryType
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateDataDictionaryType(DataDictionaryType instance);

	/**
	 * 删除DataDictionaryType
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(DataDictionaryType instance);
	
	/**
	 * 删除DataDictionaryType
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * DataDictionaryType分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<DataDictionaryType> pageQuery);

	/**
	 * DataDictionaryType根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * DataDictionaryType根据条件查询列表;
	 * 
	 * @param dataDictionaryType
	 * @return
	 */
	public List<DataDictionaryType> findByHQLAndParams(DataDictionaryType dataDictionaryType);
	
	public List<DataDictionaryType> findByDetachedCriteria(DetachedCriteria detachedCriteria);
}
