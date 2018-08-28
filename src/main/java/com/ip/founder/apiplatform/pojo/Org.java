package com.ip.founder.apiplatform.pojo;

public class Org {
	private String fid;
	private String org_name;
	private String parentfid;
	private String type;
	private String deleted;
	
		
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	public String getParentfid() {
		return parentfid;
	}
	public void setParentfid(String parentfid) {
		this.parentfid = parentfid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
