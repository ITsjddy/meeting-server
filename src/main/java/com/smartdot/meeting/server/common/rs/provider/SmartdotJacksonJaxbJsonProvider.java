package com.smartdot.meeting.server.common.rs.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class SmartdotJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {
	public SmartdotJacksonJaxbJsonProvider(ObjectMapper objectMapper){
		super(objectMapper, DEFAULT_ANNOTATIONS);
	}
}
