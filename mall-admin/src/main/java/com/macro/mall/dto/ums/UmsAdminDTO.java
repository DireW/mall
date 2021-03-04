package com.macro.mall.dto.ums;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAgencyWechatUser;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 返回到前台的用户信息，包含对应的门店和区域信息
 */
public class UmsAdminDTO {

    private Long id;

    private String username;

    private String avatarUrl;

    private String realName;

    private String idCard;

    private Integer gender;

    private String phoneNumber;

    private String email;

    private String note;

    private Date createTime;

    private Date loginTime;

    private Integer status;
    private Long areaId;
    private String areaName;
    private Long departmentId;
    private String departmentName;
    /**
     * 对应的微信信息
     */
    private UmsAgencyWechatUser wechatUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public UmsAgencyWechatUser getWechatUser() {
        return wechatUser;
    }

    public void setWechatUser(UmsAgencyWechatUser wechatUser) {
        this.wechatUser = wechatUser;
    }
}
