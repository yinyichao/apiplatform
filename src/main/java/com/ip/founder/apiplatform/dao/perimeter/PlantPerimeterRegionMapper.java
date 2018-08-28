package com.ip.founder.apiplatform.dao.perimeter;

import com.ip.founder.apiplatform.pojo.PlantPerimeterRegion;

import java.util.List;

public interface PlantPerimeterRegionMapper {
    void updateVideoRegion(String oldId, String newId);

    List<PlantPerimeterRegion> findAllVideoRegion();

    void updateVideoRegionP(String oldId, String newId);

    void delete();
}