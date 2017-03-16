package com.smartdot.meeting.server.modules.datadictionarytype.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.datadictionarytype.dao.IDataDictionaryTypeDao;
import com.smartdot.meeting.server.modules.datadictionarytype.service.IDataDictionaryTypeService;
import com.smartdot.meeting.server.common.entity.DataDictionaryType;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IDataDictionaryTypeService.SERVICE_NAME)
public class DataDictionaryTypeServiceImpl implements IDataDictionaryTypeService {
	
	@Resource(name = IDataDictionaryTypeDao.DAO_NAME)
	private IDataDictionaryTypeDao dataDictionaryTypeDao;

	@Override
	public List<DataDictionaryType> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionaryType.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return dataDictionaryTypeDao.findByDetachedCriteria(dc);
	}

	@Override
	public DataDictionaryType getDataDictionaryTypeById(String id) {
		
		return dataDictionaryTypeDao.findById(id);
	}

	@Audit(module= "DataDictionaryType管理",action= "DataDictionaryType信息添加",description= "DataDictionaryType信息添加说明")
	@Override
	public boolean save(DataDictionaryType dataDictionaryType) {
		boolean flag = false;
		if (dataDictionaryType != null) {
			dataDictionaryTypeDao.save(dataDictionaryType);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "DataDictionaryType管理",action= "DataDictionaryType多条信息添加",description= "DataDictionaryType多条信息添加说明")
	@Override
	public boolean saveAll(List<DataDictionaryType> dataDictionaryTypeList) {
		boolean flag = false;
		if (dataDictionaryTypeList != null) {
			dataDictionaryTypeDao.saveAll(dataDictionaryTypeList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "DataDictionaryType管理",action= "DataDictionaryType信息修改",description= "DataDictionaryType信息修改说明")
	@Override
	public boolean updateDataDictionaryType(DataDictionaryType dataDictionaryType) {
		boolean flag = false;
		if (dataDictionaryType != null) {
			dataDictionaryTypeDao.update(dataDictionaryType);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(DataDictionaryType dataDictionaryType) {
		boolean flag = false;
		if (dataDictionaryType != null) {
			DataDictionaryType instance = this.getDataDictionaryTypeById(dataDictionaryType.getId());
			dataDictionaryTypeDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "DataDictionaryType管理",action= "DataDictionaryType多选信息删除",description= "DataDictionaryType多选信息删除说明")
	@Override
	public boolean deleteAll(String[] ids) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<DataDictionaryType> pageQuery) {
		
		return dataDictionaryTypeDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "DataDictionaryType管理",action= "DataDictionaryType信息删除",description= "DataDictionaryType信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				DataDictionaryType dataDictionaryType = dataDictionaryTypeDao.get(id);
				dataDictionaryType.setEnable(false);
				dataDictionaryTypeDao.update(dataDictionaryType);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<DataDictionaryType> findByHQLAndParams(DataDictionaryType dataDictionaryType) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<DataDictionaryType> sList = dataDictionaryTypeDao
				.findByHQLAndParams("from DataDictionaryType where enable = true","");
		return sList;
	}

	@Override
	public List<DataDictionaryType> findByDetachedCriteria(
			DetachedCriteria detachedCriteria) {
		return dataDictionaryTypeDao.findByDetachedCriteria(detachedCriteria);
	}
	
}
