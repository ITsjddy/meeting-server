package com.smartdot.meeting.server.modules.guest.model;


public class GuestAndServicePersonnelForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 类型唯一标识
	 */
	private String type;
	/**
	 * 嘉宾id
	 */
	private String guestId;
	/**
	 * 服务人员id
	 */
	private String servicePersonnelId;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 服务人员姓名
	 */
	private String servicePersonnelName;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getServicePersonnelName() {
		return servicePersonnelName;
	}
	public void setServicePersonnelName(String servicePersonnelName) {
		this.servicePersonnelName = servicePersonnelName;
	}
	
	public String getServicePersonnelId() {
		return servicePersonnelId;
	}
	public void setServicePersonnelId(String servicePersonnelId) {
		this.servicePersonnelId = servicePersonnelId;
	}
	
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	
}
