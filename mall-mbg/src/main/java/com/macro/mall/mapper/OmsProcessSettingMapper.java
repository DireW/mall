package com.macro.mall.mapper;

import com.macro.mall.model.OmsProcessSetting;
import com.macro.mall.model.OmsProcessSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsProcessSettingMapper {
    long countByExample(OmsProcessSettingExample example);

    int deleteByExample(OmsProcessSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsProcessSetting record);

    int insertSelective(OmsProcessSetting record);

    List<OmsProcessSetting> selectByExample(OmsProcessSettingExample example);

    OmsProcessSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsProcessSetting record, @Param("example") OmsProcessSettingExample example);

    int updateByExample(@Param("record") OmsProcessSetting record, @Param("example") OmsProcessSettingExample example);

    int updateByPrimaryKeySelective(OmsProcessSetting record);

    int updateByPrimaryKey(OmsProcessSetting record);
}