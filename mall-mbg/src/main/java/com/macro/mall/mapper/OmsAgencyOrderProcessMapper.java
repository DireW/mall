package com.macro.mall.mapper;

import com.macro.mall.model.OmsAgencyOrderProcess;
import com.macro.mall.model.OmsAgencyOrderProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsAgencyOrderProcessMapper {
    long countByExample(OmsAgencyOrderProcessExample example);

    int deleteByExample(OmsAgencyOrderProcessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsAgencyOrderProcess record);

    int insertSelective(OmsAgencyOrderProcess record);

    List<OmsAgencyOrderProcess> selectByExample(OmsAgencyOrderProcessExample example);

    OmsAgencyOrderProcess selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsAgencyOrderProcess record, @Param("example") OmsAgencyOrderProcessExample example);

    int updateByExample(@Param("record") OmsAgencyOrderProcess record, @Param("example") OmsAgencyOrderProcessExample example);

    int updateByPrimaryKeySelective(OmsAgencyOrderProcess record);

    int updateByPrimaryKey(OmsAgencyOrderProcess record);
}