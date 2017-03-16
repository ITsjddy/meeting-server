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
@Table(name = "T_QUESTIONNAIRE_TOPIC")
public class QuestionnaireTopic extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6480581563809277396L;

	/** 题号*/
	private String topicNumber;
	
	/** 题目*/
	private String topic;
	
	/** 备注*/
	private String notes;
	
	/** 题目类型（填空题、单项选择题、多项选择题、简答题）*/
	private String topicType;
	
	/** 问卷编号*/
	private String questionnaireId;

	@Column(name="TOPICNUMBER")
	public String getTopicNumber() {
		return topicNumber;
	}

	public void setTopicNumber(String topicNumber) {
		this.topicNumber = topicNumber;
	}

	@Column(name="TOPIC")
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Column(name="NOTES")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name="TOPICTYPE")
	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	
	@Column(name="QUESTIONNAIREID")
	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	
}
