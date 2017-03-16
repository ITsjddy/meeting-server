package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * @ClassName: SmsPushs 
 * @Description: 短信推送记录
 * @author: haomt
 * @date: 2017年2月13日 下午3:13:04  
 */
@Entity
@Table(name = "T_SMS_PUSHS")
public class SmsPushs extends BaseEntity{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 226486433382038991L;
	
	
	/**
	 * @fieldName: smstId
	 * @fieldType: String
	 * @Description: 短信模板ID
	 */
	private String smstId;
	
	@Column(name = "SMST_ID")
	public String getSmstId() {
		return smstId;
	}
	
	public void setSmstId(String smstId) {
		this.smstId = smstId;
	}
	/**
	 * @fieldName: smspTitle
	 * @fieldType: String
	 * @Description: 推送的标题
	 */
	private String smspTitle;
	
	@Column(name = "SMSP_TITLE")
	public String getSmspTitle() {
		return smspTitle;
	}

	public void setSmspTitle(String smspTitle) {
		this.smspTitle = smspTitle;
	}
	/**
	 * @fieldName: smspText
	 * @fieldType: String
	 * @Description: 推送内容
	 */
	private String smspText;
	
	@Column(name = "SMSP_TEXT")
	public String getSmspText() {
		return smspText;
	}

	public void setSmspText(String smspText) {
		this.smspText = smspText;
	}
	/**
	 * @fieldName: smsphoneNums
	 * @fieldType: String
	 * @Description: 手机号码
	 */
	private String smsphoneNums;
	
	@Column(name = "SMSP_HOME_NUMS")
	public String getSmsphoneNums() {
		return smsphoneNums;
	}

	public void setSmsphoneNums(String smsphoneNums) {
		this.smsphoneNums = smsphoneNums;
	}
	/**
	 * @fieldName: smspStbers
	 * @fieldType: String
	 * @Description: 工作人员
	 */
	private String smspStbers;
	
	@Column(name = "SMSP_STBERS")
	public String getSmspStbers() {
		return smspStbers;
	}

	public void setSmspStbers(String smspStbers) {
		this.smspStbers = smspStbers;
	}
	/**
	 * @fieldName: smspGuests
	 * @fieldType: String
	 * @Description: 嘉宾
	 */
	private String smspGuests;

	@Column(name = "SMSP_GUESTS")
	public String getSmspGuests() {
		return smspGuests;
	}

	public void setSmspGuests(String smspGuests) {
		this.smspGuests = smspGuests;
	}
	
	
	/**
	 * @fieldName: smspBf
	 * @fieldType: Boolean
	 * @Description: 是否推送成功
	 */
	private Boolean smspBf;
	
	@Column(name = "SMSP_BF")
	public Boolean isSmspBf() {
		return smspBf;
	}

	public void setSmspBf(Boolean smspBf) {
		this.smspBf = smspBf;
	}
	
	
	
	
	
}
