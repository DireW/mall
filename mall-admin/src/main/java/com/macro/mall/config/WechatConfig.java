package com.macro.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfig {

    public static final String AUTH_URL_FMT = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static final String WECHAT_REDIS_KEY = "wechatSessionKeyOpenId";

    @Value("${wechat.app-id}")
    public String WECHAT_APP_ID;

    @Value("${wechat.app-secret}")
    public String WECHAT_APP_SECRET;

    public String formatAuthUrl(String code) {
        return String.format(WechatConfig.AUTH_URL_FMT, WECHAT_APP_ID, WECHAT_APP_SECRET, code);
    }

}
