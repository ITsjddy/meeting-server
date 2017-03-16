package com.smartdot.meeting.server.modules.datadictionary.dao;

import java.util.List;

import com.smartdot.meeting.server.common.dao.GenericDao;
import com.smartdot.meeting.server.common.entity.DataDictionary;
public interface IDataDictionaryDao extends GenericDao<DataDictionary> {
	public final static String DAO_NAME="dataDictionaryDao";
	
	public List<DataDictionary> getListDatadictionary(String type);
}
