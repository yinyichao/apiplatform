package com.ip.founder.apiplatform.service.perimeter;

import com.ip.founder.apiplatform.dao.perimeter.PlantFactoryMapper;
import com.ip.founder.apiplatform.dao.perimeter.PlantPerimeterMapper;
import com.ip.founder.apiplatform.dao.perimeter.PlantPerimeterRegionMapper;
import com.ip.founder.apiplatform.dao.perimeter.YjFaceMapper;
import com.ip.founder.apiplatform.dao.tel.PlantRunningStatusMapper;
import com.ip.founder.apiplatform.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class PlantPerimeterService {

    private PlantPerimeterMapper plantPerimeterMapper;

    protected PlantRunningStatusMapper plantRunningStatusMapper;

    protected YjFaceMapper yjFaceMapper;

    private PlantPerimeterRegionMapper plantPerimeterRegionMapper;

    private PlantFactoryMapper plantFactoryMapper;

    @Autowired
    public void setPlantPerimeterMapper(PlantPerimeterMapper plantPerimeterMapper) {
        this.plantPerimeterMapper = plantPerimeterMapper;
    }

    @Autowired
    public void setPlantRunningStatusMapper(PlantRunningStatusMapper plantRunningStatusMapper) {
        this.plantRunningStatusMapper = plantRunningStatusMapper;
    }

    @Autowired
    public void setYjFaceMapper(YjFaceMapper yjFaceMapper) {
        this.yjFaceMapper = yjFaceMapper;
    }

    @Autowired
    public void setPlantPerimeterRegionMapper(PlantPerimeterRegionMapper plantPerimeterRegionMapper) {
        this.plantPerimeterRegionMapper = plantPerimeterRegionMapper;
    }

    @Autowired
    public void setPlantFactoryMapper(PlantFactoryMapper plantFactoryMapper) {
        this.plantFactoryMapper = plantFactoryMapper;
    }

    //摄像机概览
    @Transactional(readOnly = true)
    public Pager<View> findCameraView(Pager<View> pager) {
        List<View> cvList = plantPerimeterMapper.findCameraView(pager);
        pager.setResults(cvList);
        return pager;
    }

    @Transactional(readOnly = true)
    public String findCameraName(String code) {
        return plantPerimeterMapper.findCameraName(code);
    }
    //添加状态信息

    public void insertDataStatus(PlantRunningStatus plantRunningStatus) {
        plantRunningStatusMapper.insertData(plantRunningStatus);
        plantPerimeterMapper.updateData(plantRunningStatus);
    }
    //添加设备详细信息

    public void insertDataTerminal(PlantPerimeter plantPerimeter) {
        plantPerimeterMapper.insertData(plantPerimeter);
    }
    //删除设备信息

    public void deleteDataTerminal(String code) {
        plantPerimeterMapper.deleteData(code);
    }

    //查询RegionId
    @Transactional(readOnly = true)
    public String selectRegionIdByName(String name) {
        return plantPerimeterMapper.selectRegionIdByName(name);

    }

    @Transactional(readOnly = true)
    public PlantPerimeter findData(String code) {
        return plantPerimeterMapper.findData(code);
    }

    //通过方正编码查询海康摄像头ip
    @Transactional(readOnly = true)
    public String findAddressUrlByRemark(String code) {
        return plantPerimeterMapper.findAddressUrlByRemark(code);
    }

    //通过方正编码查询海康编码
    @Transactional(readOnly = true)
    public String findCodeByRemark(String code) {
        return plantPerimeterMapper.findCodeByRemark(code);
    }
    //添加设备详细信息

    public void insertDataTerminal(YjFace yjFace) {
        yjFaceMapper.insertData(yjFace);
    }

    public void insertPlantPerimeterDH(String filename, String prison) throws Exception {
        plantPerimeterRegionMapper.delete();
        List<PlantPerimeter> plantPerimeterList = plantPerimeterMapper.findAllVideoNotNull();
        Map<String, PlantPerimeter> map = new HashMap<>();
        for (PlantPerimeter p : plantPerimeterList) {
            map.put(p.getAddressUrl() + p.getTerrace(), p);
        }
        InputStream is = new FileInputStream(filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        NodeList list = doc.getElementsByTagName("Department");
        PlantPerimeterRegion plantPerimeterRegion;
        PlantPerimeter plantPerimeter;
        String terrace = "", addressUrl = "";
        String pid = null;
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);
            plantPerimeterRegion = new PlantPerimeterRegion();
            System.out.println(e.getAttribute("name"));
            plantPerimeterRegion.setName(e.getAttribute("name"));
            plantPerimeterRegion.setSource(1);
            plantPerimeterRegion.setCode(prison);
            String code = e.getAttribute("coding");
            plantPerimeterRegion.setRemarks(code);
            Integer level = 0;
            if (code.length() <= 3) {
                level = 1;
            } else if (code.length() >= 12) {
                level = 3;
            } else {
                level = 2;
            }
            plantPerimeterRegion.setRegionLevel(level.toString());
            plantPerimeterRegion.preInsert();
            if (i == 0) {
                pid = plantPerimeterRegion.getId();
            } else {
                plantPerimeterRegion.setParent_id(pid);
            }
            plantPerimeterMapper.insertPlantPerimeterRegion(plantPerimeterRegion);
            if (level == 3 || (level == 2 && e.getElementsByTagName("Department").getLength() == 0)) {
                NodeList channels = e.getElementsByTagName("Channel");
                in:
                for (int j = 0; j < channels.getLength(); j++) {
                    plantPerimeter = new PlantPerimeter();
                    Element cs = (Element) channels.item(j);
                    PlantFactory plantFactory = plantFactoryMapper.findPlantFactoryData(prison);
                    plantPerimeter.setFid(plantFactory.getId());
                    plantPerimeter.setPid("1");
                    plantPerimeter.setRegionId(plantPerimeterRegion.getId());
                    plantPerimeter.setStatus(0);
                    String remark = cs.getAttribute("id");
                    NodeList hh = doc.getElementsByTagName("Channel");
                    for (int k = 0; k < hh.getLength(); k++) {
                        Element h = (Element) hh.item(k);
                        if (h.getAttribute("id").equals(remark) && h.hasAttribute("name")) {
                            Element n1 = (Element) h.getParentNode();
                            Element n2 = (Element) n1.getParentNode();
                            //terrace = h.getAttribute("channelSN");
                            addressUrl = n2.getAttribute("ip");
                            if (addressUrl == null || addressUrl.equals("")) {
                                continue in;
                            }
                            //System.out.println(h.getAttribute("name"));
                            plantPerimeter.setName(h.getAttribute("name"));
                            plantPerimeter.setLocation(h.getAttribute("name"));
                            String codeDH = h.getAttribute("id");
                            plantPerimeter.setCode(codeDH);
                            plantPerimeter.setRemark(h.getAttribute("cameraNo"));
                            plantPerimeter.setAddressUrl(n2.getAttribute("ip"));
                            String type = h.getAttribute("cameraType");
                            if (type.equals("1")) {
                                type = "0";
                            } else if (type.equals("3")) {
                                type = "1";
                            }
                            plantPerimeter.setType(type);
                            String[] s = codeDH.split("\\$");
                            terrace = s[0] + s[1] + s[2] + s[s.length - 1];
                            plantPerimeter.setTerrace(terrace);
                            PlantPerimeter pp = map.get(addressUrl + terrace);
                            if (pp != null) {
                                plantPerimeter.setId(pp.getId());
                                map.remove(addressUrl + terrace);
                                plantPerimeterMapper.updatePlantPerimeter(plantPerimeter);
                            } else {
                                plantPerimeter.setInTime(new Date());
                                plantPerimeter.preInsert();
                                plantPerimeterMapper.insertCamera(plantPerimeter);
                            }
                        }
                    }
                }
            }
        }
        for (String key : map.keySet()) {
            PlantPerimeter p = map.get(key);
            plantPerimeterMapper.updateDelete(p.getId());
        }
        System.out.println("success");
    }

    public void updateVideo(String oldId, String newId, String regid) {
        plantPerimeterMapper.updateVideo(oldId, newId, regid);
    }

    public void updateVideoRegion(String oldId, String newId) {
        plantPerimeterRegionMapper.updateVideoRegion(oldId, newId);
    }

    public void updateVideoRegionP(String oldId, String newId) {
        plantPerimeterRegionMapper.updateVideoRegionP(oldId, newId);
    }

    @Transactional(readOnly = true)
    public List<PlantPerimeter> findAllVideo() {
        return plantPerimeterMapper.findAllVideo();
    }

    @Transactional(readOnly = true)
    public List<PlantPerimeterRegion> findAllVideoRegion() {
        return plantPerimeterRegionMapper.findAllVideoRegion();
    }

    @Transactional(readOnly = true)
    public int findFaceByTime(String time){
        return yjFaceMapper.findFaceByTime(time);
    }
}
