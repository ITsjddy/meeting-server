package com.smartdot.meeting.server.modules.jpushentiy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.smartdot.meeting.server.modules.jpushentiy.dao.IJpushEntiyDao;

import com.smartdot.meeting.server.modules.jpushentiy.service.IJpushEntiyService;
import com.smartdot.meeting.server.common.entity.JpushEntiy;

@Service(value = IJpushEntiyService.SERVICE_NAME)
public class JpushEntiyServiceImpl implements IJpushEntiyService {
	@Resource(name = IJpushEntiyDao.DAO_NAME)
	private IJpushEntiyDao jpushEntiyDao;

	@Override
	public List<JpushEntiy> findAll() {
		return jpushEntiyDao.findAll();
	}

	@Override
	public JpushEntiy getJpushEntiyById(String id) {
		
		return jpushEntiyDao.findById(id);
	}

	@Override
	public boolean save(JpushEntiy jpushEntiy) {
		boolean flag = false;
		if (jpushEntiy != null) {
			jpushEntiyDao.save(jpushEntiy);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<JpushEntiy> jpushEntiyList) {
		boolean flag = false;
		if (jpushEntiyList != null) {
			jpushEntiyDao.saveAll(jpushEntiyList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateJpushEntiy(JpushEntiy jpushEntiy) {
		boolean flag = false;
		if (jpushEntiy != null) {
			jpushEntiyDao.update(jpushEntiy);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(JpushEntiy jpushEntiy) {
		boolean flag = false;
		if (jpushEntiy != null) {
			JpushEntiy instance = this.getJpushEntiyById(jpushEntiy.getId());
			jpushEntiyDao.delete(instance);
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
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				jpushEntiyDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<JpushEntiy> findByHQLAndParams(JpushEntiy jpushEntiy) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<JpushEntiy> sList = jpushEntiyDao
				.findByHQLAndParams(
						"from JpushEntiy where 1=1",
						"");
		return sList;
	}
	
}
