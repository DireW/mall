package com.macro.mall.service.oms;

import cn.hutool.db.sql.Order;
import com.macro.mall.common.exception.ApiException;
import com.macro.mall.dto.oms.AgencyOrderDTO;
import com.macro.mall.dto.ums.UmsAdminDTO;
import com.macro.mall.mapper.OmsAgencyOrderMapper;
import com.macro.mall.mapper.OmsProcessMapper;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.OmsAgencyOrder;
import com.macro.mall.model.OmsAgencyOrderProcess;
import com.macro.mall.model.OmsProcess;
import com.macro.mall.model.OmsProcessExample;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.service.ums.UmsAdminService;
import com.macro.mall.utils.BasicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OmsProcessMapper processMapper;
    @Autowired
    OmsAgencyOrderMapper agencyOrderMapper;
    @Autowired
    OrderProcessService orderProcessService;
    @Autowired
    UmsAdminService adminService;



    public List<OmsProcess> getAllProcess() {
        OmsProcessExample processExample = new OmsProcessExample();
        processExample.createCriteria().andDeletedEqualTo(false);
        return processMapper.selectByExample(processExample);
    }

    @Transactional
    public int createOrder(AgencyOrderDTO agencyOrderDTO) {
        Long userId = BasicUtil.getCurrentUserId();
        Date now = new Date();
        LOGGER.info("用户【{}】开始创建订单，订单名称：【{}】", userId, agencyOrderDTO.getName());
        UmsAdminDTO umsAdminDTO = adminService.getFullInfo(userId);
        OmsAgencyOrder agencyOrder = new OmsAgencyOrder();
        agencyOrder.setPrincipalId(userId);
        agencyOrder.setName(agencyOrderDTO.getName());
        agencyOrder.setDepartmentId(umsAdminDTO.getDepartmentId());
        agencyOrder.setAreaId(umsAdminDTO.getAreaId());
        agencyOrder.setDeleted(false);
        agencyOrder.setCreatedBy(userId);
        agencyOrder.setCreatedTime(now);
        agencyOrder.setUpdatedBy(userId);
        agencyOrder.setUpdatedTime(now);
        int result = agencyOrderMapper.insert(agencyOrder);

        Long orderId = agencyOrder.getId();
        agencyOrderDTO.getOrderProcessBOList().forEach(orderProcessBO -> {
            orderProcessService.saveOrderProcess(orderId, orderProcessBO.getProcessId(), orderProcessBO.getEnabled());
        });

        return result;
    }

    public int updateOrder(AgencyOrderDTO agencyOrderDTO) {
        Long id = agencyOrderDTO.getId();
        if (id == null) {
            String msg = "非法参数，请传入订单ID进行更新操作";
            LOGGER.info(msg);
            throw new ApiException(msg);
        }
        OmsAgencyOrder omsAgencyOrder = agencyOrderMapper.selectByPrimaryKey(id);
        omsAgencyOrder.setUpdatedBy(BasicUtil.getCurrentUserId());
        omsAgencyOrder.setUpdatedTime(new Date());
        omsAgencyOrder.setName(agencyOrderDTO.getName());
        omsAgencyOrder.setDepartmentId(agencyOrderDTO.getDepartmentId());
        omsAgencyOrder.setAreaId(agencyOrderDTO.getAreaId());
        return agencyOrderMapper.updateByPrimaryKey(omsAgencyOrder);
    }


    public List<AgencyOrderDTO> findCurrentUserOrders() {
        Long userId = BasicUtil.getCurrentUserId();
        return null;
    }
}
