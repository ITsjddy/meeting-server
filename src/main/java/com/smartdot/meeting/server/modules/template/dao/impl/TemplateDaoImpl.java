package com.smartdot.meeting.server.modules.template.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.template.dao.ITemplateDao;
import com.smartdot.meeting.server.common.entity.Template;
@Repository(value = ITemplateDao.DAO_NAME)
public class TemplateDaoImpl extends GenericDaoHibernateSupport<Template> implements ITemplateDao {

	public List<Template> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return (List<Template>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	
}
