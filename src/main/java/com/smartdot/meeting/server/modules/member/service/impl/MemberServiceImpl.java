package com.smartdot.meeting.server.modules.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.modules.datadictionary.dao.IDataDictionaryDao;
import com.smartdot.meeting.server.modules.member.dao.IMemberDao;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.modules.systemsetting.dao.ISystemSettingRedisDao;
import com.smartdot.meeting.server.modules.sysuser.dao.ISysUserDao;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.entity.SysUser;
import com.smartdot.meeting.server.common.util.EncodEncryUtil;


@Service(value = IMemberService.SERVICE_NAME)
public class MemberServiceImpl implements IMemberService {
	
	@Resource(name = IMemberDao.DAO_NAME)
	private IMemberDao memberDao;
	@Resource(name = ISysUserDao.DAO_NAME)
	private ISysUserDao sysUserDao;
	@Resource(name = IDataDictionaryDao.DAO_NAME)
	private IDataDictionaryDao dataDictionaryDao;
	@Resource(name = ISystemSettingRedisDao.DAO_NAME)
	private ISystemSettingRedisDao systemSettingRedisDao;
	
	private ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
	private String languageKey = bundle.getString("language_type_key");
	
	
	@Override
	public List<Member> findAll() {
		return memberDao.findAll();
	}
	
	@Override
	public boolean save(Member instance) {
		boolean result = false;
		if(null != instance){
			memberDao.save(instance);
			result = true;
		}
		return result;
	}
	@Override
	public boolean update(Member instance) {
		boolean result = false;
		if(null != instance){
			memberDao.update(instance);
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				memberDao.deleteById(id);
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
	public Member findById(String idValue) {
		
		return memberDao.findById(idValue);
	}

	@Override
	public List<Member> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		
		return memberDao.findByDetachedCriteria(detachedCriteria);
	}
	
	@Override
	public boolean savePassword(String id, String password) {
		boolean result = false;
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(password)){
			Member member = memberDao.get(id);
			//密码
			Map<String,String> map = EncodEncryUtil.generateSalt(password);//SALT+MD5加密
			String salt = map.get("salt");
			member.setSalt(EncodEncryUtil.compressData2(salt));//对盐进行base64加密
			member.setPassword(map.get("saltpassword"));
			result = true;
		}
		return result;
	}


	@Override
	public boolean checkRepeat(String id, String value) {
		boolean result = false;
		if(StringUtils.isNotBlank(value)){
			DetachedCriteria dcmember = DetachedCriteria.forClass(Member.class);
			dcmember.add(Restrictions.eq("enable", true));
			dcmember.add(Restrictions.or(Restrictions.eq("invitationCode", value), Restrictions.eq("userName", value)));
			if(StringUtils.isNotBlank(id)){
				dcmember.add(Restrictions.ne("memberId", id));
			}
			List<Member> lmember = memberDao.findByDetachedCriteria(dcmember);
			//
			DetachedCriteria dcsysuser = DetachedCriteria.forClass(SysUser.class);
			dcsysuser.add(Restrictions.eq("enable", true));
			dcsysuser.add(Restrictions.eq("userName", value));
			if(StringUtils.isNotBlank(id)){
				dcsysuser.add(Restrictions.ne("id", id));
			}
			List<SysUser> lsysuser = sysUserDao.findByDetachedCriteria(dcsysuser);
			if((null != lmember && lmember.size() > 0) || (null != lsysuser && lsysuser.size() > 0)){
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public List<DataDictionary> getDatadictionary(String type) {
		List<DataDictionary> lDatadictionary = new ArrayList<DataDictionary>();
		if(StringUtils.isBlank(type)){
			return lDatadictionary;
		}
		lDatadictionary = dataDictionaryDao.getListDatadictionary(type);
		return lDatadictionary;
	}
}
