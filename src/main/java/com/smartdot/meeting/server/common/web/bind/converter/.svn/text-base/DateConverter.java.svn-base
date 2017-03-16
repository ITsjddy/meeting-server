package com.smartdot.meeting.server.common.web.bind.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	private static final Logger _LOG = Logger.getLogger(DateConverter.class);
	
	@Override
	public Date convert(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			_LOG.warn(e);
		}
		return null;
	}

}
