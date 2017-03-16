package com.smartdot.meeting.server.modules.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.QuestionnaireTopic;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.questionnaire.dao.IQuestionnaireTopicDao;
import com.smartdot.meeting.server.modules.questionnaire.service.IQuestionnaireTopicService;

@Service(value = IQuestionnaireTopicService.SERVICE_NAME)
public class QuestionnaireTopicServiceImpl implements IQuestionnaireTopicService {
	@Resource(name = IQuestionnaireTopicDao.DAO_NAME)
	private IQuestionnaireTopicDao questionnaireTopicDao;

	@Override
	public List<QuestionnaireTopic> findAll() {
		return questionnaireTopicDao.findAll();
	}

	@Override
	public QuestionnaireTopic getQuestionnaireTopicById(String id) {
		
		return questionnaireTopicDao.findById(id);
	}

	@Override
	public boolean save(QuestionnaireTopic questionnaireTopic) {
		boolean flag = false;
		if (questionnaireTopic != null) {
			questionnaireTopicDao.save(questionnaireTopic);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<QuestionnaireTopic> questionnaireTopicList) {
		boolean flag = false;
		if (questionnaireTopicList != null) {
			questionnaireTopicDao.saveAll(questionnaireTopicList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateQuestionnaireTopic(QuestionnaireTopic questionnaireTopic) {
		boolean flag = false;
		if (questionnaireTopic != null) {
			questionnaireTopicDao.update(questionnaireTopic);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(QuestionnaireTopic questionnaireTopic) {
		boolean flag = false;
		if (questionnaireTopic != null) {
			QuestionnaireTopic instance = this.getQuestionnaireTopicById(questionnaireTopic.getId());
			questionnaireTopicDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Page<QuestionnaireTopic> findQuestionnaireTopicByPage(QuestionnaireTopic questionnaireTopic,Page<QuestionnaireTopic> pageQuery) {
		Page<QuestionnaireTopic> page = new Page<QuestionnaireTopic>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from QuestionnaireTopic obj where 1=1 ");
		Object[] params = paramList.toArray();
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				questionnaireTopicDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<QuestionnaireTopic> findByHQLAndParams(QuestionnaireTopic questionnaireTopic) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<QuestionnaireTopic> sList = questionnaireTopicDao
				.findByHQLAndParams(
						"from QuestionnaireTopic where 1=1",
						"");
		return sList;
	}

	@Override
	public List<QuestionnaireTopic> findAllByProperty(String propertyName,
			Object propertyValue) {
		return questionnaireTopicDao.findAllByProperty(propertyName, propertyValue);
	}
	
}
