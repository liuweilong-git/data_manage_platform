package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerTypeEnum {

    SELF_CUSTOMER (1, "自助客户"),
    AGENT_CUSTOMER(2, "代理商"),
    DIRECT_CUSTOMER(3, "直客");

    private final Integer code;
    private final String desc;

}
