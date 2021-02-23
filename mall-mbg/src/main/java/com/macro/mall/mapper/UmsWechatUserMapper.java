package com.macro.mall.mapper;

import com.macro.mall.model.UmsWechatUser;
import com.macro.mall.model.UmsWechatUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsWechatUserMapper {
    long countByExample(UmsWechatUserExample example);

    int deleteByExample(UmsWechatUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsWechatUser record);

    int insertSelective(UmsWechatUser record);

    List<UmsWechatUser> selectByExample(UmsWechatUserExample example);

    UmsWechatUser selectByPrimaryKey(Long id);

    UmsWechatUser findByOpenid(String openid);

    int updateByExampleSelective(@Param("record") UmsWechatUser record, @Param("example") UmsWechatUserExample example);

    int updateByExample(@Param("record") UmsWechatUser record, @Param("example") UmsWechatUserExample example);

    int updateByPrimaryKeySelective(UmsWechatUser record);

    int updateByPrimaryKey(UmsWechatUser record);
}