package com.ip.founder.apiplatform.pojo;

public class Terminal {
	private String fid;
	private String number;
	private String name;
	private String sn;
	private String position;
	private Integer type;
	private Integer level;
	private String ip;
	private String mac;
	private String org_fid;
	private String super_fid;
	private String model;
	private Integer deleted;
	private String updatetime;
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getOrg_fid() {
		return org_fid;
	}
	public void setOrg_fid(String org_fid) {
		this.org_fid = org_fid;
	}
	public String getSuper_fid() {
		return super_fid;
	}
	public void setSuper_fid(String super_fid) {
		this.super_fid = super_fid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
}
