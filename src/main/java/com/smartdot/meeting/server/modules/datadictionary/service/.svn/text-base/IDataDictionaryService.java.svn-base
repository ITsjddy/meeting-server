package com.smartdot.meeting.server.modules.datadictionary.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.DataDictionary;

/** 
 * @ClassName: IDataDictionaryService 
 * @Description: TODO
 * @author: smartdot
 * @date: 2017年3月13日 下午5:04:57  
 */
public interface IDataDictionaryService {
	public final static String SERVICE_NAME = "dataDictionaryService";

	/**
	 * 全部查看DataDictionary
	 * 
	 * @return List
	 */
	public List<DataDictionary> findAll();

	/**
	 * 根据ID查找DataDictionary
	 * 
	 * @param id
	 *            可以根据ID查看DataDictionary中的字段
	 * @return DataDictionary
	 */
	public DataDictionary getDataDictionaryById(String id);

	/**
	 * 添加DataDictionary
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(DataDictionary instance);
	
	/**
	 * 添加多个DataDictionary对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<DataDictionary> instanceList);

	/**
	 * 修改DataDictionary
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateDataDictionary(DataDictionary instance);

	/**
	 * 删除DataDictionary
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(DataDictionary instance);
	
	/**
	 * 删除DataDictionary
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * DataDictionary分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<DataDictionary> pageQuery);

	/**
	 * DataDictionary根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * DataDictionary根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByList(List<DataDictionary> dataDictionaries);
	
	/**
	 * DataDictionary根据条件查询列表;
	 * 
	 * @param dataDictionary
	 * @return
	 */
	public List<DataDictionary> findByHQLAndParams(DataDictionary dataDictionary);
	
	public List<DataDictionary> findByDetachedCriteria(DetachedCriteria detachedCriteria);
	
	
	/** 
	 * @Title: findAllByTypeLogo 
	 * @Description: 根据数据字典唯一标识获取该字典下所有子类
	 * @param typeLogo 数据字典唯一标识  languageType 语言标识
	 * @return
	 * @return: List<DataDictionary>
	 */
	public List<DataDictionary> findAllByTypeLogo(String typeLogo, String languageType); 
	
	
	/** 
	 * @Title: findByTypeLogo 
	 * @Description: 根据数据字典子类唯一标识获取该字子类
	 * @param typeLogo 数据字典唯一标识
	 * @param languageType 语言标识
	 * @return
	 * @return: DataDictionary
	 */
	public DataDictionary findByTypeLogo(String typeLogo,String languageType); 
	
	
}
