package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.mapper.manage.TestDataAttributeMapper;
import com.github.code.manage_web.service.manage.ITestDataAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Service
public class TestDataAttributeServiceImpl extends ServiceImpl<TestDataAttributeMapper, TestDataAttribute> implements ITestDataAttributeService {

    @Resource
    private TestDataAttributeMapper testDataAttributeMapper;

    public TestDataAttribute getTestDataAttributeByAttrId(RunInstanceDto data) {
        return testDataAttributeMapper.selectOne(new QueryWrapper<TestDataAttribute>()
                .eq(data.getAttrId()!=null,"id", data.getAttrId()));
    }
}
