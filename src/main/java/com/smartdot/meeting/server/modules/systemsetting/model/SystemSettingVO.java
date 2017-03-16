package com.smartdot.meeting.server.modules.systemsetting.model;

public class SystemSettingVO {
	
	private String id;
	private String uniqueId;
	private String content;
	private String status;
	private String description;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getUniqueId(){
		return this.uniqueId;
	}

	public String getContent(){
		return this.content;
	}

	public String getStatus(){
		return this.status;
	}

	public String getDescription(){
		return this.description;
	}

	public void setUniqueId(String uniqueid){
		this.uniqueId=uniqueid;
	}

	public void setContent(String content){
		this.content=content;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public void setDescription(String description){
		this.description=description;
	}
}
