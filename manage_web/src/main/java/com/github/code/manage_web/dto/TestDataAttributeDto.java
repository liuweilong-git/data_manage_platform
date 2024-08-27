package com.github.code.manage_web.dto;

import com.github.code.manage_common.enums.dataAttributeValueTypeEnum;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Component
public class TestDataAttributeDto {

    // 使用反射创建TestDataAttribute对象
    public TestDataAttribute createTestDataAttribute(String testDataId, String attrName, Object actualValue, boolean isAutoUpdate) {
        TestDataAttribute attr = new TestDataAttribute();
        attr.setTestDataId(testDataId);
        attr.setData(testDataId);
        attr.setAttr(attrName);
        attr.setExpectedValue(String.valueOf(actualValue)); // 转换为字符串存储
        attr.setValueType(determineValueType(actualValue)); // 判断值类型
        attr.setCategory(determineCategory(attrName)); // 根据属性名称判断类别
        attr.setAutoUpdate(isAutoUpdate ? 1 : 0); // 判断是否是预期值
        attr.setCreateTime(LocalDateTime.now());
        attr.setModifyTime(LocalDateTime.now());
        return attr;
    }

    // 判断值的类型
    private Integer determineValueType(Object actualValue) {
        if (actualValue instanceof Integer) {
            return dataAttributeValueTypeEnum.INT.getCode(); // int 类型
        } else if (actualValue instanceof String) {
            return dataAttributeValueTypeEnum.STRING.getCode(); // string 类型
        } else {
            return dataAttributeValueTypeEnum.JSON.getCode(); // json 或其他
        }
    }

    // 根据属性名判断属性类标
    public String determineCategory(String attrName) {
        // 例如根据属性名前缀判断类别
        if (attrName.equals("customerId") ||
                attrName.equals("customerType") ||
                attrName.equals("businessType")) {
            return "crm";
        } else if (attrName.startsWith("cont")) {
            return "cont";
        } else if (attrName.equals("certStatus") ||
                attrName.equals("qualificationType") ||
                attrName.equals("qualificationStatus")) {
            return "cert";

        }
        return "other";
    }
}
