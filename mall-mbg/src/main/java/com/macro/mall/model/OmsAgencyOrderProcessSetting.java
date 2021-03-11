package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class OmsAgencyOrderProcessSetting implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单中对应步骤的id，用来分组哪些配置是这个步骤使用")
    private Long orderProcessId;

    @ApiModelProperty(value = "字段名称，默认column+序号")
    private String fieldName;

    @ApiModelProperty(value = "字段中文名")
    private String fieldCnName;

    @ApiModelProperty(value = "字段值")
    private String fieldValue;

    @ApiModelProperty(value = "字段类型：0-文字，1-金额，2-选项，3-日期，4-日期时间")
    private Integer fieldType;

    @ApiModelProperty(value = "字段选项用到的枚举类数组")
    private String enumArrayJson;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderProcessId() {
        return orderProcessId;
    }

    public void setOrderProcessId(Long orderProcessId) {
        this.orderProcessId = orderProcessId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldCnName() {
        return fieldCnName;
    }

    public void setFieldCnName(String fieldCnName) {
        this.fieldCnName = fieldCnName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getEnumArrayJson() {
        return enumArrayJson;
    }

    public void setEnumArrayJson(String enumArrayJson) {
        this.enumArrayJson = enumArrayJson;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderProcessId=").append(orderProcessId);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", fieldCnName=").append(fieldCnName);
        sb.append(", fieldValue=").append(fieldValue);
        sb.append(", fieldType=").append(fieldType);
        sb.append(", enumArrayJson=").append(enumArrayJson);
        sb.append(", deleted=").append(deleted);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}