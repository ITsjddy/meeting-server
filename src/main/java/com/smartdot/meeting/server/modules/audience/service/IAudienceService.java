package com.smartdot.meeting.server.modules.audience.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.Audience;
import com.smartdot.meeting.server.modules.audience.model.AudienceForm;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;

public interface IAudienceService {
	public final static String SERVICE_NAME = "audienceService";


	/**
	 * 根据ID查找Audience
	 * 
	 * @param id
	 *            可以根据ID查看Audience中的字段
	 * @return Audience
	 */
	public Audience getAudienceById(String id);

	/**
	 * 添加Audience
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(AudienceForm form);
	
	/**
	 * 修改Audience
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean update(AudienceForm form);

	/**
	 * 删除Audience
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * Audience分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Audience> pageQuery);

	/**
	 * Audience根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	

	public List<Map<String, String>> getAllLanguage();

	public List<Audience> getListAudience(String id);

	public List<MemberLanguage> getMemberLanguage(String id);

}
