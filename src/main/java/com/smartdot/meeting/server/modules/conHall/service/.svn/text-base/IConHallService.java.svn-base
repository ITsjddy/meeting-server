package com.smartdot.meeting.server.modules.conHall.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.ConHall;
import com.smartdot.meeting.server.common.model.Page;

public interface IConHallService {
	
	public static final String SERVICE_NAME = "conHallService";

	public Map<String, Object> pageConHallList(DetachedCriteria detachedCriteria,Page<ConHall> page);

	public ConHall getEntityById(String id);

	public void saveEntity(ConHall conHall);

	public List<Map<String, String>> getAllLanguage();

	public List<ConHall> getListConHall(String id);

	public boolean saveMultiEntity(String[] conHallList, String conHallMain);

	public boolean updateMultiEntity(String[] conHallList, String conHallMain);

	public void deleteByUniqueCode(String uniqueCode);

	public List<ConHall> getConHallList();

}
