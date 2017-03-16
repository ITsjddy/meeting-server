package com.smartdot.meeting.server.modules.exhibitors.model;

import com.smartdot.meeting.server.common.entity.Member;

public class ExhibitorsForm {
	
	/**
	 * 主表id
	 */
	private Object memberExhibitors;
	/**
	 * 主表外键
	 */
	private String[] arrayExhibitors;
	public Object getMemberExhibitors() {
		return memberExhibitors;
	}
	public void setMemberExhibitors(Object memberExhibitors) {
		this.memberExhibitors = memberExhibitors;
	}
	public String[] getArrayExhibitors() {
		return arrayExhibitors;
	}
	public void setArrayExhibitors(String[] arrayExhibitors) {
		this.arrayExhibitors = arrayExhibitors;
	}
	
	
}
