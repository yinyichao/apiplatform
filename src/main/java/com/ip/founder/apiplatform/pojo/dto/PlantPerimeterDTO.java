package com.ip.founder.apiplatform.pojo.dto;

import lombok.Data;


@Data
public class PlantPerimeterDTO {
	/*{"name":"名称",
		"code":"编号",
		"type":"类型",
		"terrace":"所属平台",
		"x":"空间横坐标",
		"z":"空间高度",
		"y":"空间纵坐标",
		"port":"端口",
		"user_name":"登录用户名",
		"password":"登录密码",
		"address_url":"登录地址",
		"status":"状态",
		"remark":"备注",
	"data_resource":"数据来源",
	"prison":"所属监狱"}
	status：0、新增，1、修改，2、删除*/
	private String name;
	private String code;
	private String type;
	private String terrace;
	private String x;
	private String z;
	private String y;
	private String port;
	private String user_name;
	private String password;
	private String address_url;
	private String status;
	private String remark;
	private String data_resource;
	private String prison;

	
}