package com.ip.founder.apiplatform.controller.tel.lb;

import com.ip.founder.apiplatform.pojo.Alarm;
import com.ip.founder.apiplatform.pojo.Back;
import com.ip.founder.apiplatform.pojo.Data;
import com.ip.founder.apiplatform.pojo.PlantDict;
import com.ip.founder.apiplatform.service.tel.YjPoliceService;
import com.ip.founder.apiplatform.util.HttpUtil;
import com.ip.founder.apiplatform.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequestMapping("/lb")
public class PlantTelLBAction {
	@Autowired
	protected YjPoliceService yjPoliceService;
	// 报警信息
	@RequestMapping(value = "/postAlarm", method = RequestMethod.POST)
	@ResponseBody
	public String alarmMeg(@RequestBody String data) {
		log.info("来邦报警推送调用,参数:{}",data);
		System.out.println(data);
		Back back = new Back();
		Data data1 = new Data();
		if (data != null && data.length()>0) {
			data = data.substring(5);
			if (data != null) {
				Alarm alarm = JsonUtil.jsonObjToObject(data.toLowerCase(),
						Alarm.class);
				log.info(alarm.toString());
				if(alarm.getAlarmtype()==3){
					log.info("ok");

				}
			}
			back.setError_code(0);
			data1.setMsg("上传成功");
		} else {
			back.setError_code(-1);
			data1.setMsg("上传失败");
		}
		back.setData(data1);
		return JsonUtil.toJson(back);
	}
	// 详细信息
	@RequestMapping(value = "/postTerminal", method = RequestMethod.POST)
	@ResponseBody
	public String terminalMeg(@RequestBody String data,HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		log.info("来邦设备信息推送调用,参数:{}",data);
		System.out.println(data);
		Back back = new Back();
		Data data1 = new Data();
//		if(data!=null&&data.length()>0){
//			data = data.substring(5);
//			Terminal terminal = JsonUtil.jsonObjToObject(data.toLowerCase(),
//					Terminal.class);
//			// 添加设备详细信息
//			insertTerminalData(terminal);
//			back.setError_code(0);
//			data1.setMsg("上传成功");
//		}else{
//			back.setError_code(-1);
//			data1.setMsg("数据格式有误");
//		}
//		back.setData(data1);
		return JsonUtil.toJson(back);
	}
	// 运行信息
	@RequestMapping(value = "/postStatus", method = RequestMethod.POST)
	@ResponseBody
	public String statusMeg(@RequestBody String data) {
		log.info("来邦运行信息推送调用,参数:{}",data);
		System.out.println(data);
		Back back = new Back();
		Data data1 = new Data();

		return JsonUtil.toJson(back);
	}
	// 获取通话
	@RequestMapping(value = "/call", method = RequestMethod.GET)
	@ResponseBody
	public String call(String from, String to) {
		log.info("来邦仓讲拨打调用,参数:[from:{},to:{}]",from,to);
		String mdbeUrl = "http://127.0.0.1:8888/api";//getMdbeUrl();
		if (to != null) {
			return HttpUtil.getJsonByInternet(mdbeUrl + "/call?from=" + from
					+ "$to=" + to);
		} else {
			return HttpUtil.getJsonByInternet(mdbeUrl + "/call?from=" + from);
		}
	}
	// 获取美电贝尔网址
	public String getMdbeUrl() {
		PlantDict plantDict = new PlantDict();
		String name = "mdbe";
		Integer type = 4;
		plantDict.setName(name);
		plantDict.setType(type);
		String mdbeUrl = yjPoliceService.selectUrl(plantDict);
		return mdbeUrl;
	}
}
