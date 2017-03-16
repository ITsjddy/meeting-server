package com.smartdot.meeting.server.modules.exhibitors.service.impl;

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
import com.smartdot.meeting.server.modules.member.dao.IMemberDao;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.audience.model.MemberAudience;
import com.smartdot.meeting.server.modules.exhibitors.dao.IExhibitorsDao;
import com.smartdot.meeting.server.modules.exhibitors.model.ExhibitorsForm;
import com.smartdot.meeting.server.modules.exhibitors.model.MemberExhibitors;
import com.smartdot.meeting.server.modules.exhibitors.service.IExhibitorsService;
import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.common.entity.Exhibitors;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IExhibitorsService.SERVICE_NAME)
public class ExhibitorsServiceImpl implements IExhibitorsService {
	
	@Resource(name = IMemberDao.DAO_NAME)
	private IMemberDao memberDao;
	@Resource(name = IExhibitorsDao.DAO_NAME)
	private IExhibitorsDao exhibitorsDao;
	
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Exhibitors> page) {

		return exhibitorsDao.pagedQuery(detachedCriteria, page);
	}
	
	@Override
	public Exhibitors getExhibitorsById(String id) {

		return exhibitorsDao.get(id);
	}
	
	@Audit(module= "app用户管理",action= "展商添加",description= "/展商添加说明")
	@Override
	public boolean save(ExhibitorsForm form) {
		boolean result = false;
		if(null != form){
			MemberExhibitors memberExhibitors = JSON.parseObject(String.valueOf(form.getMemberExhibitors()), MemberExhibitors.class); 
			String password = memberExhibitors.getPassword();
			Member member = new Member();
			Exhibitors exhibitors = new Exhibitors();
			String[] arrayExhibitors= form.getArrayExhibitors();
			BeanUtils.copyProperties(memberExhibitors, exhibitors);
			BeanUtils.copyProperties(memberExhibitors, member);
			if (StringUtils.isBlank(exhibitors.getId())) {
				exhibitors.setMember(member);
				if(StringUtils.isNotBlank(password)){
					Map<String,String> map=EncodEncryUtil.generateSalt(password);//SALT+MD5加密
					String salt = map.get("salt");
					member.setSalt(EncodEncryUtil.compressData2(salt));//对盐进行base64加密
					password = map.get("saltpassword");
					member.setPassword(password);
				}
				memberDao.save(member);
				exhibitorsDao.save(exhibitors);
				if(null != arrayExhibitors && arrayExhibitors.length > 0){
					for (String str : arrayExhibitors) {
						if(StringUtils.isNotBlank(str)){
							Exhibitors exhibitors2 = JSON.parseObject(str, Exhibitors.class);
							if(null != exhibitors2 && StringUtils.isNotBlank(exhibitors2.getLanguage()) && StringUtils.isNotBlank(exhibitors2.getName())){
								exhibitors2.setMember(member);
								exhibitors2.setGender(exhibitors.getGender());
								exhibitorsDao.save(exhibitors2);
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	}
	
	@Audit(module= "app用户管理",action= "展商修改",description= "/展商修改说明")
	@Override
	public boolean update(ExhibitorsForm form) {
		boolean result = false;
		if(null != form){
			MemberExhibitors memberExhibitors = JSON.parseObject(String.valueOf(form.getMemberExhibitors()), MemberExhibitors.class); 
			Member member = new Member();
			Exhibitors exhibitors = new Exhibitors();
			String[] arrayExhibitors = form.getArrayExhibitors();
			BeanUtils.copyProperties(memberExhibitors, exhibitors);
			BeanUtils.copyProperties(memberExhibitors, member);
			if (StringUtils.isNotBlank(exhibitors.getId()) && StringUtils.isNotBlank(member.getMemberId())) {
				exhibitors.setMember(member);
				memberDao.update(member);
				exhibitorsDao.update(exhibitors);
				if(null != arrayExhibitors && arrayExhibitors.length > 0){
					for (String str : arrayExhibitors) {
						if(StringUtils.isNotBlank(str)){
							Exhibitors exhibitors2 = JSON.parseObject(str, Exhibitors.class);
							if(null != exhibitors2){
								if(StringUtils.isNotBlank(exhibitors2.getId())){
									if(StringUtils.isNotBlank(exhibitors2.getLanguage()) && StringUtils.isNotBlank(exhibitors2.getName())){
										exhibitors2.setMember(member);
										exhibitors2.setGender(exhibitors.getGender());
										exhibitorsDao.update(exhibitors2);
									} else {
										exhibitors2.setEnable(false);
										exhibitorsDao.update(exhibitors2);
									}
								} else {
									if(StringUtils.isNotBlank(exhibitors2.getLanguage()) && StringUtils.isNotBlank(exhibitors2.getName())){
										exhibitors2.setMember(member);
										exhibitors2.setGender(exhibitors.getGender());
										exhibitorsDao.save(exhibitors2);
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
	
	@Audit(module= "app用户管理",action= "展商删除",description= "/展商删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				Exhibitors exhibitors = exhibitorsDao.get(id);
				exhibitors.setEnable(false);
				exhibitorsDao.update(exhibitors);
				memberDao.delete(exhibitors.getMember().getMemberId());
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
		List<Exhibitors> lExhibitors = new ArrayList<Exhibitors>();
		if(StringUtils.isNotBlank(id)){
			Exhibitors exhibitors = exhibitorsDao.get(id);
			DetachedCriteria dc = DetachedCriteria.forClass(Exhibitors.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("member", exhibitors.getMember()));
			lExhibitors = exhibitorsDao.findByDetachedCriteria(dc);
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
				if(null != lExhibitors && lExhibitors.size() > 0){
					for (Exhibitors exhibitors : lExhibitors) {
						if(exhibitors.getLanguage().equals(uneIdent)){
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
	public List<Exhibitors> getListExhibitors(String id) {
		Exhibitors exhibitors = exhibitorsDao.get(id);
		DetachedCriteria dc = DetachedCriteria.forClass(Exhibitors.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("member", exhibitors.getMember()));
		
		return exhibitorsDao.findByDetachedCriteria(dc);
	}
	
	
}
