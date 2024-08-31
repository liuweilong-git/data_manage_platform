package com.github.code.manage_web.task;

import com.github.code.manage_common.enums.RunStatusEnum;
import com.github.code.manage_web.domain.manage.RunInstance;
import com.github.code.manage_web.domain.manage.UpdateBatch;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.impl.RunInstanceServiceImpl;
import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import com.github.code.manage_web.service.impl.UpdateBatchServiceImpl;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class RunInstanceTask {

    @Resource
    private UpdateService updateService;

    @Resource
    private RunInstanceServiceImpl runInstanceService;

    @Resource
    private UpdateBatchServiceImpl updateBatchService;

    @Resource
    private TestDataAttributeServiceImpl testDataAttributeService;

    @Scheduled(cron = "0 0 * * * ?")
    public void performTask() {
        UpdateBatch latestUpdateBatch = updateBatchService.getLatestUpdateBatch();
        //查询当前run_instance表中的最近一个批次的数据
        String batch_id = latestUpdateBatch.getBatchId();
        log.info("当前需要更新的batch_id:{}", batch_id);
        List<RunInstance> runInstances = runInstanceService.getRunInstancesByBatchId(batch_id);

        for (RunInstance runInstance : runInstances) {
            if (Arrays.asList(RunStatusEnum.ADD_SUCCESS.getCode(),RunStatusEnum.SEND_SUCCESS.getCode())
                    .contains(runInstance.getRunStatus())){
                RunInstanceDto runInstanceDto = RunInstanceDto.convertToDTO(runInstance);
                runInstanceDto.setCustomerId(testDataAttributeService.getTestDataAttributeByAttr
                        (runInstance.getTestDataId(),"customerId").getExpectedValue());
                runInstanceDto.setContId(testDataAttributeService.getTestDataAttributeByAttr
                        (runInstance.getTestDataId(),"contId").getExpectedValue());
                try {
                    //对非运行成功状态的数据，进行运行调用update方法
                    updateService.update(runInstanceDto);
                } catch (Exception e) {
                    log.error("当前数据更新失败{}", runInstanceDto);
                }

            }else {
                log.info("当前实例已经运行过{}，状态为{}", runInstance.getId(), runInstance.getRunStatus());
            }


        }

    }
}
