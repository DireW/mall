package com.macro.mall.dto.ums;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAgencyWechatUser;

public class UmsAdminDTO {

    private UmsAdmin umsAdmin;

    private UmsAgencyWechatUser agencyWechatUser;

    public UmsAdmin getUmsAdmin() {
        return umsAdmin;
    }

    public void setUmsAdmin(UmsAdmin umsAdmin) {
        this.umsAdmin = umsAdmin;
    }

    public UmsAgencyWechatUser getAgencyWechatUser() {
        return agencyWechatUser;
    }

    public void setAgencyWechatUser(UmsAgencyWechatUser agencyWechatUser) {
        this.agencyWechatUser = agencyWechatUser;
    }
}
