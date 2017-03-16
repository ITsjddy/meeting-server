package com.smartdot.meeting.server.modules.importtemplate.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.importtemplate.dao.IImportTemplateDao;
import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.entity.ImportTemplate;
@Repository(value = IImportTemplateDao.DAO_NAME)
public class ImportTemplateDaoImpl extends GenericDaoHibernateSupport<ImportTemplate> implements IImportTemplateDao {
	public List<ImportTemplate> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return (List<ImportTemplate>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}
}
