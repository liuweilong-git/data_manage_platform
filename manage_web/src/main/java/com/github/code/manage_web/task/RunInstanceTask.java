package com.github.code.manage_web.task;

import com.github.code.manage_web.domain.manage.RunInstance;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.impl.RunInstanceServiceImpl;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

@Slf4j
@Service
public class RunInstanceTask {

    @Resource
    private UpdateService updateService;

    @Resource
    private RunInstanceServiceImpl runInstanceService;
//TODO
    @Scheduled(cron = "0 0 * * * ?")
    public void performTask() {

        //查询当前run_instance表中的最近一个批次的数据
        String batch_id = "11111"; //从这里开始
        List<RunInstance> runInstances = runInstanceService.getRunInstancesByBatchId(batch_id);

        for (RunInstance runInstance : runInstances) {
            RunInstanceDto runInstanceDto = new RunInstanceDto();
            RunInstanceDto runInstanceDto1 = runInstanceDto.convertToDTO(runInstance);
            try {
                //对非运行成功状态的数据，进行运行调用update方法
                updateService.update(runInstanceDto1);
            } catch (Exception e) {
                log.error("当前数据更新失败{}", runInstanceDto);
            }

        }

    }
}
