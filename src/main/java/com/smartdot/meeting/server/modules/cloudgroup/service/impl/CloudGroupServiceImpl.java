package com.smartdot.meeting.server.modules.cloudgroup.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.cloudgroup.dao.ICloudGroupDao;
import com.smartdot.meeting.server.modules.cloudgroup.service.ICloudGroupService;
import com.smartdot.meeting.server.common.entity.CloudGroup;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ICloudGroupService.SERVICE_NAME)
public class CloudGroupServiceImpl implements ICloudGroupService {
	
	@Resource(name = ICloudGroupDao.DAO_NAME)
	private ICloudGroupDao cloudGroupDao;

	@Override
	public List<CloudGroup> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(CloudGroup.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return cloudGroupDao.findByDetachedCriteria(dc);
	}

	@Override
	public CloudGroup getCloudGroupById(String id) {
		
		return cloudGroupDao.findById(id);
	}

	@Audit(module= "CloudGroup管理",action= "CloudGroup信息添加",description= "CloudGroup信息添加说明")
	@Override
	public boolean save(CloudGroup cloudGroup) {
		boolean flag = false;
		if (cloudGroup != null) {
			cloudGroupDao.save(cloudGroup);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "CloudGroup管理",action= "CloudGroup多条信息添加",description= "CloudGroup多条信息添加说明")
	@Override
	public boolean saveAll(List<CloudGroup> cloudGroupList) {
		boolean flag = false;
		if (cloudGroupList != null) {
			cloudGroupDao.saveAll(cloudGroupList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "CloudGroup管理",action= "CloudGroup信息修改",description= "CloudGroup信息修改说明")
	@Override
	public boolean updateCloudGroup(CloudGroup cloudGroup) {
		boolean flag = false;
		if (cloudGroup != null) {
			cloudGroupDao.update(cloudGroup);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(CloudGroup cloudGroup) {
		boolean flag = false;
		if (cloudGroup != null) {
			CloudGroup instance = this.getCloudGroupById(cloudGroup.getId());
			cloudGroupDao.delete(instance);
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<CloudGroup> pageQuery) {
		
		return cloudGroupDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "CloudGroup管理",action= "CloudGroup信息删除",description= "CloudGroup信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				CloudGroup cloudGroup = cloudGroupDao.get(id);
				cloudGroup.setEnable(false);
				cloudGroupDao.update(cloudGroup);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<CloudGroup> findByHQLAndParams(CloudGroup cloudGroup) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<CloudGroup> sList = cloudGroupDao
				.findByHQLAndParams("from CloudGroup where enable = true","");
		return sList;
	}

	@Override
	public List<CloudGroup> findByDetachedCriteria(
			DetachedCriteria detachedCriteria) {
		return cloudGroupDao.findByDetachedCriteria(detachedCriteria);
	}
	
}
