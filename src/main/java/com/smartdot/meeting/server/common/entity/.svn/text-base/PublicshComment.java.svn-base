package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "T_PUBLISH_COMMENT")
public class PublicshComment extends BaseEntity {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主题
	 */
	private PublicshComment subject;
	/**
	 * 发布人
	 */
	private Guest member;
	/**
	 * 回复某评论人
	 */
	private Guest hfmember;
	/**
	 * 内容
	 */
	private String message;
	/**
	 * 审核状态(1未审核,2审核通过,3审核未通过)
	 */
	private String status;
	/**
	 * 审核原因
	 */
	private String auditReason;
	/**
	 * 类别 1为主题，2为评论
	 */
	private String type;
	/**
	 * 主id
	 */
	private String groupid;
	
	
	@ManyToOne
	@JoinColumn(name = "SUBJECT")
	public PublicshComment getSubject() {
		return subject;
	}
	public void setSubject(PublicshComment subject) {
		this.subject = subject;
	}
	
	@ManyToOne
	@JoinColumn(name = "MEMBER")
	public Guest getMember() {
		return member;
	}
	public void setMember(Guest member) {
		this.member = member;
	}
	
	@ManyToOne
	@JoinColumn(name = "HFMEMBER")
	public Guest getHfmember() {
		return hfmember;
	}
	public void setHfmember(Guest hfmember) {
		this.hfmember = hfmember;
	}
	
	@Column(name = "MESSAGE")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "AUDITREASON")
	public String getAuditReason() {
		return auditReason;
	}
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "GROUPID")
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

}
