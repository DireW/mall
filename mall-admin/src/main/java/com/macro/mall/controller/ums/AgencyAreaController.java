package com.macro.mall.controller.ums;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.ums.AgencyAreaDTO;
import com.macro.mall.model.UmsAgencyArea;
import com.macro.mall.service.ums.AgencyAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "AgencyAreaController", description = "区域管理")
@RestController
@RequestMapping("/admin/ums/agency/area")
public class AgencyAreaController {

    @Autowired
    AgencyAreaService agencyAreaService;

    @ApiOperation(value = "获取全部区域信息")
    @GetMapping("/list")
    public CommonResult<List<AgencyAreaDTO>> list() {
        return CommonResult.success(agencyAreaService.fetchAllArea());
    }

    @ApiOperation(value = "保存或者更新区域信息")
    @PostMapping("/save-update")
    public CommonResult saveOrUpdate(@RequestBody UmsAgencyArea agencyArea) {
        int count = agencyAreaService.saveOrUpdate(agencyArea);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "删除指定的区域信息")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id")Long id) {
        int count = agencyAreaService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
