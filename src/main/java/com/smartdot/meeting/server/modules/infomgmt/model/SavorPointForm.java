package com.smartdot.meeting.server.modules.infomgmt.model;

public class SavorPointForm {
	private String id;
	private String spOnly;
	private String spName;
	private String spAddress;
	private String spLongitude;
	private String spDimensions;
	private String spLogo;
	private String spType;
	private String spLanguageType;
	private String spUniteId;
	public String getSpUniteId() {
		return spUniteId;
	}
	public void setSpUniteId(String spUniteId) {
		this.spUniteId = spUniteId;
	}
	

	public String getSpLanguageType() {
		return spLanguageType;
	}

	public void setSpLanguageType(String spLanguageType) {
		this.spLanguageType = spLanguageType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getSpOnly(){
		return this.spOnly;
	}

	public String getSpName(){
		return this.spName;
	}

	public String getSpAddress(){
		return this.spAddress;
	}

	public String getSpLongitude(){
		return this.spLongitude;
	}

	public String getSpDimensions(){
		return this.spDimensions;
	}

	public String getSpLogo(){
		return this.spLogo;
	}

	public String getSpType(){
		return this.spType;
	}

	public void setSpOnly(String sponly){
		this.spOnly=sponly;
	}

	public void setSpName(String spname){
		this.spName=spname;
	}

	public void setSpAddress(String spaddress){
		this.spAddress=spaddress;
	}

	public void setSpLongitude(String splongitude){
		this.spLongitude=splongitude;
	}

	public void setSpDimensions(String spdimensions){
		this.spDimensions=spdimensions;
	}

	public void setSpLogo(String splogo){
		this.spLogo=splogo;
	}

	public void setSpType(String sptype){
		this.spType=sptype;
	}

}
