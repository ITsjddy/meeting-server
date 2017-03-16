package com.smartdot.meeting.server.modules.sysuser.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.util.EncodEncryUtil;
import com.smartdot.meeting.server.modules.sysrole.dao.ISysRoleDao;
import com.smartdot.meeting.server.modules.sysuser.dao.ISysUserDao;
import com.smartdot.meeting.server.modules.sysuser.service.ISysUserService;
import com.smartdot.meeting.server.common.entity.SysRole;
import com.smartdot.meeting.server.common.entity.SysUser;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ISysUserService.SERVICE_NAME)
public class SysUserServiceImpl implements ISysUserService {
	
	@Resource(name = ISysUserDao.DAO_NAME)
	private ISysUserDao sysUserDao;
	@Resource(name = ISysRoleDao.DAO_NAME)
	private ISysRoleDao sysRoleDao;

	@Override
	public List<SysUser> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return sysUserDao.findByDetachedCriteria(dc);
	}

	@Override
	public SysUser getSysUserById(String id) {
		
		return sysUserDao.findById(id);
	}
	
	@Audit(module= "SysUser管理",action= "SysUser信息添加",description= "SysUser信息添加说明")
	@Override
	public boolean save(SysUser sysUser) {
		boolean flag = false;
		if (sysUser != null) {
			String password = sysUser.getPassword();
			if(StringUtils.isNotBlank(password)){
				password = EncodEncryUtil.string2MD5(password);
				sysUser.setPassword(password);
			}
			sysUserDao.save(sysUser);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "SysUser管理",action= "SysUser多条信息添加",description= "SysUser多条信息添加说明")
	@Override
	public boolean saveAll(List<SysUser> sysUserList) {
		boolean flag = false;
		if (sysUserList != null) {
			sysUserDao.saveAll(sysUserList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "SysUser管理",action= "SysUser信息修改",description= "SysUser信息修改说明")
	@Override
	public boolean updateSysUser(SysUser sysUser) {
		boolean flag = false;
		if (sysUser != null) {
			sysUserDao.update(sysUser);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SysUser sysUser) {
		boolean flag = false;
		if (sysUser != null) {
			SysUser instance = this.getSysUserById(sysUser.getId());
			sysUserDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "SysUser管理",action= "SysUser多选信息删除",description= "SysUser多选信息删除说明")
	@Override
	public boolean deleteAll(String[] ids) {
		boolean flag = false;
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysUser> pageQuery) {
		
		return sysUserDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "SysUser管理",action= "SysUser信息删除",description= "SysUser信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				SysUser sysUser = sysUserDao.get(id);
				sysUser.setEnable(false);
				sysUserDao.update(sysUser);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<SysUser> findByHQLAndParams(SysUser sysUser) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<SysUser> sList = sysUserDao
				.findByHQLAndParams("from SysUser where enable = true","");
		return sList;
	}

	@Override
	public List<SysRole> findAllSysRole() {
		DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return sysRoleDao.findByDetachedCriteria(dc);
	}
	
	@Override
	public SysUser getUserName(String userName) {
		SysUser user = new SysUser();
		if(StringUtils.isBlank(userName)){
			return user;
		}
		
		user = sysUserDao.getUserName(userName);
		
		return user;
	}
	
	@Override
	public List<SysUser> getListUsers() {
		
		return sysUserDao.getListUsers();
	}

	@Override
	public boolean savePassword(String id, String password) {
		boolean result = false;
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(password)){
			SysUser sysUser = sysUserDao.get(id);
			password = EncodEncryUtil.string2MD5(password);
			sysUser.setPassword(password);
			sysUserDao.save(sysUser);
			result = true;
		}
		return result;
	}

	@Override
	public boolean checkRepeat(String id, String value) {
		boolean result = false;
		if(StringUtils.isNotBlank(value)){
			DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.eq("userName", value));
			if(StringUtils.isNotBlank(id)){
				dc.add(Restrictions.ne("id", id));
			}
			List<SysUser> luser = sysUserDao.findByDetachedCriteria(dc);
			if(null != luser && luser.size() > 0){
				result = true;
			}
		}
		return result;
	}
	
}
