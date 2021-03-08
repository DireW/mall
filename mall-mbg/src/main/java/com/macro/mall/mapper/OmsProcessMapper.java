package com.macro.mall.mapper;

import com.macro.mall.model.OmsProcess;
import com.macro.mall.model.OmsProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsProcessMapper {
    long countByExample(OmsProcessExample example);

    int deleteByExample(OmsProcessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsProcess record);

    int insertSelective(OmsProcess record);

    List<OmsProcess> selectByExampleWithBLOBs(OmsProcessExample example);

    List<OmsProcess> selectByExample(OmsProcessExample example);

    OmsProcess selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsProcess record, @Param("example") OmsProcessExample example);

    int updateByExampleWithBLOBs(@Param("record") OmsProcess record, @Param("example") OmsProcessExample example);

    int updateByExample(@Param("record") OmsProcess record, @Param("example") OmsProcessExample example);

    int updateByPrimaryKeySelective(OmsProcess record);

    int updateByPrimaryKeyWithBLOBs(OmsProcess record);

    int updateByPrimaryKey(OmsProcess record);
}