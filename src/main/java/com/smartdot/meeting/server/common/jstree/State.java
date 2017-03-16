package com.smartdot.meeting.server.common.jstree;

public class State {

	private boolean selected;
	
	private boolean opened;
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	
}
