package com.smartdot.meeting.server.modules.exhibitors.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.Exhibitors;
import com.smartdot.meeting.server.modules.exhibitors.model.ExhibitorsForm;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;


public interface IExhibitorsService {
	
	public final static String SERVICE_NAME = "exhibitorsService";


	/**
	 * 根据ID查找Exhibitors
	 * 
	 * @param id
	 *            可以根据ID查看Exhibitors中的字段
	 * @return Exhibitors
	 */
	public Exhibitors getExhibitorsById(String id);
	
	/**
	 * 添加Exhibitors
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(ExhibitorsForm form);
	
	/**
	 * 修改Exhibitors
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean update(ExhibitorsForm form);
	
	/**
	 * Exhibitors根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * 删除Exhibitors
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);
	
	/**
	 * Exhibitors根据条件分页查询列表;
	 * 
	 * @param criteria
	 * @param page
	 * @return Map集合
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<Exhibitors> page);

	public List<Map<String, String>> getAllLanguage();

	public List<Exhibitors> getListExhibitors(String id);

	public List<MemberLanguage> getMemberLanguage(String id);
	
}
