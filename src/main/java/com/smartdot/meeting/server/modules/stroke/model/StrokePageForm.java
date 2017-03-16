package com.smartdot.meeting.server.modules.stroke.model;

public class StrokePageForm {
	
	private int currentPage;
	private int pageSize;
	private String id;
	private String name;
	private String strokedate;
	private String stroketime;
	private String title;
	private String location;
	private String remarks;

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
	
	public String getStrokedate(){
		return this.strokedate;
	}
	
	public String getStroketime(){
		return this.stroketime;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getRemarks(){
		return this.remarks;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setStrokedate(String strokedate){
		this.strokedate=strokedate;
	}

	public void setStroketime(String stroketime){
		this.stroketime=stroketime;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public void setLocation(String location){
		this.location=location;
	}

	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
}
