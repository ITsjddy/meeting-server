package com.smartdot.meeting.server.modules.smspushtemplate.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;

public class SmsPushTemplateVO {
	private String id;
	private String sptNo;
	private String sptTetle;
	private String sptText;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getSptNo(){
		return this.sptNo;
	}

	public String getSptTetle(){
		return this.sptTetle;
	}

	public String getSptText(){
		return this.sptText;
	}

	public void setSptNo(String sptno){
		this.sptNo=sptno;
	}

	public void setSptTetle(String spttetle){
		this.sptTetle=spttetle;
	}

	public void setSptText(String spttext){
		this.sptText=spttext;
	}
}
