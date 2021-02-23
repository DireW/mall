package com.macro.mall.service.wechat;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.macro.mall.common.service.RedisService;
import com.macro.mall.config.WechatConfig;
import com.macro.mall.dto.wechat.WechatLoginRequest;
import com.macro.mall.mapper.UmsWechatUserMapper;
import com.macro.mall.model.UmsWechatUser;
import com.macro.mall.model.UmsWechatUserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WechatLoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatLoginService.class);

    @Autowired
    WechatConfig wechatConfig;
    @Autowired
    RedisService redisService;
    @Autowired
    UmsWechatUserMapper wechatUserMapper;

    public Map<String, Object> getUserInfoMap(WechatLoginRequest wechatLoginRequest) {
        Map<String, Object> userInfoMap = new HashMap<>();
        JSONObject sessionIdAndOpenId = getSessionIdAndOpenId(wechatLoginRequest.getCode());
        LOGGER.info("had get sessionId and openId, wechatLoginRequest={}", JSONUtil.toJsonStr(sessionIdAndOpenId));
        String sessionKey = sessionIdAndOpenId.get("session_key", String.class);
        String openId = sessionIdAndOpenId.get("openid", String.class);

        redisService.set(WechatConfig.WECHAT_REDIS_KEY, sessionIdAndOpenId.toStringPretty());

        UmsWechatUser umsWechatUser = findByOpenid(openId);
        if (umsWechatUser == null) {
            return null;
        }

        return userInfoMap;
    }

    private void insertUmsWechatUser() {

    }

    private UmsWechatUser findByOpenid(String openid) {
        UmsWechatUserExample umsWechatUserExample = new UmsWechatUserExample();
        umsWechatUserExample.createCriteria().andOpenidEqualTo(openid);
        List<UmsWechatUser> wechatUserList = wechatUserMapper.selectByExample(umsWechatUserExample);
        if (CollectionUtil.isEmpty(wechatUserList)) {
            return null;
        }
        return wechatUserList.get(0);
    }

    private JSONObject getSessionIdAndOpenId(String code) {
        String authUrl = wechatConfig.formatAuthUrl(code);
        LOGGER.info("wechat login, authUrl = {}", authUrl);
        String responseStr = HttpUtil.get(authUrl);
        return JSONUtil.parseObj(responseStr);
    }

}
