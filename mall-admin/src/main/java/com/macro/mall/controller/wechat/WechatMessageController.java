package com.macro.mall.controller.wechat;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/wechat/message")
public class WechatMessageController {

    @Value("wechat.message-token")
    private String messageToken;

    /**
     * 验证微信消息推送的接口，成为开发者
     *
     * @return
     */
    @GetMapping("/validate")
    public String messageDispatch(@RequestParam("signature") String signature,
                                   @RequestParam("timestamp") String timestamp,
                                   @RequestParam("signature") String nonce,
                                   @RequestParam("echostr") String echostr) {
        List<String> list = new ArrayList<>();
        list.add(messageToken);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        String resultStr = list.stream().reduce("", (str, rlt) -> str + rlt);
        String encodedStr = Arrays.toString(DigestUtil.sha1(resultStr));
        return Objects.equals(signature, encodedStr) ? echostr : "";
    }

}
