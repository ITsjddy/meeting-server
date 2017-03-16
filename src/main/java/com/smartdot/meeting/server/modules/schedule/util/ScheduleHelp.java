package com.smartdot.meeting.server.modules.schedule.util;

import com.smartdot.meeting.server.common.entity.Schedule;

public class ScheduleHelp extends Schedule {

	/**
	 * <p>
	 * <pre>
	 * <b>属性描述：</b>
	 * 	描述内容
	 * </pre>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] memberIds;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	public String[] getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(String[] memberIds) {
		this.memberIds = memberIds;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

}
