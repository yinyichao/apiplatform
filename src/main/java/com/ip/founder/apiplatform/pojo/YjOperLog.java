package com.ip.founder.apiplatform.pojo;

import java.util.Date;

public class YjOperLog {
	private String id;
	private String type;
	private String title;
	private Date create_time;
	private String remote_addr;
	private String operator;
	private String method;
	private String data_resource;
	private String prison;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getRemote_addr() {
		return remote_addr;
	}
	public void setRemote_addr(String remote_addr) {
		this.remote_addr = remote_addr;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getData_resource() {
		return data_resource;
	}
	public void setData_resource(String data_resource) {
		this.data_resource = data_resource;
	}
	public String getPrison() {
		return prison;
	}
	public void setPrison(String prison) {
		this.prison = prison;
	}
	
}
