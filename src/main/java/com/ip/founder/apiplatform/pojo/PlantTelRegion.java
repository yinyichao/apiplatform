package com.ip.founder.apiplatform.pojo;

import com.ip.founder.apiplatform.dao.DataEntity;

public class PlantTelRegion extends DataEntity<PlantTelRegion> {
	private String id;
	private String name;
	private String remarks;
	private String parent_id;
	private String code;
	private String region_level;
	private Integer source = 1;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRegion_level() {
		return region_level;
	}
	public void setRegion_level(String region_level) {
		this.region_level = region_level;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	
	
	

}
