package com.smartdot.meeting.server.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 嘉宾的服务人员中间表
 * 
 * @author ms
 */
@Entity
@Table(name = "T_GUEST_SERVICE_PERSONNEL")
public class GuestAndServicePersonnel extends BaseEntity {
	
	private static final long serialVersionUID = 4612208631714217145L;

	
	/**
	 * 嘉宾id
	 */
	private String guestId;
	/**
	 * 服务人员id
	 */
	private String servicePersonnelId;
	/**
	 * 服务人员类型唯一标识(数据字典)
	 */
	private String type;
	
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "GUEST_ID")
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	
	@Column(name = "SERVICE_PERSONNEL_ID")
	public String getServicePersonnelId() {
		return servicePersonnelId;
	}
	public void setServicePersonnelId(String servicePersonnelId) {
		this.servicePersonnelId = servicePersonnelId;
	}
	
	
}
