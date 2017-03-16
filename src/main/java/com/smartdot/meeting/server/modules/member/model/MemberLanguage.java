package com.smartdot.meeting.server.modules.member.model;

public class MemberLanguage {
	
	
	/**
	 * 语言唯一标识
	 */
	private String uneIdent;
	/**
	 * 语言中文名称
	 */
	private String name;
	/**
	 * 是否被选中
	 */
	private boolean check = false;
	
	
	public String getUneIdent() {
		return uneIdent;
	}
	public void setUneIdent(String uneIdent) {
		this.uneIdent = uneIdent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
}
