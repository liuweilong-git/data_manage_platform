package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TriggerTypeEnum {
    AUTOMATIC_TRIGGER (1, "'自动触发'"),
    MANUAL_TRIGGER (2, "手动触发");



    private final Integer code;
    private final String desc;
}
