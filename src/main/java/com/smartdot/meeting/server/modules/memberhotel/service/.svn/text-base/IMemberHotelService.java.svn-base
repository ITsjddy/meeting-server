package com.smartdot.meeting.server.modules.memberhotel.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.MemberHotel;

public interface IMemberHotelService {
	public final static String SERVICE_NAME = "memberHotelService";

	/**
	 * 全部查看MemberHotel
	 * 
	 * @return List
	 */
	public List<MemberHotel> findAll();

	/**
	 * 根据ID查找MemberHotel
	 * 
	 * @param id
	 *            可以根据ID查看MemberHotel中的字段
	 * @return MemberHotel
	 */
	public MemberHotel getMemberHotelById(String id);

	/**
	 * 添加MemberHotel
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(MemberHotel instance);
	
	/**
	 * 添加多个MemberHotel对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<MemberHotel> instanceList);

	/**
	 * 修改MemberHotel
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateMemberHotel(MemberHotel instance);

	/**
	 * 删除MemberHotel
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(MemberHotel instance);
	
	/**
	 * 删除MemberHotel
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * MemberHotel分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<MemberHotel> pageQuery);

	/**
	 * MemberHotel根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * MemberHotel根据条件查询列表;
	 * 
	 * @param memberHotel
	 * @return
	 */
	public List<MemberHotel> findByHQLAndParams(MemberHotel memberHotel);

}
