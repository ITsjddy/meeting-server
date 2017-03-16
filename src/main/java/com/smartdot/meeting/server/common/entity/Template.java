package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *导入模版
 * 
 * @author ms
 */
@Entity
@Table(name = "T_TEMPLATE")
public class Template extends BaseEntity{
	private static final long serialVersionUID = -4200344253551901245L;
	
	/**
	 * 模版名称
	 */
	private String templateName;
	
	/**
	 * 字段名称
	 */
	private String fieldName;

	/**
	 * 模版说明
	 */
	private String explain;
	
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 是否必选字段(1为必须，0为不必须)
	 */
	private String isMust;
	
	@Column(name = "IS_MUST")
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}
	
	@Column(name = "TEMPLATE_NAME")
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	@Column(name = "FIELD_NAME")
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Column(name = "EXPLAINS")
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
