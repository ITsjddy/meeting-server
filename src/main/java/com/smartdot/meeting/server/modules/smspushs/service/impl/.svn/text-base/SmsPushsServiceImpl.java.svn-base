package com.smartdot.meeting.server.modules.smspushs.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.smspushs.dao.ISmsPushsDao;
import com.smartdot.meeting.server.modules.smspushs.service.ISmsPushsService;
import com.smartdot.meeting.server.common.entity.SmsPushs;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ISmsPushsService.SERVICE_NAME)
public class SmsPushsServiceImpl implements ISmsPushsService {
	
	@Resource(name = ISmsPushsDao.DAO_NAME)
	private ISmsPushsDao smsPushsDao;

	@Override
	public List<SmsPushs> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(SmsPushs.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return smsPushsDao.findByDetachedCriteria(dc);
	}

	@Override
	public SmsPushs getSmsPushsById(String id) {
		
		return smsPushsDao.findById(id);
	}

	@Audit(module= "SmsPushs管理",action= "SmsPushs信息添加",description= "SmsPushs信息添加说明")
	@Override
	public boolean save(SmsPushs smsPushs) {
		boolean flag = false;
		if (smsPushs != null) {
			smsPushsDao.save(smsPushs);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "SmsPushs管理",action= "SmsPushs多条信息添加",description= "SmsPushs多条信息添加说明")
	@Override
	public boolean saveAll(List<SmsPushs> smsPushsList) {
		boolean flag = false;
		if (smsPushsList != null) {
			smsPushsDao.saveAll(smsPushsList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "SmsPushs管理",action= "SmsPushs信息修改",description= "SmsPushs信息修改说明")
	@Override
	public boolean updateSmsPushs(SmsPushs smsPushs) {
		boolean flag = false;
		if (smsPushs != null) {
			smsPushsDao.update(smsPushs);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SmsPushs smsPushs) {
		boolean flag = false;
		if (smsPushs != null) {
			SmsPushs instance = this.getSmsPushsById(smsPushs.getId());
			smsPushsDao.delete(instance);
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SmsPushs> pageQuery) {
		
		return smsPushsDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "SmsPushs管理",action= "SmsPushs信息删除",description= "SmsPushs信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				SmsPushs smsPushs = smsPushsDao.get(id);
				smsPushs.setEnable(false);
				smsPushsDao.update(smsPushs);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<SmsPushs> findByHQLAndParams(SmsPushs smsPushs) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<SmsPushs> sList = smsPushsDao
				.findByHQLAndParams("from SmsPushs where enable = true","");
		return sList;
	}
	
}
