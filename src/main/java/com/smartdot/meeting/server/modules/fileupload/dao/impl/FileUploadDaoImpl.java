package com.smartdot.meeting.server.modules.fileupload.dao.impl;

import org.springframework.stereotype.Repository;

import com.smartdot.meeting.server.common.dao.GenericDaoHibernateSupport;
import com.smartdot.meeting.server.modules.fileupload.dao.IFileUploadDao;
import com.smartdot.meeting.server.common.entity.FileUpload;
@Repository(value = IFileUploadDao.DAO_NAME)
public class FileUploadDaoImpl extends GenericDaoHibernateSupport<FileUpload> implements IFileUploadDao {

}
