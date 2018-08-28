package com.ip.founder.apiplatform.dao.perimeter;

import com.ip.founder.apiplatform.pojo.PlantFactory;

import java.util.List;

public interface PlantFactoryMapper {
    //查询所有厂家
    List<PlantFactory> findAllFactory();

    //查询调用视频所需数据
    PlantFactory findPlantFactoryData(String prison);

    void update(String oldId, String newId);
}