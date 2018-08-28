package com.ip.founder.apiplatform.service.perimeter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ip.founder.apiplatform.dao.perimeter.PlantFactoryMapper;
import com.ip.founder.apiplatform.pojo.PlantFactory;

@Component
public class PlantFactoryService {

    private PlantFactoryMapper plantFactoryMapper;

    @Autowired
    public void setPlantFactoryMapper(PlantFactoryMapper plantFactoryMapper) {
        this.plantFactoryMapper = plantFactoryMapper;
    }

    //所有厂家集合
    @Transactional(readOnly = true)
    public List<PlantFactory> findAllFactory() {
        return plantFactoryMapper.findAllFactory();
    }

    //查询调用视频所需数据
    @Transactional(readOnly = true)
    public PlantFactory findPlantFactoryData(String prison) {
        return plantFactoryMapper.findPlantFactoryData(prison);
    }

    @Transactional
    public void updateFactory(String oldId, String newId) {
        plantFactoryMapper.update(oldId, newId);
    }
}
