package com.smartdot.meeting.server.modules.sysprivilege.dao;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.SysPrivilege;

public interface ISysPrivilegeDao extends GenericDao<SysPrivilege> {
	
	public final static String DAO_NAME="sysPrivilegeDao";
	
	public Map<String, String> getResources();
	
	public List<Map<String, String>> getUserPrivilegesByUserName(String userName);
	
	public List<Map<String, String>> getPrivilegesByUserName(String userName,String type,boolean state);

	public List<SysPrivilege> getListPrivileges(List<String> types);
	
	public List<Map<String, Object>> getListMapPrivileges(String types) throws Exception;
}
