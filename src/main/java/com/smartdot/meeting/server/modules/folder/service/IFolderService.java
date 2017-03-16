package com.smartdot.meeting.server.modules.folder.service;

import java.util.List;
import java.util.Map;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.Folder;

public interface IFolderService {
	public final static String SERVICE_NAME = "folderService";

	/**
	 * 全部查看Folder
	 * 
	 * @return List
	 */
	public List<Folder> findAll();

	/**
	 * 根据ID查找Folder
	 * 
	 * @param id
	 *            可以根据ID查看Folder中的字段
	 * @return Folder
	 */
	public Folder getFolderById(String id);

	/**
	 * 添加Folder
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(Folder instance);
	
	/**
	 * 添加多个Folder对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<Folder> instanceList);

	/**
	 * 修改Folder
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateFolder(Folder instance);

	/**
	 * 删除Folder
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(Folder instance);
	
	/**
	 * 删除Folder
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * Folder分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<Folder> findFolderByPage(Folder folder,Page<Folder> pageQuery);

	/**
	 * Folder根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * Folder根据条件查询列表;
	 * 
	 * @param folder
	 * @return
	 */
	public List<Folder> findByHQLAndParams(Folder folder);

	public Map<String, Object> pageList(Page<Folder> page, Map<String,Object> searchMap);

	public boolean saveAddFolder(String folder);

}
