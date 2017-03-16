package com.smartdot.meeting.server.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 权限菜单中间表
 * @author ms
 */
@Entity
@Table(name = "T_ROLE_PRIVILEGE")
public class RolePrivilege extends BaseEntity {
    
	private static final long serialVersionUID = 1L;
	
	/**
	 * 权限id
	 */
    private String roleId;
    /**
	 * 菜单id
	 */
    private String priId;

    
    public RolePrivilege(){
    	
    }

    @Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "PRI_ID")
	public String getPriId() {
		return priId;
	}
	public void setPriId(String priId) {
		this.priId = priId;
	}
    


}