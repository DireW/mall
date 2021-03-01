package com.macro.mall.mapper;

import com.macro.mall.model.UmsAgencyDepartment;
import com.macro.mall.model.UmsAgencyDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAgencyDepartmentMapper {
    long countByExample(UmsAgencyDepartmentExample example);

    int deleteByExample(UmsAgencyDepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAgencyDepartment record);

    int insertSelective(UmsAgencyDepartment record);

    List<UmsAgencyDepartment> selectByExample(UmsAgencyDepartmentExample example);

    UmsAgencyDepartment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAgencyDepartment record, @Param("example") UmsAgencyDepartmentExample example);

    int updateByExample(@Param("record") UmsAgencyDepartment record, @Param("example") UmsAgencyDepartmentExample example);

    int updateByPrimaryKeySelective(UmsAgencyDepartment record);

    int updateByPrimaryKey(UmsAgencyDepartment record);
}