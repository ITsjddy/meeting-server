package com.smartdot.meeting.server.modules.audience.model;

public class AudienceForm {
	/**
	 * 主表id
	 */
	private Object memberAudience;
	/**
	 * 主表外键
	 */
	private String[] arrayAudience;
	public Object getMemberAudience() {
		return memberAudience;
	}
	public void setMemberAudience(Object memberAudience) {
		this.memberAudience = memberAudience;
	}
	public String[] getArrayAudience() {
		return arrayAudience;
	}
	public void setArrayAudience(String[] arrayAudience) {
		this.arrayAudience = arrayAudience;
	}
	
}
