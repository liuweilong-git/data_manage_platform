package com.github.code.manage_web.domain.manage;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Data
@Schema(name="账号纬度的属性对象", description="")
public class AccountInfo implements Serializable {
    @Schema(description = "账号id")
    private String accountId;
    @Schema(description = "客户id")
    private String customerId;
    @Schema(description = "客户类型")
    private String customerType;
    @Schema(description = "业务线")
    private String businessType;
    @Schema(description = "合同id")
    private String contId;
    @Schema(description = "合同编号")
    private String contSerial;
    @Schema(description = "合同状态")
    private Integer contStatus;
    @Schema(description = "合同类型")
    private String contType;
    @Schema(description = "认证状态")
    private Integer certStatus;
    @Schema(description = "资质类型")
    private String qualificationType;
    @Schema(description = "资质状态")
    private String qualificationStatus;

    public static AccountInfo convert(List<TestDataAttribute> testDataAttributes) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountId(testDataAttributes.get(0).getTestDataId());

        for (TestDataAttribute testDataAttribute : testDataAttributes) {
            String attr = testDataAttribute.getAttr();
            String expectedValue = testDataAttribute.getExpectedValue();

            switch (attr) {
                case "qualificationStatus":
                    accountInfo.setQualificationStatus(expectedValue);
                    break;
                case "qualificationType":
                    accountInfo.setQualificationType(expectedValue);
                    break;
                case "certStatus":
                    accountInfo.setCertStatus(Integer.valueOf(expectedValue));
                    break;
                case "contType":
                    accountInfo.setContType(expectedValue);
                    break;
                case "contStatus":
                    accountInfo.setContStatus(Integer.valueOf(expectedValue));
                    break;
                case "contSerial":
                    accountInfo.setContSerial(expectedValue);
                    break;
                case "contId":
                    accountInfo.setContId(expectedValue);
                    break;
                case "businessType":
                    accountInfo.setBusinessType(expectedValue);
                    break;
                case "customerType":
                    accountInfo.setCustomerType(expectedValue);
                    break;
                case "customerId":
                    accountInfo.setCustomerId(expectedValue);
                    break;
                default:
                    // 如果需要处理未知属性
                    break;
            }
        }

        return accountInfo;


    }
}