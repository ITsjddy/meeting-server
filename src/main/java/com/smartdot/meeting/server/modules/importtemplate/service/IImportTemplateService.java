package com.smartdot.meeting.server.modules.importtemplate.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.ImportTemplate;

public interface IImportTemplateService {
	public final static String SERVICE_NAME = "importTemplateService";

	/**
	 * 全部查看ImportTemplate
	 * 
	 * @return List
	 */
	public List<ImportTemplate> findAll();

	/**
	 * 根据ID查找ImportTemplate
	 * 
	 * @param id
	 *            可以根据ID查看ImportTemplate中的字段
	 * @return ImportTemplate
	 */
	public ImportTemplate getImportTemplateById(String id);

	/**
	 * 添加ImportTemplate
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(ImportTemplate instance);
	
	/**
	 * 添加多个ImportTemplate对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<ImportTemplate> instanceList);

	/**
	 * 修改ImportTemplate
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateImportTemplate(ImportTemplate instance);

	/**
	 * 删除ImportTemplate
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(ImportTemplate instance);
	
	/**
	 * 删除ImportTemplate
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * ImportTemplate分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<ImportTemplate> pageQuery);

	/**
	 * ImportTemplate根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * ImportTemplate根据条件查询列表;
	 * 
	 * @param importTemplate
	 * @return
	 */
	public List<ImportTemplate> findByHQLAndParams(ImportTemplate importTemplate);
}
