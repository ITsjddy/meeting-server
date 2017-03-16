package com.smartdot.meeting.server.modules.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.entity.Questionnaire;
import com.smartdot.meeting.server.common.model.Page;

public interface IQuestionnaireService {
	public final static String SERVICE_NAME = "questionnaireService";

	/**
	 * 全部查看Questionnaire
	 * 
	 * @return List
	 */
	public List<Questionnaire> findAll();

	/**
	 * 根据ID查找Questionnaire
	 * 
	 * @param id
	 *            可以根据ID查看Questionnaire中的字段
	 * @return Questionnaire
	 */
	public Questionnaire getQuestionnaireById(String id);

	/**
	 * 添加Questionnaire
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(Questionnaire instance);
	
	/**
	 * 添加多个Questionnaire对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<Questionnaire> instanceList);

	/**
	 * 修改Questionnaire
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateQuestionnaire(Questionnaire instance);

	/**
	 * 删除Questionnaire
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(Questionnaire instance);
	
	/**
	 * 删除Questionnaire
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * Questionnaire分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<Questionnaire> findQuestionnaireByPage(Questionnaire questionnaire,Page<Questionnaire> pageQuery);

	/**
	 * Questionnaire根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * Questionnaire根据条件查询列表;
	 * 
	 * @param questionnaire
	 * @return
	 */
	public List<Questionnaire> findByHQLAndParams(Questionnaire questionnaire);
	
	public Map<String, Object> pageList(Page<Questionnaire> page, Map<String,Object> searchMap);
}
