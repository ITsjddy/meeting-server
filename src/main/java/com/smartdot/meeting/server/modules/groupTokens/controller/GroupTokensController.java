package com.smartdot.meeting.server.modules.groupTokens.controller;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.GroupTokens;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.GBKOrder;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.groupTokens.service.IGroupTokensService;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	融云群组
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午5:21:42
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value = "/groupTokens")
public class GroupTokensController {
	
	private static Logger logger = Logger.getLogger(GroupTokensController.class);
	
	@Resource(name = IGroupTokensService.SERVICE_NAME)
	private IGroupTokensService groupTokensService;
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
		return "/groupTokens/groupTokensList";
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
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GroupTokens.class);
		if (StringUtils.isNotBlank(name)) {
			name = new String(name.getBytes("iso-8859-1"),"UTF-8");
			detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		detachedCriteria.add(Restrictions.eq("enable", true));
		detachedCriteria.addOrder(GBKOrder.asc("name"));
		//detachedCriteria.addOrder(Order.desc("name"));
		return groupTokensService.pageGroupTokensList(detachedCriteria,PageUtilExtent.getPageInfo(request));
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
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("id",id);
		return "/groupTokens/groupTokensEdit";
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
		GroupTokens groupTokens = groupTokensService.getEntityById(id);
		return AjaxResult.objectResult(groupTokens);
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
	public Map<String, Object> editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			name = new String(name.getBytes("iso-8859-1"),"UTF-8");
		}
		String sex = request.getParameter("sex");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return AjaxResult.errorResult("参数为空");
		}
		GroupTokens groupTokens = groupTokensService.getEntityById(id);
		try {
			groupTokens = assembleEntity(request,groupTokens);
			groupTokensService.saveEntity(groupTokens);
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
	private GroupTokens assembleEntity(HttpServletRequest request,GroupTokens groupTokens) {
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
		return "/groupTokens/groupTokensAdd";
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
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			name = new String(name.getBytes("iso-8859-1"),"UTF-8");
		}
		String sex = request.getParameter("sex");
		GroupTokens groupTokens = new GroupTokens();
		try {
			groupTokens = assembleEntity(request,groupTokens);
			groupTokensService.saveEntity(groupTokens);
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
	public Map<String, Object> multiDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		if(StringUtils.isBlank(ids)){
			logger.error("方法参数错误");
			return AjaxResult.errorResult("参数为空");
		}
		String[] idArr = ids.split(",");
		for(int i=0;i<idArr.length;i++){
			GroupTokens groupTokens = groupTokensService.getEntityById(idArr[i]);
			groupTokens.setEnable(false);
			groupTokensService.saveEntity(groupTokens);
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
		GroupTokens groupTokens = groupTokensService.getEntityById(id);
		groupTokens.setEnable(false);
		groupTokensService.saveEntity(groupTokens);
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
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("id",id);
		return "/groupTokens/groupTokensDetail";
	}
}
