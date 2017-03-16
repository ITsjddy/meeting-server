package com.smartdot.meeting.server.modules.sysrole.dao;

import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.RolePrivilege;


public interface IRolePrivilegeDao extends GenericDao<RolePrivilege> {
	public final static String DAO_NAME="rolePrivilegeDao";
	
	public List<RolePrivilege> getListPrivilegesByRoleId(String roleId);
	
	/**
	 * 删除RolePrivilege
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteRolePrivilegeByRoleId(String id);
}
