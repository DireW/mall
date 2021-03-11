package com.macro.mall.service.oms;

import com.macro.mall.bo.SystemDictionary;
import com.macro.mall.mapper.OmsAgencyOrderProcessMapper;
import com.macro.mall.mapper.OmsAgencyOrderProcessSettingMapper;
import com.macro.mall.model.OmsAgencyOrderProcess;
import com.macro.mall.model.OmsAgencyOrderProcessSetting;
import com.macro.mall.model.OmsProcess;
import com.macro.mall.model.OmsProcessSetting;
import com.macro.mall.utils.BasicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderProcessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessService.class);

    @Autowired
    OmsAgencyOrderProcessMapper orderProcessMapper;
    @Autowired
    ProcessSettingService processSettingService;

    @Autowired
    ProcessService processService;
    @Autowired
    OrderProcessSettingService orderProcessSettingService;

    @Transactional
    public int saveOrderProcess(Long orderId, Long processId, Boolean enabled) {
        LOGGER.info("开始构建订单流程和流程配置，订单id：【{}】，流程id：【{}】，启用状态：【{}】", orderId, processId, enabled);
        Date now = new Date();
        OmsProcess omsProcess = processService.findById(processId);
        Integer sortNumber = omsProcess.getSortNumber();
        OmsAgencyOrderProcess agencyOrderProcess = new OmsAgencyOrderProcess();
        agencyOrderProcess.setEnabled(enabled);
        agencyOrderProcess.setName(omsProcess.getName());
        agencyOrderProcess.setCreatedTime(now);
        agencyOrderProcess.setUpdatedTime(now);
        agencyOrderProcess.setOrderId(orderId);
        agencyOrderProcess.setProcessId(processId);
        agencyOrderProcess.setSortNumber(sortNumber);
        if (sortNumber == 1) {
            agencyOrderProcess.setStatus(SystemDictionary.PROCESS_ON);
        } else {
            agencyOrderProcess.setStatus(SystemDictionary.PROCESS_NOT_START);
        }
        int result = orderProcessMapper.insert(agencyOrderProcess);

        Long orderProcessId = agencyOrderProcess.getId();

        List<OmsProcessSetting> processSettingList = processSettingService.listOfProcess(processId);
        processSettingList.forEach(omsProcessSetting -> {
            OmsAgencyOrderProcessSetting orderProcessSetting = new OmsAgencyOrderProcessSetting();
            orderProcessSetting.setCreatedTime(now);
            orderProcessSetting.setUpdatedTime(now);
            orderProcessSetting.setDeleted(false);
            orderProcessSetting.setOrderId(orderId);
            orderProcessSetting.setOrderProcessId(orderProcessId);
            orderProcessSetting.setFieldName(omsProcessSetting.getFieldName());
            orderProcessSetting.setFieldCnName(omsProcessSetting.getFieldCnName());
            orderProcessSetting.setFieldType(omsProcessSetting.getFieldType());
            orderProcessSetting.setEnumArrayJson(omsProcessSetting.getEnumArrayJson());
            orderProcessSettingService.insert(orderProcessSetting);
        });

        return result;
    }

}
