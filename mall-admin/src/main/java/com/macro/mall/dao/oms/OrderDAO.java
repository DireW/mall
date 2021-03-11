package com.macro.mall.dao.oms;

import org.apache.ibatis.annotations.Param;

public interface OrderDAO {

    void findByUserId(@Param("userId") Long userId);

}
