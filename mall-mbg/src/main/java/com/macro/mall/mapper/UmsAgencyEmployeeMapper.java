package com.macro.mall.mapper;

import com.macro.mall.model.UmsAgencyEmployee;
import com.macro.mall.model.UmsAgencyEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAgencyEmployeeMapper {
    long countByExample(UmsAgencyEmployeeExample example);

    int deleteByExample(UmsAgencyEmployeeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAgencyEmployee record);

    int insertSelective(UmsAgencyEmployee record);

    List<UmsAgencyEmployee> selectByExample(UmsAgencyEmployeeExample example);

    UmsAgencyEmployee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAgencyEmployee record, @Param("example") UmsAgencyEmployeeExample example);

    int updateByExample(@Param("record") UmsAgencyEmployee record, @Param("example") UmsAgencyEmployeeExample example);

    int updateByPrimaryKeySelective(UmsAgencyEmployee record);

    int updateByPrimaryKey(UmsAgencyEmployee record);
}