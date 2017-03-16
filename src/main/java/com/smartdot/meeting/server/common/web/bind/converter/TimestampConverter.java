package com.smartdot.meeting.server.common.web.bind.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class TimestampConverter implements Converter<String, Timestamp> {
	
	private static final Logger _LOG = Logger.getLogger(Timestamp.class); 

	@Override
	public Timestamp convert(String source) {
		
		if (StringUtils.isBlank(source)) {
			return null;
		}
		
		try {
			return Timestamp.valueOf(source);
		} catch (Exception e) {
			_LOG.warn(e);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				return new Timestamp(dateFormat.parse(source).getTime());
			} catch (ParseException e1) {
				_LOG.warn(e1);
			}
		}
		
		return null;
	}

}
