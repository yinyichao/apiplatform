package com.ip.founder.apiplatform.dao.perimeter;

import com.ip.founder.apiplatform.pojo.YjFace;

public interface YjFaceMapper {
    //添加报警信息
    void insertData(YjFace yjFace);
    int findFaceByTime(String time);
}
