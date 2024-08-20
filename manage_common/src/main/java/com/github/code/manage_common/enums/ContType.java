package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContType {

    SELF_AGREEMENT (102, "自助商务合作协议"),
    DIRECT_AGREEMENT(101, "直客商务合作协议");

    private final Integer code;
    private final String desc;
}
