package com.ip.founder.apiplatform.controller.tel.my;

import com.ip.founder.apiplatform.pojo.Alarm;
import com.ip.founder.apiplatform.pojo.PlantDict;
import com.ip.founder.apiplatform.pojo.PlantRunningStatus;
import com.ip.founder.apiplatform.pojo.YjPolice;
import com.ip.founder.apiplatform.service.tel.YjPoliceService;
import com.ip.founder.apiplatform.util.HttpUtil;
import com.ip.founder.apiplatform.util.JsonUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Controller
@RequestMapping("/my")
public class PlantTelAction {
	@Autowired
	protected YjPoliceService yjPoliceService;
	@Autowired  
    private Environment env;
/**
 * 用户名：fangzheng，key：1038d6de39778a73f77cd3d127e8e3cd
 * @return
 */
	//login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(){
		String url = env.getProperty("my.url");
		String username = env.getProperty("my.username");
		String key = env.getProperty("my.key");
		String path = "http://"+url+"/api/users/?action=login&username="+username+"&key="+key;
		String post = "";
		String result = HttpUtil.postDownloadJson(path, post);
		System.out.println(result);
		String token = JsonUtil.getStringFromJsonByKey(result, "token");
		return token;
	}
	// 报警信息
	@RequestMapping(value = "/postAlarm", method = RequestMethod.GET)
	@ResponseBody
	/**
	 * from：发生报警主机 
		type：100 是防拆报警 
		call_type: 5 是告警类 
		inputNum：防拆报警的输入口是 5 
	 */
	public void alarmMeg(String type,String from,String call_type,String inputNum) {
		if (call_type != null && from != null) {
			Alarm alarm = new Alarm();
			alarm.setFid(from);
			alarm.setAlarmtype(Integer.parseInt(call_type));
			// 添加报警信息
			insertData(alarm);
			JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory
					.newInstance();
			// 获取北京地址
			PlantDict plantDict = new PlantDict();
			String name = "bj";
			Integer Btype = 4;
			plantDict.setName(name);
			plantDict.setType(Btype);
			String bjUrl = yjPoliceService.selectUrl(plantDict);
			Client client = clientFactory.createClient(bjUrl);
			Object[] result = null;
			try {
				String data = "{'type':1,'code':'" + from + "'}";
				result = client.invoke("sendMessage", data);
				System.out.println(result[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 添加报警信息
	public void insertData(Alarm alarm) {
		String prison = env.getProperty("prison");
		YjPolice yjPolice = new YjPolice();
		yjPolice.setEquipmentCode(alarm.getFid());
		yjPolice.setAlarmTime(new Date());
		yjPolice.setAlarmType(String.valueOf(alarm.getAlarmtype()));
		yjPolice.setDataResource("03");
		yjPolice.setPrison(prison);
		yjPoliceService.insertData(yjPolice);
	}
	// 运行信息
	@RequestMapping(value = "/postStatus", method = RequestMethod.GET)
	@ResponseBody
	public void statusMeg(String type,String device_id,String state) {
		System.out.println(type);
		//上线 ：0  下线：-1
		if (device_id!=null) {
			PlantRunningStatus status = new PlantRunningStatus(device_id,Integer.parseInt(state));
			yjPoliceService.insertDataStatus(status);
		}
	}

	// 获取通话
	@RequestMapping(value = "/call", method = RequestMethod.GET)
	@ResponseBody
	public String call(String from, String to) {
		String token = login();
		String path;
		String post = "";
		String url = env.getProperty("my.url");
		if (to != null) {
			path = "http://"+url+"/api/devices/?action=call&token="+token 
					+"&from="+from+"&to="+to;
			return HttpUtil.postDownloadJson(path, post);
		} else {
			path = "http://"+url+"/api/devices/"+from+"/?action=hangup" 
				+"&token="+token;
			return HttpUtil.postDownloadJson(path, post);
		}
	}
	
	// 订阅服务
	/**
	 * 只能订阅一种，或者全部订阅
	 */
	@RequestMapping(value = "/subscibe", method = RequestMethod.GET)
	@ResponseBody
	public void subscibe(ServletRequest request) {
		String url = env.getProperty("my.url");
		String addr = "";
		int port = request.getServerPort();
		try {
			addr = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}//获得本机IP
		String uri = "http://"+addr+":"+port;
		String token = login();
		//订阅报警
		String path = "http://"+url+"/api/events/?action=subscribe&token="+token
				+"&type=alarm&url="+uri+"/my/postAlarm";
		String post = "";
		//HttpUtil.postDownloadJson(path, post);
		//订阅设备上下线
		path = "http://"+url+"/api/events/?action=subscribe&token="+token
				+"&type=status&url="+uri+"/my/postStatus";
		HttpUtil.postDownloadJson(path, post);
	}
	// 续订订阅服务（20分钟一次）
	@RequestMapping(value = "/renew", method = RequestMethod.GET)
	@ResponseBody
	public void renew() {
		String url = env.getProperty("my.url");
		String token = login();
		//续订报警
		String subscribeId = "";
		String path = "http://"+url+"/api/events/?action=renew_subscription"
				+ "&subscribeId="+subscribeId+"&token="+token;
		String post = "";
		HttpUtil.postDownloadJson(path, post);
		//续订报警
		subscribeId = "";
		path = "http://"+url+"/api/events/?action=renew_subscription"
				+ "&subscribeId="+subscribeId+"&token="+token;
		HttpUtil.postDownloadJson(path, post);
	}
	/**
	 * token string 服务安全令牌 
	start string 示可选，起始节点设备 id 或者组名称。如没有指定，则表从根节点开始遍历。 
	ref integer 可选，0：获取祖先 1：获取兄弟 2：获取后代 
	flat boolean 当 true，返回的结果是一个一维数组。false 时返回树状JSON 格式。 
	level integer 限定树查询级别，如果有 level=0 则不限制树的层级，将
	返回整棵设备树。1-n 代表限定返回树的级别。如 level=1
	只返回该设备的第一层级的设备清单，level=2 返回该设备
	和该设备下一级设备清单 
	 * {"status": "success", "descendants_count": 4, "token": "ba2363c330db4e4fa71f70f8c031f64a", "payload": [{"targetInfo": null, "name": null, "pId": "", "targetId": null, "isParent": true, "Alias": null, "tree_level": 1, "tree_id": 0, "type": 0, "id": "root"}, {"targetInfo": "", "name": "\u6240\u6709\u8bbe\u5907", "pId": "root", "targetId": null, "isParent": false, "Alias": null, "tree_level": 2, "tree_id": 0, "type": 1, "id": "custom"}, {"targetInfo": "", "name": "\u65b0\u7684\u8bbe\u5907", "pId": "root", "targetId": null, "isParent": true, "Alias": null, "tree_level": 2, "tree_id": 0, "type": 1, "id": "new"}]}
	 */
	@RequestMapping(value = "/getTerminalList", method = RequestMethod.GET)
	@ResponseBody
	public void terminalList(){
		String url = env.getProperty("my.url");
		String token = login();
		String path = "http://"+url+"/api/devices/?token="+token+"&flat=true" 
				+"&ref=2";
		String result = HttpUtil.getJsonByInternet(path);
		System.out.println(result);
	}
	/*public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }*/
	/*// 取消订阅
	@RequestMapping(value = "/call", method = RequestMethod.GET)
	@ResponseBody
	public String call(String from, String to) {
		String mdbeUrl = getMdbeUrl();
		if (to != null) {
			return HttpUtil.getJsonByInternet(mdbeUrl + "/call?from=" + from
					+ "&to=" + to);
		} else {
			return HttpUtil.getJsonByInternet(mdbeUrl + "/call?from=" + from);
		}
	}*/
		
}
