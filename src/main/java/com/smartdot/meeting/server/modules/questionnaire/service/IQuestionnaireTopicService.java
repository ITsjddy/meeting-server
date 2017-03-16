package com.smartdot.meeting.server.modules.questionnaire.service;

import java.util.List;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.QuestionnaireTopic;

public interface IQuestionnaireTopicService {
	public final static String SERVICE_NAME = "topicService";

	/**
	 * 全部查看QuestionnaireTopic
	 * 
	 * @return List
	 */
	public List<QuestionnaireTopic> findAll();

	/**
	 * 根据ID查找QuestionnaireTopic
	 * 
	 * @param id
	 *            可以根据ID查看QuestionnaireTopic中的字段
	 * @return QuestionnaireTopic
	 */
	public QuestionnaireTopic getQuestionnaireTopicById(String id);

	/**
	 * 添加QuestionnaireTopic
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(QuestionnaireTopic instance);
	
	/**
	 * 添加多个QuestionnaireTopic对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<QuestionnaireTopic> instanceList);

	/**
	 * 修改QuestionnaireTopic
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateQuestionnaireTopic(QuestionnaireTopic instance);

	/**
	 * 删除QuestionnaireTopic
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(QuestionnaireTopic instance);
	
	/**
	 * 删除QuestionnaireTopic
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * QuestionnaireTopic分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<QuestionnaireTopic> findQuestionnaireTopicByPage(QuestionnaireTopic questionnaireTopic,Page<QuestionnaireTopic> pageQuery);

	/**
	 * QuestionnaireTopic根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * QuestionnaireTopic根据条件查询列表;
	 * 
	 * @param questionnaireTopic
	 * @return
	 */
	public List<QuestionnaireTopic> findByHQLAndParams(QuestionnaireTopic questionnaireTopic);
	
	public List<QuestionnaireTopic> findAllByProperty(String propertyName, Object propertyValue);
}
