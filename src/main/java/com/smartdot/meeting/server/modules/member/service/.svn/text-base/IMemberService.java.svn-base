package com.smartdot.meeting.server.modules.member.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.Member;


public interface IMemberService {
	
	public final static String SERVICE_NAME = "memberService";

	/**
	 * 全部查看Member
	 * 
	 * @return List
	 */
	public List<Member> findAll();
	
	/**
	 * 添加Member
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(Member instance);
	
	/**
	 * 修改Member
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean update(Member instance);
	
	/**
	 * 删除Member
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);
	
	/**
	 * Member根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	/**
	 * Member根据id 查询
	 * 
	 * @param id
	 * @return
	 */
	public Member findById(String id);
	
	public List<Member> findByDetachedCriteria(DetachedCriteria detachedCriteria);
	
	public boolean savePassword(String id, String password);

	public boolean checkRepeat(String id, String value);

	public List<DataDictionary> getDatadictionary(String type);
	
}
