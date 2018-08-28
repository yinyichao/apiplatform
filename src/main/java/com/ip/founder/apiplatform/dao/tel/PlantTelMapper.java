package com.ip.founder.apiplatform.dao.tel;

import com.ip.founder.apiplatform.pojo.PlantRunningStatus;
import com.ip.founder.apiplatform.pojo.PlantTel;

import java.util.List;

public interface PlantTelMapper {
    //更新设备状态
    void updateData(PlantRunningStatus plantRunningStatus);

    //添加设备信息
    void insertData(PlantTel plantTel);

    //删除设备信息
    void deleteData(String code);

    PlantTel findData(String code);

    void updateTel(String oldId, String newId, String regid);

    List<PlantTel> findAllTel();
}
