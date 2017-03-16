package com.smartdot.meeting.server.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.smartdot.meeting.server.modules.sysprivilege.service.ISysPrivilegeService;



/**
 * 加载资源与权限的对应关系
 * @author ms
 * 2016-08-11
 * @version 1.0v
 * */
@Service
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@Autowired
	private ISysPrivilegeService  sysPrivilegeService;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private PathMatcher pathMatcher = new AntPathMatcher();

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	/**
	 * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 * //加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			Map<String, String> configs = sysPrivilegeService.getResources();
			for (Entry<String, String> entry : configs.entrySet()) {
				Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
				
				String[] vals = entry.getValue().split(",");
				for (String val : vals) {
					ConfigAttribute config = new SecurityConfig(val);
					list.add(config);
				}
				resourceMap.put(entry.getKey(), list);
			}
		}
	}
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);
		if (resourceMap == null) {
			loadResourceDefine();
		}
		if(requestUrl.indexOf("?")>-1){
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		//比较url是否存在
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (pathMatcher.match(resURL,requestUrl)) {
				return resourceMap.get(resURL);
			}
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		
		return configAttributes;
	}

	
}