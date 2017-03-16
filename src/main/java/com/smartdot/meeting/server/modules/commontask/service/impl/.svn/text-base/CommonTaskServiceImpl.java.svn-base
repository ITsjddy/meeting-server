package com.smartdot.meeting.server.modules.commontask.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.entity.CommonTask;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.commontask.dao.ICommonTaskDao;
import com.smartdot.meeting.server.modules.commontask.service.ICommonTaskService;

@Service(value = ICommonTaskService.SERVICE_NAME)
public class CommonTaskServiceImpl implements ICommonTaskService {
	@Resource(name = ICommonTaskDao.DAO_NAME)
	private ICommonTaskDao commonTaskDao;

	@Override
	public List<CommonTask> findAll() {
		return commonTaskDao.findAll();
	}

	@Override
	public CommonTask getCommonTaskById(String id) {
		
		return commonTaskDao.findById(id);
	}

	@Override
	public boolean save(CommonTask commonTask) {
		boolean flag = false;
		if (commonTask != null) {
			commonTaskDao.save(commonTask);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<CommonTask> commonTaskList) {
		boolean flag = false;
		if (commonTaskList != null) {
			commonTaskDao.saveAll(commonTaskList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateCommonTask(CommonTask commonTask) {
		boolean flag = false;
		if (commonTask != null) {
			commonTaskDao.update(commonTask);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(CommonTask commonTask) {
		boolean flag = false;
		if (commonTask != null) {
			CommonTask instance = this.getCommonTaskById(commonTask.getId());
			commonTaskDao.delete(instance);
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
	public Page<CommonTask> findCommonTaskByPage(CommonTask commonTask,Page<CommonTask> pageQuery) {
		Page<CommonTask> page = new Page<CommonTask>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from CommonTask obj where 1=1 ");
		Object[] params = paramList.toArray();
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				commonTaskDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<CommonTask> findByHQLAndParams(CommonTask commonTask) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<CommonTask> sList = commonTaskDao
				.findByHQLAndParams(
						"from CommonTask where 1=1",
						"");
		return sList;
	}

	@Override
	public Map<String, Object> pageList(Page<CommonTask> page, Map<String, Object> searchMap) {
		LinkedHashMap<String,String> orderByMap = new LinkedHashMap<String,String>();
		orderByMap.put("createTimes", "desc");
		Map<String, Object> pageData = commonTaskDao.pageQuery(searchMap,page,orderByMap,false);
		return pageData;
	}

	@Override
	public List<CommonTask> findAllByProperty(String propertyName,
			Object propertyValue) {
		return commonTaskDao.findAllByProperty(propertyName, propertyValue);
	}
	
}
