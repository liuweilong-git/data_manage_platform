package com.github.code.manage_web.dto;


import com.github.code.manage_common.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "查询账号列表请求参数")
@Data
@NoArgsConstructor
public class DataListWebRespDto {
    @Schema(description = "账户ID")
    private String accountId;
    @Schema(description = "客户ID")
    private String customerId;

    @Schema(description = "客户类型")
    private CustomerTypeEnum customerType;
    @Schema(description = "业务线类型")
    private BusinessTypeEnum businessType;
    @Schema(description = "合同ID")
    private String contId;
    @Schema(description = "合同编号")
    private String contSerial;
    @Schema(description = "合同状态")
    private ContStatusEnum contStatus;
    @Schema(description = "合同类型")
    private ContTypeEnum contType;
    @Schema(description = "认证状态")
    private CertStatusEnum certStatus;
    @Schema(description = "资质类型")
    private QualificationTypeEnum qualificationType;
    @Schema(description = "资质状态")
    private QualificationStatusEnum qualificationStatus;
}
