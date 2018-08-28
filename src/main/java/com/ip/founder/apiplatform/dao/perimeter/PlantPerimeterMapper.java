package com.ip.founder.apiplatform.dao.perimeter;

import com.ip.founder.apiplatform.pojo.*;

import java.util.List;


public interface PlantPerimeterMapper {
    //查询摄像概览
    List<View> findCameraView(Pager<View> pager);

    String findCameraName(String code);

    //更新设备状态
    void updateData(PlantRunningStatus plantRunningStatus);

    void insertData(PlantPerimeter plantPerimeter);

    //删除设备信息
    void deleteData(String code);

    //查询RegionId
    String selectRegionIdByName(String name);

    PlantPerimeter findData(String code);

    //通过方正编码查询海康摄像头ip
    String findAddressUrlByRemark(String code);

    //通过方正编码查询海康编码
    String findCodeByRemark(String code);

    void insertPlantPerimeterRegion(PlantPerimeterRegion plantPerimeterRegion);

    void insertCamera(PlantPerimeter plantPerimeter);

    void updateVideo(String oldId, String newId, String regid);

    List<PlantPerimeter> findAllVideo();

    void updateDelete(String id);

    void updatePlantPerimeter(PlantPerimeter plantPerimeter);

    List<PlantPerimeter> findAllVideoNotNull();
}