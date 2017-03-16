package com.smartdot.meeting.server.common.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 自定义无权限拦截
 * @author ms
 * 2016-08-11
 * @version 1.0v
 */
public class CustomAccessDeniedHandlerManager implements AccessDeniedHandler  {  
      
	
    public CustomAccessDeniedHandlerManager(){}  
    
    public String getAccessDeniedUrl() {  
        return accessDeniedUrl;  
    }  
   
    public void setAccessDeniedUrl(String accessDeniedUrl){  
        this.accessDeniedUrl = accessDeniedUrl;  
    }  
  
	public CustomAccessDeniedHandlerManager(String accessDeniedUrl){  
      this.accessDeniedUrl = accessDeniedUrl;  
	}  
	
    private String accessDeniedUrl;  
  
      
    @Override  
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException reason) throws ServletException, IOException {  
    	
    	String path = req.getContextPath(); 
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;
		
    	//如果是ajax请求  
        if (isAjaxRequest(req)) {         
        	accessDeniedUrl= "/dispatcher/login/acccessDeniedAjax";
        }
        
        resp.sendRedirect(basePath + accessDeniedUrl);
    }
    
    private boolean isAjaxRequest(HttpServletRequest request) {
		//请求方式
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }
    
}