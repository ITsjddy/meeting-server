package com.smartdot.meeting.server.modules.audience.model;

import com.smartdot.meeting.server.common.entity.BaseEntity;

public class MemberAudience extends BaseEntity{
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
	 * 是否已经扫描二维码登录
	 */
	private String whetherScanning;
	
	
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
	public String getWhetherScanning() {
		return whetherScanning;
	}
	public void setWhetherScanning(String whetherScanning) {
		this.whetherScanning = whetherScanning;
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