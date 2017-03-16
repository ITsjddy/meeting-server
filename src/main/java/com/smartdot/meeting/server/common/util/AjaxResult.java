package com.smartdot.meeting.server.common.util;

import java.util.HashMap;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	AJAX请求返回值
 *  为统一返回值，所有ajax请求全部使用此类对象返回
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午6:08:20
 * </pre>
 * </p>
 */
public class AjaxResult extends HashMap<String, Object>{
	private static final long serialVersionUID = -7484781490745076286L;
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取成功返回值及提示信息
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午6:08:39
	 * </pre>
	 * </p>
	 */
	public static AjaxResult successResult(String msg){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("msg", msg);
		return ajaxResult;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	 获取成功返回值
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午6:09:01
	 * </pre>
	 * </p>
	 */
	public static AjaxResult successResult(){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("msg","");
		return ajaxResult;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	获取异常返回值
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午6:09:14
	 * </pre>
	 * </p>
	 */
	public static AjaxResult errorResult(String error){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", false);
		ajaxResult.put("error", error);
		return ajaxResult;
	}
	/**
	 * 
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	返回对象
	 * <b>作者：</b>
	 * 	sunjd(孙俊东)
	 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
	 * <b>创建时间：</b> 
	 * 	2017年1月11日 下午6:09:28
	 * </pre>
	 * </p>
	 */
	public static AjaxResult objectResult(Object obj){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("data", obj);
		return ajaxResult;
	}
	
}
