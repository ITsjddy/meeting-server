package com.smartdot.meeting.server.modules.publicshcomment.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.fileupload.service.IFileUploadService;
import com.smartdot.meeting.server.modules.publicshcomment.dao.IpublicshCommentDao;
import com.smartdot.meeting.server.modules.publicshcomment.model.PublicshCommentList;
import com.smartdot.meeting.server.modules.publicshcomment.service.IpublicshCommentService;
import com.smartdot.meeting.server.common.entity.FileUpload;
import com.smartdot.meeting.server.common.entity.PublicshComment;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IpublicshCommentService.SERVICE_NAME)
public class publicshCommentServiceImpl implements IpublicshCommentService {
	
	@Resource(name = IpublicshCommentDao.DAO_NAME)
	private IpublicshCommentDao publicshCommentDao;
	
	@Resource(name = IFileUploadService.SERVICE_NAME)
	private IFileUploadService fileUploadService;

	@Override
	public List<PublicshComment> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(PublicshComment.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		return publicshCommentDao.findByDetachedCriteria(dc);
	}

	@Override
	public PublicshCommentList getpublicshCommentById(String id) {
		PublicshCommentList pbc = new PublicshCommentList();
		PublicshComment publicshCom = publicshCommentDao.findById(id);
		if(StringUtils.isNotBlank(publicshCom.getGroupid())){
			//根据groupId获取另一张表里上传的图片
			List<FileUpload> lput=fileUploadService.getFileUploadByGroupId(publicshCom.getGroupid());
//			String lput = listFile.get(0).getBigFileurl();
			pbc.setFileUpload(lput);
			pbc.setPublicsh(publicshCom);
		}else{
			pbc.setPublicsh(publicshCom);
		}
		return pbc;
	}
	
	

	@Audit(module= "publicshComment管理",action= "publicshComment信息添加",description= "publicshComment信息添加说明")
	@Override
	public boolean save(PublicshComment publicshComment) {
		boolean flag = false;
		if (publicshComment != null) {
			publicshCommentDao.save(publicshComment);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "publicshComment管理",action= "publicshComment多条信息添加",description= "publicshComment多条信息添加说明")
	@Override
	public boolean saveAll(List<PublicshComment> publicshCommentList) {
		boolean flag = false;
		if (publicshCommentList != null) {
			publicshCommentDao.saveAll(publicshCommentList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "publicshComment管理",action= "publicshComment信息修改",description= "publicshComment信息修改说明")
	@Override
	public boolean updatepublicshComment(PublicshComment publicshComment) {
		boolean flag = false;
		if (publicshComment != null) {
			publicshCommentDao.update(publicshComment);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<PublicshComment> pageQuery) {
		
		return publicshCommentDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "publicshComment管理",action= "publicshComment信息删除",description= "publicshComment信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				PublicshComment publicshComment = publicshCommentDao.get(id);
				publicshComment.setEnable(false);
				publicshCommentDao.update(publicshComment);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<PublicshComment> findByHQLAndParams(PublicshComment publicshComment) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<PublicshComment> sList = publicshCommentDao
				.findByHQLAndParams("from publicshComment where enable = true","");
		return sList;
	}

	@Override
	public boolean editStatus(PublicshComment publicshComment) {
		boolean flag = false;
		if (publicshComment != null) {
			publicshCommentDao.update(publicshComment);
			flag = true;
		}
		return flag;
	}

	@Override
	public PublicshCommentList getpublicshComment(String id,String status,String auditReason) {
		PublicshCommentList pbc = new PublicshCommentList();
		PublicshComment publicshCom = publicshCommentDao.findById(id);
		if(StringUtils.isNotBlank(publicshCom.getGroupid())){
			//根据groupId获取另一张表里上传的图片
			List<FileUpload> lput=fileUploadService.getFileUploadByGroupId(publicshCom.getGroupid());
//			String lput = listFile.get(0).getBigFileurl();
			publicshCom.setStatus(status);
			publicshCom.setAuditReason(auditReason);
			publicshCommentDao.save(publicshCom);
			pbc.setFileUpload(lput);
			pbc.setPublicsh(publicshCom);
		}else{
			publicshCom.setStatus(status);
			publicshCom.setAuditReason(auditReason);
			publicshCommentDao.save(publicshCom);
			pbc.setPublicsh(publicshCom);
		}
		return pbc;
	}

	@Override
	public List<PublicshComment> getPublicshBySubject(String id) {
		DetachedCriteria dac = DetachedCriteria.forClass(PublicshComment.class);
		dac.add(Restrictions.eq("id", id));
		dac.add(Restrictions.eq("type", "1"));
		dac.add(Restrictions.eq("enable", true));
		List<PublicshComment> pub = publicshCommentDao.findByDetachedCriteria(dac);
		return pub;
	}
	
}
