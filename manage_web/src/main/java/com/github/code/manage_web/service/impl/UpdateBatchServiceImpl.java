package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.code.manage_common.enums.RunStatusEnum;
import com.github.code.manage_web.domain.manage.RunInstance;
import com.github.code.manage_web.domain.manage.UpdateBatch;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.mapper.manage.UpdateBatchMapper;
import com.github.code.manage_web.service.manage.IUpdateBatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Service
public class UpdateBatchServiceImpl extends ServiceImpl<UpdateBatchMapper, UpdateBatch> implements IUpdateBatchService {

    @Resource
    private UpdateBatchMapper updateBatchMapper;

    public Boolean updateBatchRunStatus(UpdateBatch updateBatch, RunStatusEnum runStatus) {

        UpdateWrapper<UpdateBatch> updateWrapper = new UpdateWrapper<UpdateBatch>()
                .set(runStatus !=null, "run_status",runStatus.getCode())
                .set( "modify_time", LocalDateTime.now())
                .eq("batch_id",updateBatch.getBatchId())
                .eq("test_data_id",updateBatch.getTestDataId());
        try {
            updateBatchMapper.update(updateWrapper);
            return true;
        }catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}
