package com.smartdot.meeting.server.modules.news.service;

import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.News;
import com.smartdot.meeting.server.common.model.Page;

public interface INewsService {
	
	public static final String SERVICE_NAME = "newsService";

	public Map<String, Object> pageNewsList(DetachedCriteria detachedCriteria,Page<News> page);

	public News getEntityById(String id);

	public void saveEntity(News news);

	public boolean saveAddNews(String news);

	public News getNewsById(String id);

	public boolean saveEditNews(String news);

	public News getNewsByLanguageAndIsTop(String language, String newsIsTopYes);

	public void updateNews(News news);

	public void saveMoveNewsItem(String downOrUp, String id);

	public boolean savePublishNews(String news);

	public boolean saveSortType(String sortType);

	public String getSortType();

	public void updateNewsToTop(String id, String language);

}
