package com.ip.founder.apiplatform.pojo.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ip.founder.apiplatform.pojo.YjOperLog;
import com.ip.founder.apiplatform.util.CustomJsonDateDeserializer;

public class YjOperLogDTO {
	private String user_name;
	private String term_name;
	private String form;
	private String oper_model;
    private String oper_type;
    private String oper_info;
    private Date oper_time;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTerm_name() {
		return term_name;
	}
	public void setTerm_name(String term_name) {
		this.term_name = term_name;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getOper_model() {
		return oper_model;
	}
	public void setOper_model(String oper_model) {
		this.oper_model = oper_model;
	}
	public String getOper_type() {
		return oper_type;
	}
	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}
	public String getOper_info() {
		return oper_info;
	}
	public void setOper_info(String oper_info) {
		this.oper_info = oper_info;
	}
	public Date getOper_time() {
		return oper_time;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setOper_time(Date oper_time) {
		this.oper_time = oper_time;
	}
	/*user_name	是	string	操作用户名
	term_name	是	string	设备名称
	form	是	string	来源
	oper_model	是	string	操作模块
	oper_type	是	string	操作类型
	oper_info	是	string	操作详细信息
	oper_time	是	string	操作时间*/
	/*日志类型	type	varchar(32)	32		FALSE	FALSE	FALSE
	日志标题	title	varchar(100)	100		FALSE	FALSE	FALSE
	操作时间	create_time	datetime			FALSE	FALSE	FALSE
	操作ip	remote_addr	varchar(32)	32		FALSE	FALSE	FALSE
	操作人	operator	varchar(32)	32		FALSE	FALSE	FALSE
	操作方式	method	varchar(32)	32		FALSE	FALSE	FALSE
	数据来源	data_resource	varchar(32)	32		FALSE	FALSE	FALSE
	所属监狱	prison	varchar(32)	32		FALSE	FALSE	FALSE*/
	public static YjOperLog change(YjOperLogDTO dto){
		YjOperLog log = new YjOperLog();
		log.setType(dto.getOper_type());
		log.setTitle(dto.getOper_info());
		log.setCreate_time(dto.getOper_time());
		log.setRemote_addr(dto.getTerm_name());
		log.setOperator(dto.getForm());
		log.setMethod(dto.getOper_model());
		//大华 01、海康 02 、 美电贝尔 03 、 美一 04 、 神州明达 05 、 中电瑞达 06
		log.setData_resource("03");
		log.setPrison("04");
		return log;
	}
}
