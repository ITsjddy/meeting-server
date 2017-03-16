package com.smartdot.meeting.server.modules.smspushs.model;

public class SmsPushsVO {
	
	private String id;
	private String smstId;
	private String smspTitle;
	private String smspText;
	private String smsphoneNums;
	private String smspStbers;
	private String smspGuests;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getSmstId(){
		return this.smstId;
	}

	public String getSmspTitle(){
		return this.smspTitle;
	}

	public String getSmspText(){
		return this.smspText;
	}

	public String getSmsphoneNums(){
		return this.smsphoneNums;
	}

	public String getSmspStbers(){
		return this.smspStbers;
	}

	public String getSmspGuests(){
		return this.smspGuests;
	}

	public void setSmstId(String smstid){
		this.smstId=smstid;
	}

	public void setSmspTitle(String smsptitle){
		this.smspTitle=smsptitle;
	}

	public void setSmspText(String smsptext){
		this.smspText=smsptext;
	}

	public void setSmsphoneNums(String smsphonenums){
		this.smsphoneNums=smsphonenums;
	}

	public void setSmspStbers(String smspstbers){
		this.smspStbers=smspstbers;
	}

	public void setSmspGuests(String smspguests){
		this.smspGuests=smspguests;
	}
}
