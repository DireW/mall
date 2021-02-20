package com.macro.mall.controller.wechat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.macro.mall.config.WechatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class WechatLoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatLoginController.class);


    public static void main(String[] args) {
        String url = String.format("{name=%s}", "abc");
        System.out.println(url);
    }

    @PostMapping("/login")
    public String login(@RequestBody String code) {

        return "Good job!";
    }

}
