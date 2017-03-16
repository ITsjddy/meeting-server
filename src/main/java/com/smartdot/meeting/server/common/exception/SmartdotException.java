package com.smartdot.meeting.server.common.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@XmlRootElement(name = "Exception")
public class SmartdotException {
	private Throwable ex;
	private String message;
	private Throwable cause;

	public SmartdotException() {
	}
	
	public SmartdotException(String message) {
		this.message = message;
	}


	public SmartdotException(Throwable ex) {
		this.ex = ex;
		this.message = ex.getMessage();
		this.cause = ex.getCause();
		if (this.ex instanceof javax.ws.rs.NotAuthorizedException) {
			this.message = "WS-HTTP Status 401 - UNAUTHORIZED.";
		} else if (this.ex instanceof javax.ws.rs.InternalServerErrorException) {
			this.message = "WS-HTTP Status 500 - Internal Server Error.";
		} else if (null == this.cause && null == this.message) {
			this.message = ex.getClass().getName();
		}
	}
	
	@JsonIgnore
	@XmlTransient
	//@XmlElement(name = "cause")
	public String getCause() {
		return null != this.cause ? this.cause.toString() : null;
	}

	@XmlTransient
	@JsonIgnore
	public String getLocalizedMessage() {
		return ex.getLocalizedMessage();
	}

	@XmlElement(name = "message")
	public String getMessage() {
		return this.message;
	}

	//@XmlElement(name = "stackTrace")
	@XmlTransient
	@JsonIgnore
	public String getStackTrace() {

		
		/*StackTraceElement[] eles = ex.getStackTrace();
		StringBuffer sb = new StringBuffer();
		for (StackTraceElement ele : eles) {
			sb.append("\t" + ele + "\n");
		}*/
		

		return "";
	}

}
