package com.smartdot.meeting.server.modules.template.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.template.dao.ITemplateDao;
import com.smartdot.meeting.server.modules.template.service.ITemplateService;
import com.smartdot.meeting.server.common.entity.Template;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ITemplateService.SERVICE_NAME)
public class TemplateServiceImpl implements ITemplateService {
	
	@Resource(name = ITemplateDao.DAO_NAME)
	private ITemplateDao templateDao;

	@Override
	public List<Template> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(Template.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return templateDao.findByDetachedCriteria(dc);
	}

	@Override
	public Template getTemplateById(String id) {
		
		return templateDao.findById(id);
	}

	@Audit(module= "Template管理",action= "Template信息添加",description= "Template信息添加说明")
	@Override
	public boolean save(Template template) {
		boolean flag = false;
		if (template != null) {
			templateDao.save(template);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "Template管理",action= "Template多条信息添加",description= "Template多条信息添加说明")
	@Override
	public boolean saveAll(List<Template> templateList) {
		boolean flag = false;
		if (templateList != null) {
			templateDao.saveAll(templateList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "Template管理",action= "Template信息修改",description= "Template信息修改说明")
	@Override
	public boolean updateTemplate(Template template) {
		boolean flag = false;
		if (template != null) {
			templateDao.update(template);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(Template template) {
		boolean flag = false;
		if (template != null) {
			Template instance = this.getTemplateById(template.getId());
			templateDao.delete(instance);
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Template> pageQuery) {
		
		return templateDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "Template管理",action= "Template信息删除",description= "Template信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				Template template = templateDao.get(id);
				template.setEnable(false);
				templateDao.update(template);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<Template> findByHQLAndParams(Template template) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<Template> sList = templateDao
				.findByHQLAndParams("from Template where enable = true","");
		return sList;
	}

	@Override
	public List<Template> findByDetachedCriteria(
			DetachedCriteria detachedCriteria) {
		return templateDao.findByDetachedCriteria(detachedCriteria);
	}
	
}
