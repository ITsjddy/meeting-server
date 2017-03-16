package com.smartdot.meeting.server.modules.fileupload.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.FileUpload;

public interface IFileUploadService {
	public final static String SERVICE_NAME = "fileUploadService";

	/**
	 * 全部查看FileUpload
	 * 
	 * @return List
	 */
	public List<FileUpload> findAll();

	/**
	 * 根据ID查找FileUpload
	 * 
	 * @param id
	 *            可以根据ID查看FileUpload中的字段
	 * @return FileUpload
	 */
	public FileUpload getFileUploadById(String id);

	/**
	 * 添加FileUpload
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(FileUpload instance);
	
	/**
	 * 添加多个FileUpload对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<FileUpload> instanceList);

	/**
	 * 修改FileUpload
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateFileUpload(FileUpload instance);

	/**
	 * 删除FileUpload
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(FileUpload instance);
	
	/**
	 * 删除FileUpload
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * FileUpload分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<FileUpload> pageQuery);

	/**
	 * FileUpload根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * FileUpload根据条件查询列表;
	 * 
	 * @param fileUpload
	 * @return
	 */
	public List<FileUpload> findByHQLAndParams(FileUpload fileUpload);

	public List<FileUpload> getFileUploadByGroupId(String groupid) ;
}
