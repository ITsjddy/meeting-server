package com.smartdot.meeting.server.modules.datadictionary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.datadictionary.dao.IDataDictionaryDao;
import com.smartdot.meeting.server.common.entity.DataDictionary;
@Repository(value = IDataDictionaryDao.DAO_NAME)
public class DataDictionaryDaoImpl extends GenericDaoHibernateSupport<DataDictionary> implements IDataDictionaryDao {

	@Override
	public List<DataDictionary> getListDatadictionary(String type) {
		List<DataDictionary> lDatadictionary = new ArrayList<DataDictionary>();
		if(StringUtils.isBlank(type)){
			return lDatadictionary;
		}
		DetachedCriteria dcdata = DetachedCriteria.forClass(DataDictionary.class);
		dcdata.add(Restrictions.eq("enable", true));
		dcdata.add(Restrictions.eq("dDTypeLogo", type));
		dcdata.addOrder(Order.asc("dDNum"));
		lDatadictionary = findByDetachedCriteria(dcdata);
		
		return lDatadictionary;
	}

}
