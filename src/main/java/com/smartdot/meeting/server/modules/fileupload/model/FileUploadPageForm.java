package com.smartdot.meeting.server.modules.fileupload.model;

public class FileUploadPageForm {
	
	private int currentPage;
	private int pageSize;
	private String id;
	private String type;
	private String explain;
	private String bigFileurl;
	private String smallFileurl;
	private String bigFilename;
	private String smallFilename;
	private String groupid;

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
	
	public String getType(){
		return this.type;
	}
	
	public String getExplain(){
		return this.explain;
	}
	
	public String getBigFileurl(){
		return this.bigFileurl;
	}
	
	public String getSmallFileurl(){
		return this.smallFileurl;
	}
	
	public String getBigFilename(){
		return this.bigFilename;
	}
	
	public String getSmallFilename(){
		return this.smallFilename;
	}
	
	public String getGroupid(){
		return this.groupid;
	}

	public void setType(String type){
		this.type=type;
	}

	public void setExplain(String explain){
		this.explain=explain;
	}

	public void setBigFileurl(String bigfileurl){
		this.bigFileurl=bigfileurl;
	}

	public void setSmallFileurl(String smallfileurl){
		this.smallFileurl=smallfileurl;
	}

	public void setBigFilename(String bigfilename){
		this.bigFilename=bigfilename;
	}

	public void setSmallFilename(String smallfilename){
		this.smallFilename=smallfilename;
	}

	public void setGroupid(String groupid){
		this.groupid=groupid;
	}
}
