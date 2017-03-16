package com.smartdot.meeting.server.modules.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.QuestionnaireOption;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.questionnaire.dao.IQuestionnaireOptionDao;
import com.smartdot.meeting.server.modules.questionnaire.service.IQuestionnaireOptionService;

@Service(value = IQuestionnaireOptionService.SERVICE_NAME)
public class QuestionnaireOptionServiceImpl implements IQuestionnaireOptionService {
	@Resource(name = IQuestionnaireOptionDao.DAO_NAME)
	private IQuestionnaireOptionDao questionnaireOptionDao;

	@Override
	public List<QuestionnaireOption> findAll() {
		return questionnaireOptionDao.findAll();
	}

	@Override
	public QuestionnaireOption getQuestionnaireOptionById(String id) {
		
		return questionnaireOptionDao.findById(id);
	}

	@Override
	public boolean save(QuestionnaireOption questionnaireOption) {
		boolean flag = false;
		if (questionnaireOption != null) {
			questionnaireOptionDao.save(questionnaireOption);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<QuestionnaireOption> questionnaireOptionList) {
		boolean flag = false;
		if (questionnaireOptionList != null) {
			questionnaireOptionDao.saveAll(questionnaireOptionList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateQuestionnaireOption(QuestionnaireOption questionnaireOption) {
		boolean flag = false;
		if (questionnaireOption != null) {
			questionnaireOptionDao.update(questionnaireOption);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(QuestionnaireOption questionnaireOption) {
		boolean flag = false;
		if (questionnaireOption != null) {
			QuestionnaireOption instance = this.getQuestionnaireOptionById(questionnaireOption.getId());
			questionnaireOptionDao.delete(instance);
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
	public Page<QuestionnaireOption> findQuestionnaireOptionByPage(QuestionnaireOption questionnaireOption,Page<QuestionnaireOption> pageQuery) {
		Page<QuestionnaireOption> page = new Page<QuestionnaireOption>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from QuestionnaireOption obj where 1=1 ");
		Object[] params = paramList.toArray();
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				questionnaireOptionDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<QuestionnaireOption> findByHQLAndParams(QuestionnaireOption questionnaireOption) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<QuestionnaireOption> sList = questionnaireOptionDao
				.findByHQLAndParams(
						"from QuestionnaireOption where 1=1",
						"");
		return sList;
	}

	@Override
	public List<QuestionnaireOption> findAllByProperty(String propertyName,
			Object propertyValue) {
		return questionnaireOptionDao.findAllByProperty(propertyName, propertyValue);
	}

	@Override
	public List<QuestionnaireOption> findAllByProperties(Map<String, Object> map) {
		return questionnaireOptionDao.findAllByProperties(map);
	}
	
}
