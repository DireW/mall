package com.macro.mall.service.wechat;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.macro.mall.bo.AdminUserDetails;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.config.WechatConfig;
import com.macro.mall.dto.wechat.WechatLoginRequest;
import com.macro.mall.dto.wechat.WechatLoginResponse;
import com.macro.mall.dto.wechat.WechatUserDTO;
import com.macro.mall.mapper.UmsAgencyWechatUserMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAgencyWechatUser;
import com.macro.mall.model.UmsAgencyWechatUserExample;
import com.macro.mall.model.UmsResource;
import com.macro.mall.security.util.JwtTokenUtil;
import com.macro.mall.service.ums.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
public class WechatAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatAuthService.class);
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsAgencyWechatUserMapper wechatUserMapper;

    public CommonResult<WechatLoginResponse> loginByAccount(WechatLoginRequest loginRequest) {
        WechatLoginResponse loginResponse = new WechatLoginResponse();
        // 检查账号密码
        String token = umsAdminService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (token == null) {
            LOGGER.info("登录用户名【{}】，账号或密码错误", loginRequest.getUsername());
            return CommonResult.failed("账号或密码错误");
        }
        loginResponse.setToken(tokenHead + token);
        loginResponse.setUserInfo(convertLoginResponse(umsAdminService.getAdminByUsername(loginRequest.getUsername()), null));
        return CommonResult.success(loginResponse);
    }

    public CommonResult<WechatLoginResponse> loginWithWechat(WechatLoginRequest loginRequest) {
        WechatLoginResponse loginResponse = new WechatLoginResponse();
        // 检查账号密码
        String token = umsAdminService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (token == null) {
            LOGGER.info("登录用户名【{}】，账号或密码错误", loginRequest.getUsername());
            return CommonResult.failed("账号或密码错误");
        }
        // 获取当前登录人的openid
        JSONObject sessionKeyAndOpenId = getSessionKeyAndOpenId(loginRequest.getCode());
        LOGGER.info("获取到sessionKey和Openid：{}", JSONUtil.toJsonStr(sessionKeyAndOpenId));
        String openid = sessionKeyAndOpenId.get("openid", String.class);
        // 获取当前用户信息，并判断openid是否一致
        UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(loginRequest.getUsername());
        UmsAgencyWechatUser dbWechatUserByUserId = findByUserId(umsAdmin.getId());
        UmsAgencyWechatUser dbWechatUserByOpenid = findByOpenid(openid);
        if (dbWechatUserByUserId != null) {
            if (Objects.equals(dbWechatUserByUserId.getOpenid(), openid)) {
                LOGGER.info("登录用户名【{}】，已绑定openid【{}】，返回对应信息", loginRequest.getUsername(), openid);
                loginResponse.setUserInfo(convertLoginResponse(umsAdmin, dbWechatUserByOpenid));
            } else {
                LOGGER.info("登录用户名【{}】，已绑定openid【{}】，尝试绑定openid【{}】失败", loginRequest.getUsername(), dbWechatUserByUserId.getOpenid(), openid);
                return CommonResult.failed("该账户已被别的微信绑定，请选择账号登录");
            }
        } else if (dbWechatUserByOpenid == null) {
            // 通过userId和openid都找不到微信记录，需要新增一个
            UmsAgencyWechatUser umsWechatUser = insertWechatUser(umsAdmin.getId(), openid, loginRequest);
            loginResponse.setUserInfo(convertLoginResponse(umsAdmin, umsWechatUser));
        }
        loginResponse.setToken(token);
        return CommonResult.success(loginResponse);
    }

    private UmsAgencyWechatUser insertWechatUser(Long userId, String openid, WechatLoginRequest loginRequest) {
        UmsAgencyWechatUser wechatUser = new UmsAgencyWechatUser();
        BeanUtil.copyProperties(loginRequest, wechatUser);
        wechatUser.setUserId(userId);
        wechatUser.setOpenid(openid);
        // 昵称设置的时候需要编码，取值的时候解码
        wechatUser.setNickName(Base64.encode(loginRequest.getNickName(), StandardCharsets.UTF_8));
        int count = wechatUserMapper.insert(wechatUser);
        LOGGER.info("新增微信用户信息，wechatUser={}", wechatUser.toString());
        return wechatUser;
    }


    /**
     * 判断当前openid是否绑定公司员工
     *
     * @param code
     * @return
     */
    public WechatLoginResponse loginByOpenid(String code) {
        JSONObject sessionKeyAndOpenId = getSessionKeyAndOpenId(code);
        LOGGER.info("已获取sessionKey和openid：{}", JSONUtil.toJsonStr(sessionKeyAndOpenId));

        String sessionKey = sessionKeyAndOpenId.get("session_key", String.class);
        String openId = sessionKeyAndOpenId.get("openid", String.class);
        UmsAgencyWechatUser umsWechatUser = findByOpenid(openId);
        // 将sessionKey和openid都保存到session中
        if (umsWechatUser == null) {
            return null;
        }
        // openid有绑定的对应账号信息，生成一个token返回到前台
        Long userId = umsWechatUser.getUserId();
        UmsAdmin umsAdmin = umsAdminService.getItem(userId);
        List<UmsResource> resourceList = umsAdminService.getResourceList(userId);
        UserDetails userDetails = new AdminUserDetails(umsAdmin, resourceList);
        if (!userDetails.isEnabled()) {
            Asserts.fail("帐号已被禁用");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        WechatLoginResponse wechatLoginResponse = new WechatLoginResponse();
        wechatLoginResponse.setToken(tokenHead + token);
        wechatLoginResponse.setUserInfo(convertLoginResponse(umsAdmin, umsWechatUser));
        return wechatLoginResponse;
    }

    private WechatUserDTO convertLoginResponse(UmsAdmin umsAdmin, UmsAgencyWechatUser wechatUser) {
        WechatUserDTO wechatUserDTO = new WechatUserDTO();
        if (wechatUser != null) {
            BeanUtil.copyProperties(wechatUser, wechatUserDTO);
        }
        wechatUserDTO.setUserId(umsAdmin.getId());
        wechatUserDTO.setNickName(umsAdmin.getRealName());
        return wechatUserDTO;
    }

    private UmsAgencyWechatUser findByUserId(Long userId) {
        UmsAgencyWechatUserExample umsWechatUserExample = new UmsAgencyWechatUserExample();
        umsWechatUserExample.createCriteria().andUserIdEqualTo(userId);
        List<UmsAgencyWechatUser> wechatUserList = wechatUserMapper.selectByExample(umsWechatUserExample);
        if (CollectionUtil.isEmpty(wechatUserList)) {
            return null;
        }
        return wechatUserList.get(0);
    }

    private UmsAgencyWechatUser findByOpenid(String openid) {
        UmsAgencyWechatUserExample umsWechatUserExample = new UmsAgencyWechatUserExample();
        umsWechatUserExample.createCriteria().andOpenidEqualTo(openid);
        List<UmsAgencyWechatUser> wechatUserList = wechatUserMapper.selectByExample(umsWechatUserExample);
        if (CollectionUtil.isEmpty(wechatUserList)) {
            return null;
        }
        return wechatUserList.get(0);
    }

    private JSONObject getSessionKeyAndOpenId(String code) {
        String authUrl = wechatConfig.formatAuthUrl(code);
        LOGGER.info("wechat login, authUrl = {}", authUrl);
        String responseStr = HttpUtil.get(authUrl);
        return JSONUtil.parseObj(responseStr);
    }

}
