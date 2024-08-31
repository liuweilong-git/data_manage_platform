package com.github.code.manage_web.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "手动触发更新数据属性请求参数")
@Data
@NoArgsConstructor
public class UpdateAccountAttrReqDto {
    @Schema(description = "账户ID")
    @NotNull(message = "账号ID不可为空")
    @Min(message = "退款钱包ID错误", value = 1)
    private String accountId;
    @NotNull(message = "客户ID不可为空")
    @Schema(description = "客户ID")
    private String customerId;

    @Schema(description = "合同ID")
    @NotNull(message = "合同ID不可为空")
    private String contId;


}
