package com.ip.founder.apiplatform.pojo.dto;

import java.util.Date;

import lombok.Data;

import com.ip.founder.apiplatform.pojo.PlantRunningStatus;

@Data
public class PlantRunningStatusDTO {
	private String code;
	private String status;
	private String data_resource;
	private String prison;	
	public static PlantRunningStatus change(PlantRunningStatusDTO vs){
		PlantRunningStatus status = new PlantRunningStatus();
		status.setFid(vs.getCode());
		status.setStatus(Integer.parseInt(vs.getStatus()));
		status.setUpdatetime(new Date());
		return status;
	}
}
