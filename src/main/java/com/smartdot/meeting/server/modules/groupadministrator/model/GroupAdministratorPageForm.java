package com.smartdot.meeting.server.modules.groupadministrator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;
public class GroupAdministratorPageForm {
	private int currentPage;
	private int pageSize;
	private String id;
	private String groupId;
	private String name;

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
	
	public String getGroupId(){
		return this.groupId;
	}
	
	public String getName(){
		return this.name;
	}

	public void setGroupId(String groupid){
		this.groupId=groupid;
	}

	public void setName(String name){
		this.name=name;
	}
}
