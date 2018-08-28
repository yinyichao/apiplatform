package com.ip.founder.apiplatform.controller.tel.mdbe;

import com.ip.founder.apiplatform.pojo.*;
import com.ip.founder.apiplatform.pojo.dto.YjCallLogDTO;
import com.ip.founder.apiplatform.service.tel.YjPoliceService;
import com.ip.founder.apiplatform.util.HttpUtil;
import com.ip.founder.apiplatform.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
@Controller
@RequestMapping("/tel")
public class PlantTelMDBEAction {
	@Autowired
	protected YjPoliceService yjPoliceService;
	@Autowired  
    private Environment env;
	// 报警信息
	@RequestMapping(value = "/postAlarm", method = RequestMethod.POST)
	@ResponseBody
	public String alarmMeg(@RequestBody String data) {
		log.info("美电贝尔报警推送调用,参数:{}",data);
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
					// 添加报警信息
					insertData(alarm);
					JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory
							.newInstance();
					// 获取北京地址
					PlantDict plantDict = new PlantDict();
					String name = "bj";
					Integer type = 4;
					plantDict.setName(name);
					plantDict.setType(type);
					String bjUrl = yjPoliceService.selectUrl(plantDict);
					Client client = clientFactory.createClient(bjUrl);
					Object[] result = null;
					data = "{'type':1,'code':'" + JsonUtil.toJson(alarm) + "'}";
					System.out.println(data);
					try {
						data = "{'type':1,'code':'" + JsonUtil.toJson(alarm) + "'}";
						result = client.invoke("sendMessage", data);
						System.out.println(result[0]);
					} catch (Exception e) {
						e.printStackTrace();
					}
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
	// 运行信息
	@RequestMapping(value = "/postStatus", method = RequestMethod.POST)
	@ResponseBody
	public String statusMeg(@RequestBody String data) {
		log.info("美电贝尔运行信息推送调用,参数:{}",data);
		Back back = new Back();
		Data data1 = new Data();
		if (data != null && data.length()>0) {
			data = data.substring(5);
			/*PlantRunningStatus prs = JsonUtil.jsonObjToObject(
					data.toLowerCase(), PlantRunningStatus.class);
			if(prs.getStatus().equals("1")){
				prs.setStatus(0);
			}else{
				prs.setStatus(1);
			}*/
//			yjPoliceService.insertDataStatus(prs);
			back.setError_code(0);
			data1.setMsg("上传成功");
		} else {
			back.setError_code(-1);
			data1.setMsg("数据格式有误");
		}
		back.setData(data1);
		return JsonUtil.toJson(back);
	}
	/*// 运行信息
	@RequestMapping(value = "/postAllStatus", method = RequestMethod.POST)
	@ResponseBody
	public String postAllStatus(@RequestBody String data) {
		log.info("美电贝尔运行信息All推送调用,参数:{}",data);
		Back back = new Back();
		Data data1 = new Data();
		if (data != null && data.length()>0) {
			data = data.substring(5);
			List<PlantRunningStatus> prsList = JsonUtil.jsonArrToList(
					data.toLowerCase(), PlantRunningStatus.class);
			for (PlantRunningStatus prs : prsList) {
				if(prs.getStatus().equals("1")){
					prs.setStatus(0);
				}else{
					prs.setStatus(1);
				}
				yjPoliceService.insertDataStatus(prs);
			}
			back.setError_code(0);
			data1.setMsg("上传成功");
		} else {
			back.setError_code(-1);
			data1.setMsg("数据格式有误");
		}
		back.setData(data1);
		return JsonUtil.toJson(back);
	}*/
	// 日志信息
	@RequestMapping(value = "/postLog", method = RequestMethod.POST)
	@ResponseBody
	public String logMeg(@RequestBody String data) {
		log.info("美电贝尔日志信息推送调用,参数:{}",data);
		Back back = new Back();
		Data data1 = new Data();
		/*if (data != null && data.length()>0) {
			data = data.substring(5);
			YjOperLogDTO prs = JsonUtil.jsonObjToObject(data.toLowerCase(),
					YjOperLogDTO.class);
			YjOperLog yj = YjOperLogDTO.change(prs);
			yjPoliceService.insertDataLog(yj);
			back.setError_code(0);
			data1.setMsg("上传成功");
		} else {
			back.setError_code(-1);
			data1.setMsg("数据格式有误");
		}*/
		back.setError_code(0);
		data1.setMsg("上传成功");
		back.setData(data1);
		return JsonUtil.toJson(back);
	}

	// 添加报警信息
	public void insertData(Alarm alarm) {
		String prison = env.getProperty("prison");
		YjPolice yjPolice = new YjPolice();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(alarm
					.getUpdatetime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		yjPolice.setEquipmentCode(alarm.getFid());
		yjPolice.setAlarmTime(date);
		yjPolice.setAlarmType(String.valueOf(alarm.getAlarmtype()));
		yjPolice.setDataResource("03");
		yjPolice.setPrison(prison);
		yjPoliceService.insertData(yjPolice);
	}

	// 通话信息
	@RequestMapping(value = "/postCallRecord", method = RequestMethod.POST)
	@ResponseBody
	public String callRecordMeg(@RequestBody String data) throws UnsupportedEncodingException {
		log.info("美电贝尔通话信息推送调用,参数:{}",data);
		Back back = new Back();
		Data data1 = new Data();
		if (data != null && data.length()>0) {
			data = data.substring(5);
			YjCallLogDTO prs = JsonUtil.jsonObjToObject(data.toLowerCase(),
					YjCallLogDTO.class);
			yjPoliceService.insertDataCallRecord(YjCallLogDTO.change(prs));
			back.setError_code(0);
			data1.setMsg("上传成功");
		} else {
			back.setError_code(-1);
			data1.setMsg("数据格式有误");
		}
		back.setData(data1);
		return JsonUtil.toJson(back);
	}

	// 组织结构
	@RequestMapping(value = "/postOrg", method = RequestMethod.POST)
	@ResponseBody
	public String orgMeg(@RequestBody String data,HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		log.info("美电贝尔组织信息推送调用,参数:{}",data);
		Back back = new Back();
		Data data1 = new Data();
		if(data!=null&&data.length()>0){
			data = data.substring(5);
			Org org = JsonUtil.jsonObjToObject(data.toLowerCase(), Org.class);
			// 添加组织结构
			insertOrgData(org);
			back.setError_code(0);
			data1.setMsg("上传成功");
		}else{
			back.setError_code(-1);
			data1.setMsg("数据格式有误");
		}
		return JsonUtil.toJson(back);
	}

	// 添加组织结构
	public void insertOrgData(Org org) {
		if (org.getDeleted().equals("1")) {
			yjPoliceService.deleteDataOrg(org.getFid());
		} else {
			PlantTelRegion plantTelRegion = new PlantTelRegion();
			plantTelRegion.setName(org.getOrg_name());
			plantTelRegion.setParent_id(org.getParentfid());
			plantTelRegion.setCode(org.getFid());
			plantTelRegion.setRegion_level(org.getType());
			yjPoliceService.insertDataOrg(plantTelRegion);
		}
	}

	// 详细信息
	@RequestMapping(value = "/postTerminal", method = RequestMethod.POST)
	@ResponseBody
	public String terminalMeg(@RequestBody String data,HttpServletResponse response) throws UnsupportedEncodingException {
		//request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type" , "application/json;charset=utf-8");
		log.info("美电贝尔设备信息推送调用,参数:{}",data);
		Back back = new Back();
		Data data1 = new Data();
		/*if(data!=null&&data.length()>0){
			data = URLDecoder.decode(data, "UTF-8");
			data = data.substring(0,data.length()-1);
			log.info("改装后:{}",data);
			Terminal terminal = JsonUtil.jsonObjToObject(data.toLowerCase(),
					Terminal.class);
			// 添加设备详细信息
			insertTerminalData(terminal);*/
			back.setError_code(0);
			data1.setMsg("上传成功");
		/*}else{
			back.setError_code(-1);
			data1.setMsg("数据格式有误");
		}*/
		back.setData(data1);
		return JsonUtil.toJson(back);
	}

	// 添加设备详细信息
	public void insertTerminalData(Terminal terminal) {
		if (terminal.getDeleted() == 1) {
			yjPoliceService.deleteDataTerminal(terminal.getFid());
		} else {
			String regionId = yjPoliceService.selectRegionIdByName(terminal
					.getName());
			if(regionId!=null){
				Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(terminal
							.getUpdatetime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				PlantTel plantTel = yjPoliceService.findData(terminal.getFid());
				plantTel.setNumber(terminal.getNumber());
				plantTel.setName(terminal.getName());
				plantTel.setSn(terminal.getSn());
				plantTel.setPosition(terminal.getPosition());
				plantTel.setType(terminal.getType());
				plantTel.setIp(terminal.getIp());
				plantTel.setMac(terminal.getMac());
				plantTel.setOrgFid(terminal.getOrg_fid());
				plantTel.setSuperFid(terminal.getSuper_fid());
				plantTel.setModel(terminal.getModel());
				plantTel.setCode(terminal.getFid());
				plantTel.setPid("1");
				plantTel.setFid("2");
				plantTel.setRegion_id(regionId);
				plantTel.setStatus(0);
				plantTel.setIn_time(date);
				yjPoliceService.insertDataTerminal(plantTel);
			}
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

	// 获取通话记视频录像
	@RequestMapping(value = "/getCallRecord", method = RequestMethod.GET)
	@ResponseBody
	public String callRecord(String uuid) {
		log.info("美电贝尔获取通话视频调用,参数:{}",uuid);
		String mdbeUrl = getMdbeUrl();
		return HttpUtil.getJsonByInternet(mdbeUrl + "/getCallRecord?uuid="
				+ uuid);
	}

	// 获取通话
	@RequestMapping(value = "/call", method = RequestMethod.GET)
	@ResponseBody
	public String call(String from, String to) {
		log.info("美电贝尔仓讲拨打调用,参数:[from:{},to:{}]",from,to);
		String mdbeUrl = getMdbeUrl();
		if (to != null) {
			return HttpUtil.getJsonByInternet(mdbeUrl + "/call?from=" + from
					+ "&to=" + to);
		} else {
			return HttpUtil.getJsonByInternet(mdbeUrl + "/call?from=" + from);
		}
	}

	@RequestMapping(value = "/getOrgList", method = RequestMethod.GET)
	@ResponseBody
	public String orgList() {
		log.info("美电贝尔获取组织信息调用");
		String mdbeUrl = getMdbeUrl();
		return HttpUtil.getJsonByInternet(mdbeUrl + "/getOrgList");
	}

	@RequestMapping(value = "/getTerminalList", method = RequestMethod.GET)
	@ResponseBody
	public String terminalList(String orgFID) {
		log.info("美电贝尔获取设备信息调用,参数:{}",orgFID);
		String mdbeUrl = getMdbeUrl();
		return HttpUtil.getJsonByInternet(mdbeUrl + "/getTerminalList?orgFID="
				+ orgFID);
	}
}
