package com.smartdot.meeting.server.modules.sysprivilege.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.sysprivilege.dao.ISysPrivilegeDao;
import com.smartdot.meeting.server.common.entity.SysPrivilege;

@Repository(value = ISysPrivilegeDao.DAO_NAME)
public class SysPrivilegeDaoImpl extends GenericDaoHibernateSupport<SysPrivilege> implements ISysPrivilegeDao {
	
	@Override
	public Map<String, String> getResources() {
		DetachedCriteria dc =DetachedCriteria.forClass(SysPrivilege.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.ne("url", ""));
		dc.add(Restrictions.isNotNull("url"));
		dc.add(Restrictions.ne("resKey", ""));
		dc.add(Restrictions.isNotNull("resKey"));
		dc.add(Restrictions.or(Restrictions.eq("type", "2"), Restrictions.eq("type", "3")));
		dc.addOrder(Order.asc("level"));
		List<SysPrivilege> priList = findByDetachedCriteria(dc);
		
		Map<String, String> map = new HashMap<String, String>();
		if(null != priList){
			for (SysPrivilege privilege : priList) {
				String url = privilege.getUrl();
				String roleKey = privilege.getResKey();
				if(StringUtils.isNotBlank(url) && StringUtils.isNotBlank(roleKey)){
					String[] urls = url.split(",");
					for (int i = 0; i < urls.length; i++) {
						map.put(urls[i], "ROLE_"+roleKey);
					}
				}
			}
		}
		return map;
	}
	
	@Override
	public List<Map<String, String>> getUserPrivilegesByUserName(String userName) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		if(StringUtils.isBlank(userName)){
			return dataList;
		}
		
		StringBuffer zhBuffer = new StringBuffer();
		zhBuffer.append("select ID id,URL url,CREATE_TIMES createTimes,ROLE_KEY resKey,ENABLE enable,NAME name,UPDATE_TIMES updateTimes,PID parent,LEVEL level,TYPE type");
		zhBuffer.append(" from T_SYS_PRIVILEGE where ENABLE=true ");
		if(!"admin".equals(userName)){
			zhBuffer.append("and ID in ( select distinct(PRI_ID) from T_ROLE_PRIVILEGE where ENABLE=true and ROLE_ID ");
			zhBuffer.append("in (select ROLE_ID from T_SYS_USER where ENABLE=true and USER_NAME = ? ) )");
		}
		zhBuffer.append(" order by LEVEL");
		System.out.println(zhBuffer);
		Session session = getSession();
		Query query = session.createSQLQuery(zhBuffer.toString());
		//设定结果结果集中的每个对象为Map类型   
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		if(!"admin".equals(userName)){
			query.setParameter(0,userName);
		}
		dataList = query.list();
		//释放session
		//session.close();
		
		return dataList;
	}
	
	@Override
	public List<Map<String, String>> getPrivilegesByUserName(String userName,String type,boolean state) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		if(StringUtils.isBlank(userName)){
			return dataList;
		}
		
		StringBuffer zhBuffer = new StringBuffer();
		zhBuffer.append("select ID,URL url,CREATE_TIMES createTimes,ROLE_KEY resKey,ENABLE enable,NAME name,");
		zhBuffer.append("UPDATE_TIMES updateTimes,PID parent,LEVEL level,TYPE type");
		zhBuffer.append(" from T_SYS_PRIVILEGE where ENABLE=true ");
		if(state){
			zhBuffer.append(" and DISPLAY='1' ");
		}
		if(StringUtils.isNotBlank(type)){
			if(!"1".equals(type)){
				zhBuffer.append("and pid is not null ");
			}
			zhBuffer.append("and type = ? ");
		}
		if(!"admin".equals(userName)){
			zhBuffer.append("and ID in ( select distinct(PRI_ID) from T_ROLE_PRIVILEGE where ENABLE=true and ROLE_ID ");
			zhBuffer.append("in (select ROLE_ID from T_SYS_USER where ENABLE=true and USER_NAME = ? ) )");
		}
		zhBuffer.append(" order by LEVEL");
		System.out.println(zhBuffer);
		Session session = getSession();
		Query query = session.createSQLQuery(zhBuffer.toString());
		//设定结果结果集中的每个对象为Map类型   
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		
		if(StringUtils.isNotBlank(type) && !"admin".equals(userName)){
			query.setParameter(0,type);
			query.setParameter(1,userName);
		}else{
			if(StringUtils.isNotBlank(type)){
				query.setParameter(0,type);
			}
			if(!"admin".equals(userName)){
				query.setParameter(0,userName);
			}
		}
		
		dataList = query.list();
		//释放session
		//releaseSession(session);
		
		return dataList;
	}

	@Override
	public List<SysPrivilege> getListPrivileges(List<String> types) {
		
		DetachedCriteria dc =DetachedCriteria.forClass(SysPrivilege.class);
		dc.add(Restrictions.eq("enable", true));
		if(null != types && types.size() > 0){
			dc.add(Restrictions.in("type", types));
		}else{
			dc.add(Restrictions.ne("type", ""));
			dc.add(Restrictions.isNotNull("type"));
		}
		dc.addOrder(Order.asc("level"));
		List<SysPrivilege> priList = findByDetachedCriteria(dc);
		
		return priList;
	}
	
	@Override
	public List<Map<String, Object>> getListMapPrivileges(String types) throws Exception {
		String sql = "select ID, NAME, ifnull(PID,0) as 'PID', TYPE from t_sys_privilege where ENABLE = true ";
		if(StringUtils.isNotBlank(types)){
			sql = sql + " and TYPE in ("+types+") ";
		}
		ResultSet resultSet = executeQuery(sql);
		
		ResultSetMetaData md = resultSet.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();   
        Map rowData = new HashMap();   
        while (resultSet.next()) {   
        	rowData = new HashMap(columnCount);   
        	for (int i = 1; i <= columnCount; i++) {   
        		rowData.put(md.getColumnName(i), resultSet.getObject(i));   
        	}   
        	list.add(rowData);   
        }
		return list;
	}
	
}
