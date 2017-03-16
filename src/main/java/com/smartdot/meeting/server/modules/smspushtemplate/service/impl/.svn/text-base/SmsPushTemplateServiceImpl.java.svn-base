package com.smartdot.meeting.server.modules.smspushtemplate.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.smspushtemplate.dao.ISmsPushTemplateDao;
import com.smartdot.meeting.server.modules.smspushtemplate.service.ISmsPushTemplateService;
import com.smartdot.meeting.server.common.entity.SmsPushTemplate;

@Service(value = ISmsPushTemplateService.SERVICE_NAME)
public class SmsPushTemplateServiceImpl implements ISmsPushTemplateService {
	@Resource(name = ISmsPushTemplateDao.DAO_NAME)
	private ISmsPushTemplateDao smsPushTemplateDao;

	@Override
	public List<SmsPushTemplate> findAll() {
		return smsPushTemplateDao.findAll();
	}

	@Override
	public SmsPushTemplate getSmsPushTemplateById(String id) {
		
		return smsPushTemplateDao.findById(id);
	}

	@Override
	public boolean save(SmsPushTemplate smsPushTemplate) {
		boolean flag = false;
		if (smsPushTemplate != null) {
			smsPushTemplateDao.save(smsPushTemplate);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<SmsPushTemplate> smsPushTemplateList) {
		boolean flag = false;
		if (smsPushTemplateList != null) {
			smsPushTemplateDao.saveAll(smsPushTemplateList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateSmsPushTemplate(SmsPushTemplate smsPushTemplate) {
		boolean flag = false;
		if (smsPushTemplate != null) {
			smsPushTemplateDao.update(smsPushTemplate);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SmsPushTemplate smsPushTemplate) {
		boolean flag = false;
		if (smsPushTemplate != null) {
			SmsPushTemplate instance = this.getSmsPushTemplateById(smsPushTemplate.getId());
			smsPushTemplateDao.delete(instance);
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
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				smsPushTemplateDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<SmsPushTemplate> findByHQLAndParams(SmsPushTemplate smsPushTemplate) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<SmsPushTemplate> sList = smsPushTemplateDao
				.findByHQLAndParams(
						"from SmsPushTemplate where 1=1",
						"");
		return sList;
	}

	@Override
	public Map<String, Object> findSmsPushTemplateByPage(
			Page<SmsPushTemplate> page, Map<String, Object> searchMap) {
		LinkedHashMap<String, String> orderByMap = new LinkedHashMap<String, String>();
		orderByMap.put("createTimes", "desc");
		Map<String, Object> resultMap = smsPushTemplateDao.pageQuery(searchMap, page, orderByMap, false);
		return resultMap;
	}
	
}
