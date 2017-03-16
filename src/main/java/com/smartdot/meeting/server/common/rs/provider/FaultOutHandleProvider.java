package com.smartdot.meeting.server.common.rs.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.smartdot.meeting.server.common.rs.facade.CommonFacade;


@Provider
@Produces(value = { CommonFacade.APPLICATION_JSON_UTF_8, CommonFacade.APPLICATION_XML_UTF_8 })
public class FaultOutHandleProvider implements ExceptionMapper<Exception> {
	
	private static final Logger _LOGGER = Logger.getLogger(FaultOutHandleProvider.class);

	/*	
	@Context
	private HttpHeaders headers;
	*/
	
	@Override
	public Response toResponse(Exception e) {
		
		_LOGGER.error("Exception Occured in Server", e);
		
		String message = "Exception Occured in Server: " + e;
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).type("text/plain").build();
		
		/*//_LOGGER.error(e.getMessage(), e.getCause());

		ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage());//.type(MediaType.APPLICATION_JSON);
		
		List<MediaType> accepts = headers.getAcceptableMediaTypes();
		if (accepts != null && accepts.size() > 0) {
			MediaType m = accepts.get(0);
			rb.type(m);
		} else {
			rb = rb.type(headers.getMediaType());
		}

		return rb.build();*/
		
	}

}
