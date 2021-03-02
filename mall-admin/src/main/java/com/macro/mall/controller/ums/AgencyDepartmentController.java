package com.macro.mall.controller.ums;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.ums.AgencyAreaDTO;
import com.macro.mall.dto.ums.AgencyDepartmentDTO;
import com.macro.mall.model.UmsAgencyArea;
import com.macro.mall.model.UmsAgencyDepartment;
import com.macro.mall.service.ums.AgencyDepartmentService;
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

@RestController
@RequestMapping("/admin/ums/agency/department")
public class AgencyDepartmentController {

    @Autowired
    AgencyDepartmentService agencyDepartmentService;

    @ApiOperation(value = "根据查询条件获取门店信息")
    @PostMapping("/list")
    public CommonResult<List<AgencyDepartmentDTO>> list(@RequestBody UmsAgencyDepartment agencyDepartment) {
        return CommonResult.success(agencyDepartmentService.findByParams(agencyDepartment));
    }

    @ApiOperation(value = "保存或者更新区域信息")
    @PostMapping("/save-update")
    public CommonResult<Integer> saveOrUpdate(@RequestBody UmsAgencyDepartment agencyDepartment) {
        int count = agencyDepartmentService.saveOrUpdate(agencyDepartment);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "删除指定的区域信息")
    @DeleteMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable("id")Long id) {
        int count = agencyDepartmentService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
