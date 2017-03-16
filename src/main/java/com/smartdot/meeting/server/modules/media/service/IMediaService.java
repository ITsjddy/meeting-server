package com.smartdot.meeting.server.modules.media.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.media.model.MediaForm;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.common.entity.Media;


public interface IMediaService {
	
	public final static String SERVICE_NAME = "mediaService";


	/**
	 * 根据ID查找Media
	 * 
	 * @param id
	 *            可以根据ID查看Media中的字段
	 * @return Media
	 */
	public Media getMediaById(String id);
	
	/**
	 * 添加Media
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(MediaForm instance);
	
	/**
	 * 修改Media
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean update(MediaForm instance);
	
	/**
	 * Media根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * 删除Media
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);
	
	/**
	 * Media根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Media> page);
	/**
	 * Guest根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public List<Media> findMediaByDC(DetachedCriteria detachedCriteria);
	
	/**
	 * 根据ID查找GuestAndServicePersonnelForm
	 * 
	 * @param id
	 *            可以根据ID查看GuestAndServicePersonnelForm中的字段
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
	public List<Media> getListMedia(String id);
}
