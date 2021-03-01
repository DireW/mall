package com.macro.mall.dto.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class WechatLoginRequest {
    @NotNull(message = "code不能为空")
    @ApiModelProperty(value = "微信code", required = true)
    private String code;
    @ApiModelProperty(value = "头像信息")
    private String avatarUrl;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "国家")
    private String country;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "微信昵称")
    private String nickName;
    @ApiModelProperty(value = "省份")
    private String province;
    @ApiModelProperty(value = "语言")
    private String language;
    @ApiModelProperty(value = "系统用户名")
    private String username;
    @ApiModelProperty(value = "系统密码")
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
