package com.ip.founder.apiplatform.pojo.dto;

public class AlarmPictureDTO {
    private String eventId;
    private String handleFile;
    private String triggerType;
    private Integer triggerRet;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getHandleFile() {
        return handleFile;
    }

    public void setHandleFile(String handleFile) {
        this.handleFile = handleFile;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Integer getTriggerRet() {
        return triggerRet;
    }

    public void setTriggerRet(Integer triggerRet) {
        this.triggerRet = triggerRet;
    }
}
