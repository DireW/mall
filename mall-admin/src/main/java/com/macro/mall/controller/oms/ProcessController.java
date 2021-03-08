package com.macro.mall.controller.oms;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.OmsProcess;
import com.macro.mall.service.oms.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "ProcessController", description = "流程管理")
@RestController
@RequestMapping("/oms/process")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @ApiOperation("获取所有的流程，按照顺序显示")
    @GetMapping("/all")
    public CommonResult<List<OmsProcess>> allProcess() {
        return CommonResult.success(processService.getAll());
    }

    @ApiOperation("保存流程信息")
    @PostMapping("/save")
    public CommonResult<Integer> save(@RequestBody OmsProcess process) {
        int count = processService.save(process);
        if (count == 0) {
            return CommonResult.failed("保存失败");
        }
        return CommonResult.success(count);
    }

    @ApiOperation("更新流程信息")
    @PostMapping("/update")
    public CommonResult<Integer> update(@RequestBody OmsProcess process) {
        int count = processService.update(process);
        if (count == 0) {
            return CommonResult.failed("更新失败");
        }
        return CommonResult.success(count);
    }

    @ApiOperation("更新流程信息")
    @DeleteMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = processService.delete(id);
        if (count == 0) {
            return CommonResult.failed("保存失败");
        }
        return CommonResult.success(count);
    }

    @ApiOperation("更新流程信息")
    @PostMapping("/update-sort")
    public CommonResult<Integer> updateSortNumber(@RequestBody List<OmsProcess> process) {
        int count = processService.updateSort(process);
        if (count == 0) {
            return CommonResult.failed("更新排序失败");
        }
        return CommonResult.success(count);
    }

}
