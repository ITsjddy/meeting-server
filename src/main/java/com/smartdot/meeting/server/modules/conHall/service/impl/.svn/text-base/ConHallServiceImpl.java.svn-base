package com.smartdot.meeting.server.modules.conHall.service.impl;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.ConHall;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.util.GBKOrder;
import com.smartdot.meeting.server.modules.conHall.dao.IConHallDao;
import com.smartdot.meeting.server.modules.conHall.service.IConHallService;
import com.smartdot.meeting.server.modules.schedule.service.IScheduleService;


@Service(value = IConHallService.SERVICE_NAME)
public class ConHallServiceImpl implements IConHallService {
	
	@Resource(name = IConHallDao.DAO_NAME)
	private IConHallDao conHallDao;

	@Resource(name = IScheduleService.SERVICE_NAME)
	private IScheduleService scheduleService;
	@Override
	public Map<String, Object> pageConHallList(DetachedCriteria detachedCriteria,Page<ConHall> page) {
		Map<String, Object> pageData = conHallDao.pagedQuery(detachedCriteria,page);
		return pageData;
	}

	@Override
	public ConHall getEntityById(String id) {
		return conHallDao.findById(id);
	}

	@Override
	public void saveEntity(ConHall schedule) {
		conHallDao.saveOrUpdate(schedule);
	}

	@Override
	public List<Map<String, String>> getAllLanguage() {
		//查询数据字典的语言
//		List<Map<String,String>> llangage = new ArrayList<Map<String,String>>();
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("uneIdent", "en");
//		map.put("name", "英文");
//		Map<String,String> map2 = new HashMap<String,String>();
//		map2.put("uneIdent", "arab");
//		map2.put("name", "阿拉伯文");
//		Map<String,String> map3 = new HashMap<String,String>();
//		map3.put("uneIdent", "german");
//		map3.put("name", "德语");
//		llangage.add(map);
//		llangage.add(map2);
//		llangage.add(map3);
//		return llangage;
		return scheduleService.getLanguageDataForPublic();
	}

	@Override
	public List<ConHall> getListConHall(String id) {
		DetachedCriteria dc = DetachedCriteria.forClass(ConHall.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", id));
		return conHallDao.findByDetachedCriteria(dc);
	}

	@Override
	public boolean saveMultiEntity(String[] conHallList, String conHallMain) {
		boolean result = false;
		String unique = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(conHallMain)){
			ConHall conHall = JSON.parseObject(String.valueOf(conHallMain), ConHall.class); 
			//BeanUtils.copyProperties(memberGuest, member);
			if (StringUtils.isBlank(conHall.getId())) {
				conHall.setUniqueCode(unique);
				conHallDao.save(conHall);
				if(null != conHallList && conHallList.length > 0){
					for (String str : conHallList) {
						if(StringUtils.isNotBlank(str)){
							ConHall conHallLan = JSON.parseObject(str, ConHall.class);
							if(null != conHallLan && StringUtils.isNotBlank(conHallLan.getLanguage()) && StringUtils.isNotBlank(conHallLan.getName())){
								conHallLan.setType(conHall.getType());
								conHallLan.setIsIndoor(conHall.getIsIndoor());
								if("0".equals(conHallLan.getIsIndoor())){
									conHallLan.setDeskNumber(conHall.getDeskNumber());
									conHallLan.setTheatreNumber(conHall.getTheatreNumber());
								}else{
									conHallLan.setIndoorUnique(conHall.getIndoorUnique());
								}
								conHallLan.setUniqueCode(unique);
								conHallDao.save(conHallLan);
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
	public boolean updateMultiEntity(String[] conHallList, String conHallMain) {
		boolean result = false;
		if(StringUtils.isNotBlank(conHallMain)){
			ConHall conHall = JSON.parseObject(String.valueOf(conHallMain), ConHall.class); 
			if (StringUtils.isNotBlank(conHall.getId()) && StringUtils.isNotBlank(conHall.getUniqueCode())) {
				if("0".equals(conHall.getIsIndoor())){
					conHall.setIndoorUnique(null);
				}else{
					conHall.setDeskNumber(null);
					conHall.setTheatreNumber(null);
				}
				conHallDao.update(conHall);
				String unique = conHall.getUniqueCode();
				if(null != conHallList && conHallList.length > 0){
					for (String str : conHallList) {
						if(StringUtils.isNotBlank(str)){
							ConHall conHallLan = JSON.parseObject(str, ConHall.class);
							if(null != conHallLan){
								if(StringUtils.isNotBlank(conHallLan.getId())){
									if(StringUtils.isNotBlank(conHallLan.getLanguage()) && StringUtils.isNotBlank(conHallLan.getName())){
										conHallLan.setType(conHall.getType());
										conHallLan.setIsIndoor(conHall.getIsIndoor());
										if("0".equals(conHallLan.getIsIndoor())){
											conHallLan.setDeskNumber(conHall.getDeskNumber());
											conHallLan.setTheatreNumber(conHall.getTheatreNumber());
											conHallLan.setIndoorUnique(null);
										}else{
											conHallLan.setIndoorUnique(conHall.getIndoorUnique());
											conHallLan.setDeskNumber(null);
											conHallLan.setTheatreNumber(null);
										}
										conHallDao.update(conHallLan);
									} else {
										conHallLan.setEnable(false);
										conHallDao.update(conHallLan);
									}
								} else {
									if(StringUtils.isNotBlank(conHallLan.getLanguage()) && StringUtils.isNotBlank(conHallLan.getName())){
										conHallLan.setType(conHall.getType());
										conHallLan.setIsIndoor(conHall.getIsIndoor());
										if("0".equals(conHallLan.getIsIndoor())){
											conHallLan.setDeskNumber(conHall.getDeskNumber());
											conHallLan.setTheatreNumber(conHall.getTheatreNumber());
										}else{
											conHallLan.setIndoorUnique(conHall.getIndoorUnique());
										}
										conHallLan.setUniqueCode(unique);
										conHallDao.save(conHallLan);
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
	public void deleteByUniqueCode(String uniqueCode) {
//		String hql = " delete from ConHall where uniqueCode = ?";
//		conHallDao.executeHQL(hql, uniqueCode);
		DetachedCriteria dc = DetachedCriteria.forClass(ConHall.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", uniqueCode));
		List<ConHall> conHallList = conHallDao.findByDetachedCriteria(dc);
		if(conHallList != null&&conHallList.size()>0){
			for(int i = 0;i<conHallList.size();i++){
				ConHall conHall = conHallList.get(i);
				conHall.setEnable(false);
				conHallDao.update(conHall);
			}
		}
	}

	@Override
	public List<ConHall> getConHallList() {
		DetachedCriteria dc = DetachedCriteria.forClass(ConHall.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", "zh"));
		dc.addOrder(GBKOrder.desc("name"));
		return conHallDao.findByDetachedCriteria(dc);
	}

}
