package com.smartdot.meeting.server.modules.publicshcomment.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.PublicshComment;
import com.smartdot.meeting.server.modules.publicshcomment.model.PublicshCommentList;

public interface IpublicshCommentService {
	public final static String SERVICE_NAME = "publicshCommentService";

	/**
	 * 全部查看publicshComment
	 * 
	 * @return List
	 */
	public List<PublicshComment> findAll();

	/**
	 * 根据ID查找publicshComment
	 * 
	 * @param id
	 *            可以根据ID查看publicshComment中的字段
	 * @return publicshComment
	 */
	public PublicshCommentList getpublicshCommentById(String id);

	/**
	 * 添加publicshComment
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(PublicshComment instance);
	
	/**
	 * 添加多个publicshComment对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<PublicshComment> instanceList);

	/**
	 * 修改publicshComment
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updatepublicshComment(PublicshComment instance);

	/**
	 * 删除publicshComment
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(List<String> ids);

	/**
	 * publicshComment分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<PublicshComment> pageQuery);

	/**
	 * publicshComment根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * publicshComment根据条件查询列表;
	 * 
	 * @param publicshComment
	 * @return
	 */
	public PublicshCommentList getpublicshComment(String id,String status,String auditReason);
	
	public List<PublicshComment> findByHQLAndParams(PublicshComment publicshComment);

	public boolean editStatus(PublicshComment publicshComment);

	public List<PublicshComment> getPublicshBySubject(String id);
}
