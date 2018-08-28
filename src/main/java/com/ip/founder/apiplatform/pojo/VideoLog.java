package com.ip.founder.apiplatform.pojo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ip.founder.apiplatform.util.CustomJsonDateDeserializer;

public class VideoLog {
	private String logType;//日志类型
	private String orgName; //组织名称
	private String act; //日志动作
	private String ip; //ip
	private String module; //模块
	private String description; //描述
	private String indexCode; //编号
	private Date time; //时间
	private String operator; //用户名
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public Date getTime() {
		return time;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setTime(Date time) {
		this.time = time;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public static YjOperLog change(VideoLog log){
		YjOperLog yjLog = new YjOperLog();
		/*日志类型	type	varchar(32)	32		FALSE	FALSE	FALSE
		日志标题	title	varchar(100)	100		FALSE	FALSE	FALSE
		操作时间	create_time	datetime			FALSE	FALSE	FALSE
		操作ip	remote_addr	varchar(32)	32		FALSE	FALSE	FALSE
		操作人	operator	varchar(32)	32		FALSE	FALSE	FALSE
		操作方式	method	varchar(32)	32		FALSE	FALSE	FALSE
		数据来源	data_resource	varchar(32)	32		FALSE	FALSE	FALSE
		所属监狱	prison	varchar(32)	32		FALSE	FALSE	FALSE*/
		/*"logType": "编码设备",//日志类型
	    "orgName": "主控中心",//组织名称
	    "act": "删除编码设备",//日志动作
	    "ip": "10.10.163.21",//ip
	    "module": "编码设备",//模块
	    "description": "admin删除编码设备[dadada]",//描述
	    "indexCode": "63963641001130000014", //编号
	    "time": "2017-10-28 17:19:30", //时间
	    "operator": "admin" //用户名*/
		yjLog.setTitle(log.getLogType());
		yjLog.setTitle(log.getDescription());
		yjLog.setCreate_time(log.getTime());
		yjLog.setRemote_addr(log.getIp());
		yjLog.setOperator(log.getOperator());
		yjLog.setMethod(log.getAct());
		//大华 01、海康 02 、 美电贝尔 03 、 美一 04 、 神州明达 05 、 中电瑞达 06
		yjLog.setData_resource("02");
		yjLog.setPrison("04");
		return yjLog;
	}
	
}
