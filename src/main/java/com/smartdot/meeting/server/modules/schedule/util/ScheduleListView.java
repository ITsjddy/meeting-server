package com.smartdot.meeting.server.modules.schedule.util;

public class ScheduleListView {
//	<td ng-bind="schedule.name"></td>
//	<td ng-bind="schedule.conHallName"></td>
//	<td ng-bind="schedule.dateDir"></td>
	private String id;
	private String name;
	private String conHallName;
	private String dateDir;
	private String uniqueCode;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConHallName() {
		return conHallName;
	}
	public void setConHallName(String conHallName) {
		this.conHallName = conHallName;
	}
	public String getDateDir() {
		return dateDir;
	}
	public void setDateDir(String dateDir) {
		this.dateDir = dateDir;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
}
