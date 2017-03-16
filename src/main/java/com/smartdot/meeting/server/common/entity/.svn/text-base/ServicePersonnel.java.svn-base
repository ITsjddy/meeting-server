package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * app用户-服务人员从表
 * 
 * @author ms
 */

@Entity
@Table(name = "T_SERVICE_PERSONNEL")
public class ServicePersonnel extends BaseEntity {
	
	private static final long serialVersionUID = 4612208631714217145L;
	

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 单位
	 */
	private String company;
	/**
	 * 个人简介
	 */
	private String introduction;
	/**
	 * 主表外键
	 */
	private Member member;
	/**
	 * 语言标识 (数据字典)
	 */
	private String language;
	/**
	 * 所属团id
	 */
	private String groupId;
	/**
	 * 所属团名称
	 */
	private String groupName;
	
	@Transient
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name = "COMPANY")
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "INTRODUCTION")
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@ManyToOne
	@JoinColumn(name = "MEMBER")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Column(name = "GROUP_ID")
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
}
