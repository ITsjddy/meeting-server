package com.smartdot.meeting.server.common.web.handler.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartdot.meeting.server.common.exception.SmartdotException;

public class SmartdotMappingExceptionResolver extends AbstractHandlerExceptionResolver {

	private static final Logger _LOG = Logger.getLogger(SmartdotMappingExceptionResolver.class);
	
	private ObjectMapper objectMapper;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
		String accept = request.getHeader("Accept");
		String encoding = request.getCharacterEncoding();
		
		_LOG.error("Exception Occured in Server", ex);
		
		Object error = null;
		
		ModelAndView mav = null;
		
		try {
			int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
			if (accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
				response.setContentType(accept);
				response.setCharacterEncoding(encoding);
				
				if (ex instanceof org.springframework.validation.BindException) {
					error = ((org.springframework.validation.BindException) ex).getAllErrors();
					status = HttpServletResponse.SC_BAD_REQUEST;
				} else if (ex instanceof org.springframework.beans.ConversionNotSupportedException) {
					Class<?> reqiredType = ((org.springframework.beans.ConversionNotSupportedException) ex).getRequiredType();
					if (reqiredType == org.springframework.web.multipart.MultipartFile[].class || reqiredType == org.springframework.web.multipart.MultipartFile.class) {
						error = new SmartdotException("未选择上传文件");
						status = HttpServletResponse.SC_BAD_REQUEST;
					}else {
						error = new SmartdotException(ex);
					}
				} else {
					error = new SmartdotException(ex);
				}
				
				response.setStatus(status);
				writeResponse(response, error);
				
				mav = new ModelAndView();
			} else  {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (IOException e) {
			_LOG.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", e);
		}
		return mav;
	}
	
	private void writeResponse(HttpServletResponse resp, Object data) throws IOException {
		PrintWriter out = resp.getWriter();
		String content = objectMapper.writeValueAsString(data);
		out.print(content);
		out.flush();
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
