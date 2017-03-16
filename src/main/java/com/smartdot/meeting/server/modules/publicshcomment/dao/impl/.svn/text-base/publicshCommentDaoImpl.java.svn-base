package com.smartdot.meeting.server.modules.publicshcomment.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.publicshcomment.dao.IpublicshCommentDao;
import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.PublicshComment;
@Repository(value = IpublicshCommentDao.DAO_NAME)
public class publicshCommentDaoImpl extends GenericDaoHibernateSupport<PublicshComment> implements IpublicshCommentDao {

	@Override
	public boolean editStatus(String id,String status ){
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			PublicshComment pb= get(id);
			pb.setStatus(status);
			update(pb);
			flag = true;
		}
		return flag;
	}

}
