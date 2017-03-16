package com.smartdot.meeting.server.modules.cloudgroup.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.CloudGroup;

public interface ICloudGroupService {
	public final static String SERVICE_NAME = "cloudGroupService";
	
	public final static String LIST_P = "/cloudGroup/list";
	
	public final static String ADET = "/cloudGroup/adet";
	
	
	public final static String DETAILS = "/cloudGroup/details";

	/**
	 * 全部查看CloudGroup
	 * 
	 * @return List
	 */
	public List<CloudGroup> findAll();

	/**
	 * 根据ID查找CloudGroup
	 * 
	 * @param id
	 *            可以根据ID查看CloudGroup中的字段
	 * @return CloudGroup
	 */
	public CloudGroup getCloudGroupById(String id);

	/**
	 * 添加CloudGroup
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(CloudGroup instance);
	
	/**
	 * 添加多个CloudGroup对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<CloudGroup> instanceList);

	/**
	 * 修改CloudGroup
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateCloudGroup(CloudGroup instance);

	/**
	 * 删除CloudGroup
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(CloudGroup instance);
	
	/**
	 * 删除CloudGroup
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * CloudGroup分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<CloudGroup> pageQuery);

	/**
	 * CloudGroup根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * CloudGroup根据条件查询列表;
	 * 
	 * @param cloudGroup
	 * @return
	 */
	public List<CloudGroup> findByHQLAndParams(CloudGroup cloudGroup);
	
	
	public List<CloudGroup> findByDetachedCriteria(DetachedCriteria detachedCriteria);
}
