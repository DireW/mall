package com.macro.mall.service.ums;


import com.macro.mall.dao.ums.AgencyAreaDAO;
import com.macro.mall.dto.ums.AgencyAreaDTO;
import com.macro.mall.mapper.UmsAgencyAreaMapper;
import com.macro.mall.model.UmsAgencyArea;
import com.macro.mall.model.UmsAgencyAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AgencyAreaService {

    @Autowired
    AgencyAreaDAO agencyAreaDAO;
    @Autowired
    UmsAgencyAreaMapper agencyAreaMapper;

    public List<AgencyAreaDTO> fetchAllArea() {
        return agencyAreaDAO.getList();
    }

    public int saveOrUpdate(UmsAgencyArea agencyArea) {
        if (agencyArea.getId() == null) {
            return agencyAreaMapper.insert(agencyArea);
        } else {
            return agencyAreaMapper.updateByPrimaryKey(agencyArea);
        }
    }

    public int delete(Long id) {
        UmsAgencyArea agencyArea = new UmsAgencyArea();
        agencyArea.setId(id);
        UmsAgencyAreaExample agencyAreaExample = new UmsAgencyAreaExample();
        agencyAreaExample.createCriteria().andIdEqualTo(id);
        return agencyAreaMapper.updateByExampleSelective(agencyArea, agencyAreaExample);
    }

}
