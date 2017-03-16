package com.smartdot.meeting.server.common.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 操作结果返回值
 * 
 * @author zhaosen
 *
 */
@ApiModel(value = "返回值")
public class ReturnValue implements Serializable {
	
	private static final long serialVersionUID = -8001893618625869384L;
	
	/**
	 * 处理结果
	 */
	@ApiModelProperty(value = "返回结果")
	private boolean success = false;
	/**
	 * 信息
	 */
	@ApiModelProperty(value = "返回消息")
	private String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
