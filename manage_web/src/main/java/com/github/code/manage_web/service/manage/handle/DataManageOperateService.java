package com.github.code.manage_web.service.manage.handle;


import com.github.code.manage_web.domain.manage.AttributeIsAutoUpdate;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.dto.CreateDataReqDto;
import com.github.code.manage_web.dto.TestDataAttributeDto;

import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataManageOperateService {
    @Resource
    private TestDataAttributeServiceImpl testDataAttributeService;

    @Resource
    private TestDataAttributeDto testDataAttributeDto;

    /**
     * @param req
     * @return 完成属性的添加
     * @throws IllegalAccessException
     */
    public List<TestDataAttribute> convertAndSaveAttributes(CreateDataReqDto req) throws IllegalAccessException {
        List<TestDataAttribute> attributesToInsert = new ArrayList<>();
        List<TestDataAttribute> attributesToUpdate = new ArrayList<>();

        String testDataId = req.getAccountId();
        log.info("当前的账号id{}",testDataId);
        if (testDataId == null) {
            return null;
        }
        // 查询现有的TestDataAttribute记录（根据testDataId）
        List<TestDataAttribute> existingAttributes = testDataAttributeService.getTestDataAttributeByTestDataId(testDataId);
        Map<String, TestDataAttribute> existingAttributeMap = existingAttributes.stream()
                .collect(Collectors.toMap(TestDataAttribute::getAttr, attr -> attr));

        // 获取req类的所有字段
        Field[] fields = req.getClass().getDeclaredFields();
        // 遍历字段，将属性值与数据库中现有的值进行对比和更新
        for (Field field : fields) {
            field.setAccessible(true); // 允许访问私有字段
            Object value = field.get(req); // 获取字段的值
            if (value instanceof AttributeIsAutoUpdate) {
                String attrName = ((AttributeIsAutoUpdate) value).getAttributeName();
                Object actualValue = ((AttributeIsAutoUpdate) value).getActualValue();
                boolean isAutoUpdate = ((AttributeIsAutoUpdate) value).isAutoUpdate();
                log.info("当前属性为attrName:{},属性值为actualValue:{}", attrName, actualValue);

                // 检查该属性是否在现有记录中
                if (existingAttributeMap.containsKey(attrName)) {
                    // 更新现有记录
                    TestDataAttribute existingAttribute = existingAttributeMap.get(attrName);
                    existingAttribute.setExpectedValue(String.valueOf(actualValue));
                    existingAttribute.setAutoUpdate(isAutoUpdate ? 1 : 0);
                    existingAttribute.setCategory(testDataAttributeDto.determineCategory(attrName));
                    existingAttribute.setModifyTime(LocalDateTime.now());
                    attributesToUpdate.add(existingAttribute);
                } else {
                    // 如果不存在，创建新的TestDataAttribute对象
                    TestDataAttribute newAttribute = testDataAttributeDto.createTestDataAttribute
                            (testDataId, attrName, actualValue, isAutoUpdate);
                    attributesToInsert.add(newAttribute);
                }
            }
        }

        // 更新现有的属性
        if (!attributesToUpdate.isEmpty()) {
            log.info("需要更新的数据testDataId：{},attributesToUpdate：{}", testDataId, attributesToUpdate);
            testDataAttributeService.updateBatchById(attributesToUpdate);
        }

        // 插入新的属性
        if (!attributesToInsert.isEmpty()) {
            log.info("需要插入的数据testDataId：{},attributesToInsert：{}", testDataId, attributesToInsert);
            testDataAttributeService.saveBatch(attributesToInsert);
        }

        // 返回所有处理的属性
        List<TestDataAttribute> allAttributes = new ArrayList<>();
        allAttributes.addAll(attributesToUpdate);
        allAttributes.addAll(attributesToInsert);

        // 批量保存到数据库
        return allAttributes;
    }

}