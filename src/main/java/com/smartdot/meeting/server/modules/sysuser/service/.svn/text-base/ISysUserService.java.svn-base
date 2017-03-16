package com.smartdot.meeting.server.modules.sysuser.service;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.entity.SysRole;
import com.smartdot.meeting.server.common.entity.SysUser;

public interface ISysUserService {
	public final static String SERVICE_NAME = "sysUserService";

	/**
	 * 全部查看SysUser
	 * 
	 * @return List
	 */
	public List<SysUser> findAll();

	/**
	 * 根据ID查找SysUser
	 * 
	 * @param id
	 *            可以根据ID查看SysUser中的字段
	 * @return SysUser
	 */
	public SysUser getSysUserById(String id);

	/**
	 * 添加SysUser
	 * 
	 * @param instance
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean save(SysUser instance);
	
	/**
	 * 添加多个SysUser对象
	 * 
	 * @param instanceList
	 * @return boolean 添加成功返回true,失败为false;
	 */
	public boolean saveAll(List<SysUser> instanceList);

	/**
	 * 修改SysUser
	 * 
	 * @param instance
	 * @return boolean修改成功返回true,失败为false;
	 */
	public boolean updateSysUser(SysUser instance);

	/**
	 * 删除SysUser
	 * 
	 * @param instance
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean remove(SysUser instance);
	
	/**
	 * 删除SysUser
	 * 
	 * @param ids
	 * @return boolean删除成功返回true,失败为false;
	 */
	public boolean deleteAll(String[] ids);

	/**
	 * SysUser分页
	 * 
	 * @param page
	 * @return
	 */
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysUser> pageQuery);

	/**
	 * SysUser根据id 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * SysUser根据条件查询列表;
	 * 
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> findByHQLAndParams(SysUser sysUser);
	
	/**
	 * 全部查看SysRole
	 * 
	 * @return List
	 */
	public List<SysRole> findAllSysRole();
	
	public SysUser getUserName(String userName);
	
	public List<SysUser> getListUsers();
	
	/**
	 * 修改密码
	 * 
	 * @param id, password
	 * @return
	 */
	public boolean savePassword(String id, String password);
	
	public boolean checkRepeat(String id, String value);
}
