package com.smartdot.meeting.server.modules.systemsetting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.systemsetting.dao.ISystemSettingDao;
import com.smartdot.meeting.server.modules.systemsetting.dao.ISystemSettingRedisDao;
import com.smartdot.meeting.server.modules.systemsetting.service.ISystemSettingService;
import com.smartdot.meeting.server.common.entity.SystemSetting;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ISystemSettingService.SERVICE_NAME)
public class SystemSettingServiceImpl implements ISystemSettingService {
	
	@Resource(name = ISystemSettingDao.DAO_NAME)
	private ISystemSettingDao systemSettingDao;
	
	@Resource(name = ISystemSettingRedisDao.DAO_NAME)
	private ISystemSettingRedisDao systemSettingRedisDao;

	private ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
	private String systemKey = bundle.getString("system_setting_key");
	
	@Override
	public List<SystemSetting> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(SystemSetting.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return systemSettingDao.findByDetachedCriteria(dc);
	}

	@Override
	public SystemSetting getSystemSettingById(String id) {
		List<SystemSetting> systemSettings = new ArrayList<SystemSetting>();
		systemSettings = systemSettingRedisDao.getCacheList(systemKey);
		for (SystemSetting systemSetting : systemSettings) {
			if (systemSetting.getId().equals(id)) {
				return systemSetting;
			}
		}
		return systemSettingDao.findById(id);
	}

	@Audit(module= "SystemSetting管理",action= "SystemSetting信息添加",description= "SystemSetting信息添加说明")
	@Override
	public boolean save(SystemSetting systemSetting) {
		boolean flag = false;
		if (systemSetting != null) {
			systemSettingDao.save(systemSetting);
			systemSettingRedisDao.setCacheListT(systemKey, systemSetting);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "SystemSetting管理",action= "SystemSetting多条信息添加",description= "SystemSetting多条信息添加说明")
	@Override
	public boolean saveAll(List<SystemSetting> systemSettingList) {
		boolean flag = false;
		if (systemSettingList != null) {
			systemSettingDao.saveAll(systemSettingList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "SystemSetting管理",action= "SystemSetting信息修改",description= "SystemSetting信息修改说明")
	@Override
	public boolean updateSystemSetting(SystemSetting systemSetting) {
		boolean flag = false;
		List<SystemSetting> systemSettings = new ArrayList<SystemSetting>();
		systemSettings = systemSettingRedisDao.getCacheList(systemKey);
		if (systemSetting != null) {
			for (int i = 0; i < systemSettings.size(); i++) {
				SystemSetting system = systemSettings.get(i);
				if (system.getId().equals(systemSetting.getId())) {
					systemSettingRedisDao.deleteCacheListT(systemKey, i, system);
					break;
				}
			}
			systemSettingDao.update(systemSetting);
			systemSettingRedisDao.setCacheListT(systemKey, systemSetting);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SystemSetting systemSetting) {
		boolean flag = false;
		if (systemSetting != null) {
			SystemSetting instance = this.getSystemSettingById(systemSetting.getId());
			systemSettingDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "SystemSetting管理",action= "SystemSetting多选信息删除",description= "SystemSetting多选信息删除说明")
	@Override
	public boolean deleteAll(String[] ids) {
		List<SystemSetting> systemSettings = new ArrayList<SystemSetting>();
		systemSettings = systemSettingRedisDao.getCacheList(systemKey);
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				for (int i = 0; i < systemSettings.size(); i++) {
					SystemSetting systemSetting = systemSettings.get(i);
					if (systemSetting.getId().equals(id)) {
						this.deleteById(id);
						systemSettingRedisDao.deleteCacheListT(systemKey, i, systemSetting);
						break;
					}
				}
			}
			flag = true;
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SystemSetting> pageQuery) {
		List<SystemSetting> systemSettings = new ArrayList<SystemSetting>();
		systemSettings = systemSettingRedisDao.getCacheList(systemKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map	= systemSettingDao.pagedQuery(detachedCriteria, pageQuery);
		if ( 0 != map.size()) {
			List<SystemSetting> resultData = new ArrayList<SystemSetting>();
			List<SystemSetting> result = new ArrayList<SystemSetting>();
			resultData = (List<SystemSetting>) map.get("resultData");
			if (resultData.size() > 0) {
				for (SystemSetting systemSetting : resultData) {
					for (SystemSetting syste : systemSettings) {
						if (systemSetting.getId().equals(syste.getId())) {
							result.add(syste);
						}
					}
				}
			}
			map.put("resultData", result);
			return map;
		}
		return map;
	}

	@Audit(module= "SystemSetting管理",action= "SystemSetting信息删除",description= "SystemSetting信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				List<SystemSetting> systemSettings = new ArrayList<SystemSetting>();
				systemSettings = systemSettingRedisDao.getCacheList(systemKey);
				for (int i = 0; i < systemSettings.size(); i++) {
					SystemSetting systemSetting = systemSettings.get(i);
					if (systemSetting.getId().equals(id)) {
						systemSettingRedisDao.deleteCacheListT(systemKey, i, systemSetting);
						systemSetting.setEnable(false);
						systemSettingDao.update(systemSetting);
						break;
					}
				}
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<SystemSetting> findByHQLAndParams(SystemSetting systemSetting) {
		List<SystemSetting> sList = systemSettingDao
				.findByHQLAndParams("from SystemSetting where enable = true","");
		return sList;
	}

	@Override
	public boolean findByDetachedCriteria(String uniqueId) {
		List<SystemSetting> systemSettings = new ArrayList<SystemSetting>();
		systemSettings = systemSettingRedisDao.getCacheList(systemKey);
		boolean flag = true;
		for (SystemSetting systemSetting : systemSettings) {
			if (systemSetting.getUniqueId().equals(uniqueId)) {
				flag = false;
			}
		}
		return flag;
	}
	
}
