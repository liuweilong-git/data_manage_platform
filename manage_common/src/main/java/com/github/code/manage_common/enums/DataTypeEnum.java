package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataTypeEnum {
    REFUND (1, "退款模块的数据");

    private final Integer code;
    private final String desc;
}
