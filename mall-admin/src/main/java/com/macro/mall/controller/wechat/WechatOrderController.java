package com.macro.mall.controller.wechat;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.oms.AgencyOrderDTO;
import com.macro.mall.model.OmsAgencyOrder;
import com.macro.mall.model.OmsProcess;
import com.macro.mall.service.oms.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wechat/oms/order")
public class WechatOrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/process-list")
    public CommonResult<List<OmsProcess>> getAllProcess() {
        return CommonResult.success(orderService.getAllProcess());
    }

    @PostMapping("/create")
    public CommonResult<Integer> createOrder(@RequestBody AgencyOrderDTO agencyOrderDTO) {
        return CommonResult.success(orderService.createOrder(agencyOrderDTO));
    }

    @GetMapping("/my-order")
    public CommonResult<List<AgencyOrderDTO>> findCurrentUserOrders() {
        return CommonResult.success(null);
    }
}
