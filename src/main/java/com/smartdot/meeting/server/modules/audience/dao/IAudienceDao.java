package com.smartdot.meeting.server.modules.audience.dao;

import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.Audience;
public interface IAudienceDao extends GenericDao<Audience> {
	public final static String DAO_NAME="audienceDao";
	
	
	public boolean deleteAll(List<String> ids);
	
	public boolean delete(String id);
	
	public List<Audience> getListAudienceByMemberId(String memberId);
	
}
