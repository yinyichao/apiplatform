package com.ip.founder.apiplatform.pojo.dto;

import com.ip.founder.apiplatform.pojo.YjPolice;

import java.text.SimpleDateFormat;

public class VideoAlarmDTO {
    private String eventId;//事件ID
    private String sourceIndexCode;//通道编号
    private String eventTime;//报警时间
    private String sourceName;//通道名称
    private Integer eventType;//事件类型
    private String extendInfo;//扩展信息
    private Integer eventLevel;//报警等级
    private String handleFile;
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

    public String getHandleFile() {
        return handleFile;
    }

    public void setHandleFile(String handleFile) {
        this.handleFile = handleFile;
    }
    public void change (YjPolice yjPolice){
        this.eventId = yjPolice.getEventid();
        this.sourceIndexCode = yjPolice.getEquipmentCode();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(yjPolice.getAlarmTime());
        this.extendInfo = "";
        this.eventLevel = 2;
        this.eventTime = dateString;
        this.eventType = Integer.parseInt(yjPolice.getAlarmType());
        this.extendInfo = yjPolice.getAlarmReason();
        this.sourceName = yjPolice.getEquipmentType();
        this.handleFile = yjPolice.getHandlefile();
    }
}
