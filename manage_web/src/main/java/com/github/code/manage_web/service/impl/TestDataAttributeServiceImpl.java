package com.github.code.manage_web.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.dto.DataListWebReqDto;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.mapper.manage.TestDataAttributeMapper;
import com.github.code.manage_web.service.manage.ITestDataAttributeService;
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
@DS("manage")
public class TestDataAttributeServiceImpl extends ServiceImpl<TestDataAttributeMapper, TestDataAttribute> implements ITestDataAttributeService {

    @Resource
    private TestDataAttributeMapper testDataAttributeMapper;

    public TestDataAttribute getTestDataAttribute(RunInstanceDto data) {
        return testDataAttributeMapper.selectOne(new QueryWrapper<TestDataAttribute>()
                .eq(data.getTestDataId()!=null,"test_data_id", data.getTestDataId())
                .eq(data.getAttrKey()!=null,"attr", data.getAttrKey()));
    }

    /**
     * @param test_data_id  账号id
     * @param auto_update 是否需要更新的属性
     * @return 获取当前账号的对应的属性数据
     */
    public List<TestDataAttribute> getTestDataAttributeByTestDataId(String test_data_id, Integer auto_update) {
        return testDataAttributeMapper.selectList(new QueryWrapper<TestDataAttribute>()
                .eq(test_data_id!=null,"test_data_id", test_data_id)
                .eq(auto_update!= null,"auto_update", auto_update)
        );
    }


    /**
     * @param dataListWebReqDto 废弃
     * @return
     */
    public List<TestDataAttribute> queryByParams(DataListWebReqDto dataListWebReqDto) {
        QueryWrapper<TestDataAttribute> queryWrapper = new QueryWrapper<>();

        // 构建查询条件，只有非空参数才加入到查询条件中
        if (dataListWebReqDto.getAccountId() != null) {
            queryWrapper.eq("attr", "account_id").eq("expected_value", dataListWebReqDto.getCustomerId());
        }
        if (dataListWebReqDto.getCustomerId() != null) {
            queryWrapper.eq("attr", "customer_id").eq("expected_value", dataListWebReqDto.getCustomerId());
        }
        if (dataListWebReqDto.getCustomerType() != null) {
            queryWrapper.eq("attr", "customer_type").eq("expected_value", dataListWebReqDto.getCustomerType());
        }
        if (dataListWebReqDto.getBusinessType() != null) {
            queryWrapper.eq("attr", "business_type").eq("expected_value", dataListWebReqDto.getBusinessType());
        }
        if (dataListWebReqDto.getContId() != null) {
            queryWrapper.eq("attr", "cont_id").eq("expected_value", dataListWebReqDto.getContId());
        }
        if (dataListWebReqDto.getContSerial() != null) {
            queryWrapper.eq("attr", "cont_serial").eq("expected_value", dataListWebReqDto.getContSerial());
        }
        if (dataListWebReqDto.getContStatus() != null) {
            queryWrapper.eq("attr", "cont_status").eq("expected_value", dataListWebReqDto.getContStatus());
        }
        if (dataListWebReqDto.getContType() != null) {
            queryWrapper.eq("attr", "cont_type").eq("expected_value", dataListWebReqDto.getContType());
        }
        if (dataListWebReqDto.getCertStatus() != null) {
            queryWrapper.eq("attr", "cert_status").eq("expected_value", dataListWebReqDto.getCertStatus());
        }
        if (dataListWebReqDto.getQualificationType() != null) {
            queryWrapper.eq("attr", "qualification_type").eq("expected_value", dataListWebReqDto.getQualificationType());
        }
        if (dataListWebReqDto.getQualificationStatus() != null) {
            queryWrapper.eq("attr", "qualification_status").eq("expected_value", dataListWebReqDto.getQualificationStatus());
        }

        // 执行查询并返回结果
        return testDataAttributeMapper.selectList(queryWrapper);
    }
}

