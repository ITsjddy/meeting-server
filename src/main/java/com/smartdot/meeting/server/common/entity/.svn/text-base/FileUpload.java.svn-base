package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "T_FILE_UPLOAD")
public class FileUpload extends BaseEntity {

	private static final long serialVersionUID = 6565636158524612293L;
	/**
	 * 类别	1代表图片,2代表音频,3代表视频,0代表文件(后面可扩展)
	 */
	private String type;
	/**
	 * 说明
	 */
	private String fileExplain;
	/**
	 * 大文件url
	 */
	private String bigFileurl;
	/**
	 * 小文件url
	 */
	private String smallFileurl;
	
	/**
	 * 大文件名
	 */
	private String bigFilename;
	/**
	 * 小文件名
	 */
	private String smallFilename;
	/**
	 * 主id
	 */
	private String groupid;
	
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "FILE_EXPLAIN")
	public String getFileExplain() {
		return fileExplain;
	}
	public void setFileExplain(String fileExplain) {
		this.fileExplain = fileExplain;
	}
	@Column(name = "BIG_FILE_URL")
	public String getBigFileurl() {
		return bigFileurl;
	}
	public void setBigFileurl(String bigFileurl) {
		this.bigFileurl = bigFileurl;
	}
	@Column(name = "SMALL_FILE_URL")
	public String getSmallFileurl() {
		return smallFileurl;
	}
	public void setSmallFileurl(String smallFileurl) {
		this.smallFileurl = smallFileurl;
	}
	@Column(name = "BIG_FILE_NAME")
	public String getBigFilename() {
		return bigFilename;
	}
	public void setBigFilename(String bigFilename) {
		this.bigFilename = bigFilename;
	}
	@Column(name = "SMALL_FILE_NAME")
	public String getSmallFilename() {
		return smallFilename;
	}
	public void setSmallFilename(String smallFilename) {
		this.smallFilename = smallFilename;
	}
	@Column(name = "GROUPID")
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

}
