package com.smartdot.meeting.server.modules.jpushentiy.service;

import java.util.List;

import com.smartdot.meeting.server.common.entity.JpushEntiy;

public interface IJpushEntiyService {
	public final static String SERVICE_NAME = "jpushEntiyService";
	
	
	public final static String PAGE_PATH_NAME = "/jpush/jpush";

	/**
	 * 全部查看JpushEntiy
	 * 
	 * @return List
	 */
	public List<JpushEntiy> findAll();

	/**
	 * 根据ID查找JpushEntiy
	 * 
	 * @param id
	 *            可以根据ID查看JpushEntiy中的字段
	 * @return JpushEntiy
	 */
	public JpushEntiy getJpushEntiyById(String id);

	/**
	 * 添加JpushEntiy
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(JpushEntiy instance);
	
	/**
	 * 添加多个JpushEntiy对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<JpushEntiy> instanceList);

	/**
	 * 修改JpushEntiy
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateJpushEntiy(JpushEntiy instance);

	/**
	 * 删除JpushEntiy
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(JpushEntiy instance);
	
	/**
	 * 删除JpushEntiy
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);


	/**
	 * JpushEntiy根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * JpushEntiy根据条件查询列表;
	 * 
	 * @param jpushEntiy
	 * @return
	 */
	public List<JpushEntiy> findByHQLAndParams(JpushEntiy jpushEntiy);
}
