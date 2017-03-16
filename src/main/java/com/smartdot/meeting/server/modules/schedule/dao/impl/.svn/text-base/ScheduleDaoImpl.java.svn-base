package com.smartdot.meeting.server.modules.schedule.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.Schedule;
import com.smartdot.meeting.server.modules.schedule.dao.IScheduleDao;


@Service(value = IScheduleDao.DAO_NAME)
public class ScheduleDaoImpl extends GenericDaoHibernateSupport<Schedule> implements IScheduleDao {

	@Override
	public List<Map<String, Object>> conHallWhetherConflict(String conHallId, String startDate, String endDate) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT id,name FROM t_schedule WHERE `ENABLE`=1 ");
		sqlBuffer.append(" AND ( (SCHEDULE_START_DATE >= DATE_FORMAT( ?,'%Y-%m-%d %T') AND DATE_FORMAT( ?,'%Y-%m-%d %T') >= SCHEDULE_START_DATE) OR (SCHEDULE_END_DATE >= DATE_FORMAT(?,'%Y-%m-%d %T') AND DATE_FORMAT(?,'%Y-%m-%d %T') >= SCHEDULE_END_DATE)) ");
		sqlBuffer.append(" AND CON_HALL_ID =? AND `LANGUAGE`='zh' ");
		System.out.println(sqlBuffer);
		Session session = getSession();
		Query query = session.createSQLQuery(sqlBuffer.toString());
		//设定结果结果集中的每个对象为Map类型   
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.setParameter(0,startDate);
		query.setParameter(1,endDate);
		query.setParameter(2,startDate);
		query.setParameter(3,endDate);
		query.setParameter(4,conHallId);
		dataList = query.list();
		//释放session
		//releaseSession(session);
		return dataList;
	}

	@Override
	public List<Map<String, Object>> memberWhetherConflict(String[] ids, String startDate, String endDate) {
		String idsStr = "";
		if (ids!=null&&ids.length>0) {
			for (int i = 0; i < ids.length; i++) {
				if (i==0) {
					idsStr+=(ids[i]);
				}else{
					idsStr+=(","+ids[i]);
				}
			}
		}
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT id,name FROM t_schedule WHERE `ENABLE`=1 ");
		sqlBuffer.append(" AND ( (SCHEDULE_START_DATE >= DATE_FORMAT( ?,'%Y-%m-%d %T') AND DATE_FORMAT( ?,'%Y-%m-%d %T') >= SCHEDULE_START_DATE) OR (SCHEDULE_END_DATE >= DATE_FORMAT(?,'%Y-%m-%d %T') AND DATE_FORMAT(?,'%Y-%m-%d %T') >= SCHEDULE_END_DATE)) ");
		sqlBuffer.append(" AND `LANGUAGE`='zh' ");
		sqlBuffer.append(" AND UNIQUE_CODE in( ");
		sqlBuffer.append(" SELECT MEETING_ID FROM t_meeting_member WHERE ENABLE=1 ");
		if (StringUtils.isNotBlank(idsStr)) {
			//sqlBuffer.append(" AND MEMBER_UNIQUE in ( '2c948f895a36a781015a36b5ba41001b','2c948f895a370da1015a3710a8ca0008','2c948f995a408cd6015a40df64a50003','8a9296615a5fdf4b015a5ff583ab0011','2c948f9b5a83b750015a83c92ed50005','8a92963b5aa6bb84015aa6bd56070001','2c948f915aa88991015aa89948270002','2c948f915aa8ab1a015aa8aed0ab0001','2c948f845aabbdd8015aabd72a400015','8a9296ba5aabd119015aabe6ce900003','2c948f875ab2451a015ab251001a0001' )  ");
			sqlBuffer.append(" AND INSTR(CONCAT(',',?,','),CONCAT(',',MEMBER_UNIQUE,','))>0  ");
		}
		sqlBuffer.append("  AND SOURCE='schedule')  ");
		System.out.println(sqlBuffer);
		Session session = getSession();
		Query query = session.createSQLQuery(sqlBuffer.toString());
		//设定结果结果集中的每个对象为Map类型   
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		query.setParameter(0,startDate);
		query.setParameter(1,endDate);
		query.setParameter(2,startDate);
		query.setParameter(3,endDate);
		if (StringUtils.isNotBlank(idsStr)) {
			query.setParameter(4,idsStr);
		}
		dataList = query.list();
		//释放session
		//releaseSession(session);
		return dataList;
	}
	
}
