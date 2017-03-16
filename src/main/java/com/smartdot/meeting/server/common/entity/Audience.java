package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * app用户-观众从表
 * 
 * @author ms
 */

@Entity
@Table(name = "T_AUDIENCE")
public class Audience extends BaseEntity {
	
	private static final long serialVersionUID = 4612208631714217145L;
	

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 联系人电话
	 */
	private String contactPhone;
	/**
	 * 工作单位
	 */
	private String workPlace;
	/**
	 * 职位
	 * */
	private String job;
	/**
	 * 单位简介
	 */
	private String unitExplain;
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
	
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "CONTACT_PERSON")
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@Column(name = "CONTACT_PHONE")
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Column(name = "WORK_PLACE")
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	
	@Column(name = "JOB")
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	@Column(name = "UNIT_EXPLAIN")
	public String getUnitExplain() {
		return unitExplain;
	}
	public void setUnitExplain(String unitExplain) {
		this.unitExplain = unitExplain;
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
	
	
}
