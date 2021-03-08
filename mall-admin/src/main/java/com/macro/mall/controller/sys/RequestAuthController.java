package com.macro.mall.controller.sys;

import com.sun.el.stream.Optional;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 获取系统中所有的接口路径以及对应的注释信息  deprecated 暂时弃用
 */
public class RequestAuthController {

    @Autowired
    WebApplicationContext applicationContext;

    /**
     * 返回方法@ApiOperation 备注的信息或者类名 + 方法名
     * @return
     */
    @GetMapping("/all-request-url")
    public List<Map<String, Set<String>>> getAllRequestMappingUrls() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        return methodMap.entrySet().stream().map(entry -> {
            Map<String, Set<String>> map = new HashMap<>();
            RequestMappingInfo requestMappingInfo = entry.getKey();
            Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
            HandlerMethod handlerMethod = entry.getValue();
            ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
            String methodDesc = apiOperation.value();
            map.put(methodDesc, patterns);
            return map;
        }).collect(Collectors.toList());
    }

}
