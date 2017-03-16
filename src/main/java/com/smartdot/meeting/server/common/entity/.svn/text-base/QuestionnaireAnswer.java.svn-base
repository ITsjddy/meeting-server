package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author yanjj
 * @since 2017.1.17
 *
 */
@Entity
@Table(name = "T_QUESTIONNAIRE_ANSWER")
public class QuestionnaireAnswer extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874266835243808938L;
	
	/** 用户编号*/
	private String userId;
	
	/** 问卷编号*/
	private String questionnaireId;
	
	/** 题目编号*/
	private String topicId;
	
	/** 答案内容*/
	private String content;

	@Column(name="USERID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="QUESTIONNAIREID")
	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	@Column(name="TOPICID")
	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
