package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * App版本
 * 
 * @author yanjj
 * @since 2016.2.16
 */
@Entity
@Table(name = "T_APP_VERSION")
public class AppVersion extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8517145861129277075L;
	/**
	 * 版本名称
	 */
	private String versionName;
	/**
	 * 版本号
	 */
	private Integer versionNum;
	
	/**
	 * 1 Android 2:iOS
	 */
	private Integer type;
	
	/**
	 * 数据文件存放路径
	 */
	private String path;
	
	/**
	 * 二维码
	 */
	private String qrCode;
	
	/**
	 * ios plist文件
	 */
	private String plist;
	
	/**
	 * 更新页面显示的图片1 大
	 */
	private String picOne;
	
	/**
	 * 更新页面显示的图片2 小
	 */
	private String picTwo;
	
	/**
	 * 更新描述
	 */
	private String remark;
	
	@Column(name = "VERSION_NAME")
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@Column(name = "VERSION_NUM")
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "PATH")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name="QR_CODE")
	public String getQr_code() {
		return qrCode;
	}
	public void setQr_code(String qrCode) {
		this.qrCode = qrCode;
	}
	
	@Column(name="PIC_ONE")
	public String getPicOne() {
		return picOne;
	}
	public void setPicOne(String picOne) {
		this.picOne = picOne;
	}
	@Column(name="PIC_TWO")
	public String getPicTwo() {
		return picTwo;
	}
	public void setPicTwo(String picTwo) {
		this.picTwo = picTwo;
	}
	
	@Column(name="PLIST")
	public String getPlist() {
		return plist;
	}
	public void setPlist(String plist) {
		this.plist = plist;
	}

	@Column(name = "REMARK",length = 1024)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}