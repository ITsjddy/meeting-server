package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VO_DEPARTMENT")
public class Department extends BaseEntity{
	private static final long serialVersionUID = -4200344253551901245L;
	/**
	 * 团名称
	 */
	private String departname;
	/**
	 * 语言标识
	 */
	private String language;
	/**
	 * 团名称英文
	 */
	private String endepartname;
	/**
	 * 排序字段
	 */
	private Integer ordernumber;
	/**
	 * 父节点id
	 */
	private String parentid;
	/**
	 * 团名称
	 */
	private String parentname;
	/**
	 * 说明
	 */
	private String fullpath;
	/**
	 * 团分类(例如：内宾、外宾)
	 */
	private String classification;
	/**
	 * 团类型(例如：部长团、省市团)
	 */
	private String type;
	/**
	 * 团编号（导入用）
	 */
	private String groupnumber;
	/**
	 * 团父级编号（导入用）
	 */
	private String pgroupnumber;
	
	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Column(name = "GROUPNUMBER")
	public String getGroupnumber() {
		return groupnumber;
	}
	public void setGroupnumber(String groupnumber) {
		this.groupnumber = groupnumber;
	}
	
	@Column(name = "PGROUPNUMBER")
	public String getPgroupnumber() {
		return pgroupnumber;
	}
	public void setPgroupnumber(String pgroupnumber) {
		this.pgroupnumber = pgroupnumber;
	}
	
	@Column(name = "DEPARTNAME")
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	
	@Column(name = "ORDERNUMBER")
	public Integer getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	@Column(name = "PARENTID")
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Column(name = "FULLPATH")
	public String getFullpath() {
		return fullpath;
	}
	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}
	
	@Column(name = "EN_DEPARTNAME")
	public String getEndepartname() {
		return endepartname;
	}
	public void setEndepartname(String endepartname) {
		this.endepartname = endepartname;
	}

	@Column(name = "CLASSIFICATION")
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "PARENTNAME")
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	
}
