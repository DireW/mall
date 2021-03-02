package com.macro.mall.service.ums;

import com.macro.mall.dto.ums.AgencyDepartmentDTO;
import com.macro.mall.model.UmsAgencyDepartment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyDepartmentService {

    public List<AgencyDepartmentDTO> findByParams(AgencyDepartmentDTO agencyDepartmentDTO) {
        return null;
    }

    public int saveOrUpdate(UmsAgencyDepartment agencyDepartment) {
        return 1;
    }

    public int delete(Long id) {
        return 1;
    }

}
