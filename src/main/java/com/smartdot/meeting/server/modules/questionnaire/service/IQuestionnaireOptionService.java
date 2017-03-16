package com.smartdot.meeting.server.modules.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.QuestionnaireOption;
import com.smartdot.meeting.server.common.entity.QuestionnaireTopic;

public interface IQuestionnaireOptionService {
	public final static String SERVICE_NAME = "questionnaireOptionService";

	/**
	 * 全部查看QuestionnaireOption
	 * 
	 * @return List
	 */
	public List<QuestionnaireOption> findAll();

	/**
	 * 根据ID查找QuestionnaireOption
	 * 
	 * @param id
	 *            可以根据ID查看QuestionnaireOption中的字段
	 * @return QuestionnaireOption
	 */
	public QuestionnaireOption getQuestionnaireOptionById(String id);

	/**
	 * 添加QuestionnaireOption
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(QuestionnaireOption instance);
	
	/**
	 * 添加多个QuestionnaireOption对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<QuestionnaireOption> instanceList);

	/**
	 * 修改QuestionnaireOption
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateQuestionnaireOption(QuestionnaireOption instance);

	/**
	 * 删除QuestionnaireOption
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(QuestionnaireOption instance);
	
	/**
	 * 删除QuestionnaireOption
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * QuestionnaireOption分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<QuestionnaireOption> findQuestionnaireOptionByPage(QuestionnaireOption questionnaireOption,Page<QuestionnaireOption> pageQuery);

	/**
	 * QuestionnaireOption根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * QuestionnaireOption根据条件查询列表;
	 * 
	 * @param questionnaireOption
	 * @return
	 */
	public List<QuestionnaireOption> findByHQLAndParams(QuestionnaireOption questionnaireOption);
	
	public List<QuestionnaireOption> findAllByProperty(String propertyName, Object propertyValue);
	
	public List<QuestionnaireOption> findAllByProperties(Map<String, Object> map);
}
