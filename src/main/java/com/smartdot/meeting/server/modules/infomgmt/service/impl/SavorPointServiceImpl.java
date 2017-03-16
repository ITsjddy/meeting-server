package com.smartdot.meeting.server.modules.infomgmt.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.SavorPoint;
import com.smartdot.meeting.server.modules.infomgmt.dao.ISavorPointDao;
import com.smartdot.meeting.server.modules.infomgmt.service.ISavorPointService;

@Service(value = ISavorPointService.SERVICE_NAME)
public class SavorPointServiceImpl implements ISavorPointService {
	
	@Resource(name = ISavorPointDao.DAO_NAME)
	private ISavorPointDao savorPointDao;

	@Override
	public List<SavorPoint> findAll() {
		return savorPointDao.findAll();
	}

	@Override
	public SavorPoint getSavorPointById(String id) {
		
		return savorPointDao.findById(id);
	}

	@Override
	public boolean save(SavorPoint savorPoint) {
		boolean flag = false;
		if (savorPoint != null) { 
			savorPointDao.save(savorPoint);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<SavorPoint> savorPointList) {
		boolean flag = false;
		if (savorPointList != null) {
			savorPointDao.saveAll(savorPointList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateSavorPoint(SavorPoint savorPoint) {
		boolean flag = false;
		if (savorPoint != null) {
			savorPointDao.update(savorPoint);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SavorPoint savorPoint) {
		boolean flag = false;
		if (savorPoint != null) {
			SavorPoint instance = this.getSavorPointById(savorPoint.getId());
			savorPointDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("spUniteId", id);
				this.deleteAllsavorPoint(this.findAllByProperties(map));
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> findSavorPointByPage(Page<SavorPoint> page, Map<String, Object> searchMap) {
		LinkedHashMap<String, String> orderByMap = new LinkedHashMap<String, String>();
		orderByMap.put("createTimes", "desc");
		Map<String, Object> resultMap = savorPointDao.pageQuery(searchMap, page, orderByMap, false);
		return resultMap;
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				savorPointDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<SavorPoint> findByHQLAndParams(SavorPoint savorPoint) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<SavorPoint> sList = savorPointDao
				.findByHQLAndParams(
						"from SavorPoint where 1=1",
						"");
		return sList;
	}

	@Override
	public List<SavorPoint> findAllByProperties(Map<String, Object> map) {
		return savorPointDao.findAllByProperties(map);
	}

	@Override
	public boolean deleteAllsavorPoint(List<SavorPoint> savorPoints) {
		try {
			savorPointDao.deleteAll(savorPoints);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<SavorPoint> findByDetachedCriteria(
			DetachedCriteria detachedCriteria) {
		return savorPointDao.findByDetachedCriteria(detachedCriteria);
	}

}
