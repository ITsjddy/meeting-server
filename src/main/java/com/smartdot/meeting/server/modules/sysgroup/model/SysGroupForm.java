package com.smartdot.meeting.server.modules.sysgroup.model;

import java.util.List;

public class SysGroupForm {
	
	private String id;
	private String groupName;
	private String remark;
	private List<String> lroleIds;
	private String[] sroleIds;
	private String roleIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getGroupName(){
		return this.groupName;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setGroupName(String groupname){
		this.groupName=groupname;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public List<String> getLroleIds() {
		return lroleIds;
	}

	public void setLroleIds(List<String> lroleIds) {
		this.lroleIds = lroleIds;
	}

	public String[] getSroleIds() {
		return sroleIds;
	}

	public void setSroleIds(String[] sroleIds) {
		this.sroleIds = sroleIds;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}


}
