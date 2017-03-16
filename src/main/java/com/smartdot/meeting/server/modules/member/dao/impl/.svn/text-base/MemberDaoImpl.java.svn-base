package com.smartdot.meeting.server.modules.member.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.modules.member.dao.IMemberDao;

@Repository(value = IMemberDao.DAO_NAME)
public class MemberDaoImpl extends GenericDaoHibernateSupport<Member> implements IMemberDao {
	
	public List<Member> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return (List<Member>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	@Override
	public boolean delete(String id) throws Exception {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			ResultSet resultSet = executeQuery("select id from st_member_all where member = '"+id+"' ");
			resultSet.last();  
			if(null == resultSet || resultSet.getRow() < 2){
				Member member = get(id);
				member.setEnable(false);
				update(member);
			}
			flag = true;
		}
		return flag;
	}

}
