package com.ip.founder.apiplatform.pojo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ip.founder.apiplatform.util.CustomJsonDateDeserializer;

public class PlantRunningStatus {
	private String fid;
	private Date updatetime;
	private Integer status;
	public PlantRunningStatus(){
		
	}
	public PlantRunningStatus(String fid,Integer status) {
		super();
		this.fid = fid;
		this.updatetime = new Date();
		this.status = status;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
