package com.smartdot.meeting.server.modules.guest.dao;

import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.Guest;


public interface IGuestDao extends GenericDao<Guest> {
	
	public final static String DAO_NAME="guestDao";
	
	public boolean deleteAll(List<String> ids);
	
	public boolean delete(String id);
	
	/**
	 * 根据memberId查找List<Guest>
	 * 
	 * @param memberId
	 *            根据memberId查找List<Guest>
	 * @return List<Guest>
	 */
	public List<Guest> getListGuestByMemberId(String memberId);
}
