package com.smartdot.meeting.server.modules.audience.service.impl;

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

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.util.EncodEncryUtil;
import com.smartdot.meeting.server.modules.audience.dao.IAudienceDao;
import com.smartdot.meeting.server.modules.audience.model.AudienceForm;
import com.smartdot.meeting.server.modules.audience.model.MemberAudience;
import com.smartdot.meeting.server.modules.audience.service.IAudienceService;
import com.smartdot.meeting.server.modules.member.dao.IMemberDao;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Member;

@Service(value = IAudienceService.SERVICE_NAME)
public class AudienceServiceImpl implements IAudienceService {
	@Resource(name = IAudienceDao.DAO_NAME)
	private IAudienceDao audienceDao;
	@Resource(name = IMemberDao.DAO_NAME)
	private IMemberDao memberDao;
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria dc, Page<Audience> page) {

		return audienceDao.pagedQuery(dc, page);
	}
	
	@Override
	public boolean save(AudienceForm form) {
		boolean result = false;
		if(null != form){
			MemberAudience memberAudience = JSON.parseObject(String.valueOf(form.getMemberAudience()), MemberAudience.class); 
			String password = memberAudience.getPassword();
			Member member = new Member();
			Audience audience = new Audience();
			String[] arrayAudience= form.getArrayAudience();
			BeanUtils.copyProperties(memberAudience, audience);
			BeanUtils.copyProperties(memberAudience, member);
			if (StringUtils.isBlank(audience.getId())) {
				audience.setMember(member);
				if(StringUtils.isNotBlank(password)){
					Map<String,String> map=EncodEncryUtil.generateSalt(password);//SALT+MD5加密
					String salt = map.get("salt");
					member.setSalt(EncodEncryUtil.compressData2(salt));//对盐进行base64加密
					password = map.get("saltpassword");
					member.setPassword(password);
				}
				memberDao.save(member);
				audienceDao.save(audience);
				if(null != arrayAudience && arrayAudience.length > 0){
					for (String str : arrayAudience) {
						if(StringUtils.isNotBlank(str)){
							Audience audience2 = JSON.parseObject(str, Audience.class);
							if(null != audience2 && StringUtils.isNotBlank(audience2.getLanguage()) && StringUtils.isNotBlank(audience2.getName())){
								audience2.setMember(member);
								audience2.setGender(audience.getGender());
								audienceDao.save(audience2);
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public boolean update(AudienceForm form) {
		boolean result = false;
		if(null != form){
			MemberAudience memberAudience = JSON.parseObject(String.valueOf(form.getMemberAudience()), MemberAudience.class); 
			Member member = new Member();
			Audience audience = new Audience();
			String[] arrayAudience = form.getArrayAudience();
			BeanUtils.copyProperties(memberAudience, audience);
			BeanUtils.copyProperties(memberAudience, member);
			if (StringUtils.isNotBlank(audience.getId()) && StringUtils.isNotBlank(member.getMemberId())) {
				audience.setMember(member);
				memberDao.update(member);
				audienceDao.update(audience);
				if(null != arrayAudience && arrayAudience.length > 0){
					for (String str : arrayAudience) {
						if(StringUtils.isNotBlank(str)){
							Audience audience2 = JSON.parseObject(str, Audience.class);
							if(null != audience2){
								if(StringUtils.isNotBlank(audience2.getId())){
									if(StringUtils.isNotBlank(audience2.getLanguage()) && StringUtils.isNotBlank(audience2.getName())){
										audience2.setMember(member);
										audience2.setGender(audience.getGender());
										audienceDao.update(audience2);
									} else {
										audience2.setEnable(false);
										audienceDao.update(audience2);
									}
								} else {
									if(StringUtils.isNotBlank(audience2.getLanguage()) && StringUtils.isNotBlank(audience2.getName())){
										audience2.setMember(member);
										audience2.setGender(audience.getGender());
										audienceDao.save(audience2);
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
	
	
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (StringUtils.isNotBlank(id)) {
			try {
				Audience audience = audienceDao.get(id);
				Member member = audience.getMember();
				audience.setEnable(false);
				audienceDao.update(audience);
				memberDao.delete(member.getMemberId());
				//删除其他语言
				DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
				dc.add(Restrictions.eq("enable", true));
				dc.add(Restrictions.eq("member", member));
				List<Audience> gsall = audienceDao.findByDetachedCriteria(dc);
				if(null != gsall && gsall.size() > 0){
					for (Audience audience2 : gsall) {
						audience2.setEnable(false);
						audienceDao.update(audience2);
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
	public List<MemberLanguage> getMemberLanguage(String id) {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Audience> lAudience = new ArrayList<Audience>();
		if(StringUtils.isNotBlank(id)){
			Audience audience = audienceDao.get(id);
			DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("member", audience.getMember()));
			lAudience = audienceDao.findByDetachedCriteria(dc);
		}
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
				if(null != lAudience && lAudience.size() > 0){
					for (Audience audience : lAudience) {
						if(audience.getLanguage().equals(uneIdent)){
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
	public List<Audience> getListAudience(String id) {
		Audience audience = audienceDao.get(id);
		DetachedCriteria dc = DetachedCriteria.forClass(Audience.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("member", audience.getMember()));
		
		return audienceDao.findByDetachedCriteria(dc);
	}

	@Override
	public Audience getAudienceById(String id) {
		
		
		return audienceDao.get(id);
	}


}
