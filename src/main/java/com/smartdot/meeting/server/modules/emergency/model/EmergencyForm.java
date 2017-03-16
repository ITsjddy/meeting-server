package com.smartdot.meeting.server.modules.emergency.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;
public class EmergencyForm {
	private String id;
	private String emergencyTel;
	private String emergencyType;
	private String emergencyPic;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getEmergencyTel(){
		return this.emergencyTel;
	}

	public String getEmergencyType(){
		return this.emergencyType;
	}

	public String getEmergencyPic(){
		return this.emergencyPic;
	}

	public void setEmergencyTel(String emergencytel){
		this.emergencyTel=emergencytel;
	}

	public void setEmergencyType(String emergencytype){
		this.emergencyType=emergencytype;
	}

	public void setEmergencyPic(String emergencypic){
		this.emergencyPic=emergencypic;
	}

}
