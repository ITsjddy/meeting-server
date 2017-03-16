package com.smartdot.meeting.server.modules.newsColumn.util;

import com.smartdot.meeting.server.common.entity.NewsColumn;

public class NewsColumnView extends NewsColumn{
	/**
	 * <p>
	 * <pre>
	 * <b>属性描述：</b>
	 * 	描述内容
	 * </pre>
	 * </p>
	 */
	private static final long serialVersionUID = -4439055344107263480L;
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
