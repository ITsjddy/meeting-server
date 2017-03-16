package com.smartdot.meeting.server.modules.sysgroup.dao;


import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.GroupRole;


public interface IGroupRoleDao extends GenericDao<GroupRole> {
	public final static String DAO_NAME="groupRoleDao";
	
	public boolean deleteByGroupId(String groupId);
	
	public boolean deleteByRoleId(String roleId);
	
	public List<GroupRole> getRoleByGroupId(String groupId);
}
