package com.smartdot.meeting.server.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	具体内容
 * <b>作者：</b>
 * 	sunjd(孙俊东)
 * 	邮箱：<a href="mailto:sunjd@smartdot.com.cn" >sunjd@smartdot.com.cn</a>	
 * <b>创建时间：</b> 
 * 	2017年1月5日 下午2:28:23
 * </pre>
 * </p>
 */
@Entity
@Table(name = "T_TEST_DEMO")
public class TestDemo extends BaseEntity {

	private static final long serialVersionUID = -4200344253551901245L;
	
	private String name;
	private String sex;
	private String unCode;
	
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "un_code")
	public String getUnCode() {
		return unCode;
	}
	public void setUnCode(String unCode) {
		this.unCode = unCode;
	}
}
