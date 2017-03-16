package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author yanjj
 * @since 2017.1.16
 */

@Entity
@Table(name = "T_QUESTIONNAIRE")
public class Questionnaire extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2017216749918782939L;

	/** 问卷标题*/
	private String title;
	
	/** 说明*/
	private String description;
	
	/** 总题目数*/
	private Integer topicNum;
	
	/** 状态（0：未发布:1：已发布）注：已发布的问卷不能修改。*/
	private Integer state;

	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="TOPICNUM")
	public Integer getTopicNum() {
		return topicNum;
	}

	public void setTopicNum(Integer topicNum) {
		this.topicNum = topicNum;
	}

	@Column(name="STATE")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
