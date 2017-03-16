package com.smartdot.meeting.server.modules.servicePersonnel.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.member.dao.IMemberDao;
import com.smartdot.meeting.server.modules.servicePersonnel.dao.IServicePersonnelDao;
import com.smartdot.meeting.server.modules.servicePersonnel.service.IServicePersonnelService;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IServicePersonnelService.SERVICE_NAME)
public class ServicePersonnelServiceImpl implements IServicePersonnelService {
	
	@Resource(name = IMemberDao.DAO_NAME)
	private IMemberDao memberDao;
	@Resource(name = IServicePersonnelDao.DAO_NAME)
	private IServicePersonnelDao servicePersonnelDao;
	
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<ServicePersonnel> page) {

		return servicePersonnelDao.pagedQuery(detachedCriteria, page);
	}
	
	@Override
	public ServicePersonnel getServicePersonnelById(String id) {

		return servicePersonnelDao.get(id);
	}
	
	@Audit(module= "app用户管理",action= "服务人员添加",description= "/服务人员添加说明")
	@Override
	public boolean save(ServicePersonnel instance) {
		boolean result = false;
		if(null != instance){
			servicePersonnelDao.save(instance);
			result = true;
		}
		return result;
	}
	
	@Audit(module= "app用户管理",action= "服务人员修改",description= "/服务人员修改说明")
	@Override
	public boolean update(ServicePersonnel instance) {
		boolean result = false;
		if(null != instance){
			servicePersonnelDao.update(instance);
			result = true;
		}
		return result;
	}
	
	@Audit(module= "app用户管理",action= "服务人员删除",description= "/服务人员删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				ServicePersonnel servicePersonnel = servicePersonnelDao.get(id);
				servicePersonnel.setEnable(false);
				servicePersonnelDao.update(servicePersonnel);
				memberDao.delete(servicePersonnel.getMember().getMemberId());
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
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
	public List<ServicePersonnel> findByDetachedCriteria(
			DetachedCriteria detachedCriteria) {
		return servicePersonnelDao.findByDetachedCriteria(detachedCriteria);
	}
	
	
}
