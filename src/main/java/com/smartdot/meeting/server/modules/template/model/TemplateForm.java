package com.smartdot.meeting.server.modules.template.model;

public class TemplateForm {
	
	private String id;
	private String templateName;
	private String fieldName;
	private String explain;
	private String tableName;
	private String isMust;

	
	public String getIsMust() {
		return isMust;
	}

	public void setIsMust(String isMust) {
		this.isMust = isMust;
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
