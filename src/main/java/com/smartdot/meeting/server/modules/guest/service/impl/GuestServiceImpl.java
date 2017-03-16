package com.smartdot.meeting.server.modules.guest.service.impl;

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
import com.smartdot.meeting.server.modules.guest.dao.IGuestAndServicePersonnelDao;
import com.smartdot.meeting.server.modules.guest.dao.IGuestDao;
import com.smartdot.meeting.server.modules.guest.model.GuestAndServicePersonnelForm;
import com.smartdot.meeting.server.modules.guest.model.GuestForm;
import com.smartdot.meeting.server.modules.guest.model.MemberGuest;
import com.smartdot.meeting.server.modules.member.dao.IMemberDao;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.servicePersonnel.dao.IServicePersonnelDao;
import com.smartdot.meeting.server.modules.guest.service.IGuestService;
import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.GuestAndServicePersonnel;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IGuestService.SERVICE_NAME)
public class GuestServiceImpl implements IGuestService {
	
	@Resource(name = IMemberDao.DAO_NAME)
	private IMemberDao memberDao;
	@Resource(name = IGuestDao.DAO_NAME)
	private IGuestDao guestDao;
	@Resource(name = IGuestAndServicePersonnelDao.DAO_NAME)
	private IGuestAndServicePersonnelDao guestAndServicePersonnelDao;
	@Resource(name = IServicePersonnelDao.DAO_NAME)
	private IServicePersonnelDao servicePersonnelDao;
	
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Guest> page) {

		return guestDao.pagedQuery(detachedCriteria, page);
	}
	
	@Override
	public Guest getGuestById(String id) {

		return guestDao.get(id);
	}
	
	@Audit(module= "app用户管理",action= "嘉宾添加",description= "/嘉宾添加说明")
	@Override
	public boolean save(GuestForm form) {
		boolean result = false;
		if(null != form){
			MemberGuest memberGuest = JSON.parseObject(String.valueOf(form.getMemberGuest()), MemberGuest.class); 
			Member member = new Member();
			Guest guest = new Guest();
			String[] arrayGuest = form.getArrayGuest();
			BeanUtils.copyProperties(memberGuest, guest);
			BeanUtils.copyProperties(memberGuest, member);
			if (StringUtils.isBlank(guest.getId())) {
				//密码
				if(StringUtils.isNotBlank(member.getPassword())){
					Map<String,String> map = EncodEncryUtil.generateSalt(member.getPassword());//SALT+MD5加密
					String salt = map.get("salt");
					member.setSalt(EncodEncryUtil.compressData2(salt));//对盐进行base64加密
					member.setPassword(map.get("saltpassword"));
				}
				guest.setMember(member);
				memberDao.save(member);
				guestDao.save(guest);
				if(null != arrayGuest && arrayGuest.length > 0){
					for (String str : arrayGuest) {
						if(StringUtils.isNotBlank(str)){
							Guest guest2 = new Guest();
							guest2 = JSON.parseObject(str, Guest.class);
							if(null != guest2 && StringUtils.isNotBlank(guest2.getLanguage()) && StringUtils.isNotBlank(guest2.getName())){
								guest2 = getGuest(guest, guest2);
								guestDao.save(guest2);
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	}
	
	@Audit(module= "app用户管理",action= "嘉宾修改",description= "/嘉宾修改说明")
	@Override
	public boolean update(GuestForm form) {
		boolean result = false;
		if(null != form){
			MemberGuest memberGuest = JSON.parseObject(String.valueOf(form.getMemberGuest()), MemberGuest.class); 
			Member member = new Member();
			Guest guest = new Guest();
			String[] arrayGuest = form.getArrayGuest();
			BeanUtils.copyProperties(memberGuest, guest);
			BeanUtils.copyProperties(memberGuest, member);
			if (StringUtils.isNotBlank(guest.getId()) && StringUtils.isNotBlank(member.getMemberId())) {
				guest.setMember(member);
				memberDao.update(member);
				guestDao.update(guest);
				if(null != arrayGuest && arrayGuest.length > 0){
					for (String str : arrayGuest) {
						if(StringUtils.isNotBlank(str)){
							Guest guest2 = JSON.parseObject(str, Guest.class);
							if(null != guest2){
								if(StringUtils.isNotBlank(guest2.getId())){
									if(StringUtils.isNotBlank(guest2.getLanguage()) && StringUtils.isNotBlank(guest2.getName())){
										guest2 = getGuest(guest, guest2);
										guestDao.update(guest2);
									} else {
										guest2.setEnable(false);
										guestDao.update(guest2);
									}
								} else {
									if(StringUtils.isNotBlank(guest2.getLanguage()) && StringUtils.isNotBlank(guest2.getName())){
										guest2 = getGuest(guest, guest2);
										guestDao.save(guest2);
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
	
	@Audit(module= "app用户管理",action= "嘉宾删除",description= "/嘉宾删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (StringUtils.isNotBlank(id)) {
			try {
				Guest guest = guestDao.get(id);
				Member member = guest.getMember();
				guest.setEnable(false);
				guestDao.update(guest);
				memberDao.delete(member.getMemberId());
				//删除其他语言
				DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
				dc.add(Restrictions.eq("enable", true));
				dc.add(Restrictions.eq("member", member));
				List<Guest> gsall = guestDao.findByDetachedCriteria(dc);
				if(null != gsall && gsall.size() > 0){
					for (Guest guest2 : gsall) {
						guest2.setEnable(false);
						guestDao.update(guest2);
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
	
	@Audit(module= "app用户管理",action= "嘉宾多选删除",description= "/嘉宾多选删除说明")
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
	public List<Guest> findGuestByDC(DetachedCriteria detachedCriteria) {
		return guestDao.findByDetachedCriteria(detachedCriteria);
	}
	
	@Override
	public List<GuestAndServicePersonnelForm> getServicePersonnelByGuestId(String guestId) {
		//数据字段服务人员类型查询
		//List<> lser = ArrayList<E>();
		List<GuestAndServicePersonnelForm> lserGuest = new ArrayList<GuestAndServicePersonnelForm>();
		GuestAndServicePersonnelForm gs1 = new GuestAndServicePersonnelForm();
		gs1.setType("type1");
		gs1.setTypeName("服务1");
		lserGuest.add(gs1);
		GuestAndServicePersonnelForm gs2 = new GuestAndServicePersonnelForm();
		gs2.setType("type2");
		gs2.setTypeName("服务2");
		lserGuest.add(gs2);
		GuestAndServicePersonnelForm gs3 = new GuestAndServicePersonnelForm();
		gs3.setType("type3");
		gs3.setTypeName("服务3");
		lserGuest.add(gs3);
		GuestAndServicePersonnelForm gs4 = new GuestAndServicePersonnelForm();
		gs4.setType("type4");
		gs4.setTypeName("服务4");
		lserGuest.add(gs4);
		
		DetachedCriteria dc = DetachedCriteria.forClass(GuestAndServicePersonnel.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.isNotNull("type"));
		dc.add(Restrictions.isNotNull("servicePersonnelId"));
		dc.add(Restrictions.ne("type", ""));
		dc.add(Restrictions.ne("servicePersonnelId", ""));
		dc.add(Restrictions.eq("guestId", guestId));
		List<GuestAndServicePersonnel> gsall = guestAndServicePersonnelDao.findByDetachedCriteria(dc);
		if(null != gsall && gsall.size() > 0){
			List<GuestAndServicePersonnelForm> lserGuest2 = lserGuest;
			lserGuest = new ArrayList<GuestAndServicePersonnelForm>();
			for (GuestAndServicePersonnelForm sergu : lserGuest2) {
				String type = sergu.getType();
				for (GuestAndServicePersonnel guser : gsall) {
					DetachedCriteria dcser = DetachedCriteria.forClass(ServicePersonnel.class);
					dcser.add(Restrictions.eq("enable", true));
					dcser.add(Restrictions.eq("id", guser.getServicePersonnelId()));
					List<ServicePersonnel> serall = servicePersonnelDao.findByDetachedCriteria(dcser);
					if(null != serall && serall.size() == 1){
						ServicePersonnel ser = serall.get(0);
						if(type.equals(guser.getType())){
							sergu.setServicePersonnelId(guser.getServicePersonnelId());
							sergu.setServicePersonnelName(ser.getName());
						}
					}
					
				}
				lserGuest.add(sergu);
			}
		}
		
		return lserGuest;
	}
	
	@Override
	public boolean deleteGuestAndServicePersonnel(String guestId) {
		DetachedCriteria dc = DetachedCriteria.forClass(GuestAndServicePersonnel.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("guestId", guestId));
		List<GuestAndServicePersonnel> gsall = guestAndServicePersonnelDao.findByDetachedCriteria(dc);
		if(null != gsall && gsall.size() > 0){
			for (GuestAndServicePersonnel guestAndServicePersonnel : gsall) {
				guestAndServicePersonnel.setEnable(false);
				guestAndServicePersonnelDao.update(guestAndServicePersonnel);
			}
		}
		return true;
	}

	@Override
	public boolean saveGuestAndServicePersonnel(String guestId, String[] lguestSer) {
		boolean result = false;
		if(StringUtils.isNotBlank(guestId)){
			//先删除
			deleteGuestAndServicePersonnel(guestId);
			if(null != lguestSer && lguestSer.length > 0){
				for (String guestAndServicePersonnel2 : lguestSer) {
					GuestAndServicePersonnel gser = JSON.parseObject(guestAndServicePersonnel2, GuestAndServicePersonnel.class);
					if(null != gser && StringUtils.isNotBlank(gser.getServicePersonnelId())){
						gser.setGuestId(guestId);
						guestAndServicePersonnelDao.save(gser);
					}
				}
			}
			result = true;
		}
		return result;
	}

	@Override
	public List<MemberLanguage> getMemberLanguage(String id) {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Guest> lGuest = new ArrayList<Guest>();
		if(StringUtils.isNotBlank(id)){
			Guest guest = guestDao.get(id);
			DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("member", guest.getMember()));
			lGuest = guestDao.findByDetachedCriteria(dc);
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
				if(null != lGuest && lGuest.size() > 0){
					for (Guest guest : lGuest) {
						if(guest.getLanguage().equals(uneIdent)){
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
	public List<Guest> getListGuest(String id) {
		
		Guest guest = guestDao.get(id);
		DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("member", guest.getMember()));
		
		return guestDao.findByDetachedCriteria(dc);
	}
	
	public Guest getGuest(Guest guest, Guest guest2) {
		guest2.setMember(guest.getMember());
		guest2.setGender(guest.getGender());
		guest2.setIdType(guest.getIdType());
		guest2.setDocumentNumber(guest.getDocumentNumber());
		guest2.setVip(guest.getVip());
		guest2.setGroupId(guest.getGroupId());
		guest2.setCreateTimes(guest.getCreateTimes());
		guest2.setUpdateTimes(guest.getUpdateTimes());
		return guest2;
	}

	@Override
	public List<Map<String, Object>> queryALLServicePersonnel() throws Exception {
		
		return guestAndServicePersonnelDao.queryALLServicePersonnel();
	}

	@Override
	public List<Map<String, Object>> findServicePersonnelForName(String name)
			throws Exception {
		return guestAndServicePersonnelDao.findServicePersonnelForName(name);
	}
	@Override
	public void savetemplate(Guest guest) throws Exception {
		guestDao.save(guest);
		
	}
	/**
	 * 大会点评模块查询发布人和回复人
	 * */
	@Override
	public List<Guest> findByDetachedCriteria(DetachedCriteria dcpc) {

		return guestDao.findByDetachedCriteria(dcpc);
	}
}
