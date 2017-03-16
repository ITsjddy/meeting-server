package com.smartdot.meeting.server.modules.datadictionary.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.datadictionary.dao.IDataDictionaryDao;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IDataDictionaryService.SERVICE_NAME)
public class DataDictionaryServiceImpl implements IDataDictionaryService {
	
	@Resource(name = IDataDictionaryDao.DAO_NAME)
	private IDataDictionaryDao dataDictionaryDao;

	@Override
	public List<DataDictionary> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return dataDictionaryDao.findByDetachedCriteria(dc);
	}

	@Override
	public DataDictionary getDataDictionaryById(String id) {
		return dataDictionaryDao.findById(id);
	}

	@Audit(module= "DataDictionary管理",action= "DataDictionary信息添加",description= "DataDictionary信息添加说明")
	@Override
	public boolean save(DataDictionary dataDictionary) {
		boolean flag = false;
		if (dataDictionary != null) {
			dataDictionaryDao.save(dataDictionary);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "DataDictionary管理",action= "DataDictionary多条信息添加",description= "DataDictionary多条信息添加说明")
	@Override
	public boolean saveAll(List<DataDictionary> dataDictionaryList) {
		boolean flag = false;
		if (dataDictionaryList != null) {
			dataDictionaryDao.saveAll(dataDictionaryList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "DataDictionary管理",action= "DataDictionary信息修改",description= "DataDictionary信息修改说明")
	@Override
	public boolean updateDataDictionary(DataDictionary dataDictionary) {
		boolean flag = false;
		if (dataDictionary != null) {
			dataDictionaryDao.update(dataDictionary);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(DataDictionary dataDictionary) {
		boolean flag = false;
		if (dataDictionary != null) {
			DataDictionary instance = this.getDataDictionaryById(dataDictionary.getId());
			dataDictionaryDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "DataDictionary管理",action= "DataDictionary多选信息删除",description= "DataDictionary多选信息删除说明")
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<DataDictionary> pageQuery) {
		
		return dataDictionaryDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "DataDictionary管理",action= "DataDictionary信息删除",description= "DataDictionary信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				DataDictionary dataDictionary = dataDictionaryDao.get(id);
				dataDictionary.setEnable(false);
				dataDictionaryDao.update(dataDictionary);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<DataDictionary> findByHQLAndParams(DataDictionary dataDictionary) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<DataDictionary> sList = dataDictionaryDao
				.findByHQLAndParams("from DataDictionary where enable = true","");
		return sList;
	}

	@Override
	public List<DataDictionary> findByDetachedCriteria(
			DetachedCriteria detachedCriteria) {
		return dataDictionaryDao.findByDetachedCriteria(detachedCriteria);
	}

	@Override
	public boolean deleteByList(List<DataDictionary> dataDictionaries) {
		boolean flag = false;
		try {
			for (DataDictionary dataDictionary : dataDictionaries) {
				dataDictionary.setEnable(false);
				dataDictionaryDao.update(dataDictionary);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}


	@Override
	public List<DataDictionary> findAllByTypeLogo(String typeLogo, String languageType) {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		if (StringUtils.isNotBlank(languageType)) {
			dc.add(Restrictions.eq("dDLanguageType", languageType));
		}
		if (StringUtils.isNotBlank(typeLogo)) {
			dc.add(Restrictions.eq("dDTypeLogo", typeLogo));
		}
		return dataDictionaryDao.findByDetachedCriteria(dc);
	}

	@Override
	public DataDictionary findByTypeLogo(String typeLogo, String languageType) {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDictionary.class);
		dc.add(Restrictions.eq("enable", true));
		if (StringUtils.isNotBlank(typeLogo)) {
			dc.add(Restrictions.eq("dDLogo", typeLogo));
		}
		if (StringUtils.isNotBlank(languageType)) {
			dc.add(Restrictions.eq("dDLanguageType", languageType));
		}
		List<DataDictionary> dataDictionaries = new ArrayList<DataDictionary>();
		dataDictionaries = dataDictionaryDao.findByDetachedCriteria(dc);
		if (dataDictionaries.size() > 0) {
			return dataDictionaries.get(0);
		}
		return null;
	}
	
}
