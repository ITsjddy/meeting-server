package com.smartdot.meeting.server.common.rs.provider;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.interceptor.security.AccessDeniedException;
import org.apache.log4j.Logger;

import com.smartdot.meeting.server.common.exception.SmartdotException;
import com.smartdot.meeting.server.common.rs.facade.CommonFacade;

@Provider
@Produces(value = { CommonFacade.APPLICATION_JSON_UTF_8, CommonFacade.APPLICATION_XML_UTF_8 })
public class SecurityExceptionProvider implements ExceptionMapper<AccessDeniedException> {
	
	private static final Logger _LOGGER = Logger.getLogger(SecurityExceptionProvider.class);
	@Context
	private HttpHeaders headers;

    public Response toResponse(AccessDeniedException e) {
    	_LOGGER.error(e.getMessage(), e.getCause());
    	
    	ResponseBuilder rb = Response.status(Response.Status.FORBIDDEN).entity(new SmartdotException(e));//.type(MediaType.APPLICATION_JSON);
		
		List<MediaType> accepts = headers.getAcceptableMediaTypes();
		if (accepts != null && accepts.size() > 0) {
			MediaType m = accepts.get(0);
			rb = rb.type(m);
		} else {
			rb = rb.type(headers.getMediaType());
		}

		return rb.build();
    }

}