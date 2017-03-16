package com.smartdot.meeting.server.common.jstree;

import java.util.ArrayList;
import java.util.List;


public class JsTree {

	private List<String> plugins = new ArrayList<String>();
	
	private Core core;

	public JsTree(){
		plugins.add("wholerow");
		plugins.add("checkbox");
	}
	
	public List<String> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<String> plugins) {
		this.plugins = plugins;
	}

	public Core getCore() {
		return core;
	}

	public void setCore(Core core) {
		this.core = core;
	}
}
