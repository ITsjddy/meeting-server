package com.smartdot.meeting.server.common.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	论坛
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午4:30:17
 * </pre>
 * </p>
 */
@Entity
@Table(name = "T_SCHEDULE")
public class Schedule extends BaseEntity {
	
	private static final long serialVersionUID = 2080782515698527526L;
	
	/**
	 * 会议/论坛名称
	 */
	private String name;
	/**
	 * 会议/论坛名称缩写
	 */
	private String shortname;
	/**
	 * 所属板块id
	 */
	private String plateId;

	/**
	 * 会场地点id
	 */
	private String conHallId;
	
	/**
	 * 会场联系人
	 */
	private String venueContact;
	
	/**
	 * 会场联系人电话
	 */
	private String venueMobile;
	
	/**
	 * 论坛开始日期
	 */
	private Date scheduleStartDate;
	/**
	 * 论坛结束日期
	 */
	private Date scheduleEndDate;
	/**
	 * 论坛开始时间 HH:mm
	 */
	private Timestamp startTimes;
	/**
	 * 论坛结束时间HH:mm
	 */
	private Timestamp endTimes;
	
	/**
	 * 承办单位
	 */
	private String undertakingUnit;
	/**
	 * 承办单位 联系人
	 */
	private String undertakingContact;
	/**
	 * 承办单位 联系电话
	 */
	private String undertakingMobile;
	
	/**
	 * 责任单位
	 */
	private String responsibilityUnit;
	/**
	 * 责任单位 联系人
	 */
	private String responsibilityContact;
	/**
	 * 责任单位 联系电话
	 */
	private String responsibilityMobile;
	
	/**
	 * 论坛组 对接人
	 */
	private String dockingPerson;
	
	/**
	 * 主办单位
	 */
	private String hostUnit;
	/**
	 * 主办单位 联系人
	 */
	private String hostContact;

	/**
	 * 支持单位
	 */
	private String supportUnit;
	/**
	 * 协办单位
	 */
	private String coUnit;
	
	/**
	 * 主持人
	 */
	private String hostPerson;
	/**
	 * 论坛简介(描述) 
	 * */
	private String remark;
	/**
	 * 是否已经推送 0 未推送,1推送
	 */
	private String isJpush;
	/**
	 * 这条记录的语言标示
	 */
	private String language;
	/**
	 * 这条记录的唯一标示
	 */
	private String uniqueCode;
	
	
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "SHORT_NAME")
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	@Column(name = "PLATE_ID")
	public String getPlateId() {
		return plateId;
	}
	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}
	@Column(name = "CON_HALL_ID")
	public String getConHallId() {
		return conHallId;
	}
	public void setConHallId(String conHallId) {
		this.conHallId = conHallId;
	}
	@Column(name = "VENUE_CONTACT")
	public String getVenueContact() {
		return venueContact;
	}
	public void setVenueContact(String venueContact) {
		this.venueContact = venueContact;
	}
	@Column(name = "VENUE_MOBILE")
	public String getVenueMobile() {
		return venueMobile;
	}
	public void setVenueMobile(String venueMobile) {
		this.venueMobile = venueMobile;
	}
	@Column(name = "SCHEDULE_START_DATE")
	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}
	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}
	@Column(name = "SCHEDULE_END_DATE")
	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}
	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}
	@Column(name = "START_TIMES")
	public Timestamp getStartTimes() {
		return startTimes;
	}
	public void setStartTimes(Timestamp startTimes) {
		this.startTimes = startTimes;
	}
	@Column(name = "END_TIMES")
	public Timestamp getEndTimes() {
		return endTimes;
	}
	public void setEndTimes(Timestamp endTimes) {
		this.endTimes = endTimes;
	}
	@Column(name = "UNDERTAKING_UNIT")
	public String getUndertakingUnit() {
		return undertakingUnit;
	}
	public void setUndertakingUnit(String undertakingUnit) {
		this.undertakingUnit = undertakingUnit;
	}
	@Column(name = "UNDERTAKING_CONTACT")
	public String getUndertakingContact() {
		return undertakingContact;
	}
	public void setUndertakingContact(String undertakingContact) {
		this.undertakingContact = undertakingContact;
	}
	@Column(name = "UNDERTAKING_MOBILE")
	public String getUndertakingMobile() {
		return undertakingMobile;
	}
	public void setUndertakingMobile(String undertakingMobile) {
		this.undertakingMobile = undertakingMobile;
	}
	@Column(name = "RESPONSIBILITY_UNIT")
	public String getResponsibilityUnit() {
		return responsibilityUnit;
	}
	public void setResponsibilityUnit(String responsibilityUnit) {
		this.responsibilityUnit = responsibilityUnit;
	}
	@Column(name = "RESPONSIBILITY_CONTACT")
	public String getResponsibilityContact() {
		return responsibilityContact;
	}
	public void setResponsibilityContact(String responsibilityContact) {
		this.responsibilityContact = responsibilityContact;
	}
	@Column(name = "RESPONSIBILITY_MOBILE")
	public String getResponsibilityMobile() {
		return responsibilityMobile;
	}
	public void setResponsibilityMobile(String responsibilityMobile) {
		this.responsibilityMobile = responsibilityMobile;
	}
	@Column(name = "DOCKING_PERSON")
	public String getDockingPerson() {
		return dockingPerson;
	}
	public void setDockingPerson(String dockingPerson) {
		this.dockingPerson = dockingPerson;
	}
	@Column(name = "HOST_UNIT")
	public String getHostUnit() {
		return hostUnit;
	}
	public void setHostUnit(String hostUnit) {
		this.hostUnit = hostUnit;
	}
	@Column(name = "HOST_CONTACT")
	public String getHostContact() {
		return hostContact;
	}
	public void setHostContact(String hostContact) {
		this.hostContact = hostContact;
	}
	@Column(name = "SUPPORT_UNIT")
	public String getSupportUnit() {
		return supportUnit;
	}
	public void setSupportUnit(String supportUnit) {
		this.supportUnit = supportUnit;
	}
	@Column(name = "CO_UNIT")
	public String getCoUnit() {
		return coUnit;
	}
	public void setCoUnit(String coUnit) {
		this.coUnit = coUnit;
	}
	@Column(name = "HOST_PERSON")
	public String getHostPerson() {
		return hostPerson;
	}
	public void setHostPerson(String hostPerson) {
		this.hostPerson = hostPerson;
	}
	@Column(name = "REMARK",length = 2000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "IS_JPUSH")
	public String getIsJpush() {
		return isJpush;
	}
	public void setIsJpush(String isJpush) {
		this.isJpush = isJpush;
	}
	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Column(name = "UNIQUE_CODE")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
}
