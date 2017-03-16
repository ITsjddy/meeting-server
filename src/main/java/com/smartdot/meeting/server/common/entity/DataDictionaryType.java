package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * @ClassName: DataDictionaryType 
 * @Description: 数据字典类型
 * @author: haomt
 * @date: 2017年2月28日 下午2:46:00  
 */

@Entity
@Table(name = "T_DATA_DICTIONARY_TYPE")
public class DataDictionaryType extends BaseEntity{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @fieldName: dDLogo
	 * @fieldType: String
	 * @Description: 数据字典类型唯一标识
	 */
	private String dTypeName;
	/**
	 * @fieldName: dDLogo
	 * @fieldType: String
	 * @Description: 数据字典类型名称
	 */
	private String dTypeLogo;
	/**
	 * @fieldName: dDLogo
	 * @fieldType: String
	 * @Description: 数据字典类型说明
	 */
	private String dTypeExplanation;
	
	
	@Column(name = "DTYPE_NAME")
	public String getdTypeName() {
		return dTypeName;
	}
	public void setdTypeName(String dTypeName) {
		this.dTypeName = dTypeName;
	}
	
	@Column(name = "DTYPE_LOGO")
	public String getdTypeLogo() {
		return dTypeLogo;
	}
	public void setdTypeLogo(String dTypeLogo) {
		this.dTypeLogo = dTypeLogo;
	}
	
	@Column(name = "DTYPE_EXPLANATION")
	public String getdTypeExplanation() {
		return dTypeExplanation;
	}
	public void setdTypeExplanation(String dTypeExplanation) {
		this.dTypeExplanation = dTypeExplanation;
	}
	

}
