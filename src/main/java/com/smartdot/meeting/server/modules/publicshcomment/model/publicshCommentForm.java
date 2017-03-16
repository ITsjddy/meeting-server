package com.smartdot.meeting.server.modules.publicshcomment.model;

public class publicshCommentForm {
	
	private String id;
	private String message;
	private String status;
	private String auditReason;
	private String type;
	private String groupid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getMessage(){
		return this.message;
	}

	public String getStatus(){
		return this.status;
	}

	public String getAuditReason(){
		return this.auditReason;
	}

	public String getType(){
		return this.type;
	}

	public String getGroupid(){
		return this.groupid;
	}

	public void setMessage(String message){
		this.message=message;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public void setAuditReason(String auditreason){
		this.auditReason=auditreason;
	}

	public void setType(String type){
		this.type=type;
	}

	public void setGroupid(String groupid){
		this.groupid=groupid;
	}

}
