package com.ip.founder.apiplatform.pojo;

import java.util.Date;

public class YjPolice {
	private String id;
	private String equipmentCode;
	private String equipmentType;
	private Date alarmTime;
	private String alarmType;
	private String alarmReason;
	private String dataResource;
	private String prison;
	private String handlefile;
	private String eventid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmReason() {
		return alarmReason;
	}

	public void setAlarmReason(String alarmReason) {
		this.alarmReason = alarmReason;
	}

	public String getDataResource() {
		return dataResource;
	}

	public void setDataResource(String dataResource) {
		this.dataResource = dataResource;
	}

	public String getPrison() {
		return prison;
	}
	public void setPrison(String prison) {
		this.prison = prison;
	}

	public String getHandlefile() {
		return handlefile;
	}

	public void setHandlefile(String handlefile) {
		this.handlefile = handlefile;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
}
