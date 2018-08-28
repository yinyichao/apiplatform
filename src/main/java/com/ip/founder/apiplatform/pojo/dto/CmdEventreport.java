/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ip.founder.apiplatform.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ip.founder.apiplatform.dao.DataEntity;
import com.ip.founder.apiplatform.util.CustomJsonDateDeserializer;
import com.ip.founder.apiplatform.util.JsonUtil;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 事件上报Entity
 * @author zwh
 * @version 2017-09-27
 */
public class CmdEventreport extends DataEntity<CmdEventreport> {
	
	private static final long serialVersionUID = 1L;
	private Date alarmTime;		// 报警时间
	private String alarmPerson;		// 报警人
	private String alarmLocation;		// 报警地点
	private String statement;		//说明 
	private String relatedPrison;		//相关人员
	private String relatedArea;		// 相关监区
	private String eventType;		// 事件类型
	private String remark;		// 报警来源，例如仓讲报警、视频报警、视频事件登记等
	private String remarks;
	private String remark2;		// 案件等级 取字典表
	private String remark3;		// 处置流程
	private String remark4;		// 区分警务督察、安全生产和狱情
	private String remark5;		// 存储事件类型编号，用于上报省局
	private String iswrong;		// 是否误报
	private String status;		// 状态
	private String equipmentCode;		// 报警设备编码
	private String equipmentName;		// 报警设备名称
	private Date latestTime;		// 最后推送时间
	private String ispush;		// 是否推送成功
	private String jyid;		// 所属监狱
	private String handlePrison;  //处理人
	private String handleTime;    //处理时间
	private String handleResult;  //处理结果
	private String isjyj;         //是否为监狱局下发事件 0为监狱，1为监狱局下发
	private String ishandle;         //是否处理
	private String handleRule;  //考核细则（取字典表lable  handle_rule）
	private String handleScore;  //考核分数（取字典表value  handle_rule）
	private String managerHandle;  //指挥长审批（取字典表   manager_handle）
	private String managerRemark;  //指挥长审批意见
	private Date managerTime;  //指挥长审批时间
	private String managerId;  //指挥长用户id
	private Date alarmTime2;		// 
	private String filename;     //临时：文件名
	private String parentIds;    //临时：事件类型的父级id
	private String roleId;    //临时：报警人的角色ID\
	private String type;   //给一张图二维传参用
	private String isyuqing;
	private Date createDate;
	private Date updateDate;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsyuqing() {
		return isyuqing;
	}

	public void setIsyuqing(String isyuqing) {
		this.isyuqing = isyuqing;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String  prisonName;
	public String getPrisonName() {
		return prisonName;
	}

	public void setPrisonName(String prisonName) {
		this.prisonName = prisonName;
	}

	public CmdEventreport() {
		super();
	}

	public CmdEventreport(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAlarmTime() {
		return alarmTime;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAlarmTime2() {
		return alarmTime2;
	}

	public void setAlarmTime2(Date alarmTime2) {
		this.alarmTime2 = alarmTime2;
	}

	@Length(min=0, max=32, message="alarm_person长度必须介于 0 和 32 之间")
	public String getAlarmPerson() {
		return alarmPerson;
	}

	public void setAlarmPerson(String alarmPerson) {
		this.alarmPerson = alarmPerson;
	}
	
	@Length(min=0, max=200, message="alarm_location长度必须介于 0 和 200 之间")
	public String getAlarmLocation() {
		return alarmLocation;
	}

	public void setAlarmLocation(String alarmLocation) {
		this.alarmLocation = alarmLocation;
	}
	
	@Length(min=0, max=500, message="statement长度必须介于 0 和 500 之间")
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	@Length(min=0, max=32, message="related_prison长度必须介于 0 和 32 之间")
	public String getRelatedPrison() {
		return relatedPrison;
	}

	public void setRelatedPrison(String relatedPrison) {
		this.relatedPrison = relatedPrison;
	}
	
	@Length(min=0, max=200, message="related_area长度必须介于 0 和 200 之间")
	public String getRelatedArea() {
		return relatedArea;
	}

	public void setRelatedArea(String relatedArea) {
		this.relatedArea = relatedArea;
	}
	
	@Length(min=0, max=32, message="event_type长度必须介于 0 和 32 之间")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Length(min=0, max=200, message="remark长度必须介于 0 和 200 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=200, message="remark2长度必须介于 0 和 200 之间")
	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	@Length(min=0, max=200, message="remark3长度必须介于 0 和 200 之间")
	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	
	@Length(min=0, max=200, message="remark4长度必须介于 0 和 200 之间")
	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	
	@Length(min=0, max=200, message="remark5长度必须介于 0 和 200 之间")
	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}
	
	@Length(min=0, max=10, message="iswrong长度必须介于 0 和 10 之间")
	public String getIswrong() {
		return iswrong;
	}

	public void setIswrong(String iswrong) {
		this.iswrong = iswrong;
	}
	
	@Length(min=0, max=10, message="status长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=32, message="equipment_code长度必须介于 0 和 32 之间")
	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	
	@Length(min=0, max=100, message="equipment_name长度必须介于 0 和 100 之间")
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}
	
	@Length(min=0, max=10, message="ispush长度必须介于 0 和 10 之间")
	public String getIspush() {
		return ispush;
	}

	public void setIspush(String ispush) {
		this.ispush = ispush;
	}
	
	@Length(min=0, max=32, message="jyid长度必须介于 0 和 32 之间")
	public String getJyid() {
		return jyid;
	}

	public void setJyid(String jyid) {
		this.jyid = jyid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getHandlePrison() {
		return handlePrison;
	}

	public void setHandlePrison(String handlePrison) {
		this.handlePrison = handlePrison;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getIsjyj() {
		return isjyj;
	}

	public void setIsjyj(String isjyj) {
		this.isjyj = isjyj;
	}
	
	public String getIshandle() {
		return ishandle;
	}

	public void setIshandle(String ishandle) {
		this.ishandle = ishandle;
	}


	public String getHandleRule() {
		return handleRule;
	}

	public void setHandleRule(String handleRule) {
		this.handleRule = handleRule;
	}

	public String getHandleScore() {
		return handleScore;
	}

	public void setHandleScore(String handleScore) {
		this.handleScore = handleScore;
	}

	public String getManagerHandle() {
		return managerHandle;
	}

	public void setManagerHandle(String managerHandle) {
		this.managerHandle = managerHandle;
	}

	public String getManagerRemark() {
		return managerRemark;
	}

	public void setManagerRemark(String managerRemark) {
		this.managerRemark = managerRemark;
	}

	public Date getManagerTime() {
		return managerTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setManagerTime(Date managerTime) {
		this.managerTime = managerTime;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public static void main(String[] args) {
		String s = "[{\n" +
				"\t\"id\": \"a573c7ec781546d19470d7cfb9612d9e\",\n" +
				"\t\"isNewRecord\": false,\n" +
				"\t\"remarks\": null,\n" +
				"\t\"createDate\": null,\n" +
				"\t\"updateDate\": null,\n" +
				"\t\"alarmTime\": \"2018-01-04 05:48:11\",\n" +
				"\t\"alarmPerson\": \"68da0745785b4dffa9c150a7c89a9229\",\n" +
				"\t\"alarmLocation\": \"一号习艺楼四层3大队4号头\",\n" +
				"\t\"statement\": \"三大队4号摄像机偏移，,三大队4号摄像机偏移,三大队4号摄像机偏移，,三大队4号摄像机偏移\",\n" +
				"\t\"relatedPrison\": null,\n" +
				"\t\"relatedArea\": null,\n" +
				"\t\"eventType\": \"1\",\n" +
				"\t\"remark\": null,\n" +
				"\t\"remark2\": null,\n" +
				"\t\"remark3\": null,\n" +
				"\t\"remark4\": \"jingwu\",\n" +
				"\t\"remark5\": null,\n" +
				"\t\"iswrong\": \"0\",\n" +
				"\t\"status\": \"1\",\n" +
				"\t\"equipmentCode\": \"0403010406010102059 \",\n" +
				"\t\"equipmentName\": \"一号习艺楼四层3大队4号头\",\n" +
				"\t\"latestTime\": null,\n" +
				"\t\"ispush\": null,\n" +
				"\t\"jyid\": \"04\",\n" +
				"\t\"handlePrison\": \"ab8f92c20ffb4c8b8bf065dea66ddc7d\",\n" +
				"\t\"handleTime\": \"2018-01-26 15:35:12\",\n" +
				"\t\"handleResult\": \"分手的个人666666\",\n" +
				"\t\"isjyj\": \"0\",\n" +
				"\t\"ishandle\": \"1\",\n" +
				"\t\"handleRule\": null,\n" +
				"\t\"handleScore\": null,\n" +
				"\t\"managerHandle\": null,\n" +
				"\t\"managerRemark\": null,\n" +
				"\t\"managerTime\": null,\n" +
				"\t\"managerId\": null,\n" +
				"\t\"alarmTime2\": null,\n" +
				"\t\"filename\": null,\n" +
				"\t\"parentIds\": null,\n" +
				"\t\"roleId\": null,\n" +
				"\t\"type\": null,\n" +
				"\t\"isyuqing\": null,\n" +
				"\t\"prisonName\": null\n" +
				"}, {\n" +
				"\t\"id\": \"a573c7ec781546d19470d7cfb9612d9e\",\n" +
				"\t\"isNewRecord\": false,\n" +
				"\t\"remarks\": null,\n" +
				"\t\"createDate\": null,\n" +
				"\t\"updateDate\": null,\n" +
				"\t\"alarmTime\": \"2018-01-03 21:48:11\",\n" +
				"\t\"alarmPerson\": \"68da0745785b4dffa9c150a7c89a9229\",\n" +
				"\t\"alarmLocation\": \"一号习艺楼四层3大队4号头\",\n" +
				"\t\"statement\": \"三大队4号摄像机偏移，,三大队4号摄像机偏移,三大队4号摄像机偏移，,三大队4号摄像机偏移\",\n" +
				"\t\"relatedPrison\": null,\n" +
				"\t\"relatedArea\": null,\n" +
				"\t\"eventType\": \"1\",\n" +
				"\t\"remark\": null,\n" +
				"\t\"remark2\": null,\n" +
				"\t\"remark3\": null,\n" +
				"\t\"remark4\": \"jingwu\",\n" +
				"\t\"remark5\": null,\n" +
				"\t\"iswrong\": \"0\",\n" +
				"\t\"status\": \"1\",\n" +
				"\t\"equipmentCode\": \"0403010406010102059 \",\n" +
				"\t\"equipmentName\": \"一号习艺楼四层3大队4号头\",\n" +
				"\t\"latestTime\": null,\n" +
				"\t\"ispush\": null,\n" +
				"\t\"jyid\": \"04\",\n" +
				"\t\"handlePrison\": \"ab8f92c20ffb4c8b8bf065dea66ddc7d\",\n" +
				"\t\"handleTime\": \"2018-01-26 15:35:12\",\n" +
				"\t\"handleResult\": \"分手的个人666666\",\n" +
				"\t\"isjyj\": \"0\",\n" +
				"\t\"ishandle\": \"1\",\n" +
				"\t\"handleRule\": null,\n" +
				"\t\"handleScore\": null,\n" +
				"\t\"managerHandle\": null,\n" +
				"\t\"managerRemark\": null,\n" +
				"\t\"managerTime\": null,\n" +
				"\t\"managerId\": null,\n" +
				"\t\"alarmTime2\": null,\n" +
				"\t\"filename\": null,\n" +
				"\t\"parentIds\": null,\n" +
				"\t\"roleId\": null,\n" +
				"\t\"type\": null,\n" +
				"\t\"isyuqing\": null,\n" +
				"\t\"prisonName\": null\n" +
				"}]";
		List<CmdEventreport> a = JsonUtil.jsonArrToList(s,CmdEventreport.class);
String ss = "[{\n" +
		"\t\"id\": null,\n" +
		"\t\"isNewRecord\": true,\n" +
		"\t\"remarks\": null,\n" +
		"\t\"createDate\": null,\n" +
		"\t\"updateDate\": null,\n" +
		"\t\"workId\": \"d332023fe72340f0b59e49827fbf343e\",\n" +
		"\t\"workerId\": \"230622197908288311\",\n" +
		"\t\"startDate\": \"2018-07-08 16:00:00\",\n" +
		"\t\"endDate\": null,\n" +
		"\t\"allday\": null,\n" +
		"\t\"postId\": \"90596eee96784a70b51f6055ee607d43\",\n" +
		"\t\"orgId\": null,\n" +
		"\t\"duration\": null,\n" +
		"\t\"groupId\": null,\n" +
		"\t\"workerName\": null,\n" +
		"\t\"rankName\": null,\n" +
		"\t\"postName\": null,\n" +
		"\t\"orgName\": null,\n" +
		"\t\"telphone\": null,\n" +
		"\t\"periodStart\": null,\n" +
		"\t\"periodEnd\": null,\n" +
		"\t\"workHours\": null,\n" +
		"\t\"workType\": null\n" +
		"}, {\n" +
		"\t\"id\": null,\n" +
		"\t\"isNewRecord\": true,\n" +
		"\t\"remarks\": null,\n" +
		"\t\"createDate\": null,\n" +
		"\t\"updateDate\": null,\n" +
		"\t\"workId\": \"d332023fe72340f0b59e49827fbf343e\",\n" +
		"\t\"workerId\": \"23010319810822131X\",\n" +
		"\t\"startDate\": \"2018-07-09 16:00:00\",\n" +
		"\t\"endDate\": null,\n" +
		"\t\"allday\": null,\n" +
		"\t\"postId\": \"90596eee96784a70b51f6055ee607d43\",\n" +
		"\t\"orgId\": null,\n" +
		"\t\"duration\": null,\n" +
		"\t\"groupId\": null,\n" +
		"\t\"workerName\": null,\n" +
		"\t\"rankName\": null,\n" +
		"\t\"postName\": null,\n" +
		"\t\"orgName\": null,\n" +
		"\t\"telphone\": null,\n" +
		"\t\"periodStart\": null,\n" +
		"\t\"periodEnd\": null,\n" +
		"\t\"workHours\": null,\n" +
		"\t\"workType\": null\n" +
		"}, {\n" +
		"\t\"id\": null,\n" +
		"\t\"isNewRecord\": true,\n" +
		"\t\"remarks\": null,\n" +
		"\t\"createDate\": null,\n" +
		"\t\"updateDate\": null,\n" +
		"\t\"workId\": \"d332023fe72340f0b59e49827fbf343e\",\n" +
		"\t\"workerId\": \"370283198601086850\",\n" +
		"\t\"startDate\": \"2018-07-10 16:00:00\",\n" +
		"\t\"endDate\": null,\n" +
		"\t\"allday\": null,\n" +
		"\t\"postId\": \"90596eee96784a70b51f6055ee607d43\",\n" +
		"\t\"orgId\": null,\n" +
		"\t\"duration\": null,\n" +
		"\t\"groupId\": null,\n" +
		"\t\"workerName\": null,\n" +
		"\t\"rankName\": null,\n" +
		"\t\"postName\": null,\n" +
		"\t\"orgName\": null,\n" +
		"\t\"telphone\": null,\n" +
		"\t\"periodStart\": null,\n" +
		"\t\"periodEnd\": null,\n" +
		"\t\"workHours\": null,\n" +
		"\t\"workType\": null\n" +
		"}]\n";
	}
}