package com.github.code.manage_web.task;

import com.github.code.manage_common.enums.RunStatusEnum;
import com.github.code.manage_web.domain.manage.RunInstance;
import com.github.code.manage_web.domain.manage.UpdateBatch;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.impl.RunInstanceServiceImpl;
import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import com.github.code.manage_web.service.impl.UpdateBatchServiceImpl;
import com.github.code.manage_web.service.manage.handle.DataManageOperateService;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
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

    @Resource
    private DataManageOperateService dataManageOperateService;

//    @Scheduled(cron = "0 0 * * * ?")
//    @Scheduled(cron = "*/10 * * * *")
    @Scheduled(cron = "0 5/10 * * * ?")
    public void performTask() {
        UpdateBatch latestUpdateBatch = updateBatchService.getLatestUpdateBatch();
        //查询当前run_instance表中的最近一个批次的数据
        String batch_id = latestUpdateBatch.getBatchId();
        log.info("当前需要更新的batch_id:{}", batch_id);
        Boolean b = updateService.runInstanceToUpdateAttr(batch_id);
        if (b) {
            log.info("运行更新操作的定时任务运行成功batch_id:{}，运行时间{}", batch_id, LocalDateTime.now());
        }
    }
}
