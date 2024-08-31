package com.github.code.manage_web.dto;
import com.github.code.manage_web.domain.manage.RunInstance;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RunInstanceDto {


    private Integer id;
    private String batchId;
    private String testDataId;
    private String attrKey;
    private Integer runStatus;
    private String afterValue;
    private String beforeValue;

    private String customerId;
    private String contId;
    private String valueType;


    public static RunInstanceDto convertToDTO(RunInstance runInstance) {
        RunInstanceDto dto = new RunInstanceDto();
        dto.setId(runInstance.getId());
        dto.setBatchId(runInstance.getBatchId());
        dto.setTestDataId(runInstance.getTestDataId());
        dto.setRunStatus(runInstance.getRunStatus());
        dto.setAttrKey(runInstance.getAttrKey());
        dto.setBeforeValue(runInstance.getBeforeValue());
        dto.setAfterValue(runInstance.getAfterValue());

        //需要添加一些其他元素
        dto.setCustomerId("");
        dto.setContId("");
        return dto;

    }

}
