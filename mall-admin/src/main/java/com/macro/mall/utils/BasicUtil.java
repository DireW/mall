package com.macro.mall.utils;

import com.macro.mall.bo.AdminUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BasicUtil {

    public static Long getCurrentUserId() {
        AdminUserDetails details = (AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return details.getId();
    }


}
