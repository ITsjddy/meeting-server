package com.smartdot.meeting.server.modules.groupadministrator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;


import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.groupadministrator.dao.IGroupAdministratorDao;

import com.smartdot.meeting.server.modules.groupadministrator.service.IGroupAdministratorService;
import com.smartdot.meeting.server.common.entity.GroupAdministrator;
import com.smartdot.meeting.server.common.entity.Guest;

@Service(value = IGroupAdministratorService.SERVICE_NAME)
public class GroupAdministratorServiceImpl implements IGroupAdministratorService {
	@Resource(name = IGroupAdministratorDao.DAO_NAME)
	private IGroupAdministratorDao groupAdministratorDao;

	@Override
	public List<GroupAdministrator> findAll() {
		return groupAdministratorDao.findAll();
	}

	@Override
	public GroupAdministrator getGroupAdministratorById(String id) {
		
		return groupAdministratorDao.findById(id);
	}
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<GroupAdministrator> page) {

		return groupAdministratorDao.pagedQuery(detachedCriteria, page);
	}
	
	@Override
	public boolean save(GroupAdministrator groupAdministrator) {
		boolean flag = false;
		if (groupAdministrator != null) {
			groupAdministratorDao.save(groupAdministrator);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<GroupAdministrator> groupAdministratorList) {
		boolean flag = false;
		if (groupAdministratorList != null) {
			groupAdministratorDao.saveAll(groupAdministratorList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateGroupAdministrator(GroupAdministrator groupAdministrator) {
		boolean flag = false;
		if (groupAdministrator != null) {
			groupAdministratorDao.update(groupAdministrator);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(GroupAdministrator groupAdministrator) {
		boolean flag = false;
		if (groupAdministrator != null) {
			GroupAdministrator instance = this.getGroupAdministratorById(groupAdministrator.getId());
			groupAdministratorDao.delete(instance);
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
	public Page<GroupAdministrator> findGroupAdministratorByPage(GroupAdministrator groupAdministrator,Page<GroupAdministrator> pageQuery) {
		Page<GroupAdministrator> page = new Page<GroupAdministrator>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from GroupAdministrator obj where 1=1 ");
		Object[] params = paramList.toArray();
		return (Page<GroupAdministrator>) groupAdministratorDao.pageQueryByHql(page,
		"select count(obj) " + hql.toString(), hql.toString(), params,
		params);
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				groupAdministratorDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<GroupAdministrator> findByHQLAndParams(GroupAdministrator groupAdministrator) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<GroupAdministrator> sList = groupAdministratorDao
				.findByHQLAndParams(
						"from GroupAdministrator where 1=1",
						"");
		return sList;
	}
	
}
