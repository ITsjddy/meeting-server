package com.smartdot.meeting.server.modules.department.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.department.dao.IDepartmentDao;
import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.entity.Template;
@Repository(value = IDepartmentDao.DAO_NAME)
public class DepartmentDaoImpl extends GenericDaoHibernateSupport<Department> implements IDepartmentDao {
	public List<Department> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return (List<Department>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List<Department> getMaxChildrenNumByParentNum(String parentGroupNum) {
		String hql = "FROM Department t where ENABLE=1 AND PGROUPNUMBER =? ORDER BY CONVERT(GROUPNUMBER,SIGNED) DESC ";
		return this.findByHQLAndParams(hql, parentGroupNum);
	}

	@Override
	public List<Department> getMaxNumInOneLevel() {
		return getMaxChildrenNumByParentNum("0");
	}
}
