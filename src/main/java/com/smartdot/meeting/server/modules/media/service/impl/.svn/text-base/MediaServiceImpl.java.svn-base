package com.smartdot.meeting.server.modules.media.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.util.EncodEncryUtil;
import com.smartdot.meeting.server.modules.media.dao.IMediaDao;
import com.smartdot.meeting.server.modules.media.model.MediaForm;
import com.smartdot.meeting.server.modules.media.model.MemberMedia;
import com.smartdot.meeting.server.modules.media.service.IMediaService;
import com.smartdot.meeting.server.modules.member.dao.IMemberDao;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.Media;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IMediaService.SERVICE_NAME)
public class MediaServiceImpl implements IMediaService {
	
	@Resource(name = IMemberDao.DAO_NAME)
	private IMemberDao memberDao;
	@Resource(name = IMediaDao.DAO_NAME)
	private IMediaDao mediaDao;
	
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Media> page) {

		return mediaDao.pagedQuery(detachedCriteria, page);
	}
	
	@Override
	public Media getMediaById(String id) {

		return mediaDao.get(id);
	}
	
	@Audit(module= "app用户管理",action= "媒体添加",description= "/媒体添加说明")
	@Override
	public boolean save(MediaForm form) {
		boolean result = false;
		if(null != form){
			MemberMedia memberMedia = JSON.parseObject(String.valueOf(form.getMemberMedia()), MemberMedia.class); 
			String password = memberMedia.getPassword();
			Member member = new Member();
			Media media = new Media();
			String[] arrayMedia = form.getArrayMedia();
			BeanUtils.copyProperties(memberMedia, media);
			BeanUtils.copyProperties(memberMedia, member);
			if (StringUtils.isBlank(media.getId())) {
				if(StringUtils.isNotBlank(password)){
					Map<String,String> map=EncodEncryUtil.generateSalt(password);//SALT+MD5加密
					String salt = map.get("salt");
					member.setSalt(EncodEncryUtil.compressData2(salt));//对盐进行base64加密
					password = map.get("saltpassword");
					member.setPassword(password);
				}
				media.setMember(member);
				memberDao.save(member);
				mediaDao.save(media);
				if(null != arrayMedia && arrayMedia.length > 0){
					for (String str : arrayMedia) {
						if(StringUtils.isNotBlank(str)){
							Media media2 = JSON.parseObject(str, Media.class);
							if(null != media2 && StringUtils.isNotBlank(media2.getLanguage()) && StringUtils.isNotBlank(media2.getName())){
								media2.setMember(member);
								media2.setGender(media2.getGender());
								media2.setIdType(media2.getIdType());
								media2.setDocumentNumber(media2.getDocumentNumber());
								
								mediaDao.save(media2);
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	}
	
	
	@Audit(module= "app用户管理",action= "媒体修改",description= "/媒体修改说明")
	@Override
	public boolean update(MediaForm form) {
		boolean result = false;
		if(null != form){
			MemberMedia memberMedia = JSON.parseObject(String.valueOf(form.getMemberMedia()), MemberMedia.class); 
			Member member = new Member();
			Media media = new Media();
			String[] arrayMedia = form.getArrayMedia();
			BeanUtils.copyProperties(memberMedia, media);
			BeanUtils.copyProperties(memberMedia, member);
			if (StringUtils.isNotBlank(media.getId()) && StringUtils.isNotBlank(member.getMemberId())) {
				media.setMember(member);
				memberDao.update(member);
				mediaDao.update(media);
				if(null != arrayMedia && arrayMedia.length > 0){
					for (String str : arrayMedia) {
						if(StringUtils.isNotBlank(str)){
							Media media2 = JSON.parseObject(str, Media.class);
							if(null != media2){
								if(StringUtils.isNotBlank(media2.getId())){
									if(StringUtils.isNotBlank(media2.getLanguage()) && StringUtils.isNotBlank(media2.getName())){
										media2.setMember(member);
										media2.setGender(media.getGender());
										media2.setIdType(media.getIdType());
										media2.setDocumentNumber(media.getDocumentNumber());
										
										mediaDao.update(media2);
									} else {
										media2.setEnable(false);
										mediaDao.update(media2);
									}
								} else {
									if(StringUtils.isNotBlank(media2.getLanguage()) && StringUtils.isNotBlank(media2.getName())){
										media2.setMember(member);
										media2.setGender(media2.getGender());
										media2.setIdType(media2.getIdType());
										media2.setDocumentNumber(media2.getDocumentNumber());
										
										mediaDao.save(media2);
									}
								}
							}
						}
					}
				}
			}
			result = true;
		}
		return result;
	}
	
	@Audit(module= "app用户管理",action= "媒体删除",description= "/媒体删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (StringUtils.isNotBlank(id)) {
			try {
				Media media = mediaDao.get(id);
				Member member = media.getMember();
				media.setEnable(false);
				mediaDao.update(media);
				memberDao.delete(member.getMemberId());
				//删除其他语言
				DetachedCriteria dc = DetachedCriteria.forClass(Media.class);
				dc.add(Restrictions.eq("enable", true));
				dc.add(Restrictions.eq("member", member));
				List<Media> gsall = mediaDao.findByDetachedCriteria(dc);
				if(null != gsall && gsall.size() > 0){
					for (Media media2 : gsall) {
						media2.setEnable(false);
						mediaDao.update(media2);
					}
				}
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Audit(module= "app用户管理",action= "媒体多选删除",description= "/媒体多选删除说明")
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
	public List<Media> findMediaByDC(DetachedCriteria detachedCriteria) {
		return mediaDao.findByDetachedCriteria(detachedCriteria);
	}
	@Override
	public List<MemberLanguage> getMemberLanguage(String id) {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Media> lMedia = new ArrayList<Media>();
		if(StringUtils.isNotBlank(id)){
			Media media = mediaDao.get(id);
			DetachedCriteria dc = DetachedCriteria.forClass(Media.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("member", media.getMember()));
			lMedia = mediaDao.findByDetachedCriteria(dc);
		}
		//查询数据字典的语言
		List<Map<String,String>> llangage = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("uneIdent", "en");
		map.put("name", "英文");
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("uneIdent", "arab");
		map2.put("name", "阿拉伯文");
		Map<String,String> map3 = new HashMap<String,String>();
		map3.put("uneIdent", "german");
		map3.put("name", "德语");
		llangage.add(map);
		llangage.add(map2);
		llangage.add(map3);
		if(null != llangage && llangage.size() > 0){
			for (Map<String, String> maps : llangage) {
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lMedia && lMedia.size() > 0){
					for (Media media : lMedia) {
						if(media.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							continue;
						}
					}
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		
		return lMemberLanguage;
	}

	@Override
	public List<Map<String, String>> getAllLanguage() {
		//查询数据字典的语言
		List<Map<String,String>> llangage = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("uneIdent", "en");
		map.put("name", "英文");
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("uneIdent", "arab");
		map2.put("name", "阿拉伯文");
		Map<String,String> map3 = new HashMap<String,String>();
		map3.put("uneIdent", "german");
		map3.put("name", "德语");
		llangage.add(map);
		llangage.add(map2);
		llangage.add(map3);
		return llangage;
	}

	@Override
	public List<Media> getListMedia(String id) {
		
		Media media = mediaDao.get(id);
		DetachedCriteria dc = DetachedCriteria.forClass(Media.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("member", media.getMember()));
		
		return mediaDao.findByDetachedCriteria(dc);
	}

	
	
	
}
