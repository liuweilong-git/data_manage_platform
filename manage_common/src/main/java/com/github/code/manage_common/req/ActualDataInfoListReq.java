package com.github.code.manage_common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualDataInfoListReq {
    @Schema(description = "账户ID")
    @NotNull(message = "退款钱包ID不可为空")
    @Min(message = "退款钱包ID错误", value = 1)
    private String accountId;
}
