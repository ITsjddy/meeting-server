package com.smartdot.meeting.server.modules.sysprivilege.model;

public class SysPrivilegePageForm {
	
	private int currentPage;
	private int pageSize;
	private String id;
	private String name;
	private String url;
	private String type;
	private String level;
	private String remark;
	private String display;
	private String resKey;

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
	
	public String getUrl(){
		return this.url;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getLevel(){
		return this.level;
	}
	
	public String getRemark(){
		return this.remark;
	}
	
	public String getDisplay(){
		return this.display;
	}
	
	public String getResKey(){
		return this.resKey;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public void setType(String type){
		this.type=type;
	}

	public void setLevel(String level){
		this.level=level;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public void setDisplay(String display){
		this.display=display;
	}

	public void setResKey(String reskey){
		this.resKey=reskey;
	}
}
