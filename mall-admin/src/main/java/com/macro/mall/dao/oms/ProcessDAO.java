package com.macro.mall.dao.oms;

import org.apache.ibatis.annotations.Param;

public interface ProcessDAO {

    void decreaseBySortNumber(@Param("sortNumber") Integer sortNumber);

}
