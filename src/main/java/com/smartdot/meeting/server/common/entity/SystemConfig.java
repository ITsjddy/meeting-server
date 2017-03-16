package com.smartdot.meeting.server.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统配置表
 * 
 * @author ms
 */

@Entity
@Table(name = "T_SYSTEM_CONFIG")
public class SystemConfig extends BaseEntity {
	private static final long serialVersionUID = 4612208631714217145L;
	
	
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 状态   1正常  -1锁定
	 */
	private Integer state = 1;
	/**
	 * 唯一标识
	 */
	private String uneIdent;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 说明
	 */
	private String explain;
	
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "UNIQUE_IDENTIFIER")
	public String getUneIdent() {
		return uneIdent;
	}
	public void setUneIdent(String uneIdent) {
		this.uneIdent = uneIdent;
	}
	
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "EXPLAINS")
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	@Column(name = "STATE")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
