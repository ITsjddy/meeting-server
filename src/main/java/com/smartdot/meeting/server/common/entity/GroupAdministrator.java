package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 团管理员帐号中间表
 * 
 * @author ms
 */

@Entity
@Table(name = "T_GROUP_ADMINISTRATOR")
public class GroupAdministrator extends BaseEntity {
	
	private static final long serialVersionUID = 4612208631714217145L;
	
	
	/**
	 * 团id
	 */
	private String groupId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 团管理员帐号外键
	 */
	private Member member;
	
	
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
	@ManyToOne
	@JoinColumn(name = "MEMBER")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
}
