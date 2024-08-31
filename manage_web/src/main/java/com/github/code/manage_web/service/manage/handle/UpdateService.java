package com.github.code.manage_web.service.manage.handle;

import com.github.code.manage_common.enums.RunStatusEnum;
import com.github.code.manage_web.domain.manage.CnfAtomic;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.domain.manage.UpdateLog;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.common.handle.UpdateStrategy;
import com.github.code.manage_web.service.impl.CnfAtomicServiceImpl;
import com.github.code.manage_web.service.impl.RunInstanceServiceImpl;
import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UpdateService {
    private final Map<String, UpdateStrategy> strategies;

    @Resource
    private TestDataAttributeServiceImpl testDataAttributeService;

    @Resource
    public RunInstanceServiceImpl runInstanceService;

    @Resource
    public CnfAtomicServiceImpl cnfAtomicService;

    @Resource
    private DataManageOperateService dataManageOperateService;

    @Autowired
    public UpdateService(List<UpdateStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(strategy -> strategy.getClass().getSimpleName()
//                        .replace("Strategy", "")
                        , strategy -> strategy));
    }

    /**
     * @param data 更新的数据值
     */
    public void update(RunInstanceDto data) {
//        1.根据test_data_id查询test_data_attribute获取预期值
        TestDataAttribute data_attribute = testDataAttributeService.getTestDataAttribute(data);
        String attr = data.getAttrKey();
        CnfAtomic cnf_atomic = cnfAtomicService.getCnfAtomicByAttr(attr);
        String atomic_key = cnf_atomic.getAtomicKey();
//        System.out.println(strategies);
        UpdateStrategy strategy = strategies.get(atomic_key);
//        System.out.println(strategy);
        if (strategy != null) {
//            2.更新companyInfo中属性的状态为预期值
            if (strategy.update(data, data_attribute.getExpectedValue())){
//              3.更新run_instance表的数据查询条件id），更新run_status为运行完成，after_value为预期值
                Map<String, Object> updateData = new HashMap<>();
                updateData.put("run_status", RunStatusEnum.RUN_SUCCESS.getCode());
                updateData.put("after_value", data_attribute.getExpectedValue());
                updateData.put("modify_time", LocalDateTime.now());
                log.info("数据实际值更新为预期值{}",updateData);
                runInstanceService.updateRunInstanceByAttrId(data, updateData);
                UpdateLog updateLog = dataManageOperateService.createUpdateLog(data,
                        data_attribute.getExpectedValue());
                log.info("更新记录添加成功updateLog{}",updateLog);
            }
            else {
                Map<String, Object> updateData = new HashMap<>();
                updateData.put("run_status", RunStatusEnum.RUN_FAILED.getCode());
                log.info("数据实际值更新失败预期值为{}",updateData);
                runInstanceService.updateRunInstanceByAttrId(data, updateData);
            }
        }

        else {
            log.error("没有匹配的更新方法,属性key为{}",atomic_key);
            throw new IllegalArgumentException("No strategy found for database type: " + atomic_key);
        }
    }
}
