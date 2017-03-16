package com.smartdot.meeting.server.modules.newsColumn.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.NewsColumn;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.GBKOrder;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;
import com.smartdot.meeting.server.modules.newsColumn.service.INewsColumnService;
import com.smartdot.meeting.server.modules.newsColumn.util.NewsColumnView;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	论坛/日程
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午5:21:42
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value = "/newsColumn")
public class NewsColumnController {
	
	private static Logger logger = Logger.getLogger(NewsColumnController.class);
	
	@Resource(name = INewsColumnService.SERVICE_NAME)
	private INewsColumnService newsColumnService;
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
	public String toList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/newsColumn/newsColumnList";
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
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(NewsColumn.class);
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (StringUtils.isNotBlank(type)) {
			detachedCriteria.add(Restrictions.eq("type", type));
		}
		detachedCriteria.add(Restrictions.eq("enable", true));
		detachedCriteria.add(Restrictions.eq("language", "zh"));
		detachedCriteria.addOrder(GBKOrder.asc("name"));
		//detachedCriteria.addOrder(Order.desc("name"));
		Map<String, Object> dataMap = newsColumnService.pageNewsColumnList(detachedCriteria,PageUtilExtent.getPageInfo(request));
		if(dataMap == null||dataMap.get("resultData")==null){
			return dataMap;
		}
		List<NewsColumn> list =  (List<NewsColumn>) dataMap.get("resultData");
		List<NewsColumnView> listViews = null;
		if(list!=null&&list.size()>0){
			listViews = new ArrayList<NewsColumnView>();
			for (int i = 0; i < list.size(); i++) {
				NewsColumn newsColumn = list.get(i);
				NewsColumnView listView = new NewsColumnView();
				BeanUtils.copyProperties(newsColumn, listView);
				listView.setParentName(getParentName(newsColumn));
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
	 * 	获取分类的栏目名称
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月18日 上午9:51:06
	 * </pre>
	 * </p>
	 */
	private String getParentName(NewsColumn newsColumn) {
		if(StringUtils.isBlank(newsColumn.getColumnId()))return "";
		NewsColumn entity = newsColumnService.getEntityById(newsColumn.getColumnId());
		return entity.getName();
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
		if(StringUtils.isBlank(uniqueCode)||StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("uniqueCode",uniqueCode);
		model.addAttribute("id",id);
		return "/newsColumn/newsColumnEdit";
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
		NewsColumn newsColumn = newsColumnService.getEntityById(id);
		return AjaxResult.objectResult(newsColumn);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	删除
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月17日 下午5:55:37
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/deleteByUniqueCode")
	@ResponseBody
	public Map<String, Object> deleteByUniqueCode(HttpServletRequest request, HttpServletResponse response,String uniqueCode) throws Exception {
		try {
			if(StringUtils.isBlank(uniqueCode)){
				return AjaxResult.errorResult("参数错误");
			}
			newsColumnService.deleteByUniqueCode(uniqueCode);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
		return AjaxResult.successResult();
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
	public Map<String, Object> editSave(HttpServletRequest request, HttpServletResponse response,String[] newsColumnList,String newsColumnMain) throws Exception {
		try {
			boolean flag = false;
			flag = newsColumnService.updateMultiEntity(newsColumnList,newsColumnMain);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
		return AjaxResult.successResult();
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
	private NewsColumn assembleEntity(HttpServletRequest request,NewsColumn newsColumn) {
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
	public String toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/newsColumn/newsColumnAdd";
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
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response,String[] newsColumnList,String newsColumnMain) throws Exception {
		try {
			boolean flag = false;
			flag = newsColumnService.saveMultiEntity(newsColumnList,newsColumnMain);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
		return AjaxResult.successResult();
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
	public Map<String, Object> multiDelete(HttpServletRequest request,String[] ids, HttpServletResponse response) throws Exception {
//		String ids = request.getParameter("ids");
//		if(StringUtils.isBlank(ids)){
//			logger.error("方法参数错误");
//			return AjaxResult.errorResult("参数为空");
//		}
//		String[] idArr = ids.split(",");
		for(int i=0;i<ids.length;i++){
			NewsColumn newsColumn = newsColumnService.getEntityById(ids[i]);
			newsColumn.setEnable(false);
			newsColumnService.saveEntity(newsColumn);
		}
		return AjaxResult.successResult();
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
		NewsColumn newsColumn = newsColumnService.getEntityById(id);
		newsColumn.setEnable(false);
		newsColumnService.saveEntity(newsColumn);
		return AjaxResult.successResult();
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
	 * 	2017年2月18日 上午10:19:46
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getLanguage")
	@ResponseBody
	public Map<String, Object> getLanguage(HttpServletRequest request, HttpServletResponse response,String id) throws Exception {

		List<MemberLanguage> conHallLanguage = new ArrayList<MemberLanguage>();
		List<NewsColumn> conHallList = new ArrayList<NewsColumn>();
		
		List<Map<String, String>> llanguage = newsColumnService.getAllLanguage();
		List<NewsColumn> lGuest = newsColumnService.getListNewsColumn(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lGuest && lGuest.size() > 0){
					for (NewsColumn guest : lGuest) {
						if(guest.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							conHallList.add(guest);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					NewsColumn guest = new NewsColumn();
					conHallList.add(guest);
				}
				conHallLanguage.add(memberLanguage);
			}
		}
		List<NewsColumn> columnListInfo = newsColumnService.getAllNewsColumnByType(NewsColumn.NEWS_COLUMN_TYPE_CATEGORY,"zh");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newsColumnList", conHallList);
		map.put("columnListInfo", columnListInfo);
		map.put("newsColumnLanguage", conHallLanguage);
		return map;
	
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
		if(StringUtils.isBlank(uniqueCode)||StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("uniqueCode",uniqueCode);
		model.addAttribute("id",id);
		return "/newsColumn/newsColumnDetail";
	}
}
