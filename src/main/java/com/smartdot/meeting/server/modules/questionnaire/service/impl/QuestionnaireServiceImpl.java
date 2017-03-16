package com.smartdot.meeting.server.modules.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.Questionnaire;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.questionnaire.dao.IQuestionnaireDao;
import com.smartdot.meeting.server.modules.questionnaire.service.IQuestionnaireService;

@Service(value = IQuestionnaireService.SERVICE_NAME)
public class QuestionnaireServiceImpl implements IQuestionnaireService {
	@Resource(name = IQuestionnaireDao.DAO_NAME)
	private IQuestionnaireDao questionnaireDao;

	@Override
	public List<Questionnaire> findAll() {
		return questionnaireDao.findAll();
	}

	@Override
	public Questionnaire getQuestionnaireById(String id) {
		
		return questionnaireDao.findById(id);
	}

	@Override
	public boolean save(Questionnaire questionnaire) {
		boolean flag = false;
		if (questionnaire != null) {
			questionnaireDao.save(questionnaire);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<Questionnaire> questionnaireList) {
		boolean flag = false;
		if (questionnaireList != null) {
			questionnaireDao.saveAll(questionnaireList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateQuestionnaire(Questionnaire questionnaire) {
		boolean flag = false;
		if (questionnaire != null) {
			questionnaireDao.update(questionnaire);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(Questionnaire questionnaire) {
		boolean flag = false;
		if (questionnaire != null) {
			Questionnaire instance = this.getQuestionnaireById(questionnaire.getId());
			questionnaireDao.delete(instance);
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
	public Page<Questionnaire> findQuestionnaireByPage(Questionnaire questionnaire,Page<Questionnaire> pageQuery) {
		Page<Questionnaire> page = new Page<Questionnaire>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from Questionnaire obj where 1=1 ");
		Object[] params = paramList.toArray();
		return (Page<Questionnaire>) questionnaireDao.pageQueryByHql(page,
		"select count(obj) " + hql.toString(), hql.toString(), params,
		params);
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				questionnaireDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<Questionnaire> findByHQLAndParams(Questionnaire questionnaire) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<Questionnaire> sList = questionnaireDao
				.findByHQLAndParams(
						"from Questionnaire where 1=1",
						"");
		return sList;
	}
	
	@Override
	public Map<String, Object> pageList(Page<Questionnaire> page, Map<String,Object> searchMap) {
		LinkedHashMap<String,String> orderByMap = new LinkedHashMap<String,String>();
		orderByMap.put("createTimes", "desc");
		Map<String, Object> pageData = questionnaireDao.pageQuery(searchMap,page,orderByMap,false);
		return pageData;
	}
}
