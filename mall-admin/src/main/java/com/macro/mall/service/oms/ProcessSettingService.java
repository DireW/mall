package com.macro.mall.service.oms;

import afu.org.checkerframework.checker.oigj.qual.O;
import com.macro.mall.mapper.OmsProcessSettingMapper;
import com.macro.mall.model.OmsProcessExample;
import com.macro.mall.model.OmsProcessSetting;
import com.macro.mall.model.OmsProcessSettingExample;
import com.macro.mall.utils.BasicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessSettingService {

    @Autowired
    private OmsProcessSettingMapper processSettingMapper;

    public List<OmsProcessSetting> listOfProcess(Long processId) {
        OmsProcessSettingExample processSettingExample = new OmsProcessSettingExample();
        processSettingExample.createCriteria().andProcessIdEqualTo(processId).andDeletedEqualTo(false);
        return processSettingMapper.selectByExample(processSettingExample);
    }

    /**
     * 批量调整配置信息
     * 1、将之前的所有配置信息deleted设置为true
     * 2、将前台传递过来的有id的配置过滤出来，根据id进行数据更新，即页面字段为新值，deleted为false
     * 3、将前台传递过来的没id的配置作为新的记录插入数据库
     * @param processId
     * @param processSettingList
     */
    @Transactional
    public Boolean saveOrUpdateSettings(Long processId, List<OmsProcessSetting> processSettingList) {
        OmsProcessSettingExample updateExample = new OmsProcessSettingExample();
        updateExample.createCriteria().andDeletedEqualTo(false).andProcessIdEqualTo(processId);
        OmsProcessSetting updateRecord = new OmsProcessSetting();
        updateRecord.setDeleted(true);
        processSettingMapper.updateByExampleSelective(updateRecord, updateExample);

        Date now = new Date();
        processSettingList.stream().filter(setting -> setting.getId() != null)
                .forEach(setting -> {
                    setting.setUpdatedBy(BasicUtil.getCurrentUserId());
                    setting.setUpdatedTime(now);
                    setting.setDeleted(false);
                    processSettingMapper.updateByPrimaryKey(setting);
                });


        processSettingList.stream().filter(setting -> setting.getId() == null)
                .forEach(setting -> {
                    setting.setCreatedBy(BasicUtil.getCurrentUserId());
                    setting.setCreatedTime(now);
                    setting.setUpdatedBy(BasicUtil.getCurrentUserId());
                    setting.setUpdatedTime(now);
                    setting.setDeleted(false);
                    processSettingMapper.insert(setting);
                });
        return true;
    }


}
