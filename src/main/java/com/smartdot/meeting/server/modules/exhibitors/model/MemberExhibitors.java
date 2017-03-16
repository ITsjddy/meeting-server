package com.smartdot.meeting.server.modules.exhibitors.model;

import com.smartdot.meeting.server.common.entity.BaseEntity;

public class MemberExhibitors extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主表id
	 */
	private String memberId;
	/**
	 * code码/邀请码/用户名 (数字+字母组成、app访问接口的唯一标识)
	 */
	private String invitationCode;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 用户名(特殊字符或汉字组成的)
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 盐值
	 */
	private String salt;
	/**
	 * 是否已经扫描二维码登录
	 */
	private String whetherScanning;
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
	 * 嘉宾分类类别 多选  数据字典 
	 */
	private String classification;
	/**
	 * 嘉宾级别 多选  数据字典
	 */
	private String level;
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
	 * 是否 领导关注
	 */
	private String leadAttention;
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
	 * 语言标识 (数据字典)
	 */
	private String language;
	/**
	 * 主表外键
	 *//*
	private Member member;*/
	
	
	
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getWhetherScanning() {
		return whetherScanning;
	}
	public void setWhetherScanning(String whetherScanning) {
		this.whetherScanning = whetherScanning;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getLeadAttention() {
		return leadAttention;
	}
	public void setLeadAttention(String leadAttention) {
		this.leadAttention = leadAttention;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUnitExplain() {
		return unitExplain;
	}
	public void setUnitExplain(String unitExplain) {
		this.unitExplain = unitExplain;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/*public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}*/
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
}
