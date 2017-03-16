package com.smartdot.meeting.server.modules.news.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.News;
import com.smartdot.meeting.server.common.entity.NewsColumn;
import com.smartdot.meeting.server.common.model.ReturnUploadImage;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.datadictionarytype.service.IDataDictionaryTypeService;
import com.smartdot.meeting.server.modules.news.service.INewsService;
import com.smartdot.meeting.server.modules.news.util.NewsView;
import com.smartdot.meeting.server.modules.newsColumn.service.INewsColumnService;
import com.smartdot.meeting.server.modules.schedule.service.IScheduleService;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	新闻
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午5:21:42
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController {
	
	private static Logger logger = Logger.getLogger(NewsController.class);
	
	@Resource(name = INewsService.SERVICE_NAME)
	private INewsService newsService;
	
	@Resource(name = INewsColumnService.SERVICE_NAME)
	private INewsColumnService newsColumnService;
	
	@Resource(name = IScheduleService.SERVICE_NAME)
	private IScheduleService scheduleService;
	
	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;
	
	@Resource(name = IDataDictionaryTypeService.SERVICE_NAME)
	private IDataDictionaryTypeService dataDictionaryTypeService;
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 跳转到列表页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:24:11
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toList")
	public String toList(HttpServletRequest request, HttpServletResponse response,String language,Model model) throws Exception {
		if (StringUtils.isBlank(language)) {
			language = "zh";
		}
		model.addAttribute("languageNow", language);//默认查询语言
		List<NewsColumn> newsColumnList = null;
		newsColumnList = newsColumnService.getAllCategory("zh");
		model.addAttribute("categoryId", newsColumnList.get(0).getId());//默认查询分类
		return "/news/newsList";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	ajax请求列表数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:24:27
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("title");
		String languageNow = request.getParameter("languageNow");
		String categoryId = request.getParameter("categoryId");
		String newColumn = request.getParameter("newColumn");
		//String sortType = request.getParameter("sortType");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(News.class);
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("title", "%" + name + "%"));
		}
		if(StringUtils.isNotBlank(languageNow)&&!"all".equals(languageNow)){
			detachedCriteria.add(Restrictions.eq("language", languageNow));
		}
		if(StringUtils.isNotBlank(categoryId)&&!"all".equals(categoryId)){
			detachedCriteria.add(Restrictions.eq("category", categoryId));
		}
		if(StringUtils.isNotBlank(newColumn)){
			detachedCriteria.add(Restrictions.eq("newColumn", newColumn));
		}
		detachedCriteria.add(Restrictions.eq("enable", true));
		//detachedCriteria.addOrder(Order.desc("isTop"));
		String sortType =newsService.getSortType();
		if(StringUtils.isNotBlank(sortType)){
			if(sortType.equals(News.NEWS_SORT_TYPE_CREATE_TIME)){
				detachedCriteria.addOrder(Order.desc("createTimes"));
			}else if(sortType.equals(News.NEWS_SORT_TYPE_UPDATE_TIME)){
				detachedCriteria.addOrder(Order.desc("updateTimes"));
			}else if(sortType.equals(News.NEWS_SORT_TYPE_SORT_FILED)){
				detachedCriteria.addOrder(Order.asc("sortNumber"));
			}
		}
		detachedCriteria.addOrder(Order.desc("createTimes"));
		Map<String, Object> dataMap = newsService.pageNewsList(detachedCriteria,PageUtilExtent.getPageInfo(request));
		if(dataMap == null||dataMap.get("resultData")==null){
			return dataMap;
		}
		List<News> list =  (List<News>) dataMap.get("resultData");
		List<NewsView> listViews = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(list!=null&&list.size()>0){
			listViews = new ArrayList<NewsView>();
			for (int i = 0; i < list.size(); i++) {
				News news = list.get(i);
				NewsView listView = new NewsView();
				BeanUtils.copyProperties(news, listView);
				listView.setNewsCreateTime(format.format(news.getCreateTimes()));
				listView.setCategoryName(getCategoryName(news.getCategory()));
				listView.setColumnName(getCategoryName(news.getNewColumn()));
				listView.setLanguageName(getLanguageNameByUnique(news.getLanguage()));
				listView.setNewsUpdateTime(format.format(new Date(news.getUpdateTimes())));
				listViews.add(listView);
			}
			dataMap.put("resultData", listViews);
		}
		return dataMap;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据语言唯一标示获取语言名称
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月22日 上午10:57:41
	 * </pre>
	 * </p> 
	 */
	private String getLanguageNameByUnique(String language) {
		if (DataDictionary.DATA_DICTIONARY_LANGUAGE_ZH.equals(language)) {
			return "中文";
		}
		DataDictionary dataDictionary = dataDictionaryService.findByTypeLogo(language, DataDictionary.DATA_DICTIONARY_LANGUAGE_ZH);
		return dataDictionary.getdDName();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月22日 上午10:41:26
	 * </pre>
	 * </p>
	 */
	private String getCategoryName(String columnId) {
		NewsColumn newsColumn = newsColumnService.findNewsColumnByIdAndLanguage(columnId,"zh");
		return newsColumn == null?"":newsColumn.getName();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取所有语言
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月21日 上午9:57:27
	 * </pre>
	 * </p>
	 */
	@RequestMapping("/getLanguage")
	@ResponseBody
	public Map<String, Object> getLanguage(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> mapZh = new HashMap<String,String>();
		mapZh.put("uneIdent", "zh");
		mapZh.put("name", "中文");
		Map<String,String> mapAll = new HashMap<String,String>();
		mapAll.put("uneIdent", "all");
		mapAll.put("name", "所有语言");
		List<Map<String, String>> allLanguage = getAllLanguage();
		allLanguage.add(0, mapZh);
		allLanguage.add(mapAll);
		return AjaxResult.objectResult(allLanguage);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月23日 下午5:59:56
	 * </pre>
	 * </p>
	 */
	@RequestMapping("/getSortType")
	@ResponseBody
	public Map<String, Object> getSortType(HttpServletRequest request, HttpServletResponse response){
		String sortType =newsService.getSortType();
		return AjaxResult.objectResult(sortType);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	更新排序方式
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月23日 下午5:46:51
	 * </pre>
	 * </p>
	 */
	@RequestMapping("/updateSortType")
	@ResponseBody
	public Map<String, Object> updateSortType(HttpServletRequest request, HttpServletResponse response,String sortType){
		try {
			boolean flag = newsService.saveSortType(sortType);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月21日 下午3:21:22
	 * </pre>
	 * </p>
	 */
	@RequestMapping("/getColumnData")
	@ResponseBody
	public Map<String, Object> getColumnData(HttpServletRequest request, HttpServletResponse response){
		String categoryId = request.getParameter("categoryId");
		String language = request.getParameter("language");
		if (StringUtils.isBlank(language)) {
			language = "zh";
		}
		List<NewsColumn> newsColumnList = null;
		if (StringUtils.isNotBlank(categoryId)) {
			newsColumnList = newsColumnService.getAllColumnDataByCategoryId(categoryId,language);
			if (newsColumnList==null||newsColumnList.size()<=0) {
				newsColumnList = newsColumnService.getAllColumnDataByCategoryId(categoryId,"zh");
			}
		}else{
			newsColumnList = newsColumnService.getAllNewsColumnByType(NewsColumn.NEWS_COLUMN_TYPE_COLUMN,language);
			if (newsColumnList==null||newsColumnList.size()<=0) {
				newsColumnList = newsColumnService.getAllNewsColumnByType(NewsColumn.NEWS_COLUMN_TYPE_COLUMN,"zh");
			}
		}
		return AjaxResult.objectResult(newsColumnList);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月13日 上午11:21:01
	 * </pre>
	 * </p>
	 */
	@RequestMapping("/getCategoryData")
	@ResponseBody
	public Map<String, Object> getCategoryData(HttpServletRequest request, HttpServletResponse response){
		//String columnId = request.getParameter("columnId");
		String language = request.getParameter("language");
		if (StringUtils.isBlank(language)) {
			language = "zh";
		}
		List<NewsColumn> newsColumnList = null;
		newsColumnList = newsColumnService.getAllCategory(language);
		return AjaxResult.objectResult(newsColumnList);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取新闻实体
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月22日 下午3:33:53
	 * </pre>
	 * </p>
	 */
	@RequestMapping("/getNewsData")
	@ResponseBody
	public Map<String, Object> getNewsData(HttpServletRequest request, HttpServletResponse response,String id,String language){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			News news = newsService.getNewsById(id);
			map.put("news", news);
			List<NewsColumn> categoryList = newsColumnService.getAllCategory("zh");
			List<NewsColumn> columnList = newsColumnService.getAllColumnDataByCategoryId(news.getCategory(), "zh");
			map.put("categoryList", categoryList);
			map.put("columnList", columnList);
			return AjaxResult.objectResult(map);
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转到新闻主页
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月20日 下午6:12:03
	 * </pre>
	 * </p>
	 */
	@RequestMapping("/toNewsMain")
	public String toNewsMain(HttpServletRequest request, HttpServletResponse response,String language){
		return "/news/newsMain";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	多语言，从数据字典取
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月20日 下午3:51:12
	 * </pre>
	 * </p>
	 */
	public List<Map<String, String>> getAllLanguage() {
		//查询数据字典的语言
		return scheduleService.getLanguageDataForPublic();
	}
	
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转到编辑页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:25:03
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String uniqueCode = request.getParameter("uniqueCode");
		String id = request.getParameter("id");
		String language = request.getParameter("language");
		if(StringUtils.isBlank(uniqueCode)||StringUtils.isBlank(id)||StringUtils.isBlank(language)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("uniqueCode",uniqueCode);
		model.addAttribute("id",id);
		model.addAttribute("languageNow",language);
		return "/news/newsEdit";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	ajax根据id获取实体
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:25:52
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getEntityById")
	@ResponseBody
	public Map<String, Object> getEntityById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return AjaxResult.errorResult("参数为空");
		}
		News schedule = newsService.getEntityById(id);
		return AjaxResult.objectResult(schedule);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	保存修改的内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:26:07
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/editSave")
	@ResponseBody
	public Map<String, Object> editSave(HttpServletRequest request, HttpServletResponse response,String news) throws Exception {
		try {
			boolean flag = false;
			flag = newsService.saveEditNews(news);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	置顶功能
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月22日 下午7:35:15
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toTop")
	@ResponseBody
	public Map<String, Object> toTop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String id = request.getParameter("id");
			String language = request.getParameter("language");
			if(StringUtils.isBlank(id)||StringUtils.isBlank(language)){
				return AjaxResult.errorResult("参数错误");
			}
//			News news = newsService.getNewsByLanguageAndIsTop(language,News.NEWS_IS_TOP_YES);
//			if(news!=null){
//				news.setIsTop(News.NEWS_IS_TOP_NO);
//				newsService.updateNews(news);
//			}
//			News topNews = newsService.getNewsById(id);
//			topNews.setIsTop(News.NEWS_IS_TOP_YES);
//			newsService.updateNews(topNews);
			newsService.updateNewsToTop(id,language);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	组装实体对象
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午9:56:15
	 * </pre>
	 * </p>
	 */
	private News assembleEntity(HttpServletRequest request,News news) {
		return null;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转到新增页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:26:22
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String languageNow = request.getParameter("languageNow");
		if (StringUtils.isBlank(languageNow)) {
			languageNow = "zh";
		}
		model.addAttribute("languageNow", languageNow);
		return "/news/newsAdd";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	ajax保存新增数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午5:26:50
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/addSave")
	@ResponseBody
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response,String news) throws Exception {
		try {
			boolean flag = false;
			flag = newsService.saveAddNews(news);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	发布函数
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月23日 上午10:38:28
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/publish")
	@ResponseBody
	public Map<String, Object> publish(HttpServletRequest request, HttpServletResponse response,String news) throws Exception {
		try {
			boolean flag = false;
			flag = newsService.savePublishNews(news);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	发布新闻
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月23日 上午11:04:14
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/publishNewsById")
	@ResponseBody
	public Map<String, Object> publishNewsById(HttpServletRequest request, HttpServletResponse response,String id) throws Exception {
		try {
			if (StringUtils.isBlank(id)) {
				return AjaxResult.errorResult("参数错误");
			}
			News news = newsService.getNewsById(id);
			news.setNewStatus(News.NEWS_STATUS_PUBLISH);
			newsService.saveEntity(news);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	撤回函数
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月23日 上午11:08:16
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/withdraw")
	@ResponseBody
	public Map<String, Object> withdraw(HttpServletRequest request, HttpServletResponse response,String id) throws Exception {
		try {
			if (StringUtils.isBlank(id)) {
				return AjaxResult.errorResult("参数错误");
			}
			News news = newsService.getNewsById(id);
			news.setNewStatus(News.NEWS_STATUS_WITHDRAW);
			newsService.saveEntity(news);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据id集合删除多个对象
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午10:01:09
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/multiDelete")
	@ResponseBody
	public Map<String, Object> multiDelete(HttpServletRequest request, String[] ids,HttpServletResponse response) throws Exception {
//		String ids = request.getParameter("ids");
//		if(StringUtils.isBlank(ids)){
//			logger.error("方法参数错误");
//			return AjaxResult.errorResult("参数为空");
//		}
//		String[] idArr = ids.split(",");
		for(int i=0;i<ids.length;i++){
			News news = newsService.getEntityById(ids[i]);
			news.setEnable(false);
			newsService.saveEntity(news);
		}
		return AjaxResult.successResult();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	移动新闻条目
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月22日 下午8:44:20
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/moveIt")
	@ResponseBody
	public Map<String, Object> moveIt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String downOrUp = request.getParameter("downOrUp");
			String id = request.getParameter("id");
			if(StringUtils.isBlank(id)||StringUtils.isBlank(downOrUp)){
				return AjaxResult.errorResult("参数错误");
			}
			newsService.saveMoveNewsItem(downOrUp,id);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	具体内容
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午10:09:05
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return AjaxResult.errorResult("参数为空");
		}
		News news = newsService.getEntityById(id);
		news.setEnable(false);
		newsService.saveEntity(news);
		return AjaxResult.successResult();
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转的详情页面
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月12日 上午10:01:33
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/toDetail")
	public String toDetail(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String uniqueCode = request.getParameter("uniqueCode");
		String id = request.getParameter("id");
		String language = request.getParameter("language");
		if(StringUtils.isBlank(uniqueCode)||StringUtils.isBlank(id)||StringUtils.isBlank(language)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("uniqueCode",uniqueCode);
		model.addAttribute("id",id);
		model.addAttribute("languageNow",language);
		return "/news/newsDetail";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	富文本图片上传
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月18日 下午5:16:33
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "upload")
	public String uploadImage(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("upfile"); // UEditor传到后台的是这个upfile，其他的文件上传，应该也差不多是这个，还没去研究，断点一下就知道了
		if (file == null || file.getSize() == 0) {
			return null;
		}
		//获取上传文件主目录
        ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String upFolder = bundle.getString("upload_folder");//
		if(StringUtils.isBlank(upFolder)){
			return null;
		}
		String type = "ueditor";
		String upFolderResult=upFolder;
		if(StringUtils.isNotBlank(type)){
			upFolder=upFolder + File.separator + type;
			upFolderResult=upFolderResult + "/" + type;
		}
		
		String name = file.getOriginalFilename();
		// 后缀小数点前面的字符数
		int i = name.lastIndexOf(".");
		// 得到后缀
		String dec = name.substring(i);
		dec = dec.toLowerCase();

		String filePath = request.getRealPath("/");
		String result = "";
		filePath = filePath + File.separator + upFolder + File.separator;
		result = "/" + upFolderResult + "/";
		String fileName = UUID.randomUUID() + RandomStringUtils.randomNumeric(3) + dec;
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		filePath = filePath + fileName;
		result = result + fileName;
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 考虑大附件上传， 一点点存储
			fis = file.getInputStream();
			fos = new FileOutputStream(filePath);
			file.getInputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.write(file.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result=null;
		} catch (IOException e) {
			e.printStackTrace();
			result=null;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				result=null;
			}
		}
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		ReturnUploadImage resultForUe = new ReturnUploadImage();// 这个是UEditor需要的返回值内容，UEditor要的返回值需要封装成Json格式
		resultForUe.setTitle(file.getOriginalFilename());// 这里需要设置文件名称如：xxx.jpg
		resultForUe.setOriginal(file.getOriginalFilename());// 这里需要设置文件名称如：xxx.jpg
		resultForUe.setState("SUCCESS");
		resultForUe.setUrl(basePath+result);
		
		String resultJson = JSON.toJSONString(resultForUe);// 这边就是为了返回给UEditor做的格式转换
		response.getWriter().write(resultJson);
		return null;
	
	}
}
