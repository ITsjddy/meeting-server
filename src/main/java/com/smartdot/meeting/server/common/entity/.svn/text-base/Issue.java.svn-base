package com.smartdot.meeting.server.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	议题
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月11日 下午4:30:17
 * </pre>
 * </p>
 */
@Entity
@Table(name = "T_ISSUE")
public class Issue extends BaseEntity {

	private static final long serialVersionUID = 4612208631714217145L;
	/**
	 * 论坛id
	 */
	private String scheduleId;
	/**
	 * 议题名称
	 */
	private String issueName;
	/**
	 * 会场id
	 */
	private String conHallId;
	/**
	 * 主持人
	 */
	private String hostPerson;
	/**
	 * 议题简介
	 */
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
	/**
	 * 论坛开始日期
	 */
	private Date startDate;
	/**
	 * 论坛结束日期
	 */
	private Date endDate;
	@Column(name = "SCHEDULE_ID")
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	@Column(name = "ISSUE_NAME")
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	@Column(name = "CON_HALL_ID")
	public String getConHallId() {
		return conHallId;
	}
	public void setConHallId(String conHallId) {
		this.conHallId = conHallId;
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
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
