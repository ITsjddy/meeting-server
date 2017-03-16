package com.smartdot.meeting.server.modules.guest.dao;


import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.GuestAndServicePersonnel;


public interface IGuestAndServicePersonnelDao extends GenericDao<GuestAndServicePersonnel> {
	
	public final static String DAO_NAME="guestAndServicePersonnelDao";
	
	public boolean delete(String id);
	
	public List<Map<String, Object>> queryALLServicePersonnel() throws Exception;
	
	
	public List<Map<String, Object>> findServicePersonnelForName(String name) throws Exception;
}
