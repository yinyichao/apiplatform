package com.ip.founder.apiplatform.pojo;

public class VideoAlarm implements Cloneable{
	private String eventId;//事件ID
	private String sourceIndexCode;//通道编号
	private String eventTime;//报警时间
	private String sourceName;//通道名称
	private Integer eventType;//事件类型
	private String extendInfo;//扩展信息
	private Integer eventLevel;//报警等级
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getSourceIndexCode() {
		return sourceIndexCode;
	}
	public void setSourceIndexCode(String sourceIndexCode) {
		this.sourceIndexCode = sourceIndexCode;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Integer getEventType() {
		return eventType;
	}
	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}

	public Integer getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(Integer eventLevel) {
		this.eventLevel = eventLevel;
	}

	@Override
    public Object clone() {  
		VideoAlarm va = null;  
        try{  
            va = (VideoAlarm)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return va;  
    } 
}
