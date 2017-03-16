package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName: DataDictionary
 * @Description: 数据字典
 * @author: haomt
 * @date: 2017年2月28日 下午1:45:51
 */

@Entity
@Table(name = "T_DATA_DICTIONARY")
public class DataDictionary extends BaseEntity {
	public static final String DATA_DICTIONARY_LANGUAGE_ZH="zh";
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6063472912544672443L;

	/**
	 * @fieldName: dDLogo
	 * @fieldType: String
	 * @Description: 数据字典唯一标识
	 */
	private String dDLogo;
	/**
	 * @fieldName: dDName
	 * @fieldType: String
	 * @Description: 数据字典名称
	 */
	private String dDName;
	/**
	 * @fieldName: dDcommId
	 * @fieldType: String
	 * @Description: 多语言关联唯一标识
	 */
	private String dDcommId;
	/**
	 * @fieldName: dDType
	 * @fieldType: String
	 * @Description: 数据字典类型名称
	 */
	private String dDType;
	/**
	 * @fieldName: dDexplanation
	 * @fieldType: String
	 * @Description: 数据字典详细说明
	 */
	private String dDexplanation;
	/**
	 * @fieldName: dDLanguageType
	 * @fieldType: String
	 * @Description: 数据字典对应语言
	 */
	private String dDLanguageType;
	/**
	 * @fieldName: dDTypeLogo
	 * @fieldType: String
	 * @Description: 字典类型唯一
	 */
	private String dDTypeLogo;

	@Column(name = "DD_TYPE_LOGO")
	public String getdDTypeLogo() {
		return dDTypeLogo;
	}

	public void setdDTypeLogo(String dDTypeLogo) {
		this.dDTypeLogo = dDTypeLogo;
	}

	/**
	 * @fieldName: dDNum
	 * @fieldType: String
	 * @Description: 排序字段
	 */
	private Integer dDNum;

	@Column(name = "DD_NUM")
	public Integer getdDNum() {
		return dDNum;
	}

	public void setdDNum(Integer dDNum) {
		this.dDNum = dDNum;
	}

	@Column(name = "DD_NAME")
	public String getdDName() {
		return dDName;
	}

	public void setdDName(String dDName) {
		this.dDName = dDName;
	}

	@Column(name = "DD_COMM_ID")
	public String getdDcommId() {
		return dDcommId;
	}

	public void setdDcommId(String dDcommId) {
		this.dDcommId = dDcommId;
	}

	@Column(name = "DD_TYPE_NAME")
	public String getdDType() {
		return dDType;
	}

	public void setdDType(String dDType) {
		this.dDType = dDType;
	}

	@Column(name = "DD_LOGO")
	public String getdDLogo() {
		return dDLogo;
	}

	public void setdDLogo(String dDLogo) {
		this.dDLogo = dDLogo;
	}

	@Column(name = "DD_LANGUAGE_TYPE")
	public String getdDLanguageType() {
		return dDLanguageType;
	}

	public void setdDLanguageType(String dDLanguageType) {
		this.dDLanguageType = dDLanguageType;
	}

	@Column(name = "DD_EXPLANATION")
	public String getdDexplanation() {
		return dDexplanation;
	}

	public void setdDexplanation(String dDexplanation) {
		this.dDexplanation = dDexplanation;
	}

}
