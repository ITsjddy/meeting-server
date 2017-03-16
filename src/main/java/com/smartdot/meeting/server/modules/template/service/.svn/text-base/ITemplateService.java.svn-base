package com.smartdot.meeting.server.modules.template.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.Template;

public interface ITemplateService {
	public final static String SERVICE_NAME = "templateService";

	/**
	 * 全部查看Template
	 * 
	 * @return List
	 */
	public List<Template> findAll();

	/**
	 * 根据ID查找Template
	 * 
	 * @param id
	 *            可以根据ID查看Template中的字段
	 * @return Template
	 */
	public Template getTemplateById(String id);

	/**
	 * 添加Template
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(Template instance);
	
	/**
	 * 添加多个Template对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<Template> instanceList);

	/**
	 * 修改Template
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateTemplate(Template instance);

	/**
	 * 删除Template
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(Template instance);
	
	/**
	 * 删除Template
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * Template分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Template> pageQuery);

	/**
	 * Template根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * Template根据条件查询列表;
	 * 
	 * @param template
	 * @return
	 */
	public List<Template> findByHQLAndParams(Template template);
	
	public List<Template> findByDetachedCriteria(DetachedCriteria detachedCriteria);
}
