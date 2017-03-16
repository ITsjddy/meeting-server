package com.smartdot.meeting.server.modules.smspushtemplate.service;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.SmsPushTemplate;

public interface ISmsPushTemplateService {
	
	public final static String SERVICE_NAME = "smsPushTemplateService";
	
	public final static String PAGE_MAIN_PATH = "/smsTemplate/list";
	
	public final static String PAGE_ADDEDIT_PATH = "/smsTemplate/addEdit";
	
	public final static String PAGE_PUSHSMS_PATH = "/smsTemplate/pushSms";

	/**
	 * 全部查看SmsPushTemplate
	 * 
	 * @return List
	 */
	public List<SmsPushTemplate> findAll();

	/**
	 * 根据ID查找SmsPushTemplate
	 * 
	 * @param id
	 *            可以根据ID查看SmsPushTemplate中的字段
	 * @return SmsPushTemplate
	 */
	public SmsPushTemplate getSmsPushTemplateById(String id);

	/**
	 * 添加SmsPushTemplate
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SmsPushTemplate instance);
	
	/**
	 * 添加多个SmsPushTemplate对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SmsPushTemplate> instanceList);

	/**
	 * 修改SmsPushTemplate
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSmsPushTemplate(SmsPushTemplate instance);

	/**
	 * 删除SmsPushTemplate
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SmsPushTemplate instance);
	
	/**
	 * 删除SmsPushTemplate
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * SavorPoint分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> findSmsPushTemplateByPage(Page<SmsPushTemplate> page, Map<String, Object> searchMap);

	/**
	 * SmsPushTemplate根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SmsPushTemplate根据条件查询列表;
	 * 
	 * @param smsPushTemplate
	 * @return
	 */
	public List<SmsPushTemplate> findByHQLAndParams(SmsPushTemplate smsPushTemplate);
}
