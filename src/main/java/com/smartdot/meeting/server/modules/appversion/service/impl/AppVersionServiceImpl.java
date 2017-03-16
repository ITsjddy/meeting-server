package com.smartdot.meeting.server.modules.appversion.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.AppVersion;
import com.smartdot.meeting.server.common.log.Audit;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.appversion.dao.IAppVersionDao;
import com.smartdot.meeting.server.modules.appversion.service.IAppVersionService;


@Service(value = IAppVersionService.SERVICE_NAME)
public class AppVersionServiceImpl implements IAppVersionService {
	
	@Resource(name = IAppVersionDao.DAO_NAME)
	private IAppVersionDao appVersionDao;

	@Override
	public List<AppVersion> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(AppVersion.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return appVersionDao.findByDetachedCriteria(dc);
	}

	@Override
	public AppVersion getAppVersionById(String id) {
		
		return appVersionDao.findById(id);
	}

	@Audit(module= "AppVersion管理",action= "AppVersion信息添加",description= "AppVersion信息添加说明")
	@Override
	public boolean save(AppVersion appVersion) {
		boolean flag = false;
		if (appVersion != null) {
			appVersionDao.save(appVersion);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "AppVersion管理",action= "AppVersion多条信息添加",description= "AppVersion多条信息添加说明")
	@Override
	public boolean saveAll(List<AppVersion> appVersionList) {
		boolean flag = false;
		if (appVersionList != null) {
			appVersionDao.saveAll(appVersionList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "AppVersion管理",action= "AppVersion信息修改",description= "AppVersion信息修改说明")
	@Override
	public boolean updateAppVersion(AppVersion appVersion) {
		boolean flag = false;
		if (appVersion != null) {
			appVersionDao.update(appVersion);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(AppVersion appVersion) {
		boolean flag = false;
		if (appVersion != null) {
			AppVersion instance = this.getAppVersionById(appVersion.getId());
			appVersionDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<AppVersion> pageQuery) {
		
		return appVersionDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "AppVersion管理",action= "AppVersion信息删除",description= "AppVersion信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				appVersionDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<AppVersion> findByHQLAndParams(AppVersion appVersion) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<AppVersion> sList = appVersionDao
				.findByHQLAndParams("from AppVersion where enable = true","");
		return sList;
	}

	@Override
	public Map<String, Object> pageList(Page<AppVersion> page,
			Map<String, Object> searchMap) {
		LinkedHashMap<String,String> orderByMap = new LinkedHashMap<String,String>();
		orderByMap.put("createTimes", "desc");
		Map<String, Object> pageData = appVersionDao.pageQuery(searchMap,page,orderByMap,false);
		return pageData;
	}

	@Override
	public List<AppVersion> getLastVersion(int type) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("type", type);
		LinkedHashMap<String,String> orderByMap = new LinkedHashMap<String,String>();
		orderByMap.put("versionNum", "desc");
		return appVersionDao.findAllByProperties(map, orderByMap);
	}
	
}
