package com.smartdot.meeting.server.modules.conHall.controller;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.entity.ConHall;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.GBKOrder;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.conHall.service.IConHallService;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.datadictionarytype.service.IDataDictionaryTypeService;
import com.smartdot.meeting.server.modules.member.model.MemberLanguage;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	会场模块
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午5:21:42
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value = "/conHall")
public class ConHallController {
	
	private static Logger logger = Logger.getLogger(ConHallController.class);
	
	@Resource(name = IConHallService.SERVICE_NAME)
	private IConHallService conHallService;
	
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
	public String toList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/conHall/conHallList";
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
		String name = request.getParameter("name");//会场名称
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ConHall.class);
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		detachedCriteria.add(Restrictions.eq("language", "zh"));
		detachedCriteria.add(Restrictions.eq("enable", true));
		detachedCriteria.addOrder(GBKOrder.asc("name"));
		//detachedCriteria.addOrder(Order.desc("name"));
		return conHallService.pageConHallList(detachedCriteria,PageUtilExtent.getPageInfo(request));
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
		return "/conHall/conHallEdit";
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
		ConHall conHall = conHallService.getEntityById(id);
		return AjaxResult.objectResult(conHall);
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取所有会场类型数据
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年3月14日 下午6:04:30
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getConHallTypeList")
	@ResponseBody
	public Map<String, Object> getConHallTypeList() throws Exception {
		try {
			//获取所有会场类型数据
			List<DataDictionary> forumplateList  = dataDictionaryService.findAllByTypeLogo("conhalltype", DataDictionary.DATA_DICTIONARY_LANGUAGE_ZH);
			return AjaxResult.objectResult(forumplateList);
		} catch (Exception e) {
			return AjaxResult.errorResult("服务器内部错误");
		}
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
	public Map<String, Object> editSave(HttpServletRequest request, HttpServletResponse response,String[] conHallList,String conHallMain) throws Exception {
		try {
			boolean flag = false;
			flag = conHallService.updateMultiEntity(conHallList,conHallMain);
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
	private ConHall assembleEntity(HttpServletRequest request,ConHall conHall) {
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
		return "/conHall/conHallAdd";
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
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response,String[] conHallList,String conHallMain) throws Exception {
		try {
			boolean flag = false;
			flag = conHallService.saveMultiEntity(conHallList,conHallMain);
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
	 * 	根据唯一标示删除元素
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月15日 上午9:59:59
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
			conHallService.deleteByUniqueCode(uniqueCode);
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
	public AjaxResult multiDelete(HttpServletRequest request,String[] ids, HttpServletResponse response) throws Exception {
//		String ids = request.getParameter("ids");
//		if(StringUtils.isBlank(ids)){
//			logger.error("方法参数错误");
//			return AjaxResult.errorResult("方法参数错误");
//		}
//		String[] idArr = ids.split(",");
		for(int i=0;i<ids.length;i++){
			ConHall conHall = conHallService.getEntityById(ids[i]);
			conHall.setEnable(false);
			conHallService.saveEntity(conHall);
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
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			logger.error("方法参数错误");
			return "405";
		}
		ConHall conHall = conHallService.getEntityById(id);
		conHall.setEnable(false);
		conHallService.saveEntity(conHall);
		return "redirect:/dispatcher/conHall/toList";
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
		String uniqueCode = request.getParameter("uniqueCode");
		if(StringUtils.isBlank(id)||StringUtils.isBlank(uniqueCode)){
			logger.error("方法参数错误");
			return "405";
		}
		model.addAttribute("id",id);
		model.addAttribute("uniqueCode",uniqueCode);
		return "/conHall/conHallDetail";
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取语言标示
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年2月13日 下午7:08:31
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/getLanguage")
	@ResponseBody
	public Map<String,Object> getLanguage(String id) throws Exception {
		List<MemberLanguage> conHallLanguage = new ArrayList<MemberLanguage>();
		List<ConHall> conHallList = new ArrayList<ConHall>();
		
		List<Map<String, String>> llanguage = conHallService.getAllLanguage();
		List<ConHall> lGuest = conHallService.getListConHall(id);
		if(null != llanguage && llanguage.size() > 0){
			for (Map<String, String> maps : llanguage) {
				int i = 0;
				String uneIdent = maps.get("uneIdent");
				String name = maps.get("name");
				MemberLanguage memberLanguage = new MemberLanguage();
				memberLanguage.setUneIdent(uneIdent);
				memberLanguage.setName(name);
				if(null != lGuest && lGuest.size() > 0){
					for (ConHall guest : lGuest) {
						if(guest.getLanguage().equals(uneIdent)){
							memberLanguage.setCheck(true);
							conHallList.add(guest);
							i++;
							continue;
						}
					}
				}
				if(i == 0){
					ConHall guest = new ConHall();
					conHallList.add(guest);
				}
				conHallLanguage.add(memberLanguage);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("conHallList", conHallList);
		map.put("conHallLanguage", conHallLanguage);
		return map;
	}
}
