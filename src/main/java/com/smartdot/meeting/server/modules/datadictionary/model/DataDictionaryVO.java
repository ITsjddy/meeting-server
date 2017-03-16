package com.smartdot.meeting.server.modules.datadictionary.model;

public class DataDictionaryVO {

	private String id;
	private String dDName;
	private String dDType;
	private String dDLogo;
	private String dDcommId;
	private String dDexplanation;
	private String[] languages;

	private Integer dDNum;

	public Integer getdDNum() {
		return dDNum;
	}

	public void setdDNum(Integer dDNum) {
		this.dDNum = dDNum;
	}

	private String dDTypeLogo;

	public String getdDTypeLogo() {
		return dDTypeLogo;
	}

	public void setdDTypeLogo(String dDTypeLogo) {
		this.dDTypeLogo = dDTypeLogo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getdDName() {
		return dDName;
	}

	public void setdDName(String dDName) {
		this.dDName = dDName;
	}

	public String getdDType() {
		return dDType;
	}

	public void setdDType(String dDType) {
		this.dDType = dDType;
	}

	public String getdDLogo() {
		return dDLogo;
	}

	public void setdDLogo(String dDLogo) {
		this.dDLogo = dDLogo;
	}

	public String getdDcommId() {
		return dDcommId;
	}

	public void setdDcommId(String dDcommId) {
		this.dDcommId = dDcommId;
	}

	public String getdDexplanation() {
		return dDexplanation;
	}

	public void setdDexplanation(String dDexplanation) {
		this.dDexplanation = dDexplanation;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

}
