package com.macro.mall.service.wechat;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.macro.mall.bo.AdminUserDetails;
import com.macro.mall.common.exception.ApiException;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.config.WechatConfig;
import com.macro.mall.dto.wechat.WechatLoginRequest;
import com.macro.mall.dto.wechat.WechatLoginResponse;
import com.macro.mall.dto.wechat.WechatUserDTO;
import com.macro.mall.mapper.UmsWechatUserMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsWechatUser;
import com.macro.mall.model.UmsWechatUserExample;
import com.macro.mall.security.util.JwtTokenUtil;
import com.macro.mall.security.util.WechatUtil;
import com.macro.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UmsWechatUserMapper wechatUserMapper;

    public WechatLoginResponse login(WechatLoginRequest loginRequest) {
        WechatLoginResponse loginResponse = new WechatLoginResponse();
        // 检查账号密码
        String token = umsAdminService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (token == null) {
            throw new ApiException("用户名或密码错误");
        }
        UmsWechatUser umsWechatUser = null;
        if (loginRequest.getCode() != null) {
            umsWechatUser = new UmsWechatUser();
            JSONObject sessionKeyAndOpenId = getSessionKeyAndOpenId(loginRequest.getCode());
            LOGGER.info("already get sessionKey and openId, wechatLoginRequest={}", JSONUtil.toJsonStr(sessionKeyAndOpenId));
            String openid = sessionKeyAndOpenId.get("openid", String.class);
            umsWechatUser.setOpenid(openid);
            BeanUtil.copyProperties(loginRequest, umsWechatUser);
            UmsWechatUser dbWechatUser = findByOpenid(openid);
            if (dbWechatUser == null) {
                wechatUserMapper.insert(umsWechatUser);
            } else {
                umsWechatUser.setId(dbWechatUser.getId());
                wechatUserMapper.updateByPrimaryKey(umsWechatUser);
            }
        }
        loginResponse.setToken(tokenHead + token);
        loginResponse.setUserInfo(convertLoginResponse(umsAdminService.getAdminByUsername(loginRequest.getUsername()), umsWechatUser));
        return loginResponse;
    }

    public Map<String, Object> getUserInfoMap(WechatLoginRequest wechatLoginRequest) {
        Map<String, Object> userInfoMap = new HashMap<>();
        JSONObject sessionKeyAndOpenId = getSessionKeyAndOpenId(wechatLoginRequest.getCode());
        LOGGER.info("already get sessionKey and openId, wechatLoginRequest={}", JSONUtil.toJsonStr(sessionKeyAndOpenId));
        String sessionKey = sessionKeyAndOpenId.get("session_key", String.class);
        String openId = sessionKeyAndOpenId.get("openid", String.class);



        UmsWechatUser umsWechatUser = findByOpenid(openId);
        if (umsWechatUser == null) {
            return null;
        }

        return userInfoMap;
    }

    /**
     * 判断当前openid是否绑定公司员工
     *
     * @param code
     * @return
     */
    public WechatLoginResponse loginByOpenid(String code) {
        JSONObject sessionKeyAndOpenId = getSessionKeyAndOpenId(code);
        LOGGER.info("already get sessionId and openId, wechatLoginRequest={}", JSONUtil.toJsonStr(sessionKeyAndOpenId));

        String sessionKey = sessionKeyAndOpenId.get("session_key", String.class);
        String openId = sessionKeyAndOpenId.get("openid", String.class);
        UmsWechatUser umsWechatUser = findByOpenid(openId);
        // 将sessionKey和openid都保存到session中
        if (umsWechatUser == null) {
            ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert servletRequest != null;
            HttpSession session = (HttpSession)servletRequest.getSessionMutex();
            session.setAttribute(WechatConfig.WECHAT_SESSION_KEY, sessionKey);
            session.setAttribute(WechatConfig.WECHAT_OPENID, sessionKey);
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

    private WechatUserDTO convertLoginResponse(UmsAdmin umsAdmin, UmsWechatUser wechatUser) {
        WechatUserDTO wechatUserDTO = new WechatUserDTO();
        if (wechatUser != null) {
            BeanUtil.copyProperties(wechatUser, wechatUserDTO);
        } else {
            wechatUserDTO.setUserId(umsAdmin.getId());
            wechatUserDTO.setAvatarUrl(umsAdmin.getIcon());
            wechatUserDTO.setNickName(umsAdmin.getNickName());
        }
        return wechatUserDTO;
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

    private JSONObject getSessionKeyAndOpenId(String code) {
        String authUrl = wechatConfig.formatAuthUrl(code);
        LOGGER.info("wechat login, authUrl = {}", authUrl);
        String responseStr = HttpUtil.get(authUrl);
        return JSONUtil.parseObj(responseStr);
    }

}