package com.smartdot.meeting.server.common.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * app用户主表
 * @author ms
 */
@Entity
@Table(name = "T_MEMBER")
public class Member {
	
	
	/**
	 * 主键
	 */
	protected String memberId;
	
	/**
	 * 创建时间
	 * */
	private Timestamp createTimes = new Timestamp(System.currentTimeMillis());
	
	/**
	 * 修改时间
	 * */
	private Long updateTimes = System.currentTimeMillis();
	
	/**
	 * 是否启用
	 */
	private Boolean enable = true;
	
	/**
	 * code码/邀请码/用户名 (数字+字母组成、app访问接口的唯一标识)
	 */
	private String invitationCode;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 用户名(特殊字符或汉字组成的)
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 盐值
	 */
	private String salt;
	
	/**
	 * 是否已经扫描二维码登录
	 */
	private String whetherScanning;

	/**
	 * app使用的语言标识 (此标识和数据字典语言类型对应)
	 */
	private String language;

	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Column(name="CREATE_TIMES")
	public Timestamp getCreateTimes() {
		return createTimes;
	}
	public void setCreateTimes(Timestamp createTimes) {
		this.createTimes = createTimes;
	}

	@Column(name = "ENABLE")
	public Boolean isEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	@Column(name="UPDATE_TIMES")
	public Long getUpdateTimes() {
		return updateTimes;
	}
	public void setUpdateTimes(Long updateTimes) {
		this.updateTimes = updateTimes;
	}
	
	@Column(name = "INVITATION_CODE")
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "SALT")
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Column(name = "WHETHER_SCANNING")
	public String getWhetherScanning() {
		return whetherScanning;
	}
	public void setWhetherScanning(String whetherScanning) {
		this.whetherScanning = whetherScanning;
	}
	
	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
