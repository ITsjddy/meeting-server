package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 操作日志
 * @author ms
 */
@Entity
@Table(name = "T_LOG")
public class Log extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 操作用户名
	 */
	private String username;
	/**
	 * 操作模块
	 */
	private String module;
	/**
	 * 操作功能
	 */
	private String action;
	/**
	 * 操作描述
	 */
	private String description;
	/**
	 * 操作serviceImpl类名
	 */
	private String serviceClassName;
	/**
	 * 操作serviceImpl方法名
	 */
	private String serviceMethodName;
	/**
	 * 操作耗费时间毫秒
	 */
	private String actionTime;
	/**
	 * 操作IP
	 */
	private String userIP;
	
	
	@Column(name = "USER_NAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "MODULE")
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
	@Column(name = "ACTION")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "SERVICE_CLASS_NAME")
	public String getServiceClassName() {
		return serviceClassName;
	}
	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}
	
	@Column(name = "SERVICE_METHOD_NAME")
	public String getServiceMethodName() {
		return serviceMethodName;
	}
	public void setServiceMethodName(String serviceMethodName) {
		this.serviceMethodName = serviceMethodName;
	}
	
	@Column(name = "ACTION_TIME")
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	
	@Column(name = "USER_IP")
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	
	
}
