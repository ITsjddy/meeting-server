package com.smartdot.meeting.server.modules.media.model;


public class MediaForm {
	/**
	 * 主表id
	 */
	private Object memberMedia;
	/**
	 * 主表外键
	 */
	private String[] arrayMedia;
	
	
	public Object getMemberMedia() {
		return memberMedia;
	}
	public void setMemberMedia(Object memberMedia) {
		this.memberMedia = memberMedia;
	}
	
	public String[] getArrayMedia() {
		return arrayMedia;
	}
	public void setArrayMedia(String[] arrayMedia) {
		this.arrayMedia = arrayMedia;
	}
	
}
