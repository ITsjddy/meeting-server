package com.smartdot.meeting.server.modules.media.dao;


import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.Media;


public interface IMediaDao extends GenericDao<Media> {
	
	public final static String DAO_NAME="mediaDao";
	
	public boolean delete(String id);
	
	public boolean deleteAll(List<String> ids);
	
	public List<Media> getListMediaByMemberId(String memberId);
	
}
