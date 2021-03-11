package com.macro.mall.mapper;

import com.macro.mall.model.OmsAgencyOrder;
import com.macro.mall.model.OmsAgencyOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsAgencyOrderMapper {
    long countByExample(OmsAgencyOrderExample example);

    int deleteByExample(OmsAgencyOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsAgencyOrder record);

    int insertSelective(OmsAgencyOrder record);

    List<OmsAgencyOrder> selectByExample(OmsAgencyOrderExample example);

    OmsAgencyOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsAgencyOrder record, @Param("example") OmsAgencyOrderExample example);

    int updateByExample(@Param("record") OmsAgencyOrder record, @Param("example") OmsAgencyOrderExample example);

    int updateByPrimaryKeySelective(OmsAgencyOrder record);

    int updateByPrimaryKey(OmsAgencyOrder record);
}