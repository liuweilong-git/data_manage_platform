package com.github.code.manage_web.dto;


import com.github.code.manage_web.domain.manage.AttributeIsAutoUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "创建账号信息请求参数")
@Data
@NoArgsConstructor
public class CreateDataReqDto {
    @Schema(description = "账户ID")
    @NotNull(message = "账号ID不可为空")
    @Min(message = "退款钱包ID错误", value = 1)
    private String accountId;
    @NotNull(message = "客户ID不可为空")
    @Schema(description = "客户ID")
    private AttributeIsAutoUpdate customerId;

    @NotNull(message = "退款钱包ID不可为空")
    @Schema(description = "客户类型")
    private AttributeIsAutoUpdate customerType;
    @Schema(description = "业务线类型")
    @NotNull(message = "退款钱包ID不可为空")
    private AttributeIsAutoUpdate businessType;
    @Schema(description = "合同ID")
    @NotNull(message = "合同ID不可为空")
    private AttributeIsAutoUpdate contId;
    @Schema(description = "合同编号")
    @NotNull(message = "合同编号不可为空")
    private AttributeIsAutoUpdate contSerial;
    @Schema(description = "合同状态")
    @NotNull(message = "合同状态不可为空")
    private AttributeIsAutoUpdate contStatus;
    @Schema(description = "合同类型")
    @NotNull(message = "合同类型不可为空")
    private AttributeIsAutoUpdate contType;
    @Schema(description = "认证状态")
    @NotNull(message = "认证状态不可为空")
    private AttributeIsAutoUpdate certStatus;
    @Schema(description = "资质类型")
    @NotNull(message = "资质类型不可为空")
    private AttributeIsAutoUpdate qualificationType;
    @Schema(description = "资质状态")
    @NotNull(message = "资质状态不可为空")
    private AttributeIsAutoUpdate qualificationStatus;

//    请求参数案例
//    {
//        "qualificationType": {
//        "isAutoUpdate": false,
//                "actualValue": "UpdateQualificationType",
//                "attributeName": "qualificationType"
//    },
//        "customerType": {
//        "isAutoUpdate": false,
//                "actualValue": "AGENT_CUSTOMER",
//                "attributeName": "customerType"
//    },
//        "contId": {
//        "isAutoUpdate": false,
//                "actualValue": "70000000001",
//                "attributeName": "contId"
//    },
//        "accountId": "1700000204",
//            "certStatus": {
//        "isAutoUpdate": false,
//                "actualValue": 2,
//                "attributeName": "certStatus"
//    },
//        "customerId": {
//        "isAutoUpdate": true,
//                "actualValue": "6001234",
//                "attributeName": "customerId"
//    },
//        "qualificationStatus": {
//        "isAutoUpdate": false,
//                "actualValue": "NOT_STARTED",
//                "attributeName": "qualificationStatus"
//    },
//        "contSerial": {
//        "isAutoUpdate": false,
//                "actualValue": "contninhao",
//                "attributeName": "contSerial"
//    },
//        "contType": {
//        "isAutoUpdate": false,
//                "actualValue": "1111111",
//                "attributeName": "contType"
//    },
//        "businessType": {
//        "isAutoUpdate": false,
//                "actualValue": "AD",
//                "attributeName": "businessType"
//    },
//        "contStatus": {
//        "isAutoUpdate": false,
//                "actualValue": 2,
//                "attributeName": "contStatus"
//    }
//    }
}

