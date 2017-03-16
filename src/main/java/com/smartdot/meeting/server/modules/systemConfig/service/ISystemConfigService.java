package com.smartdot.meeting.server.modules.systemConfig.service;

import java.util.List;
import com.smartdot.meeting.server.common.entity.SystemConfig;


public interface ISystemConfigService {
	
	public static final String SERVICE_NAME = "systemConfigService";
	
	public List<SystemConfig> redisGetAll();
	
	public boolean redisSave(final SystemConfig systemConfig);
	
	public SystemConfig redisGet(final String systemConfigId);
	
	public void redisDelete(String id);
	
}
