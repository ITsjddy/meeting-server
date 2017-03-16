package com.smartdot.meeting.server.common.util;


import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.smartdot.meeting.server.common.model.Page;

/**
 * PageUtilExtent扩展
 *
 * @author ms
 */
public class PageUtilExtent {
	
	private static Logger logger = Logger.getLogger(PageUtilExtent.class);
	
	public final static int WEB_PAGESIZE = 15;

	private PageUtilExtent(){}
	
	
	@SuppressWarnings("rawtypes")
	public final synchronized static Page getPageInfo(HttpServletRequest request){
		Page page = new Page();
		int pageNow = 1, pageSize = WEB_PAGESIZE;
		
		String currentPage = request.getParameter("pageNow");
		String pageSizeStr = request.getParameter("pageSize");
		try {
			if(StringUtils.isNotBlank(currentPage))
				pageNow = Integer.valueOf(currentPage);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		try {
			if(StringUtils.isNotBlank(pageSizeStr))
				pageSize = Integer.valueOf(pageSizeStr);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		page.setCurrentPage(pageNow);
		page.setPageSize(pageSize);
		
		return page;
	}
	
	
	public final synchronized static int getPageNo(String pno){
		int pageno = 1;
		try {
			if(StringUtils.isNotBlank(pno))
				pageno = Integer.valueOf(pno);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return pageno;
	}
	
	public final synchronized static Object getPrivateProperty(Object object, String propertyName) 
			throws IllegalAccessException, NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		return field.get(object);
	}
	
	public final synchronized static void setPrivateProperty(Object object, String propertyName, Object newValue) 
			throws IllegalAccessException, NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		field.set(object, newValue);
	}
	
	public final synchronized static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

}
