package com.macro.mall.dto.oms;

import com.macro.mall.bo.oms.OrderProcessBO;
import com.macro.mall.model.OmsProcess;

import java.util.Date;
import java.util.List;

public class AgencyOrderDTO {

    private Long id;

    private String name;

    private Long principalId;

    private String principalName;

    private Long departmentId;

    private String departmentName;

    private Long areaId;

    private String areaName;

    private Date createdTime;

    private List<OrderProcessBO> orderProcessBOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public List<OrderProcessBO> getOrderProcessBOList() {
        return orderProcessBOList;
    }

    public void setOrderProcessBOList(List<OrderProcessBO> orderProcessBOList) {
        this.orderProcessBOList = orderProcessBOList;
    }
}
