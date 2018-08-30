package com.ip.founder.apiplatform.controller.perimeter;

import com.ip.founder.apiplatform.pojo.*;
import com.ip.founder.apiplatform.pojo.dto.AlarmDTO;
import com.ip.founder.apiplatform.pojo.dto.PlantPerimeterDTO;
import com.ip.founder.apiplatform.pojo.dto.PlantRunningStatusDTO;
import com.ip.founder.apiplatform.pojo.dto.VideoAlarmDTO;
import com.ip.founder.apiplatform.service.perimeter.PlantPerimeterService;
import com.ip.founder.apiplatform.service.tel.YjPoliceService;
import com.ip.founder.apiplatform.util.JsonUtil;
import com.ip.founder.apiplatform.util.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
public class VideoDH {

	protected PlantPerimeterService plantPerimeterService;

	protected YjPoliceService yjPoliceService;

	private Environment env;
	@Autowired
	public void setPlantPerimeterService(PlantPerimeterService plantPerimeterService) {
		this.plantPerimeterService = plantPerimeterService;
	}
	@Autowired
	public void setYjPoliceService(YjPoliceService yjPoliceService) {
		this.yjPoliceService = yjPoliceService;
	}
	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	// 详细信息
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	@ResponseBody
	public String terminalMeg(String data) throws UnsupportedEncodingException {
		String resultData = "0";
		log.info("大华设备信息推送调用,参数：{}",data);
		if (data != null && !"".equals(data.trim())) {
			PlantPerimeterDTO perimeter = JsonUtil.jsonObjToObject(data,
					PlantPerimeterDTO.class);
			// 添加设备详细信息
			insertTerminalData(perimeter);
		}else{
			resultData = "1";
		}
		return resultData;
	}
	// 添加设备详细信息
	public void insertTerminalData(PlantPerimeterDTO terminal) {
		//操作类型 1.add,2.update,3.del
		if (terminal.getStatus().equals("3")) {
			plantPerimeterService.deleteDataTerminal(terminal.getCode());
		} else {
			PlantPerimeter perimeter = plantPerimeterService.findData(terminal.getCode());
			perimeter.setName(terminal.getName());
			perimeter.setCode(terminal.getCode());
			perimeter.setInTime(new Date());
			perimeter.setPort(terminal.getPort());
			perimeter.setAddressUrl(terminal.getAddress_url());
			perimeter.setUserName(terminal.getUser_name());
			plantPerimeterService.insertDataTerminal(perimeter);
		}
	}
	// 报警信息
	//{"equipmentCode": "23000001561180241001","alarmTime": "1524728150","alarmType": "596","handleResult": "1","handleFile": "","dataResource": "","prison": ""}
	@RequestMapping(value = "/behavior", method = RequestMethod.GET)
	@ResponseBody
	public String alarmMeg(String data) {
		log.info("大华报警推送调用,参数：{}",data);
		String resultData = "0";
		if (data != null && !"".equals(data.trim())) {
			//data = HttpUtil.unicode(data);
			List<AlarmDTO> alarmDTOList = JsonUtil.jsonArrToList(data, AlarmDTO.class);
			assert alarmDTOList != null;
			for (AlarmDTO alarmDTO : alarmDTOList){
				if(alarmDTO.getHandleFile()!=null){
					VideoAlarmDTO al = new VideoAlarmDTO();
					al.setSourceIndexCode(alarmDTO.getEquipmentCode().substring(0,alarmDTO.getEquipmentCode().length()-1));
					al.setEventType(Integer.parseInt(alarmDTO.getAlarmType()));
					al.setExtendInfo(alarmDTO.getHandleResult());
					al.setEventTime(stampToDate(alarmDTO.getAlarmTime()));
					al.setHandleFile(alarmDTO.getHandleFile());
					PlantDict plantDict = new PlantDict();
					String name = "bj";
					Integer type = 4;
					plantDict.setName(name);
					plantDict.setType(type);
					String bjUrl = yjPoliceService.selectUrl(plantDict);
					String method = "sendMessage";
					if(al.getEventType()>=300&&al.getEventType()<=1000){
						data = "{'type':4,'code':'" + JsonUtil.toJson(al) + "'}";
					}else if((al.getEventType()>=41&&al.getEventType()<=47&&al.getEventType()!=42&&al.getEventType()!=45)||(al.getEventType()>=52&&al.getEventType()<=55)){
						data = "{'type':6,'code':'" + JsonUtil.toJson(al) + "'}";
					}
					resultData = WebService.service(bjUrl, method, data);
					insertData(alarmDTO);
				}
			}
		} else {
			resultData = "1";
		}
		return resultData;
	}
	// 添加报警信息
	public void insertData(AlarmDTO alarm) {
		String prison = env.getProperty("prison");
		YjPolice yjPolice = new YjPolice();
		Date date = null;
		try {
			String time = alarm.getAlarmTime();
			time = stampToDate(time);
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		yjPolice.setEquipmentCode(alarm.getEquipmentCode());
		yjPolice.setAlarmTime(date);
		yjPolice.setAlarmType(alarm.getAlarmType());
		yjPolice.setAlarmReason(alarm.getHandleResult());
		yjPolice.setHandlefile(alarm.getHandleFile());
		yjPolice.setDataResource("02");
		yjPolice.setPrison(prison);
		yjPoliceService.insertData(yjPolice);
	}
	// 运行信息
	@RequestMapping(value = "/videoState", method = RequestMethod.GET)
	@ResponseBody
	public String statusMeg(String data) {
		log.info("大华运行信息推送调用,参数：{}",data);
		String resultData = "0";
		if (data != null && !"".equals(data.trim())) {
			PlantRunningStatusDTO vs = JsonUtil.jsonObjToObject(data, PlantRunningStatusDTO.class);
			PlantRunningStatus status = PlantRunningStatusDTO.change(vs);
			plantPerimeterService.insertDataStatus(status);
		} else {
			resultData = "1";
		}
		return resultData;
	}
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s) * 1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

	/**
	 * 大华订阅人脸
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/face", method = RequestMethod.GET)
	@ResponseBody
    public String subscribeFace() throws IOException {
		String urlPath = "http://10.1.0.113:11180/face/v1/framework/user/login";
		URL url = new URL(urlPath);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		// 发送POST请求必须设置如下两行
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		// 提交模式
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Content-Type", "application/json");
		//conn.setRequestProperty("Cookie", "session_id=123456");
		// 获取URLConnection对象对应的输出流
		PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
		// 发送请求参数
		//密码需要md5
		//post的参数 xx=xx&yy=yy
		String str = "{\"name\":\"admin\",\"password\":\"e99a18c428cb38d5f260853678922e03\"}";
		printWriter.write(str);
		printWriter.flush();
		printWriter.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String session_id = sb.toString();
		String s = JsonUtil.getStringFromJsonByKey(session_id, "session_id");
		br.close();
		ttt(s);
		//tttt(s);
    	return session_id;
	}
	/*
	 * 大华人脸推送
	 * @return
			 * @throws IOException
	 */
	@RequestMapping(value = "/postFace", method = RequestMethod.POST)
	@ResponseBody
	public String postFace(@RequestBody String data) {
		log.info("大华人脸推送调用,参数：{}",data);
		String time = JsonUtil.getStringFromJsonByKey(data, "timestamp");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long timestamp = Long.parseLong(time)*1000;
		time = sdf.format(timestamp);
		Date datetime = null;
		try {
			datetime = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean is_alarm = false;
		String photo1 = JsonUtil.getStringFromJsonByKey(data, "face_image_uri");
		String similar_faces = JsonUtil.getStringFromJsonByKeyE(data, "similar_faces");
		YjFace face = new YjFace();
		if(similar_faces!=null){
			List<FaceDH> faceDHS = JsonUtil.jsonArrToList(similar_faces,FaceDH.class);
			FaceDH faceDH = faceDHS.get(0);
			face.setPhoto_url2(faceDH.getFace_image_uri());
			face.setIc_card(faceDH.getPerson_id());
			face.setSimilarity(faceDH.getSimilarity());
			face.setName(faceDH.getName());
			String type = env.getProperty("type");
			if(type.equals(faceDH.getRepository_id().toString())){
				face.setType(2);
				face.setIs_alarm(1);
				is_alarm = true;
			}else{
				face.setType(1);
				face.setIs_alarm(0);
			}
		}else{
			face.setType(0);
			face.setIs_alarm(0);
		}
		face.setPhoto_url1(photo1);
		face.setIdentify_time(datetime);
		String prison = env.getProperty("prison");
		face.setPrison_id(prison);
		plantPerimeterService.insertDataTerminal(face);
		String resultData = null;
		if(is_alarm) {
			PlantDict plantDict = new PlantDict();
			String name = "bj";
			Integer type = 4;
			plantDict.setName(name);
			plantDict.setType(type);
			String bjUrl = yjPoliceService.selectUrl(plantDict);
			//String bjUrl = "http://192.168.1.198:8088/jyjsecurity/api/plant?wsdl";
			String method = "sendMessage";
			data = "{'type':5,'id':'" + face.getId() + "'}";
			resultData = WebService.service(bjUrl, method, data);
		}
		return resultData;
	}
	public static void  ttt(String session_id)throws IOException{
		String urlPath = "http://10.1.0.113:9900/face/v1/framework/face_video/subscription";
		URL url = new URL(urlPath);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		// 发送POST请求必须设置如下两行
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		// 提交模式
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("Content-Type", "application/json");
		httpURLConnection.setRequestProperty("Cookie", "session_id="+session_id);
		// 获取URLConnection对象对应的输出流
		PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
		// 发送请求参数
		//密码需要md5
		//post的参数 xx=xx&yy=yy
		String str = "{\n"  +
				"\t\"target_url\": \"http://10.14.0.102:8080/api/postFace\"\n" +
				"}";
		printWriter.write(str);
		printWriter.flush();
		printWriter.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
		br.close();
	}

	public static void  tttt(String session_id)throws IOException{
		String urlPath = "http://10.1.0.113:9900/face/v1/framework/face_video/subscription";
		URL url = new URL(urlPath);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		// 发送POST请求必须设置如下两行
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		// 提交模式
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("Content-Type", "application/json");
		httpURLConnection.setRequestProperty("Cookie", "session_id="+session_id);
		// 获取URLConnection对象对应的输出流
		PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());

		BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
		br.close();
	}

}
