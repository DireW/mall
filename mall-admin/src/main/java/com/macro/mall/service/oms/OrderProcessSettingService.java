package com.macro.mall.service.oms;

import com.macro.mall.mapper.OmsAgencyOrderProcessSettingMapper;
import com.macro.mall.model.OmsAgencyOrderProcessSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessSettingService {

    @Autowired
    OmsAgencyOrderProcessSettingMapper orderProcessSettingMapper;

    public int insert(OmsAgencyOrderProcessSetting orderProcessSetting) {
        return orderProcessSettingMapper.insert(orderProcessSetting);
    }

}
