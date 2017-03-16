package com.smartdot.meeting.server.modules.newsColumn.service.impl;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.NewsColumn;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.newsColumn.dao.INewsColumnDao;
import com.smartdot.meeting.server.modules.newsColumn.service.INewsColumnService;
import com.smartdot.meeting.server.modules.schedule.service.IScheduleService;


@Service(value = INewsColumnService.SERVICE_NAME)
public class NewsColumnServiceImpl implements INewsColumnService {
	
	@Resource(name = INewsColumnDao.DAO_NAME)
	private INewsColumnDao newsColumnDao;

	@Resource(name = IScheduleService.SERVICE_NAME)
	private IScheduleService scheduleService;
	@Override
	public Map<String, Object> pageNewsColumnList(DetachedCriteria detachedCriteria,Page<NewsColumn> page) {
		Map<String, Object> pageData = newsColumnDao.pagedQuery(detachedCriteria,page);
		return pageData;
	}

	@Override
	public NewsColumn getEntityById(String id) {
		return newsColumnDao.findById(id);
	}

	@Override
	public void saveEntity(NewsColumn newsColumn) {
		newsColumnDao.saveOrUpdate(newsColumn);
	}

	@Override
	public void deleteByUniqueCode(String uniqueCode) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", uniqueCode));
		List<NewsColumn> newsColumnList = newsColumnDao.findByDetachedCriteria(dc);
		if(newsColumnList != null&&newsColumnList.size()>0){
			for(int i = 0;i<newsColumnList.size();i++){
				NewsColumn newsColumn = newsColumnList.get(i);
				newsColumn.setEnable(false);
				newsColumnDao.update(newsColumn);
			}
		}
	}

	@Override
	public List<Map<String, String>> getAllLanguage() {

		//查询数据字典的语言
//		List<Map<String,String>> llangage = new ArrayList<Map<String,String>>();
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("uneIdent", "en");
//		map.put("name", "英文");
//		Map<String,String> map2 = new HashMap<String,String>();
//		map2.put("uneIdent", "arab");
//		map2.put("name", "阿拉伯文");
//		Map<String,String> map3 = new HashMap<String,String>();
//		map3.put("uneIdent", "german");
//		map3.put("name", "德语");
//		llangage.add(map);
//		llangage.add(map2);
//		llangage.add(map3);
//		return llangage;
		return scheduleService.getLanguageDataForPublic();
	}

	@Override
	public List<NewsColumn> getListNewsColumn(String id) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("uniqueCode", id));
		return newsColumnDao.findByDetachedCriteria(dc);
	
	}

	@Override
	public boolean saveMultiEntity(String[] newsColumnList, String newsColumnMain) {
		boolean result = false;
		String unique = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(newsColumnMain)){
			NewsColumn newsColumn = JSON.parseObject(String.valueOf(newsColumnMain), NewsColumn.class);
			if (StringUtils.isBlank(newsColumn.getId())) {
				newsColumn.setUniqueCode(unique);
				newsColumnDao.save(newsColumn);
				if(null != newsColumnList && newsColumnList.length > 0){
					for (String str : newsColumnList) {
						if(StringUtils.isNotBlank(str)){
							NewsColumn newsColumnLan = JSON.parseObject(str, NewsColumn.class);
							if(null != newsColumnLan && StringUtils.isNotBlank(newsColumnLan.getLanguage())){
								newsColumnLan.setType(newsColumn.getType());
								newsColumnLan.setColumnId(newsColumn.getColumnId());
								newsColumnLan.setUniqueCode(newsColumn.getUniqueCode());
								newsColumnDao.save(newsColumnLan);
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
	public List<NewsColumn> getAllNewsColumnByType(String type,String language) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", language));
		dc.add(Restrictions.eq("type", type));
		return newsColumnDao.findByDetachedCriteria(dc);
	}

	@Override
	public boolean updateMultiEntity(String[] newsColumnList, String newsColumnMain) {
		boolean result = false;
		if(StringUtils.isNotBlank(newsColumnMain)){
			NewsColumn newsColumn = JSON.parseObject(String.valueOf(newsColumnMain), NewsColumn.class);
			if (StringUtils.isNotBlank(newsColumn.getId())&&StringUtils.isNotBlank(newsColumn.getUniqueCode())) {
				newsColumnDao.update(newsColumn);
				if(null != newsColumnList && newsColumnList.length > 0){
					for (String str : newsColumnList) {
						if(StringUtils.isNotBlank(str)){
							NewsColumn newsColumnLan = JSON.parseObject(str, NewsColumn.class);
							if(StringUtils.isNotBlank(newsColumnLan.getId())){
								if(null != newsColumnLan && StringUtils.isNotBlank(newsColumnLan.getLanguage())){
									newsColumnLan.setType(newsColumn.getType());
									newsColumnLan.setColumnId(newsColumn.getColumnId());
									newsColumnLan.setUniqueCode(newsColumn.getUniqueCode());
									newsColumnDao.update(newsColumnLan);
								}else{
									newsColumnLan.setEnable(false);
									newsColumnDao.update(newsColumnLan);
								}
							}else{
								if(null != newsColumnLan && StringUtils.isNotBlank(newsColumnLan.getLanguage())){
									newsColumnLan.setType(newsColumn.getType());
									newsColumnLan.setColumnId(newsColumn.getColumnId());
									newsColumnLan.setUniqueCode(newsColumn.getUniqueCode());
									newsColumnDao.save(newsColumnLan);
								}
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
	public List<NewsColumn> getAllCategoryByColumnId(String columnId,String language) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_CATEGORY));
		dc.add(Restrictions.eq("id", columnId));
		List<NewsColumn> newsColumns = newsColumnDao.findByDetachedCriteria(dc);
		if(newsColumns==null||newsColumns.size()<=0){
			return null;
		}
		DetachedCriteria dc1 = DetachedCriteria.forClass(NewsColumn.class);
		dc1.add(Restrictions.eq("enable", true));
		dc1.add(Restrictions.eq("language", "zh"));
		dc1.add(Restrictions.eq("uniqueCode", newsColumns.get(0).getUniqueCode()));
		List<NewsColumn> all = newsColumnDao.findByDetachedCriteria(dc1);
		
		DetachedCriteria dc2 = DetachedCriteria.forClass(NewsColumn.class);
		dc2.add(Restrictions.eq("enable", true));
		dc2.add(Restrictions.eq("language", language));
		dc2.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_COLUMN));
		dc2.add(Restrictions.eq("columnId", all.get(0).getId()));
		return newsColumnDao.findByDetachedCriteria(dc2);
	}

	@Override
	public NewsColumn findNewsColumnByIdAndLanguage(String columnId, String language) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language",language));
		dc.add(Restrictions.eq("id", columnId));
		List<NewsColumn> list = newsColumnDao.findByDetachedCriteria(dc);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<NewsColumn> getAllCategoryByColumnId(String columnId) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("language", "zh"));
		dc.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_COLUMN));
		dc.add(Restrictions.eq("columnId", columnId));
		return newsColumnDao.findByDetachedCriteria(dc);
	}

	@Override
	public List<NewsColumn> getAllCategoryByCategoryId(String categoryId, String language) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_CATEGORY));
		dc.add(Restrictions.eq("id", categoryId));
		List<NewsColumn> newsColumns = newsColumnDao.findByDetachedCriteria(dc);
		if(newsColumns==null||newsColumns.size()<=0){
			return null;
		}
		DetachedCriteria dc1 = DetachedCriteria.forClass(NewsColumn.class);
		dc1.add(Restrictions.eq("enable", true));
		dc1.add(Restrictions.eq("language", "zh"));
		dc1.add(Restrictions.eq("uniqueCode", newsColumns.get(0).getUniqueCode()));
		List<NewsColumn> all = newsColumnDao.findByDetachedCriteria(dc1);
		
		DetachedCriteria dc2 = DetachedCriteria.forClass(NewsColumn.class);
		dc2.add(Restrictions.eq("enable", true));
		dc2.add(Restrictions.eq("language", language));
		dc2.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_COLUMN));
		dc2.add(Restrictions.eq("columnId", all.get(0).getId()));
		return newsColumnDao.findByDetachedCriteria(dc2);
	
	}

	@Override
	public List<NewsColumn> getAllCategory(String language) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_CATEGORY));
		dc.add(Restrictions.eq("language", language));
		dc.addOrder(Order.asc("updateTimes"));
		return newsColumnDao.findByDetachedCriteria(dc);
	}

	@Override
	public List<NewsColumn> getAllColumnDataByCategoryId(String categoryId, String language) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsColumn.class);
		dc.add(Restrictions.eq("enable", true));
		dc.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_CATEGORY));
		dc.add(Restrictions.eq("id", categoryId));
		List<NewsColumn> newsColumns = newsColumnDao.findByDetachedCriteria(dc);
		if(newsColumns==null||newsColumns.size()<=0){
			return null;
		}
		DetachedCriteria dc1 = DetachedCriteria.forClass(NewsColumn.class);
		dc1.add(Restrictions.eq("enable", true));
		dc1.add(Restrictions.eq("language", "zh"));
		dc1.add(Restrictions.eq("uniqueCode", newsColumns.get(0).getUniqueCode()));
		List<NewsColumn> all = newsColumnDao.findByDetachedCriteria(dc1);
		
		DetachedCriteria dc2 = DetachedCriteria.forClass(NewsColumn.class);
		dc2.add(Restrictions.eq("enable", true));
		dc2.add(Restrictions.eq("language", language));
		dc2.add(Restrictions.eq("type", NewsColumn.NEWS_COLUMN_TYPE_COLUMN));
		dc2.add(Restrictions.eq("columnId", all.get(0).getId()));
		return newsColumnDao.findByDetachedCriteria(dc2);
	
	
	}
	
}
