package com.smartdot.meeting.server.modules.stroke.model;

public class StrokeForm {
	
	private String id;
	private String name;
	private String startStrokedate;
	private String startStroketime;
	private String stopStrokedate;
	private String stopStroketime;
	private String title;
	private String location;
	private String remarks;
	private String language;
	/**
	 * 主表id
	 */
	private Object memberStroke;
	public Object getMemberStroke() {
		return memberStroke;
	}

	public void setMemberStroke(Object memberStroke) {
		this.memberStroke = memberStroke;
	}

	/**
	 * 主表外键
	 */
	private String[] arrayStroke;
    public String[] getArrayStroke() {
		return arrayStroke;
	}

	public void setArrayStroke(String[] arrayStroke) {
		this.arrayStroke = arrayStroke;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	private String memberId;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getName(){
		return this.name;
	}

	public String getTitle(){
		return this.title;
	}

	public String getLocation(){
		return this.location;
	}

	public String getRemarks(){
		return this.remarks;
	}

	public String getStartStrokedate() {
		return startStrokedate;
	}

	public void setStartStrokedate(String startStrokedate) {
		this.startStrokedate = startStrokedate;
	}

	public String getStartStroketime() {
		return startStroketime;
	}

	public void setStartStroketime(String startStroketime) {
		this.startStroketime = startStroketime;
	}

	public String getStopStrokedate() {
		return stopStrokedate;
	}

	public void setStopStrokedate(String stopStrokedate) {
		this.stopStrokedate = stopStrokedate;
	}

	public String getStopStroketime() {
		return stopStroketime;
	}

	public void setStopStroketime(String stopStroketime) {
		this.stopStroketime = stopStroketime;
	}

	public void setName(String name){
		this.name=name;
	}
	public void setTitle(String title){
		this.title=title;
	}

	public void setLocation(String location){
		this.location=location;
	}

	public void setRemarks(String remarks){
		this.remarks=remarks;
	}

}
