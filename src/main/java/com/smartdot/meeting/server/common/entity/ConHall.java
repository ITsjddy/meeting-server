package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	会场实体
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月12日 下午2:07:48
 * </pre>
 * </p>
 */

@Entity
@Table(name = "T_CON_HALL")
public class ConHall extends BaseEntity {

	
	private static final long serialVersionUID = 393571894742669196L;
	/**
	 * 会场名称
	 */
	private String name;
	/**
	 * 经度
	 * */
	private String deskNumber;
	/**
	 * 纬度
	 * */
	private String theatreNumber;
	/**
	 * 面积
	 * */
	private String area;
	/**
	 * 会场类型 数据字典
	 * */
	private String type;
	/**
	 * 是否是室内导航，1是，0不是
	 */
	private String isIndoor;
	/**
	 * 室内导航唯一标示
	 */
	private String indoorUnique;
	/**
	 * 这条记录的语言标示
	 */
	private String language;
	/**
	 * 这条记录的唯一标示
	 */
	private String uniqueCode;
	
	/**
	 * 会场联系人
	 */
	private String venueContact;
	
	/**
	 * 会场联系人电话
	 */
	private String venueMobile;
	
	@Column(name = "is_indoor")
	public String getIsIndoor() {
		return isIndoor;
	}
	public void setIsIndoor(String isIndoor) {
		this.isIndoor = isIndoor;
	}
	@Column(name = "indoor_unique")
	public String getIndoorUnique() {
		return indoorUnique;
	}
	public void setIndoorUnique(String indoorUnique) {
		this.indoorUnique = indoorUnique;
	}
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "DESK_NUMBER")
	public String getDeskNumber() {
		return deskNumber;
	}
	public void setDeskNumber(String deskNumber) {
		this.deskNumber = deskNumber;
	}
	
	@Column(name = "THEATRE_NUMBER")
	public String getTheatreNumber() {
		return theatreNumber;
	}
	public void setTheatreNumber(String theatreNumber) {
		this.theatreNumber = theatreNumber;
	}
	
	@Column(name = "AREA")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Column(name = "UNIQUE_CODE")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	@Column(name = "VENUE_CONTACT")
	public String getVenueContact() {
		return venueContact;
	}
	public void setVenueContact(String venueContact) {
		this.venueContact = venueContact;
	}
	@Column(name = "VENUE_MOBILE")
	public String getVenueMobile() {
		return venueMobile;
	}
	public void setVenueMobile(String venueMobile) {
		this.venueMobile = venueMobile;
	}
}
