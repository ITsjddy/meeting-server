package com.smartdot.meeting.server.modules.systemConfig.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.entity.SystemConfig;
import com.smartdot.meeting.server.modules.systemConfig.dao.ISystemConfigDao;
import com.smartdot.meeting.server.modules.systemConfig.dao.ISystemConfigRedisDao;
import com.smartdot.meeting.server.modules.systemConfig.service.ISystemConfigService;


@Service(value = ISystemConfigService.SERVICE_NAME)
public class SystemConfigServiceImpl implements ISystemConfigService {
	
	private static Logger logger = Logger.getLogger(SystemConfigServiceImpl.class);
	
	@Resource(name = ISystemConfigDao.DAO_NAME)
	private ISystemConfigDao systemConfigDao;
	
	@Resource(name = ISystemConfigRedisDao.DAO_NAME)
	private ISystemConfigRedisDao systemConfigRedisDao;
	
	
	public List<SystemConfig> redisGetAll() {
		
		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String systemKey = bundle.getString("system_config_key");
		
		List<SystemConfig> lsys = new ArrayList<SystemConfig>();
		try {
			lsys = systemConfigRedisDao.getCacheList(systemKey);
			if(null == lsys || lsys.size() == 0){
				//为空则先去mysql数据库中取在存到redis库中
				lsys = systemConfigDao.getAllList();
				systemConfigRedisDao.setCacheList(systemKey,lsys);
			}
		} catch (Exception e) {
			logger.error("redis取值失败，redis连接失败错误信息： "+e.getMessage());
		}
		return lsys;
	}

	public SystemConfig redisGet(String systemConfigId) {
		
		if(StringUtils.isBlank(systemConfigId)){
			return new SystemConfig();
		}
		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String systemKey = bundle.getString("system_config_key");
		
		List<SystemConfig> lsys = new ArrayList<SystemConfig>();
		try {
			lsys = systemConfigRedisDao.getCacheList(systemKey);
			if(null == lsys || lsys.size() == 0){
				lsys = systemConfigDao.getAllList();
			}
			
			for (SystemConfig systemConfig : lsys) {
				String id = systemConfig.getId();
				if(systemConfigId.equals(id)){
					return systemConfig;
				}
			}
		} catch (Exception e) {
			logger.error("redis取值失败，redis连接失败错误信息： "+e.getMessage());
		}
		
		return new SystemConfig();
	}
	
	public boolean redisSave(SystemConfig systemConfig) {
		
		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String systemKey = bundle.getString("system_config_key");
		
		//修改
		if(null != systemConfig && StringUtils.isNotBlank(systemConfig.getId())){
			//
			List<SystemConfig> lsys = systemConfigRedisDao.getCacheList(systemKey);
			if(null != lsys && lsys.size() > 0){
				for (int i = 0; i < lsys.size(); i++) {
					String systemConfigId = lsys.get(i).getId();
					if(systemConfigId.equals(systemConfig.getId())){
						//先移除
						systemConfigRedisDao.deleteCacheListT(systemKey, i, lsys.get(i));
						continue;
					}
				}
			}
		}
		//先添加到数据库
		systemConfigDao.save(systemConfig);
		//
		systemConfigRedisDao.setCacheListT(systemKey,systemConfig);
		return true;
	}
	
	public void redisDelete(String id) {
		
		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String systemKey = bundle.getString("system_config_key");
		
		//删除
		if(StringUtils.isNotBlank(id)){
			//
			List<SystemConfig> lsys = systemConfigRedisDao.getCacheList(systemKey);
			if(null != lsys && lsys.size() > 0){
				for (int i = 0; i < lsys.size(); i++) {
					String systemConfigId = lsys.get(i).getId();
					if(systemConfigId.equals(id)){
						//先移除
						systemConfigRedisDao.deleteCacheListT(systemKey, i, lsys.get(i));
						
						SystemConfig systemConfig = systemConfigDao.get(id);
						systemConfig.setEnable(false);
						//先添加到数据库
						systemConfigDao.save(systemConfig);
						break;
					}
				}
			}
		}
	}
	
	
}
