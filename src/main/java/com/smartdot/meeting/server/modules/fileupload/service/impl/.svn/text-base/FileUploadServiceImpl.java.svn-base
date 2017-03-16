package com.smartdot.meeting.server.modules.fileupload.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.fileupload.dao.IFileUploadDao;
import com.smartdot.meeting.server.modules.fileupload.service.IFileUploadService;
import com.smartdot.meeting.server.common.entity.FileUpload;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IFileUploadService.SERVICE_NAME)
public class FileUploadServiceImpl implements IFileUploadService {
	
	@Resource(name = IFileUploadDao.DAO_NAME)
	private IFileUploadDao fileUploadDao;

	@Override
	public List<FileUpload> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(FileUpload.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return fileUploadDao.findByDetachedCriteria(dc);
	}

	@Override
	public FileUpload getFileUploadById(String id) {
		
		return fileUploadDao.findById(id);
	}

	@Audit(module= "FileUpload管理",action= "FileUpload信息添加",description= "FileUpload信息添加说明")
	@Override
	public boolean save(FileUpload fileUpload) {
		boolean flag = false;
		if (fileUpload != null) {
			fileUploadDao.save(fileUpload);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "FileUpload管理",action= "FileUpload多条信息添加",description= "FileUpload多条信息添加说明")
	@Override
	public boolean saveAll(List<FileUpload> fileUploadList) {
		boolean flag = false;
		if (fileUploadList != null) {
			fileUploadDao.saveAll(fileUploadList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "FileUpload管理",action= "FileUpload信息修改",description= "FileUpload信息修改说明")
	@Override
	public boolean updateFileUpload(FileUpload fileUpload) {
		boolean flag = false;
		if (fileUpload != null) {
			fileUploadDao.update(fileUpload);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(FileUpload fileUpload) {
		boolean flag = false;
		if (fileUpload != null) {
			FileUpload instance = this.getFileUploadById(fileUpload.getId());
			fileUploadDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "FileUpload管理",action= "FileUpload多选信息删除",description= "FileUpload多选信息删除说明")
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<FileUpload> pageQuery) {
		
		return fileUploadDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "FileUpload管理",action= "FileUpload信息删除",description= "FileUpload信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				FileUpload fileUpload = fileUploadDao.get(id);
				fileUpload.setEnable(false);
				fileUploadDao.update(fileUpload);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<FileUpload> findByHQLAndParams(FileUpload fileUpload) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<FileUpload> sList = fileUploadDao
				.findByHQLAndParams("from FileUpload where enable = true","");
		return sList;
	}

	@Override
	public List<FileUpload> getFileUploadByGroupId(String groupid){
		
		DetachedCriteria dca = DetachedCriteria.forClass(FileUpload.class);
		dca.add(Restrictions.eq("enable", true));
		dca.add(Restrictions.eq("groupid", groupid));
		List<FileUpload> fileUpload = fileUploadDao.findByDetachedCriteria(dca);
		BeanUtils.copyProperties(dca,fileUpload);
		return fileUpload;
	}
	
}
