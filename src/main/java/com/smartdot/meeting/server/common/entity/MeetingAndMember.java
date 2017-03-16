package com.smartdot.meeting.server.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	参加 会议(议题)的嘉宾 中间表
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月12日 下午2:14:07
 * </pre>
 * </p>
 */
@Entity
@Table(name = "T_MEETING_MEMBER")
public class MeetingAndMember extends BaseEntity {
	private static final long serialVersionUID = 4612208631714217145L;
	/**
	 * 论坛选嘉宾
	 */
	public static final String MEETING_MEMBER_TYPE_GUEST = "scheduleGuest";
	//===================数据来源常量
	/**
	 * MEETING_MEMBER_SOURCE_SCHEDULE 数据来源为论坛选人
	 * MEETING_MEMBER_SOURCE_ISSUE  数据来源为议题选人
	 */
	public static final String MEETING_MEMBER_SOURCE_SCHEDULE = "schedule";
	/**
	 * MEETING_MEMBER_SOURCE_SCHEDULE 数据来源为论坛选人
	 * MEETING_MEMBER_SOURCE_ISSUE  数据来源为议题选人
	 */
	public static final String MEETING_MEMBER_SOURCE_ISSUE = "issue";
	
	/**
	 * 会议(议题)id
	 * 活动(活动议题)id
	 */
	private String meetingId;
	/**
	 * 嘉宾code码/工作人员id
	 */
	private String memberUnique;
	/**
	 * 类型 
	 */
	private String type;
	/**
	 * 数据来源
	 */
	private String source;
	/**
	 * 议题/论坛选嘉宾，排序字段
	 */
	private Integer sortNumber;
	
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "MEETING_ID")
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	@Column(name = "MEMBER_UNIQUE")
	public String getMemberUnique() {
		return memberUnique;
	}
	public void setMemberUnique(String memberUnique) {
		this.memberUnique = memberUnique;
	}
	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name = "SORT_NUMBER")
	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}
	
}
