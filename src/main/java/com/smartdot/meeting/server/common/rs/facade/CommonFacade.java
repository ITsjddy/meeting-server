package com.smartdot.meeting.server.common.rs.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@Consumes(value = { CommonFacade.APPLICATION_JSON_UTF_8, CommonFacade.APPLICATION_XML_UTF_8 })
@Produces(value = { CommonFacade.APPLICATION_JSON_UTF_8, CommonFacade.APPLICATION_XML_UTF_8 })
public interface CommonFacade {
	
	
	public final static String APPLICATION_JSON_UTF_8 = "application/json; charset=UTF-8";
	
	public final static String APPLICATION_XML_UTF_8 = "application/xml; charset=UTF-8";
}
