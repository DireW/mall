package com.macro.mall.dao.ums;

import com.macro.mall.dto.ums.AgencyAreaDTO;
import com.macro.mall.dto.ums.UmsAdminDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域自定义查询Dao
 * Created by DireW
 */
public interface UmsAdminDAO {

    List<UmsAdminDTO> findByPage(@Param("queryParam") UmsAdminDTO umsAdminDTO);

}
