package com.ip.founder.apiplatform.dao.tel;

import com.ip.founder.apiplatform.pojo.PlantTelRegion;

import java.util.List;

public interface PlantTelRegionMapper {
    //添加组织结构
    void insertData(PlantTelRegion plantTelRegion);

    //删除组织结构
    void deleteData(String code);

    //查询RegionId
    String selectRegionIdByName(String name);

    void updateTelRegion(String oldId, String newId);

    List<PlantTelRegion> findAllTelRegion();
}
