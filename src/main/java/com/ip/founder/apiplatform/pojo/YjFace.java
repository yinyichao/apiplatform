package com.ip.founder.apiplatform.pojo;

import java.util.Date;

public class YjFace {
	private String id;
	private String photo_url1; //抓拍的照片
	private Date identify_time; //报警时间
	private String ic_card; //身份证
	private Double similarity; //最大相似度
	private String name;  //姓名
	private String photo_url2;  //原照片
	//private String alarm_type;//alarm_type": "whitealarm" (白名单) /"blackalarm" (黑名单) /"repetitivealarm" (重复报警)  //报警类型当值为"whitealarm"时,下面的字段只有photo_url1,
	private Integer type;
	private Integer is_alarm;
	private String prison_id;
	/*{"photo_url1":""
	,"alarm_type":"whitealarm"}*/
	public String getPhoto_url1() {
		return photo_url1;
	}
	public void setPhoto_url1(String photo_url1) {
		this.photo_url1 = photo_url1;
	}
	public Date getIdentify_time() {
		return identify_time;
	}
	public void setIdentify_time(Date identify_time) {
		this.identify_time = identify_time;
	}
	public String getIc_card() {
		return ic_card;
	}
	public void setIc_card(String ic_card) {
		this.ic_card = ic_card;
	}
	public Double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto_url2() {
		return photo_url2;
	}
	public void setPhoto_url2(String photo_url2) {
		this.photo_url2 = photo_url2;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIs_alarm() {
		return is_alarm;
	}
	public void setIs_alarm(Integer is_alarm) {
		this.is_alarm = is_alarm;
	}
	public String getPrison_id() {
		return prison_id;
	}
	public void setPrison_id(String prison_id) {
		this.prison_id = prison_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
