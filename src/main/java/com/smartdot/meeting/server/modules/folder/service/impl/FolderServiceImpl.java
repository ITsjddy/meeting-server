package com.smartdot.meeting.server.modules.folder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.folder.dao.IFolderDao;
import com.smartdot.meeting.server.modules.folder.service.IFolderService;
import com.smartdot.meeting.server.common.entity.Exhibitors;
import com.smartdot.meeting.server.common.entity.Folder;
import com.smartdot.meeting.server.common.entity.News;

@Service(value = IFolderService.SERVICE_NAME)
public class FolderServiceImpl implements IFolderService {
	@Resource(name = IFolderDao.DAO_NAME)
	private IFolderDao folderDao;
	

	@Override
	public List<Folder> findAll() {
		return folderDao.findAll();
	}

	@Override
	public Folder getFolderById(String id) {
		
		return folderDao.findById(id);
	}

	@Override
	public boolean save(Folder folder) {
		boolean flag = false;
		if (folder != null) {
			folderDao.save(folder);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean saveAll(List<Folder> folderList) {
		boolean flag = false;
		if (folderList != null) {
			folderDao.saveAll(folderList);
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean updateFolder(Folder folder) {
		boolean flag = false;
		if (folder != null) {
			folderDao.update(folder);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean remove(Folder folder) {
		boolean flag = false;
		if (folder != null) {
			Folder instance = this.getFolderById(folder.getId());
			folderDao.delete(instance);
			flag = true;
		}
		return flag;
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
	public Page<Folder> findFolderByPage(Folder folder,Page<Folder> pageQuery) {
		Page<Folder> page = new Page<Folder>();
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setPageSize(pageQuery.getPageSize());
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" from Folder obj where 1=1 ");
		Object[] params = paramList.toArray();
		return (Page<Folder>) folderDao.pageQueryByHql(page,
		"select count(obj) " + hql.toString(), hql.toString(), params,
		params);
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (id != null) {
			try {
				Folder folder = folderDao.get(id);
				folder.setEnable(false);
				folderDao.update(folder);
//				memberDao.delete(exhibitors.getMember().getMemberId());
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<Folder> findByHQLAndParams(Folder folder) {
		//findByHQLAndParams("hql","参数".......); hql语句 和条件自我完善;
		List<Folder> sList = folderDao
				.findByHQLAndParams(
						"from Folder where 1=1",
						"");
		return sList;
	}

	@Override
	public Map<String, Object> pageList(Page<Folder> page, Map<String,Object> searchMap) {
		LinkedHashMap<String,String> orderByMap = new LinkedHashMap<String,String>();
		orderByMap.put("createTimes", "desc");
		Map<String, Object> pageData = folderDao.pageQuery(searchMap,page,orderByMap,false);
		return pageData;
	}

	@Override
	public boolean saveAddFolder(String folderJsonStr) {
		boolean result = false;
		if(StringUtils.isNotBlank(folderJsonStr)){
			Folder folder = JSON.parseObject(String.valueOf(folderJsonStr), Folder.class); 
			if (StringUtils.isBlank(folder.getId())) {
//				if(News.NEWS_LOCATION_CAROUSEL.equals(folder.getLocation())&&folder.getVieverSort()==null){
//					folder.setVieverSort(1);
//				}
//				if(folder.getSortNumber()==null){
//					folder.setSortNumber(1);
//				}
				if(StringUtils.isNotBlank(folder.getFileName())){
					folder.setFileName(folder.getFileName());
				}
				if(StringUtils.isNotBlank(folder.getFileType())){
					folder.setFileName(folder.getFileType());
				}
				if(StringUtils.isNotBlank(folder.getExplains())){
					folder.setFileName(folder.getExplains());
				}
				if(StringUtils.isNotBlank(folder.getFilePath())){
					folder.setFileName(folder.getFilePath());
				}
				folderDao.save(folder);
				result = true;
			}
		}
		return result;
	}

	
}
