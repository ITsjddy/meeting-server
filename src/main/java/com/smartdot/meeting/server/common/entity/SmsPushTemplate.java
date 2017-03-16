package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * @ClassName: SmsPushTemplate 
 * @Description: 短信推送模板
 * @author: haomt
 * @date: 2017年1月18日 下午2:07:10  
 */
@Entity
@Table(name = "T_SMS_PUSH_TEMPLATE")
public class SmsPushTemplate  extends BaseEntity {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @fieldName: sptNo
	 * @fieldType: String
	 * @Description: 模板编号
	 */
	private String sptNo;
	@Column(name = "SPT_NO")
	public String getSptNo() {
		return sptNo;
	}

	public void setSptNo(String sptNo) {
		this.sptNo = sptNo;
	}
	
	/**
	 * @fieldName: sptTetle
	 * @fieldType: String
	 * @Description: 模板标题
	 */
	private String sptTetle;
	@Column(name = "SPT_TETLE")
	public String getSptTetle() {
		return sptTetle;
	}

	public void setSptTetle(String sptTetle) {
		this.sptTetle = sptTetle;
	}
	
	
	/**
	 * @fieldName: sptText
	 * @fieldType: String
	 * @Description: 模板内容
	 */
	private String sptText;
	@Column(name = "SPT_TEXT")
	public String getSptText() {
		return sptText;
	}

	public void setSptText(String sptText) {
		this.sptText = sptText;
	}
	
	

}
