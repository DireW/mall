package com.macro.mall.service.ums;


import com.macro.mall.dao.ums.AgencyAreaDAO;
import com.macro.mall.dto.ums.AgencyAreaDTO;
import com.macro.mall.mapper.UmsAgencyAreaMapper;
import com.macro.mall.model.UmsAgencyArea;
import com.macro.mall.model.UmsAgencyAreaExample;
import com.macro.mall.utils.BasicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        agencyArea.setDeleted(false);
        Date now = new Date();
        if (agencyArea.getId() == null) {
            agencyArea.setCreatedBy(BasicUtil.getCurrentUserId());
            agencyArea.setCreatedTime(now);
            return agencyAreaMapper.insert(agencyArea);
        } else {
            agencyArea.setUpdatedBy(BasicUtil.getCurrentUserId());
            agencyArea.setUpdatedTime(now);
            return agencyAreaMapper.updateByPrimaryKey(agencyArea);
        }
    }

    public int delete(Long id) {
        UmsAgencyArea agencyArea = new UmsAgencyArea();
        agencyArea.setId(id);
        agencyArea.setDeleted(true);
        UmsAgencyAreaExample agencyAreaExample = new UmsAgencyAreaExample();
        agencyAreaExample.createCriteria().andIdEqualTo(id);
        return agencyAreaMapper.updateByExampleSelective(agencyArea, agencyAreaExample);
    }

}
