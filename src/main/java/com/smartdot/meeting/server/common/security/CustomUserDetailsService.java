package com.smartdot.meeting.server.common.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.smartdot.meeting.server.common.entity.SysUser;
import com.smartdot.meeting.server.common.util.RoleConstants;
import com.smartdot.meeting.server.modules.sysprivilege.service.ISysPrivilegeService;
import com.smartdot.meeting.server.modules.sysuser.service.ISysUserService;


/**
 * 自定义用户与权限的关系
 * @author ms
 * 2016-08-11
 * @version 1.0v
 */
public class CustomUserDetailsService implements UserDetailsService {
	
	private static Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysPrivilegeService  sysPrivilegeService;
	
	
	/**
	 * 根据用户名获取用户-权限等用户信息
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails user = null;
		try {
			SysUser dbUser = sysUserService.getUserName(username);
			if(null != dbUser){ //判断是否禁用
				Integer state = dbUser.getState();
				if(null == state || state == 0){
					dbUser = new SysUser();
				}
			}
			user = new User(dbUser.getUserName(), dbUser.getPassword().toLowerCase(), true, true, true, true,getAuthorities(dbUser));
		} catch (Exception e) {
			logger.error("Error in retrieving user");  
		}
		return user;
	}
	
	 /** 
     * 获得访问角色权限 
     *  
     * @param access 
     * @return 
     */  
	private Collection<GrantedAuthority> getAuthorities(SysUser dbUser) {  
		
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        if(null != dbUser && StringUtils.isNotBlank(dbUser.getUserName())){
        	List<Map<String, String>> priList = sysPrivilegeService.getUserPrivilegesByUserName(dbUser.getUserName());
        	for (Map<String, String> pri : priList) {
    			// TODO:ZZQ 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
    			// 关联代码：applicationContext-security.xml
        		if(null != pri && StringUtils.isNotBlank(pri.get("resKey"))){
        			authList.add(new SimpleGrantedAuthority("ROLE_" + pri.get("resKey")));
        		}
    		}
        } else{
        	// 所有的用户默认拥有ROLE_USER权限  
        	authList.add(new  SimpleGrantedAuthority(RoleConstants.ROLE_USER));
        }
        
        return authList;  
    }  
	

}
