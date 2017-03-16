package com.smartdot.meeting.server.modules.department.model;

public class DepartmentVO {
	private String id;
	private String departname;
	private String endepartname;
	private Integer ordernumber;
	private String parentid;
	private String fullpath;
	private String classification;
	private String type;
	private String parentname;
	private String language;
	private String groupnumber;
	private String pgroupnumber;
	
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGroupnumber() {
		return groupnumber;
	}

	public void setGroupnumber(String groupnumber) {
		this.groupnumber = groupnumber;
	}

	public String getPgroupnumber() {
		return pgroupnumber;
	}

	public void setPgroupnumber(String pgroupnumber) {
		this.pgroupnumber = pgroupnumber;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDepartname(){
		return this.departname;
	}

	public String getEndepartname(){
		return this.endepartname;
	}

	public Integer getOrdernumber(){
		return this.ordernumber;
	}

	public String getParentid(){
		return this.parentid;
	}

	public String getFullpath(){
		return this.fullpath;
	}

	public String getClassification(){
		return this.classification;
	}

	public String getType(){
		return this.type;
	}

	public void setDepartname(String departname){
		this.departname=departname;
	}

	public void setEndepartname(String endepartname){
		this.endepartname=endepartname;
	}

	public void setOrdernumber(Integer ordernumber){
		this.ordernumber=ordernumber;
	}

	public void setParentid(String parentid){
		this.parentid=parentid;
	}

	public void setFullpath(String fullpath){
		this.fullpath=fullpath;
	}

	public void setClassification(String classification){
		this.classification=classification;
	}

	public void setType(String type){
		this.type=type;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
}
