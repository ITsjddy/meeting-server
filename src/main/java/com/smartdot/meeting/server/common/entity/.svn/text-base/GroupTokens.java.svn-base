package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	融云群组
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月12日 下午2:07:48
 * </pre>
 * </p>
 */

@Entity
@Table(name = "T_GROUP_TOKENS")
public class GroupTokens extends BaseEntity {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 被同步群信息的用户 Id。（必传）
	 * */
	private String groupId;
	/**
	 * 该用户的群信息，如群 Id 已经存在，则不会刷新对应群组名称，如果想刷新群组名称请调用刷新群组信息方法。
	 * */
	private String name;
	/**
	 * 嘉宾唯一标识 预留创建群组
	 */
	private String invitationCode;
	
	@Column(name = "GROUP_ID")
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "INVITATION_CODE")
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	

}
