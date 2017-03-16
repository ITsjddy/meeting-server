package com.smartdot.meeting.server.modules.datadictionarytype.model;

public class DataDictionaryTypeVO {
	
	private String id;
	private String dTypeName;
	private String dTypeLogo;
	private String dTypeExplanation;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDTypeName(){
		return this.dTypeName;
	}

	public String getDTypeLogo(){
		return this.dTypeLogo;
	}

	public String getDTypeExplanation(){
		return this.dTypeExplanation;
	}

	public void setDTypeName(String dtypename){
		this.dTypeName=dtypename;
	}

	public void setDTypeLogo(String dtypelogo){
		this.dTypeLogo=dtypelogo;
	}

	public void setDTypeExplanation(String dtypeexplanation){
		this.dTypeExplanation=dtypeexplanation;
	}
}
