package com.ip.founder.apiplatform.pojo;

import java.util.Date;

public class VideoStatus {
	private Integer isOnline;
	private String cameraIndexCode;
	public Integer getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Integer isOnline) {
		if(isOnline==-1){
			isOnline = 1;
		}else{
			isOnline = 0;
		}
		this.isOnline = isOnline;
	}
	public String getCameraIndexCode() {
		return cameraIndexCode;
	}
	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}
	public Date getUpdateTime() {
		return new Date();
	}
	public static PlantRunningStatus change(VideoStatus vs){
		PlantRunningStatus status = new PlantRunningStatus();
		status.setFid(vs.getCameraIndexCode());
		status.setStatus(vs.getIsOnline());
		status.setUpdatetime(vs.getUpdateTime());
		return status;
	}
}
