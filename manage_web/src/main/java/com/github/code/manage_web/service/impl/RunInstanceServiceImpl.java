package com.github.code.manage_web.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.code.manage_web.domain.manage.RunInstance;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.mapper.manage.RunInstanceMapper;
import com.github.code.manage_web.service.manage.IRunInstanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
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
@DS("manage")
public class RunInstanceServiceImpl extends ServiceImpl<RunInstanceMapper, RunInstance> implements IRunInstanceService {
    @Resource
    private RunInstanceMapper runInstanceMapper;

    public Boolean updateRunInstanceByAttrId(RunInstanceDto data, Map<String, Object> updateData) {

        UpdateWrapper<RunInstance> updateWrapper = new UpdateWrapper<RunInstance>()
                .set(updateData.get("run_status") !=null, "run_status",updateData.get("run_status"))
                .set(updateData.get("after_value") !=null, "after_value",updateData.get("after_value"))
                .eq("id",data.getId());
        try {
            runInstanceMapper.update(updateWrapper);
            return true;
        }catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public List<RunInstance> getRunInstancesByBatchId(String batchId){
        QueryWrapper<RunInstance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("batch_id",batchId);
        return runInstanceMapper.selectList(queryWrapper);
    }


}

