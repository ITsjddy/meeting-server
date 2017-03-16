package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * app用户-媒体从表
 * 
 * @author ms
 */

@Entity
@Table(name = "T_MEDIA")
public class Media extends BaseEntity {
	
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
	 * 工作单位
	 */
	private String workPlace;
	/**
	 * 职位
	 * */
	private String job;
	/**
	 * 国籍
	 */
	private String nationality;
	/**
	 * 籍贯
	 * */
	private String nativePlace;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 证件类型   数据字典
	 */
	private String idType;
	/**
	 * 证件号
	 */
	private String documentNumber;
	/**
	 * 头像
	 */
	private String avatar;
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
	
	@Column(name = "NATIONALITY")
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Column(name = "NATIVE_PLACE")
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Column(name = "NATION")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Column(name = "ID_TYPE")
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	@Column(name = "DOCUMENT_NUMBER")
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@Column(name = "AVATAR")
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
