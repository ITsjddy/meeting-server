package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author yanjj
 * @since 2017.1.6
 */

@Entity
@Table(name = "T_EMERGENCY")
public class Emergency extends BaseEntity {

	private static final long serialVersionUID = -8868553102704949384L;

	private String emergencyTel;
	
	private String emergencyType;
	
	private String emergencyPic;

	@Column(name = "EMERGENCY_TEL")
	public String getEmergencyTel() {
		return emergencyTel;
	}

	public void setEmergencyTel(String emergencyTel) {
		this.emergencyTel = emergencyTel;
	}

	@Column(name = "EMERGENCY_TYPE")
	public String getEmergencyType() {
		return emergencyType;
	}

	public void setEmergencyType(String emergencyType) {
		this.emergencyType = emergencyType;
	}

	@Column(name = "EMERGENCY_PIC")
	public String getEmergencyPic() {
		return emergencyPic;
	}

	public void setEmergencyPic(String emergencyPic) {
		this.emergencyPic = emergencyPic;
	}
	
	
}
