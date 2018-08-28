package com.ip.founder.apiplatform.pojo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ip.founder.apiplatform.pojo.YjCallLog;
import com.ip.founder.apiplatform.util.CustomJsonDateDeserializer;

import java.util.Date;

public class YjCallLogDTO {
	private String uuid;
	private String currentfid;
	private Integer type;
	private Date start_time;
    private Date end_time;
    private String otherfid;
    private String user_id;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getStart_time() {
		return start_time;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCurrentfid() {
		return currentfid;
	}

	public void setCurrentfid(String currentfid) {
		this.currentfid = currentfid;
	}

	public String getOtherfid() {
		return otherfid;
	}

	public void setOtherfid(String otherfid) {
		this.otherfid = otherfid;
	}

	public static YjCallLog change(YjCallLogDTO dto){
		YjCallLog call = new YjCallLog();
		call.setCurrentfid(dto.getCurrentfid());
		call.setEnd_time(dto.getEnd_time());
		call.setOtherfid(dto.getOtherfid());
		call.setStart_time(dto.getStart_time());
		call.setType(String.valueOf(dto.getType()));
		call.setUser_id(dto.getUser_id());
		return call;
	}
	
}
