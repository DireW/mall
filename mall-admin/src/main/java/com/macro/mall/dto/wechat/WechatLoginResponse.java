package com.macro.mall.dto.wechat;

public class WechatLoginResponse {

    private String token;

    private WechatUserDTO userInfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WechatUserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WechatUserDTO userInfo) {
        this.userInfo = userInfo;
    }
}
