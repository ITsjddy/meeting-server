package com.smartdot.meeting.server.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


@MappedSuperclass
public class BaseEntity  implements Serializable {

	private static final long serialVersionUID = 4610540428006982620L;

	/**
	 * 主键
	 */
	protected String id;
	/**
	 * 创建时间
	 * */
	private Timestamp createTimes = new Timestamp(System.currentTimeMillis());
	/**
	 * 修改时间
	 * */
	private Long updateTimes = System.currentTimeMillis();
	
	/**
	 * 是否启用
	 */
	private Boolean enable = true;
	/**
	 * hibernate 的默认jpa实现会忽略initialValue设置，而使用默认值,此为bug<br>
	 * 为了保证向前兼容，设置hibernate.id.new_generator_mappings为true,启动新的hibernate实现。
	 * 
	 * @return
	 */

	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="CREATE_TIMES")
	public Timestamp getCreateTimes() {
		return createTimes;
	}

	@Column(name = "ENABLE")
	public Boolean isEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	public void setCreateTimes(Timestamp createTimes) {
		this.createTimes = createTimes;
	}
	@Column(name="UPDATE_TIMES")
	public Long getUpdateTimes() {
		return updateTimes;
	}

	public void setUpdateTimes(Long updateTimes) {
		this.updateTimes = updateTimes;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + ":" + String.valueOf(this.getId());
	}

}
