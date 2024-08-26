package com.github.code.manage_web.dto;
import com.github.code.manage_web.domain.manage.RunInstance;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
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
    private String customerId;
    private String contId;
    //    test_data_attribute表中主键id
    private Integer attrId;
    private String valueType;
    private Integer atomicId;
//    cnf_atomic表中的atomic_key
//    private String atomicKey;

    private Integer runStatus;
    private String afterValue;

    public RunInstanceDto convertToDTO(RunInstance runInstance) {
        RunInstanceDto dto = new RunInstanceDto();
        dto.setBatchId(runInstance.getBatchId());
        dto.setTestDataId(runInstance.getTestDataId());
        dto.setRunStatus(runInstance.getRunStatus());
        dto.setAttrId(runInstance.getAttrId());
        dto.setAtomicId(runInstance.getAtomicId());
        dto.setAfterValue(runInstance.getAfterValue());
        //需要添加一些其他元素


        return dto;

}
}
