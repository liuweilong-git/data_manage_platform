package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum dataAttributeValueTypeEnum {

    INT (1, "int"),
    STRING(2, "String"),
    JSON(3, "json");

    private final Integer code;
    private final String desc;

}