package com.macro.mall.mapper;

import com.macro.mall.model.UmsAgencyWechatUser;
import com.macro.mall.model.UmsAgencyWechatUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAgencyWechatUserMapper {
    long countByExample(UmsAgencyWechatUserExample example);

    int deleteByExample(UmsAgencyWechatUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAgencyWechatUser record);

    int insertSelective(UmsAgencyWechatUser record);

    List<UmsAgencyWechatUser> selectByExample(UmsAgencyWechatUserExample example);

    UmsAgencyWechatUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAgencyWechatUser record, @Param("example") UmsAgencyWechatUserExample example);

    int updateByExample(@Param("record") UmsAgencyWechatUser record, @Param("example") UmsAgencyWechatUserExample example);

    int updateByPrimaryKeySelective(UmsAgencyWechatUser record);

    int updateByPrimaryKey(UmsAgencyWechatUser record);
}