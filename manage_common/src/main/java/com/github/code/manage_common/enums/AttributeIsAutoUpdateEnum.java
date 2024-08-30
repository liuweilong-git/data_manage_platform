package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttributeIsAutoUpdateEnum {
    NO(0, "不需要"),
    YES(1, "需要");

    private final Integer code;
    private final String desc;

}