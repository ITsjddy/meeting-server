package com.smartdot.meeting.server.modules.department.dao;

import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.Department;
public interface IDepartmentDao extends GenericDao<Department> {
	public final static String DAO_NAME="departmentDao";

	List<Department> getMaxChildrenNumByParentNum(String parentGroupNum);

	List<Department> getMaxNumInOneLevel();
	
}
