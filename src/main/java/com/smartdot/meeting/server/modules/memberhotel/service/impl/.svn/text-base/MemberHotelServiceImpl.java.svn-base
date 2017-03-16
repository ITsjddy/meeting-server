package com.smartdot.meeting.server.modules.memberhotel.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.memberhotel.dao.IMemberHotelDao;
import com.smartdot.meeting.server.modules.memberhotel.service.IMemberHotelService;
import com.smartdot.meeting.server.common.entity.MemberHotel;
import com.smartdot.meeting.server.common.entity.Stroke;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = IMemberHotelService.SERVICE_NAME)
public class MemberHotelServiceImpl implements IMemberHotelService {
	
	@Resource(name = IMemberHotelDao.DAO_NAME)
	private IMemberHotelDao memberHotelDao;

	@Override
	public List<MemberHotel> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(MemberHotel.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return memberHotelDao.findByDetachedCriteria(dc);
	}

	@Override
	public MemberHotel getMemberHotelById(String id) {
		
		return memberHotelDao.findById(id);
	}

	@Audit(module= "MemberHotel管理",action= "MemberHotel信息添加",description= "MemberHotel信息添加说明")
	@Override
	public boolean save(MemberHotel memberHotel) {
		boolean flag = false;
		if (memberHotel != null) {
			memberHotelDao.save(memberHotel);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "MemberHotel管理",action= "MemberHotel多条信息添加",description= "MemberHotel多条信息添加说明")
	@Override
	public boolean saveAll(List<MemberHotel> memberHotelList) {
		boolean flag = false;
		if (memberHotelList != null) {
			memberHotelDao.saveAll(memberHotelList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "MemberHotel管理",action= "MemberHotel信息修改",description= "MemberHotel信息修改说明")
	@Override
	public boolean updateMemberHotel(MemberHotel memberHotel) {
		boolean flag = false;
		if (memberHotel != null) {
			memberHotelDao.update(memberHotel);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(MemberHotel memberHotel) {
		boolean flag = false;
		if (memberHotel != null) {
			MemberHotel instance = this.getMemberHotelById(memberHotel.getId());
			memberHotelDao.delete(instance);
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<MemberHotel> pageQuery) {
		
		return memberHotelDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "MemberHotel管理",action= "MemberHotel信息删除",description= "MemberHotel信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				MemberHotel memberHotel = memberHotelDao.get(id);
				memberHotel.setEnable(false);
				memberHotelDao.update(memberHotel);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<MemberHotel> findByHQLAndParams(MemberHotel memberHotel) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<MemberHotel> sList = memberHotelDao
				.findByHQLAndParams("from MemberHotel where enable = true","");
		return sList;
	}

	
}
