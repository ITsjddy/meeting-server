package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 系统分组
 * @author ms
 *
 */
@Entity
@Table(name = "T_SYS_GROUP")
public class SysGroup extends BaseEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * 分组名称
	 * */
	private String groupName;
	
	/**
	 * 备注
	 * */
	private String remark;
	
	
	
	@Column(name = "GROUP_NAME")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	


}
