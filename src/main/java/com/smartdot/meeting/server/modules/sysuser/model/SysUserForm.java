package com.smartdot.meeting.server.modules.sysuser.model;

public class SysUserForm {
	
	private String id;
	private String userName;
	private String password;
	private String name;
	private String mobile;
	private Integer state;
	private String roleId;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getUserName(){
		return this.userName;
	}

	public String getPassword(){
		return this.password;
	}

	public String getName(){
		return this.name;
	}

	public String getMobile(){
		return this.mobile;
	}

	public Integer getState(){
		return this.state;
	}

	public String getRoleId(){
		return this.roleId;
	}

	public void setUserName(String username){
		this.userName=username;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public void setState(Integer state){
		this.state=state;
	}

	public void setRoleId(String roleid){
		this.roleId=roleid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
