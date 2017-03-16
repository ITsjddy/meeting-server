package com.smartdot.meeting.server.common.log;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Annotation for applying Audit logger on Spring Web Controller Method.<br>
 * used with @RequestMapping
 * @author ms
 * @version 0.1
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Audit {
	
	/**
	 * 日志模块
	 */
	String module();

	/**
	 * 日志执行功能
	 */
	String action();

	/**
	 * 日志执行操作描述
	 */
	String description();

}
