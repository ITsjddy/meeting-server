package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_NEWS_COLUMN")
public class NewsColumn extends BaseEntity{
	public static final String NEWS_COLUMN_TYPE_COLUMN = "column";
	public static final String NEWS_COLUMN_TYPE_CATEGORY = "category";
	/**
	 * <p>
	 * <pre>
	 * <b>属性描述：</b>
	 * 	描述内容
	 * </pre>
	 * </p>
	 */
	private static final long serialVersionUID = 8186904045096837208L;
	/**
	 * 栏目或者分类名称
	 */
	private String name;
	/**
	 * 栏目或者分类
	 */
	private String type;
	/**
	 * 栏目id（父id，分类有）  弃用，修改为分类大，栏目小
	 * 此处为分类id
	 */
	private String columnId;
	/**
	 * 这条记录的语言标示
	 */
	private String language;
	/**
	 * 这条记录的唯一标示
	 */
	private String uniqueCode;
	/**
	 * 描述
	 */
	private String remark;
	
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "COLUMN_ID")
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Column(name = "UNIQUE_CODE")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
