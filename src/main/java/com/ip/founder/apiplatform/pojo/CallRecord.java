package com.ip.founder.apiplatform.pojo;

public class CallRecord {
	private String uuid;
	private String currentFID;
	private Integer type;
	private String start_time;
	private String end_time;
	private String otherFID;
	private String user_id;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCurrentFID() {
		return currentFID;
	}
	public void setCurrentFID(String currentFID) {
		this.currentFID = currentFID;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getOtherFID() {
		return otherFID;
	}
	public void setOtherFID(String otherFID) {
		this.otherFID = otherFID;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
