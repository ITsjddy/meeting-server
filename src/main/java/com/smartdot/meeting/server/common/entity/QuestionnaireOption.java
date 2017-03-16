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
@Table(name = "T_QUESTIONNAIRE_OPTION")
public class QuestionnaireOption extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4250723253003308491L;
	
	/** 选项内容*/
	private String content;
	
	/** 题目编号*/
	private String topicId;
	
	/** 问卷编号*/
	private String questionnaireId;

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="TOPICID")
	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	@Column(name="QUESTIONNAIREID")
	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	
}
