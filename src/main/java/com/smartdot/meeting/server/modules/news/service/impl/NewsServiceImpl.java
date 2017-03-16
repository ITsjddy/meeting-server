package com.smartdot.meeting.server.modules.news.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.News;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.modules.news.dao.INewsDao;
import com.smartdot.meeting.server.modules.news.service.INewsService;


@Service(value = INewsService.SERVICE_NAME)
public class NewsServiceImpl implements INewsService {
	
	@Resource(name = INewsDao.DAO_NAME)
	private INewsDao newsDao;

	@Override
	public Map<String, Object> pageNewsList(DetachedCriteria detachedCriteria,Page<News> page) {
		Map<String, Object> pageData = newsDao.pagedQuery(detachedCriteria,page);
		return pageData;
	}

	@Override
	public News getEntityById(String id) {
		return newsDao.findById(id);
	}

	@Override
	public void saveEntity(News news) {
		newsDao.saveOrUpdate(news);
	}

	@Override
	public boolean saveAddNews(String newsJsonStr) {
		boolean result = false;
		if(StringUtils.isNotBlank(newsJsonStr)){
			News news = JSON.parseObject(String.valueOf(newsJsonStr), News.class); 
			if (StringUtils.isBlank(news.getId())) {
				if(News.NEWS_LOCATION_CAROUSEL.equals(news.getLocation())&&news.getVieverSort()==null){
					news.setVieverSort(1);
				}
				if(News.NEWS_LOCATION_HEADLINES.equals(news.getLocation())&&news.getHeadlinesSort()==null){
					news.setHeadlinesSort(1);
				}
				if(news.getSortNumber()==null){
					news.setSortNumber(1);
				}
				if(news.getSortNumber()!=null){
					news.setSortNumber(judgeTheSortNumber(news));
				}
				if(news.getVieverSort()!=null){
					news.setVieverSort(judgeTheVieverSort(news));
				}
				if(news.getHeadlinesSort()!=null){
					news.setHeadlinesSort(judgeTheHeadlinesSort(news));
				}
				news.setSortType(News.NEWS_SORT_TYPE_SORT_FILED);
				news.setIsTop(News.NEWS_IS_TOP_NO);//设置时候置顶，用来初始化该值，避免以后根据该字段排序的时候出现有些为null有些为no的情况
				news.setNewStatus(News.NEWS_STATUS_SAVE);
				newsDao.save(news);
				result = true;
			}
		}
		return result;
	}
	
	private Integer judgeTheSortNumber(News news) {
		saveTheSortNumber(news.getSortNumber(),news.getLanguage(),news.getId());//如果id为null时，查不出来数据
		return news.getSortNumber();
	}
	
	private Integer judgeTheHeadlinesSort(News news) {
		saveTheHeadlinesSort(news.getHeadlinesSort(),news.getLanguage(),news.getId());
		return news.getHeadlinesSort();
	}

	private Integer judgeTheVieverSort(News news) {
		saveTheVieverSort(news.getVieverSort(),news.getLanguage(),news.getId());
		return news.getVieverSort();
	}
	private Integer saveTheHeadlinesSort(Integer headlinesSort, String language, String id) {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(News.class);
		dCriteria.add(Restrictions.eq("enable", true));
		dCriteria.add(Restrictions.eq("headlinesSort", headlinesSort));
		dCriteria.add(Restrictions.eq("language", language));
		dCriteria.add(Restrictions.isNotNull("id"));
		dCriteria.add(Restrictions.ne("id",id));
		List<News> list = newsDao.findByDetachedCriteria(dCriteria);
		if(list !=null&&list.size()>0){
			News news = list.get(0);
			news.setHeadlinesSort(news.getHeadlinesSort()+1);
			newsDao.save(news);
			return saveTheHeadlinesSort(news.getHeadlinesSort(),language,news.getId());
		}else{
			return headlinesSort;
		}
	
	}
	private Integer saveTheVieverSort(Integer vieverSort,String language,String id) {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(News.class);
		dCriteria.add(Restrictions.eq("enable", true));
		dCriteria.add(Restrictions.eq("vieverSort", vieverSort));
		dCriteria.add(Restrictions.eq("language", language));
		dCriteria.add(Restrictions.isNotNull("id"));
		dCriteria.add(Restrictions.ne("id",id));
		List<News> list = newsDao.findByDetachedCriteria(dCriteria);
		if(list !=null&&list.size()>0){
			News news = list.get(0);
			news.setVieverSort(news.getVieverSort()+1);
			newsDao.save(news);
			return saveTheVieverSort(news.getVieverSort(),language,news.getId());
		}else{
			return vieverSort;
		}
	}

	private Integer saveTheSortNumber(Integer sortNumber,String language,String id) {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(News.class);
		dCriteria.add(Restrictions.eq("enable", true));
		dCriteria.add(Restrictions.eq("sortNumber", sortNumber));
		dCriteria.add(Restrictions.eq("language", language));
		dCriteria.add(Restrictions.isNotNull("id"));
		dCriteria.add(Restrictions.ne("id",id));
		List<News> list = newsDao.findByDetachedCriteria(dCriteria);
		if(list !=null&&list.size()>0){
			News news = list.get(0);
			news.setSortNumber(news.getSortNumber()+1);
			newsDao.save(news);
			return saveTheSortNumber(news.getSortNumber(),language,news.getId());
		}else{
			return sortNumber;
		}
	
	}

	@Override
	public News getNewsById(String id) {
		return newsDao.findById(id);
	}

	@Override
	public boolean saveEditNews(String newsJsonStr) {
		boolean result = false;
		if(StringUtils.isNotBlank(newsJsonStr)){
			News news = JSON.parseObject(String.valueOf(newsJsonStr), News.class); 
			if(News.NEWS_LOCATION_CAROUSEL.equals(news.getLocation())&&news.getVieverSort()==null){
				news.setVieverSort(1);
			}
			if(news.getSortNumber()==null){
				news.setSortNumber(1);
			}
			if(News.NEWS_LOCATION_HEADLINES.equals(news.getLocation())&&news.getHeadlinesSort()==null){
				news.setHeadlinesSort(1);
			}
			if(news.getSortNumber()!=null){
				news.setSortNumber(judgeTheSortNumber(news));
			}
			if(news.getVieverSort()!=null){
				news.setVieverSort(judgeTheVieverSort(news));
			}
			if(news.getHeadlinesSort()!=null){
				news.setHeadlinesSort(judgeTheHeadlinesSort(news));
			}
			news.setUpdateTimes(new Date().getTime());
			news.setNewStatus(News.NEWS_STATUS_SAVE);
			newsDao.update(news);
			result = true;
		}
		return result;
	}

	@Override
	public News getNewsByLanguageAndIsTop(String language, String isTop) {
		DetachedCriteria dcCriteria = DetachedCriteria.forClass(News.class);
		dcCriteria.add(Restrictions.eq("enable", true));
		dcCriteria.add(Restrictions.eq("isTop", isTop));
		dcCriteria.add(Restrictions.eq("language", language));
		List<News> newsList = newsDao.findByDetachedCriteria(dcCriteria);
		if(newsList!=null&&newsList.size()>0){
			return newsList.get(0);
		}
		return null;
	}

	@Override
	public void updateNews(News news) {
		newsDao.update(news);
	}

	@Override
	public void saveMoveNewsItem(String downOrUp, String id) {
		News news = newsDao.findById(id);
		if("down".equals(downOrUp)){
			DetachedCriteria dcCriteria = DetachedCriteria.forClass(News.class);
			dcCriteria.add(Restrictions.eq("enable", true));
			dcCriteria.add(Restrictions.eq("language", news.getLanguage()));
			dcCriteria.add(Restrictions.ge("sortNumber", news.getSortNumber()));
			dcCriteria.add(Restrictions.ne("id", id));
			dcCriteria.addOrder(Order.asc("sortNumber"));
			List<News> newsList = newsDao.findByDetachedCriteria(dcCriteria);
			if(newsList!=null&&newsList.size()>0){
				News newsSec = newsList.get(0);
				Integer swithSortNum = newsSec.getSortNumber();
				newsSec.setSortNumber(news.getSortNumber());
				newsDao.update(newsSec);
				news.setSortNumber(swithSortNum);
				newsDao.update(news);
			}
		}else if("up".equals(downOrUp)){
			DetachedCriteria dcCriteria = DetachedCriteria.forClass(News.class);
			dcCriteria.add(Restrictions.eq("enable", true));
			dcCriteria.add(Restrictions.eq("language", news.getLanguage()));
			dcCriteria.add(Restrictions.le("sortNumber", news.getSortNumber()));
			dcCriteria.add(Restrictions.ne("id", id));
			dcCriteria.addOrder(Order.desc("sortNumber"));
			List<News> newsList = newsDao.findByDetachedCriteria(dcCriteria);
			if(newsList!=null&&newsList.size()>0){
				News newsSec = newsList.get(0);
				Integer swithSortNum = newsSec.getSortNumber();
				newsSec.setSortNumber(news.getSortNumber());
				newsDao.update(newsSec);
				news.setSortNumber(swithSortNum);
				newsDao.update(news);
			}
		}
	}

	@Override
	public boolean savePublishNews(String newsJsonStr) {
		boolean result = false;
		if(StringUtils.isNotBlank(newsJsonStr)){
			News news = JSON.parseObject(String.valueOf(newsJsonStr), News.class); 
			if (StringUtils.isBlank(news.getId())) {
				if(News.NEWS_LOCATION_CAROUSEL.equals(news.getLocation())&&news.getVieverSort()==null){
					news.setVieverSort(1);
				}
				if(news.getSortNumber()==null){
					news.setSortNumber(1);
				}
				if(News.NEWS_LOCATION_HEADLINES.equals(news.getLocation())&&news.getHeadlinesSort()==null){
					news.setHeadlinesSort(1);
				}
				if(news.getSortNumber()!=null){
					news.setSortNumber(judgeTheSortNumber(news));
				}
				if(news.getVieverSort()!=null){
					news.setVieverSort(judgeTheVieverSort(news));
				}
				if(news.getHeadlinesSort()!=null){
					news.setHeadlinesSort(judgeTheHeadlinesSort(news));
				}
				news.setIsTop(News.NEWS_IS_TOP_NO);//设置时候置顶，用来初始化该值，避免以后根据该字段排序的时候出现有些为null有些为no的情况
				news.setNewStatus(News.NEWS_STATUS_PUBLISH);
				newsDao.save(news);
				result = true;
			}else{
				if(News.NEWS_LOCATION_CAROUSEL.equals(news.getLocation())&&news.getVieverSort()==null){
					news.setVieverSort(1);
				}
				if(news.getSortNumber()==null){
					news.setSortNumber(1);
				}
				if(News.NEWS_LOCATION_HEADLINES.equals(news.getLocation())&&news.getHeadlinesSort()==null){
					news.setHeadlinesSort(1);
				}
				if(news.getSortNumber()!=null){
					news.setSortNumber(judgeTheSortNumber(news));
				}
				if(news.getVieverSort()!=null){
					news.setVieverSort(judgeTheVieverSort(news));
				}
				if(news.getHeadlinesSort()!=null){
					news.setHeadlinesSort(judgeTheHeadlinesSort(news));
				}
				news.setSortNumber(judgeTheSortNumber(news));
				news.setVieverSort(judgeTheVieverSort(news));
				news.setNewStatus(News.NEWS_STATUS_PUBLISH);
				newsDao.update(news);
			}
		}
		return result;
	
	}
	@Override
	public boolean saveSortType(String sortType) {
		String hql = "UPDATE News SET SORT_TYPE = ?";
		try {
			newsDao.executeHQL(hql, sortType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String getSortType() {
		DetachedCriteria dcCriteria = DetachedCriteria.forClass(News.class);
		dcCriteria.add(Restrictions.eq("enable", true));
		List<News> newsList = newsDao.findByDetachedCriteria(dcCriteria);
		if(newsList!=null&&newsList.size()>0){
			return newsList.get(0).getSortType()==null?News.NEWS_SORT_TYPE_SORT_FILED:newsList.get(0).getSortType();
		}else{
			return News.NEWS_SORT_TYPE_SORT_FILED;
		}
		
	}

	@Override
	public void updateNewsToTop(String id, String language) {
		News news = newsDao.findById(id);
		DetachedCriteria dCriteria = DetachedCriteria.forClass(News.class);
		dCriteria.add(Restrictions.eq("enable", true));
		dCriteria.add(Restrictions.eq("sortNumber", 1));
		dCriteria.add(Restrictions.eq("language", language));
		dCriteria.add(Restrictions.isNotNull("id"));
		dCriteria.add(Restrictions.ne("id",id));
		List<News> list = newsDao.findByDetachedCriteria(dCriteria);
		if(list !=null&&list.size()>0){
			news.setSortNumber(1);
			judgeTheSortNumber(news);
//			String hql = "UPDATE News SET SORT_NUMBER = (SORT_NUMBER+1) where id != ?";
//			newsDao.executeHQL(hql, id);
		}else {
			news.setSortNumber(1);
		}
		newsDao.update(news);
	}

}
