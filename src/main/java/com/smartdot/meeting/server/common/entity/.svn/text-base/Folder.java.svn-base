package com.smartdot.meeting.server.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 文件夹
 * @author suyc
 */

@Entity
@Table(name = "T_FOLDER")
public class Folder extends BaseEntity {
	
	private static final long serialVersionUID = -5075004040660204474L;
	/**
	 * 文件名称
	 * */
	private String fileName;
	
	/**
	 * 文件类型
	 * */
	private String fileType;
	/**
	 * 文件路径
	 * */
	private String filePath;
	
	/**
	 * 文件说明
	 * */
	private String explains;
	
	/**
	 * 文件修改时间
	 * */
	private Timestamp editTimes = new Timestamp(System.currentTimeMillis());
	
	

	@Column(name = "EXPLAINS")
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	
	@Column(name = "EDIT_TIMES")
	public Timestamp getEditTimes() {
		return editTimes;
	}
	public void setEditTimes(Timestamp editTimes) {
		this.editTimes = editTimes;
	}
	
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
