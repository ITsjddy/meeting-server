package com.smartdot.meeting.server.modules.folder.model;

import java.sql.Timestamp;

public class FolderSearch {
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
	
	/**
	 * 文件修改人
	 * */
//	private String editUser;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public Timestamp getEditTimes() {
		return editTimes;
	}

	public void setEditTimes(Timestamp editTimes) {
		this.editTimes = editTimes;
	}

}
