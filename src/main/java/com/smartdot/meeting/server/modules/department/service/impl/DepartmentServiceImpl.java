package com.smartdot.meeting.server.modules.department.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.department.dao.IDepartmentDao;
import com.smartdot.meeting.server.modules.department.model.DepartmentFormList;
import com.smartdot.meeting.server.modules.department.service.IDepartmentService;
import com.smartdot.meeting.server.modules.guest.model.MemberGuest;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.entity.Guest;
import com.smartdot.meeting.server.common.entity.Member;

@Service(value = IDepartmentService.SERVICE_NAME)
public class DepartmentServiceImpl implements IDepartmentService {
	@Resource(name = IDepartmentDao.DAO_NAME)
	private IDepartmentDao departmentDao;

	@Override
	public List<Department> findAll() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Department.class);
		dc.add(Restrictions.eq("enable", true));
		//dc.addOrder(Order.asc("updateTimes"));
		dc.add(Restrictions.eq("language", "zh"));
		dc.add(Restrictions.sqlRestriction(" 1=1 order by convert(departname using gbk)"));
		return departmentDao.findByDetachedCriteria(dc);
	}

	@Override
	public Department getDepartmentById(String id) {
		
		return departmentDao.findById(id);
	}

	@Override
	public boolean save(DepartmentFormList form) {

		boolean result = false;
		if(null != form){
			Department department = JSON.parseObject(String.valueOf(form.getMemberGuest()), Department.class); 
			String[] arrayGuest = form.getArrayGuest();
			if (StringUtils.isBlank(department.getId())) {
				if(StringUtils.isNotBlank(department.getParentid())&&!department.getParentid().equals("0")) {
					Department departmentparent = departmentDao.get(department.getParentid());
					String pnum = createTimeGroupNum(departmentparent.getGroupnumber());
					department.setGroupnumber(pnum);
					department.setPgroupnumber(departmentparent.getGroupnumber());
				}else{
					String pnum = createTimeGroupNum("");
					department.setGroupnumber(pnum);
					department.setPgroupnumber("0");
				}
				departmentDao.save(department);
				if(null != arrayGuest && arrayGuest.length > 0){
					for (String str : arrayGuest) {
						if(StringUtils.isNotBlank(str)){
							Department department2 = new Department();
							department2 = JSON.parseObject(str, Department.class);
							if(null != department2 && StringUtils.isNotBlank(department2.getLanguage()) && StringUtils.isNotBlank(department2.getDepartname())){
								department2.setClassification(department.getClassification());
								department2.setGroupnumber(department.getGroupnumber());
								department2.setParentid(department.getParentid());
								department2.setParentname(department.getParentname());
								department2.setPgroupnumber(department.getPgroupnumber());
								department2.setType(department.getType());
								departmentDao.save(department2);
							}
						}
					}
				}
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean saveAll(List<Department> departmentList) {
		boolean flag = false;
		if (departmentList != null) {
			departmentDao.saveAll(departmentList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateDepartment(DepartmentFormList form) {
		boolean result = false;
		if(null != form){
			Department department = JSON.parseObject(String.valueOf(form.getMemberGuest()), Department.class); 
			String[] arrayGuest = form.getArrayGuest();
			if (StringUtils.isNotBlank(department.getId())) {
				departmentDao.update(department);
				if(null != arrayGuest && arrayGuest.length > 0){
					for (String str : arrayGuest) {
						if(StringUtils.isNotBlank(str)){
							Department department2 = JSON.parseObject(str, Department.class);
							if(null != department2){
								if(StringUtils.isNotBlank(department2.getId())){
									if(StringUtils.isNotBlank(department2.getLanguage()) && StringUtils.isNotBlank(department2.getDepartname())){
										department2.setClassification(department.getClassification());
										department2.setGroupnumber(department.getGroupnumber());
										department2.setParentid(department.getParentid());
										department2.setParentname(department.getParentname());
										department2.setPgroupnumber(department.getPgroupnumber());
										department2.setType(department.getType());
										departmentDao.update(department2);
									} else {
										department2.setEnable(false);
										departmentDao.update(department2);
									}
								} else {
									if(StringUtils.isNotBlank(department2.getLanguage()) && StringUtils.isNotBlank(department2.getDepartname())){
										department2.setClassification(department.getClassification());
										department2.setGroupnumber(department.getGroupnumber());
										department2.setParentid(department.getParentid());
										department2.setParentname(department.getParentname());
										department2.setPgroupnumber(department.getPgroupnumber());
										department2.setType(department.getType());
										departmentDao.save(department2);
									}
								}
							}
						}
					}
				}
			}
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateDept(DepartmentFormList form) {
		boolean result = false;
		String pnum;
		Department department = JSON.parseObject(String.valueOf(form.getMemberGuest()), Department.class); 
		String[] arrayGuest = form.getArrayGuest();
		if(StringUtils.isNotBlank(department.getId())&&!department.getParentid().equals("0")) {
			Department departmentparent = departmentDao.get(department.getParentid());
			pnum = updateTimeGroupNum(departmentparent.getGroupnumber(),department.getGroupnumber());
			department.setGroupnumber(pnum);
			department.setPgroupnumber(departmentparent.getGroupnumber());
		}else{
			pnum = updateTimeGroupNum("0",department.getGroupnumber());
			department.setGroupnumber(pnum);
			department.setPgroupnumber("0");
		}
		if(null != form&&pnum!="unenable"){
			if (StringUtils.isNotBlank(department.getId())) {
				departmentDao.update(department);
				if(null != arrayGuest && arrayGuest.length > 0){
					for (String str : arrayGuest) {
						if(StringUtils.isNotBlank(str)){
							Department department2 = JSON.parseObject(str, Department.class);
							if(null != department2){
								if(StringUtils.isNotBlank(department2.getId())){
									if(StringUtils.isNotBlank(department2.getLanguage()) && StringUtils.isNotBlank(department2.getDepartname())){
										department2.setClassification(department.getClassification());
										department2.setGroupnumber(department.getGroupnumber());
										department2.setParentid(department.getParentid());
										department2.setParentname(department.getParentname());
										department2.setPgroupnumber(department.getPgroupnumber());
										department2.setType(department.getType());
										departmentDao.update(department2);
									} else {
										department2.setEnable(false);
										departmentDao.update(department2);
									}
								} else {
									if(StringUtils.isNotBlank(department2.getLanguage()) && StringUtils.isNotBlank(department2.getDepartname())){
										department2.setClassification(department.getClassification());
										department2.setGroupnumber(department.getGroupnumber());
										department2.setParentid(department.getParentid());
										department2.setParentname(department.getParentname());
										department2.setPgroupnumber(department.getPgroupnumber());
										department2.setType(department.getType());
										departmentDao.save(department2);
									}
								}
							}
						}
					}
				}
			}
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean remove(Department department) {
		boolean flag = false;
		if (department != null) {
			Department instance = this.getDepartmentById(department.getId());
			departmentDao.delete(instance);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public List<Department> getListDepartment(String id) {
		
		Department department = departmentDao.get(id);
		DetachedCriteria dc = DetachedCriteria.forClass(Department.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("groupnumber", department.getGroupnumber()));
		
		return departmentDao.findByDetachedCriteria(dc);
	}
	
	@Override
	public boolean deleteAll(List<String> ids) {
		boolean flag = false;
		if (ids != null) {
			for (String id : ids) {
				this.deleteById(id);
			}
			flag = true;
		}
		return flag;
	}
	
	@Override
	public  Map<String, Object> findDepartmentByPage(Department department,Page<Department> pageQuery) {
		Page<Department> page = new Page<Department>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from Department obj where 1=1 ");
		Object[] params = paramList.toArray();
		return departmentDao.pageQueryByHql(page,
		"select count(obj) " + hql.toString(), hql.toString(), params,
		params);
	}
	
	@Override
	public List<Map<String, String>> getAllLanguage() {
		//查询数据字典的语言
		List<Map<String,String>> llangage = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("uneIdent", "en");
		map.put("name", "英文");
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("uneIdent", "arab");
		map2.put("name", "阿拉伯文");
		Map<String,String> map3 = new HashMap<String,String>();
		map3.put("uneIdent", "german");
		map3.put("name", "德语");
		llangage.add(map);
		llangage.add(map2);
		llangage.add(map3);
		return llangage;
	}
	
	@Override
	public List<MemberLanguage> getMemberLanguage(String id) {
		List<MemberLanguage> lMemberLanguage = new ArrayList<MemberLanguage>();
		List<Guest> lGuest = new ArrayList<Guest>();
		if(StringUtils.isNotBlank(id)){
//			Guest guest = guestDao.get(id);
//			DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
//			dc.add(Restrictions.eq("enable", true));
//			dc.add(Restrictions.eq("member", guest.getMember()));
//			lGuest = guestDao.findByDetachedCriteria(dc);
		}
		//查询数据字典的语言
		List<Map<String,String>> llangage = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("uneIdent", "en");
		map.put("name", "英文");
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("uneIdent", "arab");
		map2.put("name", "阿拉伯文");
		Map<String,String> map3 = new HashMap<String,String>();
		map3.put("uneIdent", "german");
		map3.put("name", "德语");
		llangage.add(map);
		llangage.add(map2);
		llangage.add(map3);
		if(null != llangage && llangage.size() > 0){
			for (Map<String, String> maps : llangage) {
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lGuest && lGuest.size() > 0){
					for (Guest guest : lGuest) {
						if(guest.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							continue;
						}
					}
				}
				lMemberLanguage.add(memberLanguage);
			}
		}
		
		return lMemberLanguage;
	}


	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				departmentDao.deleteById(id);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<Department> findByHQLAndParams(Department department) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<Department> sList = departmentDao
				.findByHQLAndParams(
						"from Department where 1=1",
						"");
		return sList;
	}
	public static void main(String[] args) {
		System.out.println(getMoreThenOneNum("0032001"));
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据当前编号，返回当前编号加1的编号
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 上午11:12:07
	 * </pre>
	 * </p>
	 */
	private static String getMoreThenOneNum(String currentMaxNum) {
		Integer theNum = new Integer(currentMaxNum)+1;
		String theNumStr = theNum+"";
		int length = currentMaxNum.length()-theNumStr.length();
		for(int i=0;i<length;i++){
			theNumStr = "0"+theNumStr;
		}
		return theNumStr;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	新增团时传父编号，返回新增的子团的最大编号（新增时，如果传null就代表是新建一级团）
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 上午10:47:34
	 * </pre>
	 * </p>
	 */
	@Override
	public String createTimeGroupNum(String parentGroupNum) {
		String currentMaxNum = "";
		if(StringUtils.isNotBlank(parentGroupNum)){
			List<Department> departmentList = departmentDao.getMaxChildrenNumByParentNum(parentGroupNum);
			if(departmentList!=null&&departmentList.size()>0){
				currentMaxNum = departmentList.get(0).getGroupnumber();
				return getMoreThenOneNum(currentMaxNum);
			}else{
				return parentGroupNum+"001";
			}
		}else{
			List<Department> departmentList = departmentDao.getMaxNumInOneLevel();
			if(departmentList!=null&&departmentList.size()>0){
				currentMaxNum = departmentList.get(0).getGroupnumber();
				return getMoreThenOneNum(currentMaxNum);
			}else{
				return "001";
			}
		}
	}

	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	修改团时传修改后的父编号，和当前团的编号，如果当前团有子就返回unenable，没有子就返回修改后的子团最大id
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 上午10:47:34
	 * </pre>
	 * </p>
	 */
	@Override
	public String updateTimeGroupNum(String parentGroupNum, String groupNum) {
		String currentMaxNum = "";
		List<Department> department = departmentDao.getMaxChildrenNumByParentNum(groupNum);
		if (department!=null&&department.size()>0) {
			return "unenable";
		}else{
			List<Department> departmentList = departmentDao.getMaxChildrenNumByParentNum(parentGroupNum);
			if(departmentList!=null&&departmentList.size()>0){
				currentMaxNum = departmentList.get(0).getGroupnumber();
				return getMoreThenOneNum(currentMaxNum);
			}else{
				return parentGroupNum+"001";
			}
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 *  删除的团的编号， 删除团时如果该团有子就返回unenable没有子团就返回该团编号
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月7日 上午10:47:34
	 * </pre>
	 * </p>
	 */
	@Override
	public String deleteTimeGroupNum(String groupNum) {
		String currentMaxNum = "";
		List<Department> department = departmentDao.getMaxChildrenNumByParentNum(groupNum);
		if (department!=null&&department.size()>0) {
			return "unenable";
		}else{
			currentMaxNum = groupNum;
		}
		return currentMaxNum;
	}
}
