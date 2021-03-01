package com.macro.mall.mapper;

import com.macro.mall.model.UmsAgencyArea;
import com.macro.mall.model.UmsAgencyAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAgencyAreaMapper {
    long countByExample(UmsAgencyAreaExample example);

    int deleteByExample(UmsAgencyAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAgencyArea record);

    int insertSelective(UmsAgencyArea record);

    List<UmsAgencyArea> selectByExample(UmsAgencyAreaExample example);

    UmsAgencyArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAgencyArea record, @Param("example") UmsAgencyAreaExample example);

    int updateByExample(@Param("record") UmsAgencyArea record, @Param("example") UmsAgencyAreaExample example);

    int updateByPrimaryKeySelective(UmsAgencyArea record);

    int updateByPrimaryKey(UmsAgencyArea record);
}