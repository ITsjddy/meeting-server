package com.smartdot.meeting.server.modules.department.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;
public class DepartmentPageForm {
	private int currentPage;
	private int pageSize;
	private String id;
	private String departname;
	private String endepartname;
	private Integer ordernumber;
	private String parentid;
	private String fullpath;
	private String classification;
	private String type;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
}
