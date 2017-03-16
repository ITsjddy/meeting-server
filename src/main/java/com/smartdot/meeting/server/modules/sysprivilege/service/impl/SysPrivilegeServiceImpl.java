package com.smartdot.meeting.server.modules.sysprivilege.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.sysprivilege.dao.ISysPrivilegeDao;
import com.smartdot.meeting.server.modules.sysprivilege.service.ISysPrivilegeService;
import com.smartdot.meeting.server.modules.sysrole.dao.IRolePrivilegeDao;
import com.smartdot.meeting.server.common.entity.RolePrivilege;
import com.smartdot.meeting.server.common.entity.SysPrivilege;
import com.smartdot.meeting.server.common.log.Audit;


@Service(value = ISysPrivilegeService.SERVICE_NAME)
public class SysPrivilegeServiceImpl implements ISysPrivilegeService {
	
	@Resource(name = ISysPrivilegeDao.DAO_NAME)
	private ISysPrivilegeDao sysPrivilegeDao;
	@Resource(name = IRolePrivilegeDao.DAO_NAME)
	private IRolePrivilegeDao rolePrivilegeDao;

	@Override
	public List<SysPrivilege> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(SysPrivilege.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.asc("updateTimes"));
		
		return sysPrivilegeDao.findByDetachedCriteria(dc);
	}

	@Override
	public SysPrivilege getSysPrivilegeById(String id) {
		
		return sysPrivilegeDao.findById(id);
	}

	@Audit(module= "SysPrivilege管理",action= "SysPrivilege信息添加",description= "SysPrivilege信息添加说明")
	@Override
	public boolean save(SysPrivilege sysPrivilege) {
		boolean flag = false;
		if (sysPrivilege != null) {
			sysPrivilegeDao.save(sysPrivilege);
			flag = true;
		}
		return flag;
	}

	@Audit(module= "SysPrivilege管理",action= "SysPrivilege多条信息添加",description= "SysPrivilege多条信息添加说明")
	@Override
	public boolean saveAll(List<SysPrivilege> sysPrivilegeList) {
		boolean flag = false;
		if (sysPrivilegeList != null) {
			sysPrivilegeDao.saveAll(sysPrivilegeList);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "SysPrivilege管理",action= "SysPrivilege信息修改",description= "SysPrivilege信息修改说明")
	@Override
	public boolean updateSysPrivilege(SysPrivilege sysPrivilege) {
		boolean flag = false;
		if (sysPrivilege != null) {
			sysPrivilegeDao.update(sysPrivilege);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(SysPrivilege sysPrivilege) {
		boolean flag = false;
		if (sysPrivilege != null) {
			SysPrivilege instance = this.getSysPrivilegeById(sysPrivilege.getId());
			sysPrivilegeDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Audit(module= "SysPrivilege管理",action= "SysPrivilege多选信息删除",description= "SysPrivilege多选信息删除说明")
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
	public Map<String, Object> pagedQuery(DetachedCriteria detachedCriteria, Page<SysPrivilege> pageQuery) {
		
		return sysPrivilegeDao.pagedQuery(detachedCriteria, pageQuery);
	}

	@Audit(module= "SysPrivilege管理",action= "SysPrivilege信息删除",description= "SysPrivilege信息删除说明")
	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				SysPrivilege sysPrivilege = sysPrivilegeDao.get(id);
				sysPrivilege.setEnable(false);
				sysPrivilegeDao.update(sysPrivilege);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<SysPrivilege> findByHQLAndParams(SysPrivilege sysPrivilege) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<SysPrivilege> sList = sysPrivilegeDao
				.findByHQLAndParams("from SysPrivilege where enable = true","");
		return sList;
	}
	
	@Override
	public Map<String, String> getResources() {
		
		return sysPrivilegeDao.getResources();
	}
	
	@Override
	public List<Map<String, String>> getUserPrivilegesByUserName(String userName) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		if(StringUtils.isBlank(userName)){
			return dataList;
		}
		dataList = sysPrivilegeDao.getUserPrivilegesByUserName(userName);
		return dataList;
	}
	
	@Override
	public List<Map<String, String>> getPrivilegesByUserName(String userName,String type,boolean state) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		if(StringUtils.isBlank(userName)){
			return dataList;
		}
		
		dataList = sysPrivilegeDao.getPrivilegesByUserName(userName,type,state);
		
		return dataList;
	}

	@Override
	public List<SysPrivilege> getListPrivileges(List<String> types) {
		return sysPrivilegeDao.getListPrivileges(types);
	}

	@Override
	public boolean saveRolePrivilege(List<String> authchedkList, String roleId) {
		boolean result = false;
		if(null != authchedkList && authchedkList.size() > 0){
			List<String> lPriIds = new ArrayList<String>();
			DetachedCriteria dc = DetachedCriteria.forClass(SysPrivilege.class);
			dc.add(Restrictions.eq("enable", true));
			dc.add(Restrictions.in("id", authchedkList));
			List<SysPrivilege> lpri = sysPrivilegeDao.findByDetachedCriteria(dc);
			if(null != lpri && lpri.size() > 0){
				for (SysPrivilege sysPrivilege : lpri) {
					String priId = sysPrivilege.getId();
					SysPrivilege parPri = sysPrivilege.getParent();
					lPriIds.add(priId);
					if(null != parPri){
						String pId = parPri.getId();
						lPriIds.add(pId);
						if(null != parPri.getParent()){
							String pId2 = parPri.getParent().getId();
							lPriIds.add(pId2);
						}
					}
				}
				for (String authchedk : lPriIds) {
					RolePrivilege ropri = new RolePrivilege();
					ropri.setRoleId(roleId);
					ropri.setPriId(authchedk);
					rolePrivilegeDao.save(ropri);
				}
				result = true;
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getListMapPrivileges(String types) throws Exception {
		
		return sysPrivilegeDao.getListMapPrivileges(types);
	}
	
}
