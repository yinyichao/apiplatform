package com.ip.founder.apiplatform.controller.perimeter;

import com.ip.founder.apiplatform.pojo.*;
import com.ip.founder.apiplatform.pojo.dto.AlarmPictureDTO;
import com.ip.founder.apiplatform.pojo.dto.VideoAlarmDTO;
import com.ip.founder.apiplatform.pojo.dto.YjFaceDTO;
import com.ip.founder.apiplatform.service.perimeter.PlantFactoryService;
import com.ip.founder.apiplatform.service.perimeter.PlantPerimeterService;
import com.ip.founder.apiplatform.service.tel.YjPoliceService;
import com.ip.founder.apiplatform.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.ServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Controller
@RequestMapping("/video")
public class PlantPerimeterAction {

    protected PlantFactoryService plantFactoryService;

    protected PlantPerimeterService plantPerimeterService;

    protected YjPoliceService yjPoliceService;

    private Environment env;

    @Autowired
    public void setPlantFactoryService(PlantFactoryService plantFactoryService) {
        this.plantFactoryService = plantFactoryService;
    }

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

    @RequestMapping(value = "/cameraview", method = RequestMethod.GET)
    public String findCameraView(String code, Model model) {
        model.addAttribute("code", code);
        return "/cameraview";
    }

    @RequestMapping(value = "/preview64", method = RequestMethod.GET)
    public String preview64() {
        return "/PreviewCtrl64";
    }

    // 海康摄像头预览
    @RequestMapping(value = "/startPreview", method = RequestMethod.GET)
    public String startPreviewHK(String code, String prison, String height, String width, Model model) {

        String addressUrl = plantPerimeterService.findAddressUrlByRemark(code);

        PlantFactory plantFactory = plantFactoryService
                .findPlantFactoryData(prison);
        String name = plantPerimeterService.findCameraName(code);
        model.addAttribute("pfData", plantFactory);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("addressUrl", addressUrl);
        model.addAttribute("height", height);
        model.addAttribute("width", width);
        return "/startPreviewHK";
    }

    // 海康摄像头回放
    @RequestMapping(value = "/playBack", method = RequestMethod.GET)
    public String playBackHK(String prison, String code, String height, String width, Model model) {
        PlantFactory plantFactory = plantFactoryService
                .findPlantFactoryData(prison);
        model.addAttribute("pfData", plantFactory);

        String name = plantPerimeterService.findCameraName(code);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("height", height);
        model.addAttribute("width", width);
        return "/playBackHK";
    }

    // 大华摄像头 预览
    @RequestMapping(value = "/startPreviewDH", method = RequestMethod.GET)
    public String startPreviewDH(String prison, String code, String height, String width, Model model) {
        PlantFactory plantFactory = plantFactoryService
                .findPlantFactoryData(prison);
        model.addAttribute("pfData", plantFactory);
        String name = plantPerimeterService.findCameraName(code);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("height", height);
        model.addAttribute("width", width);
        return "/startPreviewDH";
    }

    // 大华摄像头回放
    @RequestMapping(value = "/playBackDH", method = RequestMethod.GET)
    public String playBackDH(String prison, String code, String height, String width, Model model) {
        PlantFactory plantFactory = plantFactoryService
                .findPlantFactoryData(prison);
        model.addAttribute("pfData", plantFactory);
        String name = plantPerimeterService.findCameraName(code);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("height", height);
        model.addAttribute("width", width);
        return "/playBackDH";
    }

    // 大华摄像头回放
    @RequestMapping(value = "/previewDH", method = RequestMethod.GET)
    public String previewDH() {
        return "/previewDH";
    }

    // 大华摄像头回放
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo(String prison, String code, String height, String width, Model model) {
        PlantFactory plantFactory = plantFactoryService
                .findPlantFactoryData(prison);
        model.addAttribute("pfData", plantFactory);
        String name = plantPerimeterService.findCameraName(code);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("height", height);
        model.addAttribute("width", width);
        return "/IPreviewCtrl";
    }

    // hik摄像注册服务
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    public String registerHttpService(ServletRequest request) {
        String addr = "";
        int port = request.getServerPort();
        try {
            addr = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }//获得本机IP
        String hk = env.getProperty("hk");
        String uri = "http://" + addr + ":" + port;
        String url = "http://" + hk + "/services/ICommonService?wsdl";
        String method = "registerHttpService";
        log.info("海康注册推送服务调用,参数：{}", uri);
        String[] args = new String[4];
        // serviceName:服务名(1.报警推送地址,2.设备状态推送地址,3.设备增删改推送地址,4.操作日志推送地址,5.脸谱报警信息推送地址)(必传)
        args[0] = "1";
        // serviceAddr:对应服务地址(必传)
        args[1] = uri + "/video/postAlarm";
        // used:是否启用此地址(默认启用,可以不传)(1为启用,0为不启用)
        // args[2] = "1";
        // serviceObligate:保留字段
        // args[3] = "";
        String resultData = WebService.service(url, method, args);

        // serviceName:服务名(1.报警推送地址,2.设备状态推送地址,3.设备增删改推送地址,4.操作日志推送地址,5.脸谱报警信息推送地址)(必传)
        args[0] = "2";
        // serviceAddr:对应服务地址(必传)
        args[1] = uri + "/video/postStatus";
        // used:是否启用此地址(默认启用,可以不传)(1为启用,0为不启用)
        // args[2] = "1";
        // serviceObligate:保留字段
        // args[3] = "";
        resultData = WebService.service(url, method, args);

        // serviceName:服务名(1.报警推送地址,2.设备状态推送地址,3.设备增删改推送地址,4.操作日志推送地址,5.脸谱报警信息推送地址)(必传)
        args[0] = "3";
        // serviceAddr:对应服务地址(必传)
        args[1] = uri + "/video/postTerminal";
        // used:是否启用此地址(默认启用,可以不传)(1为启用,0为不启用)
        // args[2] = "1";
        // serviceObligate:保留字段
        // args[3] = "";
        resultData = WebService.service(url, method, args);

        // serviceName:服务名(1.报警推送地址,2.设备状态推送地址,3.设备增删改推送地址,4.操作日志推送地址,5.脸谱报警信息推送地址)(必传)
        args[0] = "4";
        // serviceAddr:对应服务地址(必传)
        args[1] = uri + "/video/postLog";
        // used:是否启用此地址(默认启用,可以不传)(1为启用,0为不启用)
        // args[2] = "1";
        // serviceObligate:保留字段
        // args[3] = "";
        resultData = WebService.service(url, method, args);

        // serviceName:服务名(1.报警推送地址,2.设备状态推送地址,3.设备增删改推送地址,4.操作日志推送地址,5.脸谱报警信息推送地址)(必传)
        args[0] = "5";
        // serviceAddr:对应服务地址(必传)
        args[1] = uri + "/video/postFace";
        // used:是否启用此地址(默认启用,可以不传)(1为启用,0为不启用)
        // args[2] = "1";
        // serviceObligate:保留字段
        // args[3] = "";
        resultData = WebService.service(url, method, args);

        // serviceName:服务名(1.报警推送地址,2.设备状态推送地址,3.设备增删改推送地址,4.操作日志推送地址,5.脸谱报警信息推送地址,6、行为分析报警图片)(必传)
        args[0] = "6";
        // serviceAddr:对应服务地址(必传)
        args[1] = uri + "/video/postAlarmPicture";
        // used:是否启用此地址(默认启用,可以不传)(1为启用,0为不启用)
        // args[2] = "1";
        // serviceObligate:保留字段
        // args[3] = "";
        resultData = WebService.service(url, method, args);
        return resultData;
    }

    // hik摄像code转换
    @RequestMapping(value = "/transform", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public String transformCustomIndexCode(String customIndexCode) {
        log.info("海康转换编码调用,参数：{}", customIndexCode);
        String hk = env.getProperty("hk");
        String url = "http://" + hk + "/services/ICommonService?wsdl";
        String method = "transformCustomIndexCode";
        String[] args = new String[2];
        // 参数: indexCode:（监控点编号）customIndexCode:（自定义编号）
        args[0] = "";
        args[1] = customIndexCode;
        String resultData = WebService.service(url, method, args);
        return resultData;
    }

    // 报警信息
    @RequestMapping(value = "/postAlarmPicture", method = RequestMethod.POST)
    @ResponseBody
    public String alarmPicture(String data) {
        log.info("海康报警图片推送调用,参数：{}", data);
        String resultData = "0";
        if (data != null && !"".equals(data.trim())) {
            data = HttpUtil.unicode(data);
            log.info(data);
            AlarmPictureDTO alarm = JsonUtil.jsonObjToObject(data, AlarmPictureDTO.class);
            if (alarm.getTriggerRet() != -1) {
                YjPolice yjPolice = new YjPolice();
                yjPolice.setEventid(alarm.getEventId());
                yjPolice = yjPoliceService.selectData(yjPolice);
                if (yjPolice.getHandlefile() != null) return "1";
                yjPolice.setHandlefile(alarm.getHandleFile());
                yjPoliceService.updateData(yjPolice);
                VideoAlarmDTO al = new VideoAlarmDTO();
                al.change(yjPolice);
                System.out.println(JsonUtil.toJson(al));
                PlantDict plantDict = new PlantDict();
                String name = "bj";
                Integer type = 4;
                plantDict.setName(name);
                plantDict.setType(type);
                //String bjUrl = "http://192.168.1.122:8080/jyjsecurity/api/plant?wsdl";
                String bjUrl = yjPoliceService.selectUrl(plantDict);
                String method = "sendMessage";
                data = "{'type':4,'code':'" + JsonUtil.toJson(al) + "'}";
                resultData = WebService.service(bjUrl, method, data);
            }
        } else {
            resultData = "1";
        }
        return resultData;
    }

    // 报警信息
    @RequestMapping(value = "/postAlarm", method = RequestMethod.POST)
    @ResponseBody
    public String alarmMeg(String data) {
        log.info("海康报警推送调用,参数：{}", data);
        String resultData = "0";
        if (data != null && !"".equals(data.trim())) {
           data = HttpUtil.unicode(data);
            log.info(data);
            VideoAlarm alarm = JsonUtil.jsonObjToObject(data, VideoAlarm.class);
            insertData(alarm);
        } else {
            resultData = "1";
        }
        return resultData;
    }

    // 运行信息
    @RequestMapping(value = "/postStatus", method = RequestMethod.POST)
    @ResponseBody
    public String statusMeg(String data) {
        log.info("海康运行信息推送调用,参数：{}", data);
        String resultData = "0";
        if (data != null && !"".equals(data.trim())) {
            data = HttpUtil.unicode(data);
            log.info(data);
            String cameraInfos = JsonUtil.getStringFromJsonByKeyE(data, "cameraInfos");
            VideoStatus vs;
            if (cameraInfos != null) {
                vs = JsonUtil.jsonObjToObject(cameraInfos.replaceAll("\\[|\\]", ""), VideoStatus.class);
            } else {
                vs = JsonUtil.jsonObjToObject(data, VideoStatus.class);
            }
            PlantRunningStatus status = VideoStatus.change(vs);
            plantPerimeterService.insertDataStatus(status);
        } else {
            resultData = "1";
        }
        return resultData;
    }

    // 日志信息
    @RequestMapping(value = "/postLog", method = RequestMethod.POST)
    @ResponseBody
    public String logMeg(String data) {
        log.info("海康日志推送调用,参数：{}", data);
        String resultData = "0";
        if (data != null && !"".equals(data.trim())) {
            data = HttpUtil.unicode(data);
            log.info(data);
            VideoLog log = JsonUtil.jsonObjToObject(data, VideoLog.class);
            YjOperLog yjLog = VideoLog.change(log);
            yjPoliceService.insertDataLog(yjLog);
        } else {
            resultData = "1";
        }
        return resultData;
    }

    // 详细信息
    @RequestMapping(value = "/postTerminal", method = RequestMethod.POST)
    @ResponseBody
    public String terminalMeg(String data) {
        log.info("海康设备信息推送调用,参数：{}", data);
        String resultData = "0";
        if (data != null && !"".equals(data.trim())) {
            data = HttpUtil.unicode(data);
            log.info(data);
            VideoTerminal video = JsonUtil.jsonObjToObject(data,
                    VideoTerminal.class);
            // 添加设备详细信息
            insertTerminalData(video);
        } else {
            resultData = "1";
        }
        return resultData;
    }
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    // 人脸信息(暂时无法使用)
    @RequestMapping(value = "/postFace", method = RequestMethod.POST)
    @ResponseBody
    public String faceMeg(String data) {
        log.info("海康人脸推送调用,参数：{}", data);
        String resultData = "0";
        rwl.readLock().lock();
        try {
            if (data != null && !"".equals(data.trim())) {
                data = HttpUtil.unicode(data);
                log.info(data);
                //String str ="{\"photo_url1\":\"\",\"alarm_type\":\"whitealarm\"}";
                String whitealarm = JsonUtil.getStringFromJsonByKey(data, "alarm_type");
                YjFace face = new YjFace();
                String prison = env.getProperty("prison");
                face.setPrison_id(prison);
                String identifyTime = JsonUtil.getStringFromJsonByKey(data, "identify_time");
                if (identifyTime != null) {
                    identifyTime = identifyTime.substring(0, identifyTime.length() - 4);
                }
                int isYjFace = plantPerimeterService.findFaceByTime(identifyTime);
                if (isYjFace == 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date time = null;
                    try {
                        time = sdf.parse(identifyTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (whitealarm.equals("whitealarm")) {
                        //face.setAlarm_type(whitealarm);
                        String photo_url1 = JsonUtil.getStringFromJsonByKey(data, "photo_url1");
                        face.setPhoto_url1(photo_url1);
                        face.setType(1);
                        face.setIdentify_time(time);
                        face.setIs_alarm(0);
                        plantPerimeterService.insertDataTerminal(face);
                    } else {
                        YjFaceDTO face1 = JsonUtil.jsonObjToObject(data, YjFaceDTO.class);
                        face.setPhoto_url1(face1.getPhoto_url1());
                        face.setPhoto_url2(face1.getPhoto_url2());
                        face.setIdentify_time(time);
                        face.setIc_card(face1.getIc_card());
                        face.setSimilarity(face1.getSimilarity());
                        face.setName(face1.getName());
                        face.setType(2);
                        face.setIs_alarm(1);
                        plantPerimeterService.insertDataTerminal(face);
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
                }
            } else {
                resultData = "1";
            }
        }finally {
            rwl.readLock().unlock();
        }
        return resultData;
    }

    // 添加报警信息
    public void insertData(VideoAlarm alarm) {
        String prison = env.getProperty("prison");
        YjPolice yjPolice = new YjPolice();
        Date date = null;
        try {
            String time = alarm.getEventTime();
            time = time.split(",")[0];
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        yjPolice.setEquipmentCode(alarm.getSourceIndexCode());
        yjPolice.setAlarmTime(date);
        yjPolice.setAlarmType(String.valueOf(alarm.getEventType()));
        yjPolice.setAlarmReason(alarm.getExtendInfo());
        yjPolice.setEquipmentType(alarm.getSourceName());
        yjPolice.setDataResource("02");
        yjPolice.setPrison(prison);
        yjPolice.setEventid(alarm.getEventId());
        yjPoliceService.insertData(yjPolice);
    }

    // 添加设备详细信息
    public void insertTerminalData(VideoTerminal terminal) {
        //操作类型 1.add,2.update,3.del
        if (terminal.getOperateType() == 3) {
            plantPerimeterService.deleteDataTerminal(terminal.getDeviceIndexCode());
        } else {
            String regionId = plantPerimeterService.selectRegionIdByName(terminal
                    .getDeviceName());
            if (regionId != null) {
                PlantPerimeter perimeter = plantPerimeterService.findData(terminal.getDeviceIndexCode());
                perimeter.setName(terminal.getDeviceName());
                perimeter.setCode(terminal.getDeviceIndexCode());
                perimeter.setInTime(terminal.getUpdateTime());
                perimeter.setRegionId(regionId);
                perimeter.setPort(String.valueOf(terminal.getDevicePort()));
                perimeter.setAddressUrl(terminal.getDeviceIP());
                perimeter.setUserName(terminal.getOperator());
                plantPerimeterService.insertDataTerminal(perimeter);
            }
        }
    }

    // xml
    @RequestMapping(value = "/xml", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public String xml(String prison, String code) {
        log.info("海康免登录xml调用,参数[监狱：{},编码：{}]", prison, code);
        String hk = env.getProperty("hk");
        // 获取vmcc
        PlantDict plantDict = new PlantDict();
        String name = "vmcc";
        Integer type = 4;
        plantDict.setName(name);
        plantDict.setType(type);
        String vmcc = yjPoliceService.selectUrl(plantDict);
        String url = "http://" + hk + "/services/ServiceWebService?wsdl";
        String method = "getStreamInfo";
        String resultData = WebService.service2(url, method, code, hk, vmcc);
        try {
            resultData = XMLDom.fittingXML(resultData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    // token
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public String token(String prison) throws Exception {
        log.info("海康免登录token调用,参数[监狱：{}]", prison);
        if (prison == null || prison.equals(""))
            prison = env.getProperty("prison");
        PlantFactory plantFactory = plantFactoryService
                .findPlantFactoryData(prison);
        String hk = env.getProperty("hk");
        String url = "http://" + hk + "/services/IAuthService?wsdl";
        String method = "login";
        String resultData = null;
        String userName = plantFactory.getUsername();
        String password = SHA256.getSHA256StrJava(plantFactory.getPassword());
        resultData = WebService.service(url, method, userName, password, hk, "", "");

        DocumentBuilderFactory docbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docb = docbf.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(resultData.getBytes());
        Document doc = docb.parse(is);
        NodeList list = doc.getElementsByTagName("row");
        Element node = (Element) list.item(0);
        String st = null;
        if (node != null && node.hasAttribute("tgc")) {
            String tgc = node.getAttribute("tgc");
//			url = "http://10.26.35.253/services/IAuthService?wsdl";
            method = "applyToken";

            resultData = WebService.service(url, method, tgc);
            is = new ByteArrayInputStream(resultData.getBytes());
            doc = docb.parse(is);
            list = doc.getElementsByTagName("row");
            node = (Element) list.item(0);
            st = node.getAttribute("st");
        }
        return st;
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    @ResponseBody
    public String insert() {
        log.info("大华视频信息存库调用");
        try {
            String filename = env.getProperty("dh");
            String prison = env.getProperty("prison");
            plantPerimeterService.insertPlantPerimeterDH(filename, prison);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/es", method = RequestMethod.POST)
    @ResponseBody
    public String es(String data) {
        log.info("大华视频信息写文件调用,参数:{}", data);
        System.out.println(data);
        String filename = env.getProperty("dh");
        writeNIO(data, filename);
        return null;
    }

    static void writeNIO(String str, String filename) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filename));
            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode(str);
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的  
            System.out.println("初始化容量和limit：" + src.capacity() + ","
                    + src.limit());
            int length = 0;

            while ((length = channel.write(src)) != 0) {  
                /*  
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读  
                 */
                System.out.println("写入长度:" + length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
