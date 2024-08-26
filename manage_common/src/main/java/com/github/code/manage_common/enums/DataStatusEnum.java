package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataStatusEnum {
    INVALID(0, "无效的"),
    VALID(1, "有效的");

    private final Integer code;
    private final String desc;

}
