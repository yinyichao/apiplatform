package com.ip.founder.apiplatform.pojo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ip.founder.apiplatform.util.CustomJsonDateDeserializer;

public class VideoTerminal {
	private String deviceIP;//设备IP
	private String deviceIndexCode;//设备编码
	private Integer operateType;//操作类型 1.add,2.update,3.del
	private Integer devicePort; //设备端口号
	private Date updateTime; //操作时间
	private String deviceName; //设备名称
	private String operator; //用户
	public String getDeviceIP() {
		return deviceIP;
	}
	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}
	public String getDeviceIndexCode() {
		return deviceIndexCode;
	}
	public void setDeviceIndexCode(String deviceIndexCode) {
		this.deviceIndexCode = deviceIndexCode;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	public Integer getDevicePort() {
		return devicePort;
	}
	public void setDevicePort(Integer devicePort) {
		this.devicePort = devicePort;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
