package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 系统权限
 * 
 * @author ms
 */
@Entity
@Table(name = "T_SYS_ROLE")
public class SysRole extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	
	

	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMARK", length = 2048)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
