package com.macro.mall.controller.oms;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.OmsProcessSetting;
import com.macro.mall.service.oms.ProcessSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oms/process-setting")
public class ProcessSettingController {

    @Autowired
    private ProcessSettingService processSettingService;

    @GetMapping("/list-of-process/{processId}")
    public CommonResult<List<OmsProcessSetting>> listOfProcess(@PathVariable Long processId) {
        return CommonResult.success(processSettingService.listOfProcess(processId));
    }

    @PostMapping("/update/{processId}")
    public CommonResult<Boolean> saveOrUpdateSettings(@PathVariable Long processId, @RequestBody List<OmsProcessSetting> processSettingList) {
        Boolean result = processSettingService.saveOrUpdateSettings(processId, processSettingList);
        return result ? CommonResult.success(true) : CommonResult.failed("更新失败");
    }

}
