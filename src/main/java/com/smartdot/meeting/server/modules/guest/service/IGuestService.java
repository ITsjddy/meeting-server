package com.smartdot.meeting.server.modules.guest.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.guest.model.GuestAndServicePersonnelForm;
import com.smartdot.meeting.server.modules.guest.model.GuestForm;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.common.entity.Guest;


public interface IGuestService {
	
	public final static String SERVICE_NAME = "guestService";


	/**
	 * 根据ID查找Guest
	 * 
	 * @param id
	 *            可以根据ID查看Guest中的字段
	 * @return Guest
	 */
	public Guest getGuestById(String id);
	
	/**
	 * 添加Guest
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(GuestForm instance);
	
	/**
	 * 修改Guest
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean update(GuestForm instance);
	
	/**
	 * Guest根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * 删除Guest
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);
	
	/**
	 * Guest根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Guest> page);
	/**
	 * 根据DetachedCriteria查询嘉宾集合
	 */
	public List<Guest> findGuestByDC(DetachedCriteria detachedCriteria);
	
	/**
	 * 根据ID查找GuestAndServicePersonnelForm
	 * 
	 * @param id
	 *            可以根据ID查看GuestAndServicePersonnelForm中的字段
	 * @return Guest
	 */
	public List<GuestAndServicePersonnelForm> getServicePersonnelByGuestId(String guestId);
	
	/**
	 * 删除GuestAndServicePersonnel
	 * 
	 * @param id
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteGuestAndServicePersonnel(String guestId);

	/**
	 * 保存GuestAndServicePersonnel
	 * 
	 * @param id
	 * @return boolean添加成功返回true,失败为false;
	 */
	public boolean saveGuestAndServicePersonnel(String guestId, String[] lguestSer);
	
	/**
	 * 根据memberId查找List<MemberLanguage>
	 * 
	 * @param memberId
	 *            根据memberId查找List<MemberLanguage>
	 * @return Guest
	 */
	public List<MemberLanguage> getMemberLanguage(String memberId);

	/**
	 * 根据memberId查找List<MemberLanguage>
	 * 
	 * @param memberId
	 *            根据memberId查找List<MemberLanguage>
	 * @return Guest
	 */
	public List<Map<String, String>> getAllLanguage();

	/**
	 * 根据memberId查找List<MemberLanguage>
	 * 
	 * @param memberId
	 *            根据memberId查找List<MemberLanguage>
	 * @return Guest
	 */
	public List<Guest> getListGuest(String id);
	
	public List<Map<String, Object>> queryALLServicePersonnel() throws Exception;
	
	public void savetemplate(Guest guest)throws Exception;
	
	public List<Map<String, Object>> findServicePersonnelForName(String name) throws Exception;
	/**
	 * 大会点评模块查询发布人和回复人
	 * */
	public List<Guest> findByDetachedCriteria(DetachedCriteria dcpc);

}
