package com.ip.founder.apiplatform.pojo.dto;

import lombok.Data;

public class AlarmDTO {
	/*{"equipment_code":"视频设备编号",
		"alarm_time":"报警时间",
		"alarm_type":"报警类型",
		"handle_result":"处理结果",
		"handle_file":"分析后生成文件路径",
		"data_resource":"数据来源",
		"prison":"所属监狱"}
		{"equipmentCode": "23000001561180241001"
		,"alarmTime": "1524728150"
			,"alarmType": "596"
				,"handleResult": "1"
					,"handleFile": ""
						,"dataResource": ""
							,"prison": ""}
		*/
	private String equipmentCode;
	private String alarmTime;
	private String alarmType;
	private String handleResult;
	private String handleFile;
	private String dataResource;
	private String prison;
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public String getHandleFile() {
		return handleFile;
	}
	public void setHandleFile(String handleFile) {
		this.handleFile = handleFile;
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
	
}
