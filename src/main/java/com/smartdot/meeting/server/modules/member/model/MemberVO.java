package com.smartdot.meeting.server.modules.member.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;

public class MemberVO {
	private String id;
	private String memberId;
	private Timestamp createTimes;
	private Long updateTimes;
	private Boolean enable;
	private String invitationCode;
	private String mobile;
	private String email;
	private String userName;
	private String password;
	private String salt;
	private String whetherScanning;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId(){
		return this.memberId;
	}

	public Timestamp getCreateTimes(){
		return this.createTimes;
	}

	public Long getUpdateTimes(){
		return this.updateTimes;
	}

	public Boolean getEnable(){
		return this.enable;
	}

	public String getInvitationCode(){
		return this.invitationCode;
	}

	public String getMobile(){
		return this.mobile;
	}

	public String getEmail(){
		return this.email;
	}

	public String getUserName(){
		return this.userName;
	}

	public String getPassword(){
		return this.password;
	}

	public String getSalt(){
		return this.salt;
	}

	public String getWhetherScanning(){
		return this.whetherScanning;
	}

	public void setMemberId(String memberid){
		this.memberId=memberid;
	}

	public void setCreateTimes(Timestamp createtimes){
		this.createTimes=createtimes;
	}

	public void setUpdateTimes(Long updatetimes){
		this.updateTimes=updatetimes;
	}

	public void setEnable(Boolean enable){
		this.enable=enable;
	}

	public void setInvitationCode(String invitationcode){
		this.invitationCode=invitationcode;
	}

	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public void setUserName(String username){
		this.userName=username;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public void setSalt(String salt){
		this.salt=salt;
	}

	public void setWhetherScanning(String whetherscanning){
		this.whetherScanning=whetherscanning;
	}
}
