package com.smartdot.meeting.server.modules.datadictionary.model;

public class DataDictionaryPageForm {

	private int currentPage;
	private int pageSize;
	private String id;
	private String dDName;
	private String dDType;
	private String dDLogo;
	private String dDexplanation;
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDDName() {
		return this.dDName;
	}

	public String getDDType() {
		return this.dDType;
	}

	public String getDDLogo() {
		return this.dDLogo;
	}

	public String getDDexplanation() {
		return this.dDexplanation;
	}

	public void setDDName(String ddname) {
		this.dDName = ddname;
	}

	public void setDDType(String ddtype) {
		this.dDType = ddtype;
	}

	public void setDDLogo(String ddlogo) {
		this.dDLogo = ddlogo;
	}

	public void setDDexplanation(String ddexplanation) {
		this.dDexplanation = ddexplanation;
	}
}
