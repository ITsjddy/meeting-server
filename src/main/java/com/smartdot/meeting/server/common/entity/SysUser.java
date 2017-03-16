package com.smartdot.meeting.server.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 系统用户
 * 
 * @author ms
 */
@Entity
@Table(name = "T_SYS_USER")
public class SysUser extends BaseEntity {
	
	private static final long serialVersionUID = -4200344253551901245L;
	
	/**
	 * 登录名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 是否启用(1为启用、0为禁用)
	 */
	private Integer state = 1;
	/**
	 * 权限id
	 */
	private String roleId;
	/**
	 * 备注
	 */
	private String remark;
	

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", length = 50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "STATE")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
		
	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
