package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.code.manage_common.enums.DataStatusEnum;
import com.github.code.manage_common.enums.DataTypeEnum;
import com.github.code.manage_web.domain.manage.TestData;
import com.github.code.manage_web.mapper.manage.TestDataMapper;
import com.github.code.manage_web.service.manage.ITestDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Service
public class TestDataServiceImpl extends ServiceImpl<TestDataMapper, TestData> implements ITestDataService {

    @Resource
    private TestDataMapper testDataMapper;

//    public List<TestData> getDataList(DataTypeEnum dataType) {
//        //获取退款类型的所有非自动化数据（有效的）
//        return testDataMapper.selectList(
//                new QueryWrapper<TestData>()
//                        .eq("data_type", dataType)
//                        .eq("status", DataStatusEnum.VALID.getCode())
//                        .eq("tag", "test")
//        );
//    }

    public List<TestData> getDataList(DataTypeEnum dataType, String tag) {
        //  //获取退款类型的所有数据（有效的）
        QueryWrapper<TestData> queryWrapper = new QueryWrapper<TestData>()
                .eq("data_type", dataType)
                .eq("status", DataStatusEnum.VALID.getCode());

        // 如果 tag 不为空或不为null，添加 tag 条件
        if (tag != null && !tag.isEmpty()) {
            queryWrapper.eq("tag", tag);
        }

        // 执行查询并返回结果
        return testDataMapper.selectList(queryWrapper);
    }

}
