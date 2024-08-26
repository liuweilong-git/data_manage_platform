package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum QualificationStatusEnum {
    AUDIT (1, "'审核中'"),
    REJECT (2, "'审核拒绝'"),
    OK (3, "'审核通过'"),
    DEFAULT (4, "'无需审核'");


    private final Integer code;
    private final String desc;
}