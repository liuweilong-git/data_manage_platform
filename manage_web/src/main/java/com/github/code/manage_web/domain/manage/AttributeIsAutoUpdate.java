package com.github.code.manage_web.domain.manage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttributeIsAutoUpdate {
    private String attributeName;
    private Object actualValue;
    @Schema(description = "是否设置为自动更新，默认自动更新")
    private boolean isAutoUpdate;

}
