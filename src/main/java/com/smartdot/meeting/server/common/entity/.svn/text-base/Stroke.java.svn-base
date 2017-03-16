package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * app用户-嘉宾从表
 * 
 * @author ms
 */

@Entity
@Table(name = "T_STROKE")
public class Stroke extends BaseEntity {
	private static final long serialVersionUID = 4612208631714217145L;
	
	/**
	 * 嘉宾id
	 */
	private String memberId;
	
	/**
	 * 行程开始日期
	 */
	private String startStrokedate;
	
	/**
	 * 行程时间
	 */
	private String startStroketime;
	/**
	 * 行程结束日期
	 */
	private String stopStrokedate;
	
	/**
	 * 行程时间
	 */
	private String stopStroketime;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 地点
	 */
	private String location;
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 语言标识 (数据字典)
	 */
	private String language;
	
	
	
	/**
	 * @fieldName: importLogo
	 * @fieldType: String
	 * @Description: 数据导入时唯一标识
	 */
	private String importLogo;
	
	@Column(name = "importLogo")
	public String getImportLogo() {
		return importLogo;
	}
	public void setImportLogo(String importLogo) {
		this.importLogo = importLogo;
	}
	/**
	 * 语言唯一标识 (数据字典)
	 */
	private String uuid;
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name = "startStrokedate")
	public String getStartStrokedate() {
		return startStrokedate;
	}
	public void setStartStrokedate(String startStrokedate) {
		this.startStrokedate = startStrokedate;
	}
	@Column(name = "startStroketime")
	public String getStartStroketime() {
		return startStroketime;
	}
	public void setStartStroketime(String startStroketime) {
		this.startStroketime = startStroketime;
	}
	@Column(name = "stopStrokedate")
	public String getStopStrokedate() {
		return stopStrokedate;
	}
	public void setStopStrokedate(String stopStrokedate) {
		this.stopStrokedate = stopStrokedate;
	}
	@Column(name = "stopStroketime")
	public String getStopStroketime() {
		return stopStroketime;
	}
	public void setStopStroketime(String stopStroketime) {
		this.stopStroketime = stopStroketime;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "memberId")
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
