package com.smartdot.meeting.server.modules.questionnaire.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;
public class QuestionnairePageForm {
	private int currentPage;
	private int pageSize;
	private String id;
	private String title;
	private String description;
	private Integer topicNum;

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
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public Integer getTopicNum(){
		return this.topicNum;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public void setTopicNum(Integer topicnum){
		this.topicNum=topicnum;
	}
}
