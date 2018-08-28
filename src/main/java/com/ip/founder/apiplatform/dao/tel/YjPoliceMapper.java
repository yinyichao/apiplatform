package com.ip.founder.apiplatform.dao.tel;

import com.ip.founder.apiplatform.pojo.YjPolice;

public interface YjPoliceMapper {
    //添加报警信息
    void insertData(YjPolice yjPolice);

    void updateData(YjPolice yjPolice);

    YjPolice selectData(String e);
}
