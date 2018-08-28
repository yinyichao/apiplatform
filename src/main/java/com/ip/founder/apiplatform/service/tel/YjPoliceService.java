package com.ip.founder.apiplatform.service.tel;

import com.ip.founder.apiplatform.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ip.founder.apiplatform.dao.tel.PlantDictMapper;
import com.ip.founder.apiplatform.dao.tel.PlantRunningStatusMapper;
import com.ip.founder.apiplatform.dao.tel.PlantTelMapper;
import com.ip.founder.apiplatform.dao.tel.PlantTelRegionMapper;
import com.ip.founder.apiplatform.dao.tel.YjCallLogMapper;
import com.ip.founder.apiplatform.dao.tel.YjOperLogMapper;
import com.ip.founder.apiplatform.dao.tel.YjPoliceMapper;

import java.util.List;

@Component
@Transactional
public class YjPoliceService {

    protected YjPoliceMapper yjPoliceMapper;

    protected PlantRunningStatusMapper plantRunningStatusMapper;

    protected PlantTelMapper plantTelMapper;

    protected YjCallLogMapper yjCallLogMapper;

    protected YjOperLogMapper yjOperLogMapper;

    protected PlantTelRegionMapper plantTelRegionMapper;

    protected PlantDictMapper plantDictMapper;
    @Autowired
    public void setYjPoliceMapper(YjPoliceMapper yjPoliceMapper) {
        this.yjPoliceMapper = yjPoliceMapper;
    }
    @Autowired
    public void setPlantRunningStatusMapper(PlantRunningStatusMapper plantRunningStatusMapper) {
        this.plantRunningStatusMapper = plantRunningStatusMapper;
    }
    @Autowired
    public void setPlantTelMapper(PlantTelMapper plantTelMapper) {
        this.plantTelMapper = plantTelMapper;
    }
    @Autowired
    public void setYjCallLogMapper(YjCallLogMapper yjCallLogMapper) {
        this.yjCallLogMapper = yjCallLogMapper;
    }
    @Autowired
    public void setYjOperLogMapper(YjOperLogMapper yjOperLogMapper) {
        this.yjOperLogMapper = yjOperLogMapper;
    }
    @Autowired
    public void setPlantTelRegionMapper(PlantTelRegionMapper plantTelRegionMapper) {
        this.plantTelRegionMapper = plantTelRegionMapper;
    }
    @Autowired
    public void setPlantDictMapper(PlantDictMapper plantDictMapper) {
        this.plantDictMapper = plantDictMapper;
    }

    //添加报警信息
    public void insertData(YjPolice yjPolice) {
        YjPolice yjPolice1 = null;
        if (yjPolice.getEventid() != null) {
            yjPolice1 = yjPoliceMapper.selectData(yjPolice.getEventid());
        }
        if (yjPolice1 == null)
            yjPoliceMapper.insertData(yjPolice);
    }

    @Transactional(readOnly = true)
    public YjPolice selectData(YjPolice yjPolice) {
        return yjPoliceMapper.selectData(yjPolice.getEventid());
    }

    //添加状态信息
    public void insertDataStatus(PlantRunningStatus plantRunningStatus) {
        plantRunningStatusMapper.insertData(plantRunningStatus);
        plantTelMapper.updateData(plantRunningStatus);
    }

    //添加日志信息
    public void insertDataLog(YjOperLog yjOperLog) {
        yjOperLogMapper.insertData(yjOperLog);
    }

    //添加通话信息
    public void insertDataCallRecord(YjCallLog yjCallLog) {
        yjCallLogMapper.insertData(yjCallLog);
    }

    //添加组织结构
    public void insertDataOrg(PlantTelRegion plantTelRegion) {
        plantTelRegionMapper.insertData(plantTelRegion);
    }

    //删除组织结构
    public void deleteDataOrg(String code) {
        plantTelRegionMapper.deleteData(code);
    }

    //添加设备详细信息
    public void insertDataTerminal(PlantTel plantTel) {
        plantTelMapper.insertData(plantTel);
    }

    //删除设备信息
    public void deleteDataTerminal(String code) {
        plantTelMapper.deleteData(code);
    }

    //查询RegionId
    @Transactional(readOnly = true)
    public String selectRegionIdByName(String name) {
        return plantTelRegionMapper.selectRegionIdByName(name);

    }

    //查询网址信息
    @Transactional(readOnly = true)
    public String selectUrl(PlantDict plantDict) {
        return plantDictMapper.selectUrl(plantDict);
    }

    @Transactional(readOnly = true)
    public PlantTel findData(String code) {
        return plantTelMapper.findData(code);
    }

    public void updateTel(String oldId, String newId, String regid) {
        plantTelMapper.updateTel(oldId, newId, regid);
    }

    public void updateTelRegion(String oldId, String newId) {
        plantTelRegionMapper.updateTelRegion(oldId, newId);
    }

    @Transactional(readOnly = true)
    public List<PlantTel> findAllTel() {
        return plantTelMapper.findAllTel();
    }

    @Transactional(readOnly = true)
    public List<PlantTelRegion> findAllTelRegion() {
        return plantTelRegionMapper.findAllTelRegion();
    }

    public void updateData(YjPolice yjPolice) {
        yjPoliceMapper.updateData(yjPolice);
    }
}
