package com.ip.founder.apiplatform.pojo;

import java.util.Date;

public class YjCallLog {
	private String id;
	private String currentfid;
	private String type;
	private Date start_time;
    private Date end_time;
    private String otherfid;
    private String user_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrentfid() {
		return currentfid;
	}
	public void setCurrentfid(String currentfid) {
		this.currentfid = currentfid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getOtherfid() {
		return otherfid;
	}
	public void setOtherfid(String otherfid) {
		this.otherfid = otherfid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    
}
