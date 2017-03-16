package com.smartdot.meeting.server.modules.commontask.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;
public class CommonTaskForm {
	private String id;
	private String name;
	private String remark;
	private String uniqueCode;
	private Integer sort;
	private Long createUser;
	private Long updateUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getName(){
		return this.name;
	}

	public String getRemark(){
		return this.remark;
	}

	public String getUniqueCode(){
		return this.uniqueCode;
	}

	public Integer getSort(){
		return this.sort;
	}

	public Long getCreateUser(){
		return this.createUser;
	}

	public Long getUpdateUser(){
		return this.updateUser;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public void setUniqueCode(String uniquecode){
		this.uniqueCode=uniquecode;
	}

	public void setSort(Integer sort){
		this.sort=sort;
	}

	public void setCreateUser(Long createuser){
		this.createUser=createuser;
	}

	public void setUpdateUser(Long updateuser){
		this.updateUser=updateuser;
	}

}
