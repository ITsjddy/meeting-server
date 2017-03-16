package com.smartdot.meeting.server.modules.folder.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.folder.dao.IFolderDao;
import com.smartdot.meeting.server.common.entity.Folder;

@Service(value=IFolderDao.DAO_NAME)
public class FolderDaoImpl extends GenericDaoHibernateSupport<Folder> implements IFolderDao {

	@Override
	public List<Folder> getAllList() {
		DetachedCriteria dc =DetachedCriteria.forClass(Folder.class);
		dc.add(Restrictions.eq("enable", true));
		List<Folder> flist = findByDetachedCriteria(dc);
		return flist;
	}

	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				Folder folder = get(id);
				folder.setEnable(false);
				update(folder);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if(StringUtils.isNotBlank(id)){
			Folder folder = get(id);
			folder.setEnable(false);
			update(folder);
			flag = true;
		}
		return flag;
	}

}
