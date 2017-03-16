package com.smartdot.meeting.server.modules.template.model;

public class TemplatePageForm {
	
	private int currentPage;
	private int pageSize;
	private String id;
	private String templateName;
	private String fieldName;
	private String explain;
	private String tableName;

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
	
	public String getTemplateName(){
		return this.templateName;
	}
	
	public String getFieldName(){
		return this.fieldName;
	}
	
	public String getExplain(){
		return this.explain;
	}
	
	public String getTableName(){
		return this.tableName;
	}

	public void setTemplateName(String templatename){
		this.templateName=templatename;
	}

	public void setFieldName(String fieldname){
		this.fieldName=fieldname;
	}

	public void setExplain(String explain){
		this.explain=explain;
	}

	public void setTableName(String tablename){
		this.tableName=tablename;
	}
}
