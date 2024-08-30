package com.github.code.manage_web.task;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.code.manage_common.enums.AttributeIsAutoUpdateEnum;
import com.github.code.manage_common.enums.DataTypeEnum;
import com.github.code.manage_common.enums.RunStatusEnum;
import com.github.code.manage_web.domain.manage.*;
import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import com.github.code.manage_web.service.impl.TestDataServiceImpl;
import com.github.code.manage_web.service.impl.UpdateBatchServiceImpl;
import com.github.code.manage_web.service.manage.handle.DataManageOperateService;
import com.github.code.manage_web.service.manage.handle.QueryDataService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GenerateDataBatchTask {

    @Autowired
    private TestDataServiceImpl testDataService;

    @Autowired
    private TestDataAttributeServiceImpl testDataAttributeService;

    @Autowired
    private QueryDataService queryDataService;

    @Resource
    private DataManageOperateService dataManageOperateService;

    @Resource
    private UpdateBatchServiceImpl updateBatchService;

    @Scheduled(cron = "0 0 * * * ?")
    public void generateBatch() {
        String batchId = "batch" + DateUtil.format(LocalDateTime.now(), "YYYYMMDD") +
                RandomUtil.randomInt(1000, 10000);
        //查询test_data获取需要更新的账号id，更新batch表
        List<TestData> accountDataList = testDataService.getDataList(DataTypeEnum.REFUND, null);
        log.info("本次需要校验的账号列表{}", accountDataList.stream()   // 将列表转成流
                .map(TestData::getDataId)  // 提取每个TestData对象的dataId
                .collect(Collectors.toList()));
        for (TestData testData : accountDataList) {
            // 获取每个账号id需要更新的属性,转换为一个对象
            String accountId = testData.getDataId();
            List<TestDataAttribute> dataAttributeNeedUpdate = testDataAttributeService.
                    getTestDataAttributeByTestDataId(accountId,
                            AttributeIsAutoUpdateEnum.YES.getCode());
            log.info("获取需要自动更新的属性{}", dataAttributeNeedUpdate);
            if (!dataAttributeNeedUpdate.isEmpty()) {
                AccountInfo needUpdateAccountAttribute = AccountInfo.convert(dataAttributeNeedUpdate);
                //查询实际值，判断实际值是否与预期值想等，更新run_instance
                AccountInfo actualAccountAttribute = queryDataService.ActualAccountListByAccountId(accountId);
                log.info("账号需要检验的属性{}", needUpdateAccountAttribute);
                log.info("账号实际的属性{}", actualAccountAttribute);
                try {
                    List<Map<String, Object>> noSameAttribute = queryDataService.findNoSameAttribute
                            (needUpdateAccountAttribute, actualAccountAttribute);
                    if (!noSameAttribute.isEmpty()) {
                        UpdateBatch updateBatch = dataManageOperateService.createUpdateBatchAccount(accountId,batchId);
                        List<RunInstance> createRunInstances = dataManageOperateService.createRunInstance(batchId, noSameAttribute);
                        updateBatchService.updateBatchRunStatus(updateBatch, RunStatusEnum.SEND_SUCCESS);
                        log.info("当前账号accountId{}创建数据成功createRunInstances{}", accountId, createRunInstances);

                    } else {
                        log.info("当前账号accountId{}不需要更新", accountId);
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }

    }
}
