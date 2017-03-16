package com.smartdot.meeting.server.modules.jpushentiy.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.jpushentiy.dao.IJpushEntiyDao;
import com.smartdot.meeting.server.common.entity.JpushEntiy;
@Repository(value = IJpushEntiyDao.DAO_NAME)
public class JpushEntiyDaoImpl extends GenericDaoHibernateSupport<JpushEntiy> implements IJpushEntiyDao {

}
