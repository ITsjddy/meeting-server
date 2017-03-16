package com.smartdot.meeting.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *导入模版
 * 
 * @author ms
 */
@Entity
@Table(name = "T_IMPORT_TEMPLATE")
public class ImportTemplate extends BaseEntity{
	private static final long serialVersionUID = -4200344253551901245L;

	
	/**
	 * 模版说明
	 */
	private String explain;

	/**
	 * 模版ids
	 */
	private String temids;
	
	/**
	 * 模版唯一标识
	 */
	private String uniqueIden;
	/**
	 * 表名
	 */
	private String tableName;
	
	@Column(name = "EXPLAINS")
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	@Column(name = "TEMPLATE_IDS",length=1000)
	public String getTemids() {
		return temids;
	}
	public void setTemids(String temids) {
		this.temids = temids;
	}
	
	@Column(name = "UNIQUE_IDEN",length=1000)
	public String getUniqueIden() {
		return uniqueIden;
	}
	public void setUniqueIden(String uniqueIden) {
		this.uniqueIden = uniqueIden;
	}
	
	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}