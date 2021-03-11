package com.macro.mall.service.oms;

import com.macro.mall.dao.oms.ProcessDAO;
import com.macro.mall.mapper.OmsProcessMapper;
import com.macro.mall.model.OmsProcess;
import com.macro.mall.model.OmsProcessExample;
import com.macro.mall.utils.BasicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessService {

    @Autowired
    private OmsProcessMapper processMapper;
    @Autowired
    private ProcessDAO processDAO;

    public List<OmsProcess> getAll() {
        OmsProcessExample example = new OmsProcessExample();
        example.createCriteria().andDeletedEqualTo(false);
        example.setOrderByClause("sort_number asc");
        return processMapper.selectByExampleWithBLOBs(example);
    }

    public OmsProcess findById(Long id) {
        return processMapper.selectByPrimaryKey(id);
    }

    /**
     * 排序加到最后面一个
     *
     * @param process
     * @return
     */
    public int save(OmsProcess process) {
        int maxSort = getAll().size();
        Date now = new Date();
        process.setSortNumber(maxSort + 1);
        process.setDeleted(false);
        process.setCreatedTime(now);
        process.setCreatedBy(BasicUtil.getCurrentUserId());
        process.setUpdatedTime(now);
        process.setUpdatedBy(BasicUtil.getCurrentUserId());
        return processMapper.insert(process);
    }

    /**
     * 只更新流程名称和内容字段
     *
     * @param process
     * @return
     */
    public int update(OmsProcess process) {
        OmsProcess dbProcess = processMapper.selectByPrimaryKey(process.getId());
        dbProcess.setDeleted(false);
        dbProcess.setName(process.getName());
        dbProcess.setContentJson(process.getContentJson());
        dbProcess.setUpdatedTime(new Date());
        dbProcess.setUpdatedBy(BasicUtil.getCurrentUserId());
        return processMapper.updateByPrimaryKeyWithBLOBs(dbProcess);
    }

    /**
     * 需要更新大于这个排序的记录
     *
     * @param id
     * @return
     */
    @Transactional
    public int delete(Long id) {
        OmsProcess dbProcess = processMapper.selectByPrimaryKey(id);
        Integer sortNumber = dbProcess.getSortNumber();
        processDAO.decreaseBySortNumber(sortNumber);
        dbProcess.setDeleted(true);
        dbProcess.setUpdatedTime(new Date());
        dbProcess.setUpdatedBy(BasicUtil.getCurrentUserId());
        return processMapper.updateByPrimaryKey(dbProcess);
    }

    @Transactional
    public int updateSort(List<OmsProcess> processList) {
        Date now = new Date();
        Long userId = BasicUtil.getCurrentUserId();
        return (int) processList.stream()
                .map(process -> {
                    OmsProcess dbProcess = processMapper.selectByPrimaryKey(process.getId());
                    dbProcess.setUpdatedBy(userId);
                    dbProcess.setUpdatedTime(now);
                    dbProcess.setSortNumber(process.getSortNumber());
                    return processMapper.updateByPrimaryKey(dbProcess);
                })
                .count();
    }

}
