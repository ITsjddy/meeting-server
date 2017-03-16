package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * @ClassName: CloudGroup 
 * @Description: 融云群组实体
 * @author: haomt
 * @date: 2017年2月15日 下午4:48:20  
 */
@Entity
@Table(name = "t_cloud_group")
public class CloudGroup  extends BaseEntity {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @fieldName: fgName
	 * @fieldType: String
	 * @Description: 群组名称
	 */
	private String fgName;
	@Column(name = "FG_NAME")
	public String getFgName() {
		return fgName;
	}
	public void setFgName(String fgName) {
		this.fgName = fgName;
	}
	
	/**
	 * @fieldName: fgAppUserIds
	 * @fieldType: String
	 * @Description: app 用户 IDS
	 */
	private String fgAppUserIds;
	
	@Column(name = "FG_APPUSER_IDS")
	public String getFgAppUserIds() {
		return fgAppUserIds;
	}
	public void setFgAppUserIds(String fgAppUserIds) {
		this.fgAppUserIds = fgAppUserIds;
	}

	/**
	 * @fieldName: fgServPersonIds
	 * @fieldType: String
	 * @Description: 服务人员IDS
	 */
	private String fgServPersonIds;
	@Column(name = "FG_SERVPERSON_IDS")
	public String getFgServPersonIds() {
		return fgServPersonIds;
	}
	public void setFgServPersonIds(String fgServPersonIds) {
		this.fgServPersonIds = fgServPersonIds;
	}

	/**
	 * @fieldName: fgAppUserNames
	 * @fieldType: String
	 * @Description: APP 用户 的名字
	 */
	private String fgAppUserNames;
	@Column(name = "FG_APPUSER_NAMES")
	public String getFgAppUserNames() {
		return fgAppUserNames;
	}
	public void setFgAppUserNames(String fgAppUserNames) {
		this.fgAppUserNames = fgAppUserNames;
	}

	
	/**
	 * @fieldName: fgServPersonNames
	 * @fieldType: String
	 * @Description: 
	 */
	private String fgServPersonNames;
	@Column(name = "FG_SERVPERSON_NAMES")
	public String getFgServPersonNames() {
		return fgServPersonNames;
	}
	public void setFgServPersonNames(String fgServPersonNames) {
		this.fgServPersonNames = fgServPersonNames;
	}
	
}
