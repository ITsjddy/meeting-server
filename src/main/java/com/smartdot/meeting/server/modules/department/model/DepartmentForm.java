package com.smartdot.meeting.server.modules.department.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;
public class DepartmentForm {
	private String id;
	private String departname;
	private String endepartname;
	private Integer ordernumber;
	private Integer parentid;
	private String fullpath;
	private String classification;
	private String type;

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

	public Integer getParentid(){
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

	public void setParentid(Integer parentid){
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

}
