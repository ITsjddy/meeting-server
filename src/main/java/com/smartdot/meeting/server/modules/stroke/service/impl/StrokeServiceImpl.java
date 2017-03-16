package com.smartdot.meeting.server.modules.stroke.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.guest.model.MemberGuest;
import com.smartdot.meeting.server.modules.stroke.dao.IStrokeDao;
import com.smartdot.meeting.server.modules.stroke.model.StrokeForm;
import com.smartdot.meeting.server.modules.stroke.service.IStrokeService;
import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.ConHall;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.entity.Stroke;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IStrokeService.SERVICE_NAME)
public class StrokeServiceImpl implements IStrokeService {
	
	@Resource(name = IStrokeDao.DAO_NAME)
	private IStrokeDao strokeDao;

	@Override
	public List<Stroke> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(Stroke.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return strokeDao.findByDetachedCriteria(dc);
	}

	@Override
	public Stroke getStrokeById(String id) {
		
		return strokeDao.findById(id);
	}

	@Audit(module= "Stroke管理",action= "Stroke信息添加",description= "Stroke信息添加说明")
	@Override
	public boolean save(Stroke stroke) {
		boolean flag = false;
		if (stroke != null) {
			strokeDao.save(stroke);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "Stroke管理",action= "Stroke多条信息添加",description= "Stroke多条信息添加说明")
	@Override
	public boolean saveAll(List<Stroke> strokeList) {
		boolean flag = false;
		if (strokeList != null) {
			strokeDao.saveAll(strokeList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "Stroke管理",action= "Stroke信息修改",description= "Stroke信息修改说明")
	@Override
	public boolean updateStroke(Stroke stroke) {
		boolean flag = false;
		if (stroke != null) {
			strokeDao.update(stroke);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(Stroke stroke) {
		boolean flag = false;
		if (stroke != null) {
			Stroke instance = this.getStrokeById(stroke.getId());
			strokeDao.delete(instance);
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Stroke> pageQuery) {
		
		return strokeDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "Stroke管理",action= "Stroke信息删除",description= "Stroke信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				Stroke stroke = strokeDao.get(id);
				
				DetachedCriteria dc = DetachedCriteria.forClass(Stroke.class);
				dc.add(Restrictions.eq("enable", true));
				dc.add(Restrictions.eq("uuid", stroke.getUuid()));
				List<Stroke> stroke2 = strokeDao.findByDetachedCriteria(dc);
				if(stroke2 != null&&stroke2.size()>0){
					for(int i = 0;i<stroke2.size();i++){
						Stroke stro = stroke2.get(i);
						stro.setEnable(false);
						strokeDao.update(stro);
					}
				}
//				stroke.setEnable(false);
//				strokeDao.update(stroke);
				
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<Stroke> findByHQLAndParams(Stroke stroke) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<Stroke> sList = strokeDao
				.findByHQLAndParams("from Stroke where enable = true","");
		return sList;
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
	public List<Stroke> getListStoke(String id) {
		Stroke stroke = strokeDao.get(id);
		DetachedCriteria dc = DetachedCriteria.forClass(Stroke.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uuid", stroke.getUuid()));
		return strokeDao.findByDetachedCriteria(dc);
	}

	@Override
	public boolean update(StrokeForm form) {
		Stroke stroke = JSON.parseObject(String.valueOf(form.getMemberStroke()), Stroke.class); 
		String[] arrayGuest = form.getArrayStroke();
		String unique = UUID.randomUUID().toString();
		boolean result = false;
		if(null != form){
			if (StringUtils.isNotBlank(stroke.getId())) {
				stroke.setUuid(unique);
				strokeDao.update(stroke);
				if(null != arrayGuest && arrayGuest.length > 0){
					for (String str : arrayGuest) {
						if(StringUtils.isNotBlank(str)){
							Stroke stroke2 = JSON.parseObject(String.valueOf(str), Stroke.class); 
							if(null != stroke2){
								if(StringUtils.isNotBlank(stroke2.getId())){
									if(StringUtils.isNotBlank(stroke2.getLanguage()) && StringUtils.isNotBlank(stroke2.getTitle())){
										stroke2.setUuid(stroke.getUuid());
										strokeDao.update(stroke2);
									} else {
										stroke2.setEnable(false);
										stroke2.setUuid(stroke.getUuid());
										strokeDao.update(stroke2);
									}
								} else {
									if(StringUtils.isNotBlank(stroke2.getLanguage()) && StringUtils.isNotBlank(stroke2.getTitle())){
										stroke2.setMemberId(stroke.getMemberId());
										stroke2.setUuid(stroke.getUuid());
										strokeDao.save(stroke2);
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
	public boolean saveMultiEntity( String conHallMain,String[] conHallList) {
		boolean result = false;
		String unique = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(conHallMain)){
			Stroke stroke = JSON.parseObject(String.valueOf(conHallMain), Stroke.class); 
			if (StringUtils.isBlank(stroke.getId())) {
				stroke.setUuid(unique);
				strokeDao.save(stroke);
				if(null != conHallList && conHallList.length > 0){
					for (String str : conHallList) {
						if(StringUtils.isNotBlank(str)){
							Stroke stroke2 = JSON.parseObject(str, Stroke.class);
							if(null != stroke2 && StringUtils.isNotBlank(stroke2.getLanguage()) && StringUtils.isNotBlank(stroke2.getTitle())){
								stroke2.setMemberId(stroke.getMemberId());
								stroke2.setUuid(stroke.getUuid());
								strokeDao.save(stroke2);
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	
	}
	
}
