package com.smartdot.meeting.server.modules.guest.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.guest.dao.IGuestAndServicePersonnelDao;
import com.smartdot.meeting.server.common.entity.GuestAndServicePersonnel;
import com.smartdot.meeting.server.common.entity.Member;

@Repository(value = IGuestAndServicePersonnelDao.DAO_NAME)
public class GuestAndServicePsersonnelDaoImpl extends GenericDaoHibernateSupport<GuestAndServicePersonnel> implements IGuestAndServicePersonnelDao {
	
	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			GuestAndServicePersonnel guestAndServicePersonnel = get(id);
			guestAndServicePersonnel.setEnable(false);
			update(guestAndServicePersonnel);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public List<Map<String, Object>> queryALLServicePersonnel() throws Exception {
		
		ResultSet resultSet = executeQuery("select * from st_depart_servicepsersonnel ");
		
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
        System.out.println("list:" + list.toString());   
		return list;
	}

	@Override
	public List<Map<String, Object>> findServicePersonnelForName(String name)
			throws Exception {
		String sql = "select * from st_depart_servicepsersonnel ";
					 
		if (StringUtils.isNotBlank(name)) {
			sql = " select * from st_depart_servicepsersonnel where id in( "+
					" select pid from st_depart_servicepsersonnel where name = '" + name +"' )" +
					 " UNION ALL " + "( select * from st_depart_servicepsersonnel where name = '" + name +"' )";
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
