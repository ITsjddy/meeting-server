package com.smartdot.meeting.server.modules.sysrole.model;


public class SysRolePageForm {
	
	private int currentPage;
	private int pageSize;
	private String id;
	private String name;
	private String remark;
	private String roleKey;

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
	
	public String getName(){
		return this.name;
	}
	
	public String getRemark(){
		return this.remark;
	}
	
	public String getRoleKey(){
		return this.roleKey;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public void setRoleKey(String rolekey){
		this.roleKey=rolekey;
	}
}
