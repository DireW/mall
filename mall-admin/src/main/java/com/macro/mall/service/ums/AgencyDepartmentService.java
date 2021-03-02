package com.macro.mall.service.ums;

import com.macro.mall.dao.ums.AgencyDepartmentDAO;
import com.macro.mall.dto.ums.AgencyDepartmentDTO;
import com.macro.mall.mapper.UmsAgencyDepartmentMapper;
import com.macro.mall.model.UmsAgencyDepartment;
import com.macro.mall.model.UmsAgencyDepartmentExample;
import com.macro.mall.utils.BasicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AgencyDepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgencyDepartmentService.class);
    @Autowired
    UmsAgencyDepartmentMapper agencyDepartmentMapper;
    @Autowired
    AgencyDepartmentDAO agencyDepartmentDAO;

    public List<AgencyDepartmentDTO> findByParams(UmsAgencyDepartment agencyDepartment) {
        return agencyDepartmentDAO.getListByParams(agencyDepartment);
    }

    public int saveOrUpdate(UmsAgencyDepartment agencyDepartment) {
        agencyDepartment.setDeleted(false);
        Date now = new Date();
        if (agencyDepartment.getId() == null) {
            agencyDepartment.setCreatedBy(BasicUtil.getCurrentUserId());
            agencyDepartment.setCreatedTime(now);
            return agencyDepartmentMapper.insert(agencyDepartment);
        } else {
            agencyDepartment.setUpdatedBy(BasicUtil.getCurrentUserId());
            agencyDepartment.setUpdatedTime(now);
            return agencyDepartmentMapper.updateByPrimaryKey(agencyDepartment);
        }
    }

    public int delete(Long id) {
        UmsAgencyDepartment agencyDepartment = new UmsAgencyDepartment();
        agencyDepartment.setId(id);
        agencyDepartment.setDeleted(true);
        UmsAgencyDepartmentExample agencyDepartmentExample = new UmsAgencyDepartmentExample();
        agencyDepartmentExample.createCriteria().andIdEqualTo(id);
        return agencyDepartmentMapper.updateByExampleSelective(agencyDepartment, agencyDepartmentExample);
    }

}
