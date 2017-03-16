package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * app用户-嘉宾从表
 * 
 * @author ms
 */

@Entity
@Table(name = "T_HOTEL")
public class MemberHotel extends BaseEntity {
	
	private static final long serialVersionUID = 4612208631714217145L;
	
	/**
	 * 嘉宾id
	 */
	private String memberId;
	/**
	 * 酒店id
	 */
	private String hotelId;
	/**
	 * 酒店名称
	 */
	private String hotelName;
	/**
	 * 酒店地址
	 */
	private String hotelAddress;
	
	/**
	 * 房间号
	 */
	private String roomNum;
	/**
	 * 酒店介绍
	 */
	private String hotelIntroduction;
	/**
	 * 入住时间
	 */
	private String checkInTime;
	/**
	 * 离店时间
	 */
	private String checkOutTime;
	/**
	 * 特殊要求
	 */
	private String specialRequirements;
	
	@Column(name="hotelId")
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	@Column(name="memberId")
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Transient
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	@Column(name="hotelAddress")
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	@Column(name="roomNum")
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	@Column(name="hotelIntroduction")
	public String getHotelIntroduction() {
		return hotelIntroduction;
	}
	public void setHotelIntroduction(String hotelIntroduction) {
		this.hotelIntroduction = hotelIntroduction;
	}
	@Column(name="checkInTime")
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	@Column(name="checkOutTime")
	public String getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	@Column(name="specialRequirements")
	public String getSpecialRequirements() {
		return specialRequirements;
	}
	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}
	
	
}
