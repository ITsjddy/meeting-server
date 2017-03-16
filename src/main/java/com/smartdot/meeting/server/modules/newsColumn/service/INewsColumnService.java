package com.smartdot.meeting.server.modules.newsColumn.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.smartdot.meeting.server.common.entity.NewsColumn;
import com.smartdot.meeting.server.common.model.Page;

public interface INewsColumnService {
	
	public static final String SERVICE_NAME = "newsColumnService";

	public Map<String, Object> pageNewsColumnList(DetachedCriteria detachedCriteria,Page<NewsColumn> page);

	public NewsColumn getEntityById(String id);

	public void saveEntity(NewsColumn newsColumn);

	public void deleteByUniqueCode(String uniqueCode);

	public List<Map<String, String>> getAllLanguage();

	public List<NewsColumn> getListNewsColumn(String id);

	public boolean saveMultiEntity(String[] newsColumnList, String newsColumnMain);

	public List<NewsColumn> getAllNewsColumnByType(String newsColumnTypeColumn,String language);

	public boolean updateMultiEntity(String[] newsColumnList, String newsColumnMain);

	public List<NewsColumn> getAllCategoryByColumnId(String columnId,String language);
	
	public List<NewsColumn> getAllCategoryByColumnId(String columnId);

	public NewsColumn findNewsColumnByIdAndLanguage(String columnId, String language);

	public List<NewsColumn> getAllCategoryByCategoryId(String columnId, String language);

	public List<NewsColumn> getAllCategory(String language);

	public List<NewsColumn> getAllColumnDataByCategoryId(String categoryId, String language);

}
