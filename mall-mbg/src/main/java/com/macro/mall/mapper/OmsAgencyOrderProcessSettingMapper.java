package com.macro.mall.mapper;

import com.macro.mall.model.OmsAgencyOrderProcessSetting;
import com.macro.mall.model.OmsAgencyOrderProcessSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsAgencyOrderProcessSettingMapper {
    long countByExample(OmsAgencyOrderProcessSettingExample example);

    int deleteByExample(OmsAgencyOrderProcessSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsAgencyOrderProcessSetting record);

    int insertSelective(OmsAgencyOrderProcessSetting record);

    List<OmsAgencyOrderProcessSetting> selectByExample(OmsAgencyOrderProcessSettingExample example);

    OmsAgencyOrderProcessSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsAgencyOrderProcessSetting record, @Param("example") OmsAgencyOrderProcessSettingExample example);

    int updateByExample(@Param("record") OmsAgencyOrderProcessSetting record, @Param("example") OmsAgencyOrderProcessSettingExample example);

    int updateByPrimaryKeySelective(OmsAgencyOrderProcessSetting record);

    int updateByPrimaryKey(OmsAgencyOrderProcessSetting record);
}