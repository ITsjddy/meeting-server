package com.smartdot.meeting.server.modules.folder.dao;

import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.Folder;


public interface IFolderDao extends GenericDao<Folder> {
	public final static String DAO_NAME="folderDao";

	public List<Folder> getAllList();
	
	public boolean deleteAll(List<String> ids);
	
	public boolean delete(String id);
	
}
