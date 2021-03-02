package com.macro.mall.dao.ums;

import com.macro.mall.dto.OmsOrderDeliveryParam;
import com.macro.mall.dto.OmsOrderDetail;
import com.macro.mall.dto.OmsOrderQueryParam;
import com.macro.mall.dto.ums.AgencyAreaDTO;
import com.macro.mall.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域自定义查询Dao
 * Created by DireW
 */
public interface AgencyAreaDAO {

    List<AgencyAreaDTO> getList();


}
