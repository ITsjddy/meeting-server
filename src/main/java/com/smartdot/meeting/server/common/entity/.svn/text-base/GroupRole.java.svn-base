package com.smartdot.meeting.server.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 分组权限中间表
 * @author ms
 */
@Entity
@Table(name = "T_GROUP_ROLE")
public class GroupRole extends BaseEntity implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 分组id
	 */
    private String groupId;
	/**
	 * 权限id
	 */
    private String roleId;

    
    public GroupRole(){
    	
    }

    
    @Column(name = "GROUP_ID")
    public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}