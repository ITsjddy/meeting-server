package com.smartdot.meeting.server.modules.cloudgroup.model;

public class CloudGroupForm {
	
	private String id;
	private String fgName;
	private String fgAppUserIds;
	private String fgServPersonIds;
	private String fgAppUserNames;
	private String fgServPersonNames;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getFgName(){
		return this.fgName;
	}

	public String getFgAppUserIds(){
		return this.fgAppUserIds;
	}

	public String getFgServPersonIds(){
		return this.fgServPersonIds;
	}

	public String getFgAppUserNames(){
		return this.fgAppUserNames;
	}

	public String getFgServPersonNames(){
		return this.fgServPersonNames;
	}

	public void setFgName(String fgname){
		this.fgName=fgname;
	}

	public void setFgAppUserIds(String fgappuserids){
		this.fgAppUserIds=fgappuserids;
	}

	public void setFgServPersonIds(String fgservpersonids){
		this.fgServPersonIds=fgservpersonids;
	}

	public void setFgAppUserNames(String fgappusernames){
		this.fgAppUserNames=fgappusernames;
	}

	public void setFgServPersonNames(String fgservpersonnames){
		this.fgServPersonNames=fgservpersonnames;
	}

}
