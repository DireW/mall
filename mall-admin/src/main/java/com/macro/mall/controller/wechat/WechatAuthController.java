package com.macro.mall.controller.wechat;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.wechat.WechatLoginRequest;
import com.macro.mall.dto.wechat.WechatLoginResponse;
import com.macro.mall.service.wechat.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat/auth")
public class WechatAuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatAuthController.class);

    @Autowired
    WechatAuthService wechatLoginService;

    public static void main(String[] args) {
        String url = String.format("{name=%s}", "abc");
        System.out.println(url);
    }

    @PostMapping("/login")
    public WechatLoginResponse login(@RequestBody WechatLoginRequest request) {
        return wechatLoginService.login(request);
    }

    /**
     * 根据前台获取的code，从服务器获取openid之后，检查openid是否已经绑定账号，绑定的直接返回
     * @param code
     * @return
     */
    @GetMapping("/login-code")
    public CommonResult<WechatLoginResponse> loginByOpenid(@RequestParam("code") String code) {
        WechatLoginResponse loginResponse = wechatLoginService.loginByOpenid(code);
        if (loginResponse == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(loginResponse);
    }

}