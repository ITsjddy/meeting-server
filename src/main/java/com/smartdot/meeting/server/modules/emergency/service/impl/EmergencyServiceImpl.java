package com.smartdot.meeting.server.modules.emergency.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.Emergency;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.emergency.dao.IEmergencyDao;
import com.smartdot.meeting.server.modules.emergency.service.IEmergencyService;

@Service(value = IEmergencyService.SERVICE_NAME)
public class EmergencyServiceImpl implements IEmergencyService {
	@Resource(name = IEmergencyDao.DAO_NAME)
	private IEmergencyDao emergencyDao;

	@Override
	public Map<String, Object> pageList(Page<Emergency> page, Map<String,Object> searchMap) {
		LinkedHashMap<String,String> orderByMap = new LinkedHashMap<String,String>();
		orderByMap.put("createTimes", "desc");
		Map<String, Object> pageData = emergencyDao.pageQuery(searchMap,page,orderByMap,false);
		return pageData;
	}
	
	@Override
	public List<Emergency> findAll() {
		return emergencyDao.findAll();
	}

	@Override
	public Emergency getEmergencyById(String id) {
		return emergencyDao.findById(id);
	}

	@Override
	public boolean save(Emergency emergency) {
		boolean flag = false;
		if (emergency != null) {
			emergencyDao.save(emergency);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<Emergency> emergencyList) {
		boolean flag = false;
		if (emergencyList != null) {
			emergencyDao.saveAll(emergencyList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateEmergency(Emergency emergency) {
		boolean flag = false;
		if (emergency != null) {
			emergencyDao.update(emergency);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(Emergency emergency) {
		boolean flag = false;
		if (emergency != null) {
			Emergency instance = this.getEmergencyById(emergency.getId());
			emergencyDao.delete(instance);
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
	public Page<Emergency> findEmergencyByPage(Emergency emergency,Page<Emergency> pageQuery) {
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				emergencyDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<Emergency> findByHQLAndParams(Emergency emergency) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<Emergency> sList = emergencyDao
				.findByHQLAndParams(
						"from Emergency where 1=1",
						"");
		return sList;
	}
	
}
