package com.smartdot.meeting.server.modules.memberhotel.model;

public class MemberHotelVO {
	
	private String id;
	private String memberId;
	private String hotelId;
	private String hotelName;
	private String hotelAddress;
	private String roomNum;
	private String hotelIntroduction;
	private String checkInTime;
	private String checkOutTime;
	private String specialRequirements;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId(){
		return this.memberId;
	}

	public String getHotelId(){
		return this.hotelId;
	}

	public String getHotelName(){
		return this.hotelName;
	}

	public String getHotelAddress(){
		return this.hotelAddress;
	}

	public String getRoomNum(){
		return this.roomNum;
	}

	public String getHotelIntroduction(){
		return this.hotelIntroduction;
	}

	public String getCheckInTime(){
		return this.checkInTime;
	}

	public String getCheckOutTime(){
		return this.checkOutTime;
	}

	public String getSpecialRequirements(){
		return this.specialRequirements;
	}

	public void setMemberId(String memberid){
		this.memberId=memberid;
	}

	public void setHotelId(String hotelid){
		this.hotelId=hotelid;
	}

	public void setHotelName(String hotelname){
		this.hotelName=hotelname;
	}

	public void setHotelAddress(String hoteladdress){
		this.hotelAddress=hoteladdress;
	}

	public void setRoomNum(String roomnum){
		this.roomNum=roomnum;
	}

	public void setHotelIntroduction(String hotelintroduction){
		this.hotelIntroduction=hotelintroduction;
	}

	public void setCheckInTime(String checkintime){
		this.checkInTime=checkintime;
	}

	public void setCheckOutTime(String checkouttime){
		this.checkOutTime=checkouttime;
	}

	public void setSpecialRequirements(String specialrequirements){
		this.specialRequirements=specialrequirements;
	}
}
