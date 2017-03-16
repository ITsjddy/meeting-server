package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ms 系统菜单
 */
@Entity
@Table(name = "T_SYS_PRIVILEGE")
public class SysPrivilege extends BaseEntity {
	
	private static final long serialVersionUID = -1613765108920907423L;
	
	/**
	 * 名称
	 */
    private String name;
    /**
	 * url路径
	 */
    private String url;
    /**
	 * 几级菜单(1为一级、2为二级、3为三级)
	 */
    private String type;
    /**
	 * 上级菜单
	 */
	private SysPrivilege parent;
    /**
	 * 排序
	 */
    private String level;
    /**
	 * 说明
	 */
    private String remark;
    /**
   	 * 是否展示 (1 是 、 0 否)
   	 */
    private String display = "1";
    /**
   	 * 权限
   	 */
    private String resKey;
	
	
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name = "PID")
	public SysPrivilege getParent() {
		return parent;
	}
	public void setParent(SysPrivilege parent) {
		this.parent = parent;
	}

	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="LEVEL")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name="DISPLAY")
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	
	@Column(name="ROLE_KEY")
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	
}
