package com.smartdot.meeting.server.common.log;

import java.lang.reflect.Method;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartdot.meeting.server.common.entity.Log;
import com.smartdot.meeting.server.common.util.CommonUtil;
import com.smartdot.meeting.server.modules.log.service.ILogService;


/**
 * AOP注解方法实现日志管理 利用spring AOP 切面技术记录日志 定义切面类（这个是切面类和切入点整天合在一起的),这种情况是共享切入点情况;
 * 2016-12-30
 * @author ms
 * @version 1.0v
 */
@Aspect //该注解标示该类为切面类
@Component
public class LogAopAction {
	
	private static final Logger logger = Logger.getLogger(LogAopAction.class);
	
	@Autowired
	private ILogService logService;
	
	@Around("execution(* com.smartdot.meeting.server..service.*.*(..))")
	public Object logAll(ProceedingJoinPoint point) {
		Object result = null;
		// 执行serviceImpl类
		String className = point.getTarget().getClass().getSimpleName();
		// 执行方法名
		String methodName = point.getSignature().getName();
		
		String user = null;
		Long start = 0L;
		Long end = 0L;
		String ip = null;
		// 当前用户
		try {
			// 执行方法所消耗的时间
			start = System.currentTimeMillis();
			result = point.proceed();
			end = System.currentTimeMillis();
			// ip
			ip = InetAddress.getLocalHost().getHostAddress();
			// 登录名
			user = CommonUtil.getAuthenticatedUsername();
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		
		MethodSignature mst = (MethodSignature) point.getSignature();
		Method method = mst.getMethod();
		if (null == method) {
			logger.info("Cannot find method '" + mst.getName() + "' any where.");
			return result;
		}
		Audit audit = method.getAnnotation(Audit.class);
		if (null == audit) {
			logger.info("Cannot find method '" + mst.getName() + "' any where.");
			return result;
		}
		String module = audit.module();
		String action = audit.action();
		String description = audit.description();
		description = (description.length()>1000)?description.substring(0, 1000):description;
		
		Long time = end - start;
		//日志记录
		Log log = new Log();
		log.setUsername(user);
		log.setModule(module);
		log.setAction(action);
		log.setActionTime(time.toString());
		log.setUserIP(ip);
		log.setServiceClassName(className);
		log.setServiceMethodName(methodName);
		log.setDescription(description);
		logService.save(log);
		
		return result;
	}
}
